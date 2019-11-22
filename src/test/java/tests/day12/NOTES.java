package tests.day12;

public class NOTES {
    /*
    Today is 11/21/2019

    Agenda:
        Frames
        Actions class
        JS executor
        break
        git/github
        git add , git commit, push, pull, fetch, merge, clone
##########################
HTML frames allow developers to present documents in multiple views, which may be in a separate child window or sub-window.
Multiple views offer developers a way to keep certain information visible while other views are scrolled or replaced.
For example, within the same window, one frame might display a static banner, the second a navigation menu, and the third the main document that can be scrolled through or replaced by navigating in the second frame.
A page with frames is created using the <frameset> tag or the <iframe> tag.
Developers can embed external documents or documents from another domain using the <iframe> tag, also known as inline frames. Various social media websites provide buttons that can be embedded in your web applications to link to these websites.
 @Test(description = "Verify first image")
    public void test1() {
        //create object of actions class to perform actions (drag and drop, context click, move to specific point, etc...)
        Actions action = new Actions(driver);
        //or [class='figure']:nth-of-type(1) nth means 8th, 7th, 4th child....
        WebElement image1 = driver.findElement(By.cssSelector(".figure:nth-of-type(1)"));
        //just to hover on element not click
        // build() is required when we have more than 1 action in a chain
        //without .perform(); it will not work
        // moveToElement = hover
        // .perform() stands for triggering actions
        action.moveToElement(image1).perform();
        BrowserUtils.wait(3);//for demo
        // h5 is a grand child of .figure:nth-of-type(1) this element that contains image
        WebElement textOfImageElement = driver.findElement(By.cssSelector(".figure:nth-of-type(1) h5"));
        String expectedText = "name: user1";
        String actualText = textOfImageElement.getText();
        Assert.assertEquals(actualText, expectedText);
    }
    @Test(description = "Verify all images")
    public void test2() {
        Actions action = new Actions(driver);
        for (int i = 1; i <= 3; i++) {
            action.moveToElement(driver.findElement(By.cssSelector(".figure:nth-of-type(" + i + ")"))).perform();
            BrowserUtils.wait(3);
            String name = driver.findElement(By.cssSelector(".figure:nth-of-type(" + i + ") h5")).getText();
            System.out.println(name);
            Assert.assertEquals(name, "name: user"+i);
        }
    }
    @Test(description = "Drag and drop example")
    public void test1(){
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
        driver.manage().window().maximize();
        //click on accept cookies
        BrowserUtils.wait(3);//for demo
        driver.findElement(By.cssSelector("button[title='Accept Cookies']")).click();
        Actions actions = new Actions(driver);
        //moon - it's a draggable object (small ball)
        //earth - target, where we want to drop draggable object (big ball)
        WebElement moon = driver.findElement(By.id("draggable"));
        WebElement earth = driver.findElement(By.id("droptarget"));
        BrowserUtils.wait(3);//for demo
//      * @param source element to emulate button down at.
//      * @param target element to move to and release the mouse at.
        actions.dragAndDrop(moon, earth).perform();
        BrowserUtils.wait(3);//for demo
        driver.quit();
    }
@Test(description = "iFrame example")
    public void test1(){
        driver.findElement(By.linkText("iFrame")).click();
        //since element inside a frame, element is not visible for selenium
        //without switching to the frame
        //we can switch to frame based on id, name, index(starting from 0), web element
        driver.switchTo().frame("mce_0_ifr");
        //WITHOUT SWITCHING, WE CANNOT SEE INNER HTML DOCUMENT
        // which one to use? id, name, index, webelement?
        //1. id or name  <iframe id="mce_0_ifr" name="some_frame">
        //2. webelment driver.findElement(By.cssSelector("iframe[class='some_frame']"));
        //3. index [iframe1, iframe2, iframe3...]
        WebElement inputArea = driver.findElement(By.id("tinymce"));
        String expectedText= "Your content goes here.";
        String actualText = inputArea.getText();
        Assert.assertEquals(actualText, expectedText);
        BrowserUtils.wait(4);
        inputArea.clear();//to delete text
        BrowserUtils.wait(4);
        inputArea.sendKeys("Java is fun!");
        //to exit from the frame
        driver.switchTo().defaultContent();
    }
    //in case of nested frames
    //we must switch to first frame --> then again to another frame, that is inside
    // -- html
    // -- frame #1
    // ---- frame #2
    @Test(description = "Nested Frames example")
    public void test2(){
        //it's not switch to frame
        //it's a navigation action
        driver.findElement(By.linkText("Nested Frames")).click();
        //we switch to frame based on webelement
        driver.switchTo().frame(driver.findElement(By.cssSelector("[name='frame-bottom']")));
        //the reason why we are switching here
        //is because content that is inside a frame is not visible for selenium
        //it's like when you are on the first floor
        //trying to find what is on the second floor
        WebElement content = driver.findElement(By.tagName("body"));
        System.out.println(content.getText());
        driver.switchTo().defaultContent();//to exit from all frames, got to first floor
        driver.switchTo().frame("frame-top"); // second floor
        driver.switchTo().frame("frame-left"); // third floor
        System.out.println(driver.findElement(By.tagName("body")).getText());//print text of body
    }
@Test(description = "Scrolling with JavaScriptExecutor")
    public void test1(){
        driver.get("http://practice.cybertekschool.com/infinite_scroll");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0; i< 10; i++){
            //move 500px down
//            Parameters
//            x-coord is the horizontal pixel value that you want to scroll by.
//            y-coord is the vertical pixel value that you want to scroll by.
            js.executeScript("window.scrollBy(0, 500)");
            BrowserUtils.wait(1);
        }
        BrowserUtils.wait(3);
    }
    @Test(description = "Scrolling with JSexecutor to specific element")
    public void test2(){
        driver.get("http://practice.cybertekschool.com/large");
        WebElement link = driver.findElement(By.linkText("Cybertek School"));
        BrowserUtils.wait(2);//for demo
//        js code from the browser
//        var footer = document.getElementById('page-footer');
//        footer.scrollIntoView(true);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //this script must scroll, until link element is visible
        //once link element will be visible, it will stop scrolling
        //arguments[0] = means first webelement after comma (link)
        //arguments it's an array of webelements after comma
        //arguments[0] = link web element, it can be any web element
        js.executeScript("arguments[0].scrollIntoView(true)", link);
        BrowserUtils.wait(2);
    }
//    var btn1 = document.getElementsByTagName('a')[1];
//    btn1.click()
    @Test(description = "Click with JS executor")
    public void test3(){
        driver.get("http://practice.cybertekschool.com/dynamic_loading");
        //Example 1 is a beginning of the phrase <a href='http:'>Example 1.....</a>
        WebElement link1 = driver.findElement(By.partialLinkText("Example 1"));
        BrowserUtils.wait(2);//wait for demo
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //arguments[0] = link1 web element
        //whenever regular selenium methods are not working, I use js executor
        //or for scrolling
        //arguments[0].click() is an alternative for link1.click()
        js.executeScript("arguments[0].click()", link1);
        BrowserUtils.wait(2);//wait for demo
    }
    //document.getElementsByName('full_name')[0].setAttribute('value','My name')
    @Test(description = "Enter text with JS executor")
    public void test4(){
        driver.get("http://practice.cybertekschool.com/sign_up");
        WebElement name = driver.findElement(By.name("full_name"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement submitButton = driver.findElement(By.name("wooden_spoon"));
        //to create javascriptexecutor object we need to cast webdriver object
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //enter full name
        //arguments[0].setAttribute('value', 'John Smith') it's the same as name.sendKeys("John Smith");
        BrowserUtils.wait(2);
        js.executeScript("arguments[0].setAttribute('value', 'John Smith')", name);
        BrowserUtils.wait(2);
        js.executeScript("arguments[0].setAttribute('value', 'someemail@email.com')", email);
        BrowserUtils.wait(2);
        js.executeScript("arguments[0].click()", submitButton);
        BrowserUtils.wait(2);
    }
     */

}
