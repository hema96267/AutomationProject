package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import POJO.Deserializing_ping;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.restassured.config.SSLConfig;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import pages.Login;

public class Steps {
    public Login loginpage;
    public WebDriver driver;


    public Steps(){
        this.loginpage = new Login(driver);
    }

    private static final String BASE_URL = "https://agdemo-qnxhboskza-as.a.run.app";
    private static String Email;
    private static String Password;
    public Deserializing_ping apiResponse;
    public static String Resource;

    @Given("The required information of api's are provided")
    public void theRequiredInformationOfApiSAreProvided() {
        RestAssured.baseURI = BASE_URL;

    }

    @When("User hits the above uri with resource {string}")
    public Object userHitsTheAboveUriWithResourceResource(String resource) {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        // Another Way of calling API

        /* request.log().all().config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames())).relaxedHTTPSValidation()
                .header("Content-Type", "application/json");
                response = request.get(resource);
        Assert.assertEquals(200, response.getStatusCode());

        String jsonString = response.asString();
        System.out.println("response is "+jsonString);*/
        Gson gson = new Gson();
        Resource = resource;

        apiResponse = RestAssured.given().log().all().config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames())).relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .when().get(resource)
                .then().log().all().assertThat().statusCode(200).extract().response()
                .as(Deserializing_ping.class);

        System.out.println(apiResponse.getMessage());

        return apiResponse;

    }

    @Then("User should get the expected response from the api's and should pass all the validations")
    public void userShouldGetTheExpectedResponseFromTheApiSAndShouldPassAllTheValidations() {
        if (Resource.equals("ping")) {
            Assert.assertEquals("pong", apiResponse.getMessage());
            Assert.assertEquals("v1.0.0", apiResponse.getVersion());
            Assert.assertNotNull(apiResponse.getTime());
        } else {
            Assert.assertEquals("OK", apiResponse.getCode());
        }

    }

    @Given("the user is on the landing page of URl {string}")
    public void theUserIsOnTheLandingPageOfURl(String URL) throws InterruptedException {
        loginpage.setUpLocal(URL);

    }

    @When("the existing user inputs the username {string} and password {string}")
    public void theExistingUserInputsTheUsernameAndPassword(String email, String password) throws InterruptedException {
        Email = email;
        Password =password;
        loginpage.clickLoginButtton();

    }

    @Then("login functionality should happen based on the provides credentials")
    public void loginFunctionalityShouldHappenBasedOnTheProvidesCredentials() throws InterruptedException {
        loginpage.validateLoginCredentials(Email,Password);
        loginpage.closeDriver();
    }

    @When("the existing user clicks on forgot password link")
    public void theExistingUserClicksOnForgotPasswordLink() throws InterruptedException {
        loginpage.clickLoginButtton();
    }


    @And("the user ticks on Remember me checkbox and proceed to login")
    public void theUserTicksOnRememberMeCheckboxAndProceedToLogin() {
    }

    @Then("the user should be able to retrieve his credentials for the next login session")
    public void theUserShouldBeAbleToRetrieveHisCredentialsForTheNextLoginSession() {
    }

    @Then("the user should be able to login successfully with correct credentials")
    public void theUserShouldBeAbleToLoginSuccessfullyWithCorrectCredentials() throws InterruptedException {
        loginpage.validateLoginCredentials(Email,Password);
    }

    @And("the user should be able to see the post login information")
    public void theUserShouldBeAbleToSeeThePostLoginInformation() throws InterruptedException {
        loginpage.postLoginValidations();

    }

    @Then("the user should be able to  successfully recover his password for the email {string}")
    public void theUserShouldBeAbleToSuccessfullyRecoverHisPasswordForTheEmail(String email) throws InterruptedException {
        loginpage.clickForgotPassword(email);
    }

    @And("the user should be able to login successfully with the new password {string}")
    public void theUserShouldBeAbleToLoginSuccessfullyWithTheNewPassword(String newPassword) throws InterruptedException {
        loginpage.verifyLoginWithNewPassword(Email,newPassword);
    }

    @When("the user clicks on logout button")
    public void theUserClicksOnLogoutButton() {
        loginpage.clickLogout();
    }

    @Then("user should be able to logout successfully.")
    public void userShouldBeAbleToLogoutSuccessfully() {
        loginpage.verifyLogout();
        loginpage.closeDriver();
    }
}
