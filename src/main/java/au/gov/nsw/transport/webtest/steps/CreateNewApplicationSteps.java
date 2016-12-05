package au.gov.nsw.transport.webtest.steps;

import au.gov.nsw.transport.webtest.steps.thucydides.IACUserSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GumpuR on 29/11/2016.
 */
public class CreateNewApplicationSteps {

    @Steps
    IACUserSteps IACUserSteps;

    @Given("I am a student")
    public void IAmAStudent() {
        IACUserSteps.IACUserOpen();
    }

    @When("I apply for CEC Card")
    public void aapplyForCECCard(){
        IACUserSteps.applyForCECCard();
    }

    @When("I apply for TCEC Card with the following details:$details")
    public void givenTheFollowingDetails(ExamplesTable table){
        List studentDetails  = new ArrayList();
        for (Map<String, String> rowValues: table.getRows()){

            String EntitlementType = rowValues.get("EntitlementType");
            String InstitutionName = rowValues.get("InstitutionName");
            String StudentID = rowValues.get("StudentID");
        }
       // return studentDetails;
    }

    @When ("I apply for Tertiary CEC Card")
    public void applyForTertiaryCard(){

   }

    @Given("I am a <entitlementType>")
    public void iAmAnApplicant(String entitlementType){
        IACUserSteps.IACUserOpen();
        IACUserSteps.selectEntitlementType(entitlementType);
    }
    @When("I submit the cec application with <uniName> <concessionID>")
    public void submitCECApplication(String uniName, String concessionID){
        IACUserSteps.submitEntitlementDetails(uniName, concessionID);
    }

    @When("I submit personal details with <firstName> <lastName> <emailID> <phone> <mobile>")
    public void submitPersonalDetails(String firstName, String lastName, String emailID, String phone, String mobile){
        IACUserSteps.submitPersonalDetails(firstName,lastName,emailID,phone,mobile);
    }
    @When("I submit residential details with <addressLine1> <suburb><postCode>")
    public void submitResidentialDetails(String addressLine1, String suburb, String postCode){
        IACUserSteps.submitResidentialDetails(addressLine1,suburb,postCode);
    }
    @Then("I should see the outcome message")
    public void shouldBeAbleToGetOutcomeMessage(){
        IACUserSteps.shouldBeAbleToGetOutcomeMessage();
    }

}

