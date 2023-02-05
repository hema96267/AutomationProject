package pages;

import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Objects;

public class Login extends PageObject {

    WebDriver driver;
    LoginPOM page;

    public Login(WebDriver driver) {
        this.driver = driver;
    }


    public void setUpLocal(String url) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(Login.class.getClassLoader().getResource("drivers/chromedriver.exe")).getFile());
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        page = PageFactory.initElements(driver, LoginPOM.class);
        String title = driver.getTitle();
        System.out.println("title of landing  page is:" + title);
        Thread.sleep(5000);

    }

    public void clickLoginButtton() throws InterruptedException {


        page.click_login();
        Thread.sleep(3000);
    }

    public void validateLoginCredentials(String email, String password) throws InterruptedException {
        Thread.sleep(3000);
        page.enter_login_details(email, password);
        String expEmail = "arem.hemalatha@gmail.com";
        String expPwd = "autotest";
        if (email.equals(expEmail) && password.equals(expPwd)) {
            Thread.sleep(2000);
            Assert.assertTrue(page.txtWelcome.isDisplayed());
            Assert.assertTrue(page.btnMyAccount.isDisplayed());
            Assert.assertTrue(page.btnWishlist.isDisplayed());
            Assert.assertTrue(page.btnlogout.isDisplayed());
        } else if (!(email.equalsIgnoreCase(expEmail)) && password.equalsIgnoreCase(expPwd)) {
            Thread.sleep(2000);
            Assert.assertTrue(page.txtloginunsuccessful.isDisplayed());
            Assert.assertTrue(page.txtNoCustomerfound.isDisplayed());
        } else if (email.equalsIgnoreCase(expEmail) && !password.equalsIgnoreCase(expPwd)) {
            Thread.sleep(2000);
            Assert.assertTrue(page.txtloginunsuccessful.isDisplayed());
            Assert.assertTrue(page.txtNoCustomerfound.isDisplayed());
        } else if (!(email.equalsIgnoreCase(expEmail)) && !password.equalsIgnoreCase(expPwd)) {
            Thread.sleep(2000);
            Assert.assertTrue(page.txterror.isDisplayed());
        }

    }

    public void closeDriver(){
        driver.quit();
    }

    public void clickForgotPassword(String email) throws InterruptedException {
        page.linkForgotPwd.click();
        Thread.sleep(2000);
        Assert.assertTrue(page.txtRecoveryPassword.getText().equalsIgnoreCase("Password recovery"));
        page.txtemail.sendKeys(email);
        Thread.sleep(1000);
        page.btnRecovery.click();
        Thread.sleep(1000);
        Assert.assertTrue(page.txtEmailInst.isDisplayed());
        page.btnclose.click();
        Thread.sleep(1000);

    }

    public void verifyLoginWithNewPassword(String email, String newPassword) throws InterruptedException {
        page.click_login();
        Thread.sleep(2000);
        page.enter_login_details(email, newPassword);
        Thread.sleep(2000);
        Assert.assertTrue(page.txtWelcome.isDisplayed());
        Assert.assertTrue(page.btnMyAccount.isDisplayed());
        Assert.assertTrue(page.btnWishlist.isDisplayed());
        Assert.assertTrue(page.btnlogout.isDisplayed());

    }

    public void postLoginValidations() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(page.txtWelcome.isDisplayed());
        Assert.assertTrue(page.btnMyAccount.isDisplayed());
        Assert.assertTrue(page.btnWishlist.isDisplayed());
        Assert.assertTrue(page.btnlogout.isDisplayed());

    }

    public void clickLogout(){
        page.btnlogout.click();
    }

    public void verifyLogout(){
        driver.getCurrentUrl().equalsIgnoreCase("https://demo.nopcommerce.com/");
        Assert.assertTrue(page.linklogin.isDisplayed());
        Assert.assertTrue(page.btnRegister.isDisplayed());
    }


}
