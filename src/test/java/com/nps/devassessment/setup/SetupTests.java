package com.nps.devassessment.setup;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.service.WorkflowRepoService;
import org.hibernate.cfg.NotYetImplementedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SetupTests {

    private static final Logger log = LoggerFactory.getLogger(SetupTests.class);

    @Autowired
    private WorkflowRepoService workflowRepoService;


    // NOTE - This is a sample
    @Test
    public void test0_shouldProvideASampleOfAWorkingRepoCall() {

        // start test
        log.info("Starting test0 to demonstrate working repo call...");
        WorkflowEntity workflowEntity = this.workflowRepoService.findWorkflowById(66176L);

        // Assert
        Assert.assertNotNull(workflowEntity);
        Assert.assertEquals("IN PROGRESS", workflowEntity.getWorkflowState());

        // end test
        log.info("Workflow {} found.  yjb_yp_id = {}, workflow_state = {}", workflowEntity.getId(), workflowEntity.getYjbYp(), workflowEntity.getWorkflowState());
        log.info("test0 complete");
    }



    @Test
    public void test1_shouldDemonstrateRequestedRepoQueries() {
        log.info("Starting test1 should Demonstrate Requested Repo Queries...");
        // implement queries as per the word document

        // assert that the results of each of the query calls is not null/empty
        // write the count of each of the queries to the log

        // Select workflows by workflow_state = a given status  (e.g. “IN PROGRESS”, “CANCELLED”, “ADMITTED”)

       List<WorkflowEntity> workflowEntity = workflowRepoService.findWorkflowByStatus("CANCELLED");

        Assert.assertNotNull(workflowEntity);
        boolean match = workflowEntity.stream().anyMatch(p -> p.getWorkflowState().equals("CANCELLED"));
        Assert.assertEquals(true, match);

        workflowEntity = workflowRepoService.findWorkflowByStatus("ADMITTED");

        Assert.assertNotNull(workflowEntity);
        match = workflowEntity.stream().anyMatch(p -> p.getWorkflowState().equals("ADMITTED"));
        Assert.assertEquals(true, match);

        //workflowEntity = workflowRepoService.findAll();
        workflowEntity = workflowRepoService.findWorkflowByStatus("IN PROGRESS");

        Assert.assertNotNull(workflowEntity);
        match = workflowEntity.stream().anyMatch(p -> p.getWorkflowState().equals("IN PROGRESS"));
        Assert.assertEquals(true, match);

        // Select workflows by a given list of yjb_yp_id values  (e.g. 30848, 32524, 28117)

        ArrayList<Long> id = new ArrayList<>();
        id.add(26868L);
        id.add(30848L);

        workflowEntity = workflowRepoService.findWorkflowByGivenIds(id);
        match = workflowEntity.stream().anyMatch(ids -> ids.getYjbYp().equals(26868L)  || ids.getYjbYp().equals(30848L));
        Assert.assertEquals(true,match);


        // Select workflows by 'created' column is after a given date (e.g. 01/02/2021)

        LocalDate localDate = LocalDate.of(2012,02,01);
        final LocalDateTime data = LocalDateTime.of(2021,02,01,0,0,0);
        workflowEntity = workflowRepoService.findAllByAfterCreationDate(java.sql.Date.valueOf(localDate));
        match = workflowEntity.stream().anyMatch(date -> date.getCreated().toLocalDateTime().isAfter(data) );
        Assert.assertEquals(true,match);

        // Select workflows by 'modified' column is after a given date (e.g. 01/01/20) but before another given date (e.g. 01/03/2021)
         localDate = LocalDate.of(2021,03,01);
         final LocalDateTime data1 = LocalDateTime.of(2021,03,01,0,0,0);
        workflowEntity = workflowRepoService.findAllByModificationDateAfterGivenDate(java.sql.Date.valueOf(localDate));
        match = workflowEntity.stream().anyMatch(date -> date.getCreated().toLocalDateTime().isAfter(data1) );
        Assert.assertEquals(true,match);

        // Select workflows by process = a given value (e.g. “placementProcess”) AND task_status != a given value (e.g.  “ADMITTED”)
        workflowEntity = workflowRepoService.findAllByProcessAndStatus("placementProcess","ADMITTED");

        Assert.assertNotNull(workflowEntity);
        match = workflowEntity.stream().anyMatch(p -> p.getProcess().equals("placementProcess") && p.getWorkflowState().equals("ADMITTED"));
        Assert.assertEquals(true, match);
        // Select id, yjb_yp_id and task_status columns for all workflows where created_by = a given value (e.g. “lee.everitt”)

        workflowEntity = workflowRepoService.findAllByCreatedBy("lee.everitt");
        Assert.assertNotNull(workflowEntity);
        match = workflowEntity.stream().anyMatch(p -> p.getCreatedBy().equals("lee.everitt"));
        Assert.assertEquals(true, match);
        // Select the first 10 rows where process = a given value (e.g. “transferPlanned”).  Order the results by id in descending order
        workflowEntity = workflowRepoService.findAllByProcess("transferPlanned",10);
        Assert.assertEquals(10,workflowEntity.size());
    }


    @Test
    public void test2_shouldDemonstratePageableRepoCapability() {
        // Page through the entire workflow repo using a page size of 20
        Pageable firstPageWithTwoElements = PageRequest.of(0, 20);
        Page<WorkflowEntity> workflowEntities = workflowRepoService.findAll(firstPageWithTwoElements);
        Assert.assertEquals(20,workflowEntities.getSize());
        // For each page: write the count of each distinct workflow_status to the log
        while(workflowEntities.hasNext()) {
            log.info("Page :"+workflowEntities.getPageable().getPageNumber() +  "of "+workflowEntities.getTotalPages());
            Map<String, Long> countWorkEntity = workflowEntities.stream()
                    .collect(Collectors.groupingBy(WorkflowEntity::getWorkflowState, Collectors.counting()));

            for (Map.Entry<String, Long> entrySet : countWorkEntity.entrySet()) {
                log.info(entrySet.getKey() + "  " + entrySet.getValue());
            }
            workflowEntities = workflowRepoService.findAll(workflowEntities.nextPageable());
        }
        // Once you have paged through the entire table, write the amount of pages to the log
        // This gets written to the log during the loop
       // throw new NotYetImplementedException();
    }
}
