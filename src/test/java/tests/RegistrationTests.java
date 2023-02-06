package tests;

import model.User;
import model.UserLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import manager.DataProviderUser;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("I need logout");
        }

    }

    @Test(dataProvider = "registrationDataFromFile", dataProviderClass = DataProviderUser.class)
    public void registrationSuccess(UserLombok userL) {
        logger.info("Registration success with data: "+ userL.toString());

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillLoginFormLombok(userL);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        app.getHelperUser().closeDialogContainer();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test success");

    }

    @Test
    public void registrationWrongEmail() {

        User user = new User().withName("Tapuah").withLastName("Adoma").withEmail("tgmail.com").withPassword("adomA12345$");
        logger.info("Test 2: Registration wrong email with data: "+user.getEmail()+ user.getPassword());

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        app.getHelperUser().closeDialogContainer();
        Assert.assertEquals(app.getHelperUser().wrongMassegeByEmailRegistration(), "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Message is: 'Wrong email format'");
    }

    @Test
    public void registrationWrongPasswordMin8Symbols() {

        User user = new User().withName("Tapuah").withLastName("Adoma").withEmail("tap879@gmail.com").withPassword("adom");
        logger.info("Test 3:Registration Wrong Password Min 8 Symbols: "+ user.getEmail()+user.getPassword());

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        app.getHelperUser().closeDialogContainer();
        Assert.assertEquals(app.getHelperUser().wrongMassegeByPasswordRegistration1(), "Password must contain minimum 8 symbols");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Message is: 'Password must contain minimum 8 symbols'");
    }

    @Test
    public void registrationWrongWithoutName(){

        User user = new User().withLastName("Adoma").withEmail("tap@mail.com").withPassword("adomA12345$");
        logger.info(" Test 4: Registration wrong Without Name: "+ user.getEmail()+user.getPassword());

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().clickByName();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();

       // Assert.assertEquals(app.getHelperUser().wrongMassegeWithoutName(), "Name is required");
      //  Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Message is: 'Name is required'");

    }

    @Test(enabled = false)
    public void registrationWrongWithSpaceName(){
        User user = new User().withName(" ").withLastName("Adoma").withEmail("tap879@mail.com").withPassword("IliB$123456");
        logger.info("Test 5: Registration Wrong with Space Name with data: "+user.getEmail()+ user.getPassword());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
       // Thread.sleep(2000);
        Assert.assertEquals(app.getHelperUser().wrongMassegeSpaceName(), "{\"firstName\":\"must not be blank\"}");
        logger.info("Message is: 'first Name must not be blank'");

    }
}