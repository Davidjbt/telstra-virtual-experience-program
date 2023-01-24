package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import entity.SimCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import json.SIMCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    private SIMCard simCard;

    @Given("a working SIM Card")
    public void workingSimCard() {
        simCard = new SIMCard("1255789453849037777", "test1@gmail.com", false);
    }

    @Given("a not working SIM Card")
    public void notWorkingSimCard() {
        simCard = new SIMCard("8944500102198304826", "test2@gmail.com", false);
    }

    @When("a request to activate a SIM card is submitted")
    public void aRequestToActiveANewSimCardIsSubmitted() {
        this.restTemplate.postForObject("http://localhost:8080/activateSIM", simCard, String.class);
    }

    @Then("the sim card activates and its state is saved to the database")
    public void theSimCardActivatesAndItsStateIdToTheDatabase() {
        var simCard = this.restTemplate.getForObject("http://localhost:8080/querySIM?id={id}", SimCard.class, 1);
        assertTrue(simCard.getActive());
    }

    @Then("the sim card fails to activate and its state is saved to the database")
    public void theSimCardFailsToActivateAndItsStateIsRecordedToTheDatabase() {
        var simCard = this.restTemplate.getForObject("http://localhost:8080/querySIM?id={id}", SimCard.class, 2);
        assertFalse(simCard.getActive());
    }



}