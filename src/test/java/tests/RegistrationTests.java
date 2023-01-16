package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();

        }

    }

    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().withName("Tapuah").withLastName("Adoma").withEmail("tap" + i + "@gmail.com").withPassword("adomA12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        app.getHelperUser().closeDialogContainer();
        Assert.assertTrue(app.getHelperUser().isLogged());


    }

    @Test
    public void registrationWrongEmail() {

        User user = new User().withName("Tapuah").withLastName("Adoma").withEmail("tgmail.com").withPassword("adomA12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        app.getHelperUser().closeDialogContainer();
        Assert.assertEquals(app.getHelperUser().wrongMassegeByEmailRegistration(), "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationWrongPasswordMin8Symbols() {

        User user = new User().withName("Tapuah").withLastName("Adoma").withEmail("tap879@mail.com").withPassword("adom");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        app.getHelperUser().closeDialogContainer();
        Assert.assertEquals(app.getHelperUser().wrongMassegeByPasswordRegistration1(), "Password must contain minimum 8 symbols");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationWrongWithoutName() {

        User user = new User().withLastName("Adoma").withEmail("tap@mail.com").withPassword("adomA12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().clickByName();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Assert.assertEquals(app.getHelperUser().wrongMassegeWithoutName(), "Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test(enabled = false)
    public void registrationWrongWithSpaceName() throws InterruptedException {
        User user = new User().withName(" ").withLastName("Adoma").withEmail("tap879@mail.com").withPassword("IliB$123456");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        Thread.sleep(2000);
        Assert.assertEquals(app.getHelperUser().wrongMassegeSpaceName(), "{\"firstName\":\"must not be blank\"}");


    }
}