package au.gov.nsw.transport.webtest.pages;

import au.gov.nsw.transport.webtest.steps.CreateNewApplicationSteps;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

/**
 * Created by GumpuR on 29/11/2016.
 */

@DefaultUrl("https://tfnsw-iac-3.pegacloud.com/ApplyCEC/ApplyCEC.html")

public class IACNewApplicationPage extends PageObject {

    CreateNewApplicationSteps applicationSteps;

    @FindBy (id = "EntitlementType")
    WebElementFacade entitlementTypeDropDown;

    @FindBy (id="InstitutionName")
    WebElementFacade InstitutionName;

    @FindBy (id = "Title")
    WebElementFacade Title;

    @FindBy (id="pyFirstName")
    WebElementFacade FirstName;

    @FindBy (id="pyLastName")
    WebElementFacade LastName;

    @FindBy(id="$PpyWorkPage$ppyWorkParty$gApplicant$pDateOfBirthDySel")
    WebElementFacade DOBDate;

    @FindBy(id="$PpyWorkPage$ppyWorkParty$gApplicant$pDateOfBirthMoSel")
    WebElementFacade DOBMonth;

    @FindBy(id="$PpyWorkPage$ppyWorkParty$gApplicant$pDateOfBirthYrSel")
    WebElementFacade DOBYear;

    @FindBy (id="Email")
    WebElementFacade Email;

    @FindBy (id="LandPhone")
    WebElementFacade LandPhone;

    @FindBy (id="MobilePhone")
    WebElementFacade MobilePhone;

    @FindBy (id="AddressLineOne")
    WebElementFacade AddrLine1;

    @FindBy (id="AddressLineTwo")
    WebElementFacade AddrLine2;

    @FindBy (id="pyCity")
    WebElementFacade Suburb;

    @FindBy (id="pyPostalCode")
    WebElementFacade PostCode;

    @FindBy (id="TertiaryStudentID")
    WebElementFacade TertiaryStudentID;

    @FindBy(id = "pyWorkPagepyWorkPartyApplicantpyAddressesHomeIsNonStandardAddress")
    WebElementFacade isNonStandardisedAddress;

    @FindBy (xpath = "//div[text()='Next >>']")
    WebElementFacade  nextButton;

    @FindBy(id = "pyWorkPageAgreeTermsAndConditions")
    WebElementFacade termsAndConditions;

    @FindBy (xpath = "//div[text()='Finish']")
    WebElementFacade finishButton;



    String SegmentType_JS = "Job Seeker/Approved Centrelink customer";
    String SegmentType_TER = "Tertiary student";



    public void applyForCECCard() {
        getDriver().manage().window().maximize();
        getDriver().switchTo().frame(getDriver().findElement(By.tagName("iframe")));

        entitlementTypeDropDown.selectByVisibleText(SegmentType_TER);
        waitForRenderedElementsToBePresent(By.id("InstitutionName"));

        InstitutionName.clear();
        InstitutionName.sendKeys("University of NSW");
        InstitutionName.sendKeys(Keys.TAB);
        waitFor(300).milliseconds();
        TertiaryStudentID.clear();
        TertiaryStudentID.sendKeys("123456789");

        setPersonalDetails();
        setResidentialAddress();

        nextButton.click();
        waitFor(300).milliseconds();
        isNonStandardisedAddress.click();
        nextButton.click();
        waitForRenderedElementsToBePresent(By.id("pyWorkPageAgreeTermsAndConditions"));
        termsAndConditions.click();
        finishButton.click();
        waitFor(300).milliseconds();

    }

    public void setPersonalDetails(){

        Title.selectByVisibleText("Mr");
        waitFor(300).milliseconds();

        FirstName.sendKeys("MyFirstName");
        waitFor(300).milliseconds();

        LastName.sendKeys("MyLastName");
        waitFor(300).milliseconds();

        DOBDate.selectByVisibleText("1");
        waitFor(200).milliseconds();
        DOBMonth.selectByVisibleText("Jan");
        waitFor(200).milliseconds();
        DOBYear.selectByVisibleText("1900");
        waitFor(200).milliseconds();

        Email.sendKeys("test@test.com");
        waitFor(300).milliseconds();

        LandPhone.sendKeys("0212341234");
        waitFor(300).milliseconds();

        MobilePhone.sendKeys("0412341234");
        waitFor(300).milliseconds();
    }

    public void setResidentialAddress(){

        AddrLine1.sendKeys("14-18");
        waitFor(300).milliseconds();

        AddrLine2.sendKeys("Lee Street");
        waitFor(300).milliseconds();

        Suburb.sendKeys("Sydney");
        waitFor(300).milliseconds();

        PostCode.sendKeys("2008");
        waitFor(300).milliseconds();

        getDriver().findElement(By.xpath("//input[@id='pyWorkPagepyWorkPartyApplicantIsPostalAddressSameYes'][@value='Yes']")).click();
        waitFor(3000).milliseconds();
    }

    public void selectEntitlementType(String entitlementType) {
        getDriver().manage().window().maximize();
        getDriver().switchTo().frame(getDriver().findElement(By.tagName("iframe")));
        entitlementTypeDropDown.selectByVisibleText(entitlementType);
    }

    public void submitEntitlementDetails(String uniName, String concessionID) {
        waitForRenderedElementsToBePresent(By.id("InstitutionName"));
        InstitutionName.clear();
        InstitutionName.sendKeys(uniName);
        TertiaryStudentID.sendKeys(concessionID);
        waitForRenderedElementsToBePresent(By.id("Title"));
    }

    public void submitPersonalDetails(String firstName, String lastName, String emailID, String phone, String mobilePhone) {

        waitFor(300).milliseconds();
        Title.selectByVisibleText("Mr");
        FirstName.sendKeys(firstName);
        LastName.sendKeys(lastName);
        DOBDate.selectByVisibleText("1");
        waitFor(200).milliseconds();
        DOBMonth.selectByVisibleText("Jan");
        waitFor(200).milliseconds();
        DOBYear.selectByVisibleText("1900");
        waitFor(200).milliseconds();
        Email.sendKeys(emailID);
        LandPhone.sendKeys(phone);
        MobilePhone.sendKeys(mobilePhone);

    }

    public void submitResidentialDetails(String addressLine1, String suburb, String postCode) {
        AddrLine1.sendKeys(addressLine1);
        waitFor(300).milliseconds();

        AddrLine2.sendKeys("");
        waitFor(300).milliseconds();

        Suburb.sendKeys(suburb);
        waitFor(300).milliseconds();

        PostCode.sendKeys(postCode);
        waitFor(300).milliseconds();

        getDriver().findElement(By.xpath("//input[@id='pyWorkPagepyWorkPartyApplicantIsPostalAddressSameYes'][@value='Yes']")).click();
        waitFor(3000).milliseconds();
    }
}


