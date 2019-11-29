package VyTruckApp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.BrowserFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCases {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        System.out.println("Test is starting...");
        driver = BrowserFactory.getDriver("chrome");
        wait = new WebDriverWait(driver,10); //this setUp of explicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //implicit wait
        driver.manage().window().maximize(); //maximize window

        driver.get("https://qa1.vytrack.com");
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.name("_password")).sendKeys("UserUser123", Keys.ENTER);

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));

        //to hover on dropdown menu
        Actions action = new Actions(driver);
        action.moveToElement(activitiesElement).perform();

        driver.findElement(By.linkText("Calendar Events")).click();

        //we need to wait until mask is gone
        WebElement mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(mask));

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(description = "Verify 'Options' is displayed")
    public void test1(){
        WebElement optionsLocator = driver.findElement(By.cssSelector("[class='btn btn-link dropdown-toggle']"));
        Assert.assertTrue(optionsLocator.isDisplayed());
    }

    @Test(description = "Verify page number is 1")
    public void test2(){
        String pageNumber = driver.findElement(By.cssSelector("[type='number']")).getAttribute("value");
        Assert.assertEquals(pageNumber, "1", "wrong page number");
    }

    @Test(description = "Verify view per page is 25")
    public void test3(){
        String viewPerPageNumb = driver.findElement(By.cssSelector("[class='btn dropdown-toggle ']")).getText();
        Assert.assertEquals(viewPerPageNumb, "25", "wrong number per page");
    }

    @Test(description = "Verify total numb of records is same as number of record rows")
    public void test4(){
        //String totalOfRecord = driver.findElement(By.xpath("//label[contains(text(),'Total')]")).getText();
        String totalOfRecord = driver.findElement(By.cssSelector("[class='dib']:nth-of-type(3)")).getText();
        int actualNumOfRec = driver.findElements(By.cssSelector("tr[class='grid-row row-click-action']")).size();

        System.out.println(totalOfRecord);
        System.out.println("Number of rows: "+actualNumOfRec);
        Assert.assertTrue(totalOfRecord.contains(actualNumOfRec+""));
    }

    @Test(description = "After clicking top checkbox, Verify that all calendar events were selected")
    public void test5(){
        //read it from top left of table
        int actualNumOfRec = driver.findElements(By.cssSelector("tr[class='grid-row row-click-action']")).size();
        //clicking on checkbox to select all records
        driver.findElement(By.cssSelector("[class='btn btn-default btn-small dropdown-toggle']>input")).click();
        //to get how many selected records are there after clicking on checkbox
        int numOfSelectedRows = driver.findElements(By.cssSelector("[class='grid-row row-click-action row-selected']")).size();

        Assert.assertEquals(numOfSelectedRows, actualNumOfRec);
    }

    @Test(description = "Verify meeting details are correct")
    public void test6(){
        //to click the 14th record
        driver.findElement(By.cssSelector("[class='grid-row row-click-action']:nth-of-type(14)")).click();

        //after click one more mask so we need to wait until mask is gone
        WebElement mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(mask));

        List<String> list = new ArrayList<>(Arrays.asList(
                "Testers Meeting",
                "This is a a weekly testers meeting",
                "Nov 27, 2019, 9:30 PM",
                "Nov 27, 2019, 10:30 PM",
                "No",
                "Stephan Haley",
                "Tom Smith",
                "Weekly every 1 week on Wednesday",
                "No"));

        for(int i=0; i<list.size(); i++){
            String actualRes = driver.findElements(By.cssSelector("[class='responsive-block']>div>div>div")).get(i).getText();
            if(i==6) {
                Assert.assertEquals(actualRes.replace(" - Required",""), list.get(i));
                System.out.println(actualRes.replace(" - Required",""));
            }
            else {
                Assert.assertEquals(actualRes, list.get(i));
                System.out.println(actualRes);
            }
        }
    }






}