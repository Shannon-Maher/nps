package com.nps.devassessment.service.impl;

import com.nps.devassessment.entity.WorkflowEntity;
import com.nps.devassessment.repo.WorkflowRepo;
import com.nps.devassessment.service.WorkflowRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class WorkflowRepoServiceImpl implements WorkflowRepoService {

    private WorkflowRepo workflowRepo;

    @Autowired
    WorkflowRepoServiceImpl(WorkflowRepo workflowRepo) {
        this.workflowRepo = workflowRepo;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public WorkflowEntity findWorkflowById(Long id) {
        return this.workflowRepo.findById(id).orElse(null);
    }

    @Override
    public List<WorkflowEntity> findWorkflowByStatus(String status) {
        return this.workflowRepo.findAllByTaskStatus(status);
    }

    @Override
    public List<WorkflowEntity> findWorkflowByGivenIds(List<Long> id) {
        return this.workflowRepo.findAllByGivenIds(id);
    }

    @Override
    public List<WorkflowEntity> findAllByAfterCreationDate(Date date) {
        return this.workflowRepo.findAllByAfterCreationDate(date);
    }

    @Override
    public List<WorkflowEntity> findAllByModificationDateAfterGivenDate(Date date) {
        return this.workflowRepo.findAllByModificationDateAfterGivenDate(date);
    }

    @Override
    public List<WorkflowEntity> findAllByProcessAndStatus(String process, String status) {
        return this.workflowRepo.findAllByProcessAndTaskStatus(process,status);
    }

    @Override
    public List<WorkflowEntity> findAllByCreatedBy(String createdBy) {
        return this.workflowRepo.findAllByCreatedBy(createdBy);
    }

    @Override
    public List<WorkflowEntity> findAllByProcess(String process, int limit) {

        return entityManager.createQuery("SELECT w FROM WorkflowEntity w WHERE (w.process IS :process)",
                WorkflowEntity.class)
                .setParameter("process",process)
                .setMaxResults(limit).getResultList();
    }

    @Override
    public Page<WorkflowEntity> findAll(Pageable pageable) {
        return this.workflowRepo.findAll(pageable);
    }

    @Override
    public List<WorkflowEntity> findAll() {
        ArrayList<WorkflowEntity> workflowRepoArrayList = new ArrayList<>();
        for(WorkflowEntity workEntity:this.workflowRepo.findAll())
        {
            workflowRepoArrayList.add(workEntity);
        }
        return workflowRepoArrayList;
    }

}
