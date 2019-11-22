package tests.day8;

public class NOTES {
    /*
    Today is 11/14/2019
    Agenda:
        checkboxes
        radio buttons
        isDisplayed()
        findElements();
        DropDowns --> Select class
##########################
TestNG --> next generation testing. Still, TestNG it's a popular testing framework, that is usually used along with a selenium webdriver.
public class CheckBoxes {
    // command + option + L - to organize code for Mac
    // control + alt + L - to organize code for windows
    private WebDriver driver;
    //private because it will be used only in this class
    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com");
//        <a href="/checkboxes">Checkboxes</a>
        driver.findElement(By.linkText("Checkboxes")).click();
    }
    @Test
    public void test1() {
        //find all checkboxes
        //any checkbox will have [type='checkbox']
//        <form id="checkboxes">
        //    <input type="checkbox" checked=""> checkbox 1
        //    <br>
        //    <input type="checkbox" checked=""> checkbox 2
//                </form>
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type='checkbox']"));
        int index = 1;
        for (WebElement checkbox : checkboxes) {
            if(checkbox.isEnabled() && !checkbox.isSelected()){
                checkbox.click();
                System.out.println("Checkbox #"+index+" was clicked");
            }else {
                System.out.println("Checkbox #"+index+" was not clicked");
            }
            index++;
        }
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
public class Dropdowns {
    private WebDriver driver;
    //   <select id="dropdown">
//      <option value="" disabled="disabled" selected="selected">Please select an option</option>
//      <option value="1">Option 1</option>
//      <option value="2">Option 2</option>
//    </select>
    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        driver.findElement(By.linkText("Dropdown")).click();
    }
    @Test(description = "Select option 2 from the dropdown")
    public void test1() {
        // to work with select dropdowns, we need to use Select class in Selenium
        //step 1. Find dropdown and create a webelement
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        //ste 2. Create a select object
        //select class requires webelemnt object as a parameter
        Select select = new Select(dropdown);
        //to select any option by visible text:
        // also you can select by value, or index
        //<option value="1">Option 1</option> value is 1, Option 1 is a visible text, in between >Text< (in between angled brackets)
        select.selectByVisibleText("Option 2");
        BrowserUtils.wait(2);
        //how to verify that option 2 is selected
        //select.getFirstSelectedOption() - to get selected option/this is what is selected
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2");
    }
    @Test(description = "Print list of states")
    public void test2() {
        WebElement state = driver.findElement(By.id("state"));
        Select select = new Select(state);
        List<WebElement> states = select.getOptions();//will return available options to select
        //how to print every option, as a text, one by one?
        for (WebElement option : states) {
            System.out.println(option.getText());
        }
    }
    @Test(description = "Select your state and verify that state is selected")
    public void test3() {
        WebElement state = driver.findElement(By.id("state"));
        Select select = new Select(state);
//        <option value="MD">Maryland</option>
        //we can use tes, value or index for selection
        select.selectByValue("MD");
        BrowserUtils.wait(2);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Maryland");
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
public class RadioButtons {
    private WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        //to go to Radio Buttons page
//        <a href="/radio_buttons">Radio Buttons</a>
//        linkText works only with <a> elements
//        link text only in between >Text<
//        this is step is common for all test cases
        driver.findElement(By.linkText("Radio Buttons")).click();
    }
    @Test(description = "Verify that blue button is selected")
    public void test1() {
        //find blue radio button
        WebElement blueButton = driver.findElement(By.id("blue"));
        //let's verify that radio button is selected
        //assert true that button is selected
        //if button is selected it will return true, otherwise false
        boolean isSelected = blueButton.isSelected();
        Assert.assertTrue(isSelected); // will expected that isSelected is true
    }
    @Test(description = "Verify that red button is not selected")
    public void test2() {
        WebElement redButton = driver.findElement(By.id("red"));
        //isSelected() will return tru if button already clicked
        Assert.assertFalse(redButton.isSelected()); // assertFalse will expect that condition is false
    }
    @Test(description = "Verify that green button is not clickable")
    public void test3() {
        WebElement greenButton = driver.findElement(By.id("green"));
        // isEnabled() will return true if button is available for interaction
        //that means you can click on it, in this case
        Assert.assertFalse(greenButton.isEnabled());
    }
    //let's find all radio buttons and click on them one by one
    @Test(description = "Click on all radio buttons")
    public void test4() {
        //how to find all radio buttons?
        //find all radio buttons
        //any radio button will have type='radio' and input as a element type
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
//        let's click only if button is not clicked and is available for clicking
        for (WebElement button : radioButtons) {
//            if button is available for clicking and not clicked yet
            if (button.isEnabled() && !button.isSelected()) {
//                then click on it
                button.click();
//                in this case, id attribute represents a name of the color
//                also, there is no text in this element
//                that's why I print attribute value
//                <input type="radio" id="green" name="color" disabled=""> attribute: type, id, name, disabled
                System.out.println("Button clicked: " + button.getAttribute("id"));
            } else {
                System.out.println("Button was not clicked: " + button.getAttribute("id"));
            }
            BrowserUtils.wait(1); // for demo
        }
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

     */
}
