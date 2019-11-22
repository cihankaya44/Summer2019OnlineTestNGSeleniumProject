package tests;

public class NOTES {
    /*
    Create a project with:
Artifact id: BasicNavigationTests
group id: com.cbt
then, go to src --> test --> java
right click on java, and create a package: com.cbt.utilities
then, right click on cbt and create a package with name:
com.cbt.tests.
. (dot) works as a delimiter. When you put . in between words,
 it creates 2 packages.
 Then Enable auto-import .
Add dependencies and properties:
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>3.7.1</version>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>
    </dependencies>
They will add selenium into our project and webdriver manager.
Plus, it will tell Maven to use java 8. Because, by default
maven is using java 5.
Create local git repo for new project.
git init
Initialized empty Git repository in /Users/studio2/IdeaProjects/BasicNavigationTests/.git/
create .gitignore file
Files that you never want to commit to the github.
add to that file following:
target
.idea
click save..
Then
git add .
git commit -m "first commit"
git remote add origin <YOUR REPO URL>
git push -u origin master
########################################################
Today 10/28/2019
    Agenda: locators, web elements, findElement(), findElements()
How to we start automating?
First, we need to open the page in the browser (chrome)
Then, right click and inspect the page
Click on the selector to select specific element or text on the page.
We can click with a inspector on the specific element.
Them, we can see what HTML code is behind this element.
Based on the properties/attributes, tags and text we can choose locator strategy.
If it's possible, use id.
in code, it will look like: <input type="text" id="first_name">
WebElement first_name = driver.findElement(By.id("first_name"));
id must be unique! it's like SSN.
Usually, we try to ask developers to add id's if it's possible.
The only case when we cannot use id, is when id is dynamic. It's basically when id is different for every refresh or once in while.

 How to understand if it's a dynamic id?
 You fond id, then you've used it for finding element. First run, script passed. Second time - NoSuchElemntException.
If you see that element is not there, based on this id, that means it changed id value.
NoSuchElemntException - means that based on your locator, selenium couldn't find any elements. There are 2 cases: element is there but we need to wait for element. Another case - element is not there indeed.
please comeback at 4:15
<a href="/autocomplete">Autocomplete</a>
a - tag name
href - attribute
/autocomplete - value of href attribute
Autocomplete - text
<a href="/autocomplete"> - opening tag
</a> - closing tag
In general, <a> stands for hyperlink. It's a Clickable text. Once you click on this text, it will bring you to the different page.
We can find element, if it's a link, based on link text.
   //remember!
        //for Mac users: you don't need to use WebDriverManager for Safari
        //safari has embedded webdriver support.
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/sign_up");
        //if you want to do in one line, without creating webelemnt reference variable
        //enter full name
        driver.findElement(By.name("full_name")).sendKeys("Test User");
        //enter email
        driver.findElement(By.name("email")).sendKeys("test_email@email.com");
        //click sign up
        driver.findElement(By.name("wooden_spoon")).click();
        //pause for 3 seconds
        BrowserUtils.wait(3);
        driver.quit();
//      <a href="/autocomplete">Autocomplete</a>
//        Autocomplete - it's the text that you see in the link
//        place where it will navigate you specified in href
//        it works only if tag name is <a>
//        if you want to use linkText or partialLinkText locator strategy
//        element must start with <a>
//        this is how we specify linkd in HTML
        driver.findElement(By.linkText("Autocomplete")).click();
     */

}
