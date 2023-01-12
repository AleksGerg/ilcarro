package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.gethUser().isLogged()) {
            app.gethUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        app.gethUser().openLoginRegistrationForm();
        app.gethUser().fillLoginRegistrationForm("benb@gmail.com", "Beny$123456");
        app.gethUser().submitLogin();
        app.gethUser().click(By.cssSelector("button[class='positive-button ng-star-inserted']"));
        Assert.assertTrue(app.gethUser().isLogged());

    }

    @Test
    public void loginWrongEmail() {
        app.gethUser().openLoginRegistrationForm();
        app.gethUser().fillLoginRegistrationForm("benbgmail.com", "Beny$123456");
        Assert.assertTrue(app.gethUser().isErrorMassegeDisplayed(By.xpath("//div[text()=\"It'snot look like email\"]")));

    }

    @Test
    public void loginWrongPassword() {
        app.gethUser().openLoginRegistrationForm();
        app.gethUser().fillLoginRegistrationForm("benb@gmail.com", "Be");
        app.gethUser().submitLogin();
        app.gethUser().click(By.cssSelector("button[class='positive-button ng-star-inserted']"));
        Assert.assertTrue(app.gethUser().isErrorMassegeDisplayed(By.xpath("//button[@type='button']")));
    }

    @Test
    public void loginUnregisterUser() {
        app.gethUser().openLoginRegistrationForm();
        app.gethUser().fillLoginRegistrationForm("benb1@gmail.com", "Beny$1234567");
        app.gethUser().submitLogin();
        app.gethUser().click(By.cssSelector("button[class='positive-button ng-star-inserted']"));
        Assert.assertTrue(app.gethUser().isErrorMassegeDisplayed(By.xpath("//h2[contains(text(),'\"Login or Password incorrect\"')]")));
    }
}
