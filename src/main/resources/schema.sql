DROP TABLE IF EXISTS workflow;

CREATE TABLE workflow
(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    yjb_yp_id int8 NOT NULL,
    workflow_id int8 NOT NULL,
    kpf_confirmed boolean DEFAULT false,
    workflow_state VARCHAR(250),
    created timestamp,
    modified timestamp,
    created_by VARCHAR(250),
    modified_by VARCHAR(250),
    metadata VARCHAR(250),
    process VARCHAR(250),
    task_id VARCHAR(250),
    previous_state VARCHAR(250),
    task_status VARCHAR(250),
    task_metadata VARCHAR(250)    
)

