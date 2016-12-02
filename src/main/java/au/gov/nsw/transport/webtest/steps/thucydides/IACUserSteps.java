package au.gov.nsw.transport.webtest.steps.thucydides;

import au.gov.nsw.transport.webtest.pages.IACNewApplicationPage;
import net.thucydides.core.annotations.Step;

/**
 * Created by GumpuR on 29/11/2016.
 */
public class IACUserSteps {

    IACNewApplicationPage IACApplicationPage;

    @Step
    public void IACUserOpen() {
        IACApplicationPage.open();

    }
    @Step
    public void applyForCECCard() {
        IACApplicationPage.applyForCECCard();
    }
    @Step
    public void selectEntitlementType(String entitlementType) {
        IACApplicationPage.selectEntitlementType(entitlementType);
    }
    @Step
    public void submitEntitlementDetails(String uniName, String concessionID) {
        IACApplicationPage.submitEntitlementDetails(uniName, concessionID);
    }
    @Step
    public void submitPersonalDetails(String firstName, String lastName, String emailID, String phone, String mobilePhone) {
        IACApplicationPage.submitPersonalDetails(firstName,lastName,emailID,phone,mobilePhone);
    }

    public void submitResidentialDetails(String addressLine1, String suburb, String postCode) {
        IACApplicationPage.submitResidentialDetails(addressLine1,suburb,postCode);
    }
}
