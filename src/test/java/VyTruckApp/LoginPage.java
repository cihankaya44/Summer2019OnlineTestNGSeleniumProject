package VyTruckApp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class LoginPage {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://qa2.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user190");
        Thread.sleep(3000);
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        BrowserUtils.wait(3);
        driver.findElement(By.id("_submit")).click();

        String expectedURL = "https://qa2.vytrack.com/";
        String actualURL = driver.getCurrentUrl();
        System.out.println(actualURL);
        System.out.println(expectedURL);

        if (expectedURL.equals(actualURL)) {
            System.out.println("Test pass");

        } else {
            System.out.println("Test Fail");
            System.out.println(actualURL);
            System.out.println(expectedURL);
        }
        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();
        System.out.println("expectedTitle: " + expectedTitle);

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Test pass");

        } else {
            System.out.println("Test Fail");
            System.out.println("actualTitle: " + actualTitle);
            System.out.println("expectedTitle: " + expectedTitle);
        }
        driver.close();
    }
}
