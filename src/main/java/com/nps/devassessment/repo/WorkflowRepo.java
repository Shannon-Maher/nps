package com.nps.devassessment.repo;

import com.nps.devassessment.entity.WorkflowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface WorkflowRepo extends PagingAndSortingRepository<WorkflowEntity, Long> {

        @Query(value= "SELECT w FROM WorkflowEntity w WHERE w.workflowState=?1")
        public List<WorkflowEntity> findAllByTaskStatus(String status);

        @Query(value = "SELECT w FROM WorkflowEntity w WHERE w.yjbYp IN(?1)")
        public List<WorkflowEntity> findAllByGivenIds(List<Long> id);

        @Query(value = "SELECT w FROM WorkflowEntity w WHERE w.created >= ?1")
        public List<WorkflowEntity> findAllByAfterCreationDate(Date date);

        @Query(value = "SELECT w FROM WorkflowEntity w WHERE w.modified >= ?1")
        public List<WorkflowEntity> findAllByModificationDateAfterGivenDate(Date date);

        @Query(value = "SELECT w FROM WorkflowEntity w WHERE w.process =?1 AND w.workflowState=?2")
        public List<WorkflowEntity> findAllByProcessAndTaskStatus(String process, String status);

        @Query(value = "SELECT new com.nps.devassessment.entity.WorkflowEntity(w.id, w.yjbYp, w.taskStatus, w.createdBy) FROM WorkflowEntity w WHERE w.createdBy =?1")
        public List<WorkflowEntity> findAllByCreatedBy(String createdBy);

        @Query(value = "SELECT w FROM WorkflowEntity w")
        public Page<WorkflowEntity> findAll(Pageable pageable);
}
