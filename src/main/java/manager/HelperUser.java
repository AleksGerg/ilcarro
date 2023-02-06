package manager;


import model.User;
import org.openqa.selenium.*;
import model.UserLombok;


import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {

        super(wd);
    }


    public void openFormLogin() {

        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//input[@type='email']"), user.getEmail());
        // type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }
    public void fillLoginFormLombok(UserLombok user) {
        type(By.xpath("//input[@type='email']"), user.getEmail());
        // type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }



    public String getMessage() {

        return wd.findElement(By.cssSelector("div.dialog-container>h2")).getText();
    }

    public void closeDialogContainer() {
        if (isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
       // return isElementPresent(By.cssSelector("div.header a:nth-child(5)"));
    }

    public void logout() {
         click(By.xpath("//a[text()=' Logout ']"));
       // click(By.cssSelector("div.header a:nth-child(5)"));
    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
        // return isElementPresent(By.cssSelector("button[disabled]"));
        return !wd.findElement(By.cssSelector("button[disabled]")).isEnabled();
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.cssSelector("#name"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }
    public void fillRegistrationFormUserLombok(UserLombok user){
        type(By.cssSelector("#name"), user.getName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"),user.getPassword());
    }

    public void checkPolicy() {
        click(By.xpath("//label[@class='checkbox-label terms-label']"));

    }

    public void checkPolicyXY() {
        Dimension size = wd.manage().window().getSize();
        size.getHeight();
        size.getWidth();

        WebElement label = wd.findElement(By.xpath("//label[@class='checkbox-label terms-label']"));
        Rectangle rect = label.getRect();
        int xOffset = rect.getWidth()/2;
        Actions actions = new Actions(wd);

        actions.moveToElement(label,-xOffset,0).click().release().perform();

    }
    public void checkPolicyJS() {
        JavascriptExecutor js  = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').checked=true;");

    }

    public String wrongMassegeByEmailRegistration() {
        return wd.findElement(By.xpath("//div[text()='Wrong email format']")).getText();
    }

    public String wrongMassegeByPasswordRegistration1() {
        return wd.findElement(By.xpath("//div[text()='Password must contain minimum 8 symbols']")).getText();
    }

    public String wrongMassegeWithoutName() {
        return wd.findElement(By.xpath("//div[@class='error']")).getText();
    }

    public void clickByName() {
        click(By.cssSelector("#name"));
    }

    public String wrongMassegeSpaceName() {
        return wd.findElement(By.xpath("//h2[@class='message']")).getText();
    }

    public void login(User user) {
        openFormLogin();
        fillLoginForm(user);
        submit();
        closeDialogContainer();
    }
}