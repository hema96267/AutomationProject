package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPOM {


    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Log in')]")
    public WebElement linklogin;

    @FindBy(how = How.XPATH, using = "//input[@id='Email']")
    public WebElement txtemail;

    @FindBy(how = How.XPATH, using = "//input[@id='Password']")
    public WebElement txtpwd;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Log in')]")
    public WebElement btnlogin;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Log out')]")
    public WebElement btnlogout;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'My account')]")
    public WebElement btnMyAccount;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Wishlist')]")
    public WebElement btnWishlist;

    @FindBy(how = How.XPATH, using = "/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[1]/ul/li")
    public WebElement txtNoCustomerfound;

    @FindBy(how = How.XPATH, using = "/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[2]/form/div[1]")
    public WebElement txtloginunsuccessful;

    @FindBy(how = How.XPATH, using = "//*[@id=\"Email-error\"]")
    public WebElement txterror;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Forgot password?')]")
    public WebElement linkForgotPwd;

    @FindBy(how = How.XPATH, using = "/html/body/div[6]/div[3]/div/div/div/div[1]/h1")
    public WebElement txtRecoveryPassword;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Recover')]")
    public WebElement btnRecovery;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(),'Welcome to our store')]")
    public WebElement txtWelcome;

    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Email with instructions has been sent to you.')]")
    public WebElement txtEmailInst;

    @FindBy(how = How.XPATH, using = "//span[@title=\"Close\"]")
    public WebElement btnclose;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Register')]")
    public WebElement btnRegister;



    public void click_login() {
        linklogin.click();
    }

    public void enter_login_details(String email, String pwd) throws InterruptedException {
        txtemail.sendKeys(email);
        Thread.sleep(2000);
        txtpwd.sendKeys(pwd);
        Thread.sleep(2000);
        btnlogin.click();
        Thread.sleep(1000);
    }
}
