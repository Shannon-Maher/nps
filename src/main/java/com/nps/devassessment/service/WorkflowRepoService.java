package com.nps.devassessment.service;

import com.nps.devassessment.entity.WorkflowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface WorkflowRepoService {

    // fetch an individual workflow by its 'id'
    WorkflowEntity findWorkflowById(Long id);
    List<WorkflowEntity> findWorkflowByStatus(String status);
    List<WorkflowEntity> findWorkflowByGivenIds(List<Long> id);
    List<WorkflowEntity> findAllByAfterCreationDate(Date date);
    List<WorkflowEntity> findAllByModificationDateAfterGivenDate(Date date);
    List<WorkflowEntity> findAllByProcessAndStatus(String process, String status);
    List<WorkflowEntity> findAllByCreatedBy(String createdBy);
    List<WorkflowEntity> findAllByProcess(String process, int limit);
    Page<WorkflowEntity> findAll(Pageable pageable);
    List<WorkflowEntity> findAll();
}
