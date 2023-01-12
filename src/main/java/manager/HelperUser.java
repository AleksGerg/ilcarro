package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase {

    public boolean isLogged() {
        List<WebElement> elements = wd.findElements(By.xpath("//a[text()=' Logout ']"));
        return elements.size() > 0;
    }

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
        click(By.cssSelector("a[href=\"/login?url=%2Fsearch\"]"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.cssSelector("#email"), email);
        type(By.cssSelector("#password"), password);
    }

    public void submitLogin() {
        click(By.cssSelector("[type='submit']"));
    }

    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }


    public boolean isErrorMassegeDisplayed(By location) {
        List<WebElement> elements = wd.findElements(location);
        return elements.size()>0;
    }
}
