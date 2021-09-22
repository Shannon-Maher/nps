package com.nps.devassessment.controller;

import org.hibernate.cfg.NotYetImplementedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ControllerTests {

    private static final Logger log = LoggerFactory.getLogger(ControllerTests.class);

    MockMvc mockMvc;


    @InjectMocks
    private TestsController testsController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(testsController).build();
    }


    @Test
    public void test1_shouldTestEndpointToRetrieveWorkflowById() throws Exception {
        // Create and test a controller GET endpoint to retrieve an entry from the workflow table
        // As a parameter, the Controller should receive Integer value (representing the 'id' of the workflow)
        // As a response, the Controller should return a JSON entity representing the workflow
        // This test should contain two calls to the Controller endpoint - one that returns a workflow, and one where no
        //    workflow matching the required id has been located
        // Each of these two calls should return an apppropriate HTTP Status in accordance with REST best practices
        // Assert that the appropriate responses have been received from the endpoint

        throw new NotYetImplementedException();
    }


    @Test
    public void test2_shouldTestEndpointToRetrieveWorkflowsByYjbYId() throws Exception {
        // Create and test a controller GET endpoint to retrieve a set of entries from the workflow table
        // As a parameter, the Controller should receive a Long value (representing the 'yjb_yp_id' of the workflows)
        // As a response, the Controller should return a JSON entity containing a list of workflows matching the yjb_yp_id
        // This test should contain two calls to the Controller endpoint - one that returns a set of workflows, and one where no
        //    workflows matching the required yjb_yp_id have been located
        // Each of these two calls should return an apppropriate HTTP Status in accordance with REST best practices
        // Assert that the appropriate responses have been received from the endpoint

        throw new NotYetImplementedException();
    }


    @Test
    public void test3_shouldTestEndpointToCreateANewWorkflow() throws Exception {
        // Create and test a controller POST endpoint to create a new entry within the workflow table
        // The Controller should receive a JSON payload that represents a workflow entity (the 'id' column
        //    will have to be null or not present as the workflow has not yet been written to the table)
        // The endpoint should create a new entry in the workflow table
        // The endpoint should return a JSON representation of the newly created workflow - complete with it's id
        // Assert that the appropriate response have been received from the endpoint
        // Assert that the response body contains a workflow object, and that the id column is populated

        throw new NotYetImplementedException();
    }


    @Test
    public void test4_shouldTestEndpointToUpdateExistingWorkflow() throws Exception {
        // Create and test a controller PUT endpoint to an existing entry within the workflow table
        // As a parameter, the Controller should receive a JSON payload that represents an existing workflow entity
        //    where one or more of the columns has changed
        // Before the unit test calls the controller, it should retrieve the existing workflow, so that it may compare
        //    the existing workflow entity against the modified version
        // The endpoint should update the appropriate entry in the workflow table
        // The endpoint should return a JSON representation of the newly updated workflow
        // Assert that the appropriate response have been received from the endpoint
        // Assert that the response body contains a workflow object
        // Assert that the workflow details in the response correctly demonstrate that the workflow has been updated

        throw new NotYetImplementedException();
    }


    @Test
    public void test5_shouldTestPlacementFacade() throws Exception {
        // This tests requires you to MOCK/STUB a call to another REST endpoint in a different microservice
        // Create and test a controller GET endpoint in *this* microservice
        // The endoint in *this* microservice should receive an Integer parameter that allows the controller to retrieve
        //     an existing workflow by its id  (as per test 1)
        // Once the controller in *this* microservice has retrieved a workflow, you must demonstrate your understanding of
        //     the 'Facade Pattern' to make a call to a different microservice (the 'placements' microservice)
        // The placements microservice isn't reachable from this test project - so you must MOCK the call to the placement
        //     microservice, and provide a MOCK Response that contains a dummy placement object
        // The url for the 'placements' microservice is already in the application.properties file
        // The call to the placements microservice needs to supply the 'id' of the workflow, and the yjb_yp_id of the workflow
        // The placements microservice needs to return a com.nps.devassessment.model.placement object (already created)
        // Assert that an appropriate response has been received from the facade endpoint
        // Assert that the response body contains a 'placement' object
        // Assert that the 'placement' object id and yjb_yp_id match the id and yjb_yp_id of the workflow

        throw new NotYetImplementedException();
    }


    @Test
    public void test6_shouldDemonstrateRollbackOfModificationsDueToError() throws Exception {
        // This test requires you to demonstrate how a set of transations can be rolled back when an exception occurs
        // Create and test a controller GET endpoint.  The endpoint requires no parameters
        // The purpose of the endpoint is to locate all workflows with a 'process' of 'transferPlanned' - for all identified
        //    workflows, the endpoint should try to set the 'metadata' field to the text "PROCESSED"
        // Create a new class that extends Exception.  This class should be a new type of exception named "NpsDemoException"
        // Maintain a counter of the records being updated.  When this counter get to > 10 your code should throw a new
        //    NpsDemoException
        // Your code/service that is processing the updates should have been annotated correctly to roll back the modifications
        //    that your code had made thus far when an NpsDemoException is experienced
        // Find a way to Assert that no changes have been to the data

        throw new NotYetImplementedException();
    }
}
