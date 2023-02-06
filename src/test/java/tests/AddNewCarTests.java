package tests;

import model.Car;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("benb@gmail.com").withPassword("Beny$123456"));//?? = login
            logger.info("I need logout");
        }
    }
    @Test
    public void addNewCarSuccsses(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;

        Car car = Car.builder()
                .location("Haifa, Israel")
                .manufacture("bmw")
                .model("W154")
                .year("2020")
                .fuel("Petrol")
                .seats("4")
                .clasS("C")
                .carRegNumber("555-66-"+i)
                .price("65")
                .about("very nice car")
                .build();
        logger.info("Test 1: Add New Car Success");
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("C:\\Users\\Aleksandra\\Desktop\\GitHubQA36\\ilcarro\\WhatsApp Image 2023-01-22 at 15.47.06.jpeg");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isTitleMessageContains("Car added"));
      //  app.getHelperCar().clickByShowCar();
        logger.info("Test 1 stopped with data: "+ car.toString());
    }
}
