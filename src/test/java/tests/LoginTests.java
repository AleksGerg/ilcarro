package tests;

import manager.DataProviderUser;
import model.User;
import model.UserLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
              app.getHelperUser().logout();
            logger.info("I need logout");
        }

    }

    @DataProvider()
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"benb@gmail.com", "Beny$123456"});
        list.add(new Object[]{"ili@gmail.com", "IliB$123456"});
        list.add(new Object[]{"benb@gmail.com", "Beny$123456"});

        return list.iterator();
    }

    @Test(dataProvider = "loginData")
    public void loginSuccess(String email, String password) {
        logger.info("Test 2: Login with valid data: " + email+" password: "+password);
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();
        app.getHelperUser().pause(1000);
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Test success");
    }

    @Test(dataProvider = "loginDataCls",dataProviderClass = DataProviderUser.class)
    public void loginSuccess2(String email, String password) {
        logger.info("Test 2: Login with valid data: " + email+" password: "+password);
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();
        app.getHelperUser().pause(1000);
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Test success");
    }

    @Test
    public void loginSuccessModel() {
       User user = new User().withEmail("ili@gmail.com").withPassword("IliB$123456");
        logger.info("Test 3: Login Success Model with User: " + user.toString());
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Message is: 'Logged in success'");
    }

    @Test(dataProvider = "loginDataUserFromFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelFromFile(UserLombok user) {

        logger.info("Test 3: Login Success Model with User: " + user.toString());
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginFormLombok(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Message is: 'Logged in success'");
    }


    @Test
    public void loginWrongEmail() {
        User user = new User().withEmail("ben.com").withPassword("Beny$123456");
        logger.info("Test 4: Login Wrong Email with data: email-> ben.com & password-> Beny$123456 ");

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Error message is: 'It's not look like email'");
    }

    @Test
    public void loginWrongPassword() throws InterruptedException {
        User user = new User().withEmail("benb@gmail.com").withPassword("Be");
        logger.info("Test 5: Login Wrong Password with data:" + user.getEmail() + user.getPassword());

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Thread.sleep(2000);
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Error message is: 'Login or Password incorrect'");
    }

    @Test(enabled = false)
    public void loginUnregisterUser() {

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeDialogContainer();
    }
}
