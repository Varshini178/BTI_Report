package btipackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ch.qos.logback.core.joran.action.Action;
import io.github.bonigarcia.wdm.WebDriverManager;

public class checkListView {//BTI16
WebDriver driver;
	
	@BeforeTest
	public void launchApplication() throws InterruptedException //BTI1,BTI2
	{
		WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
        driver.get("https://app.vgqa.glint.cloud.dev.microsoft/session/auth");
        driver.manage().window().maximize();
	}
        @Test(priority =0)
	     public void Testlogin() throws InterruptedException {
	    	 
        	//email
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement emailbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	    	    emailbox.sendKeys("qatester@glintinc.com");
	    	    
	        	WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement continueButton = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-auth/form/section/footer/button")));
	    	    continueButton.click();
	    	    
	    	    //companyID
	        	WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement CompanyID = wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("clientUuid")));
	    	    CompanyID.sendKeys("qa20161215");
	    	    
	        	WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement continueButton2 = wait4.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-client/form/section/footer/button")));
	    	    continueButton2.click();
	    	    
	    	    //Password
	        	WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement Password = wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
	    	    Password.sendKeys("Dem0@pass2");
	    	    
	    	    Thread.sleep(3000);
	    	    WebElement continueButton3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
	    	    continueButton3.click();
        }
        
        
        
        @Test(priority =1)
        public void switchProgram() {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        	WebElement switchProgram = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='menuButton glintButton btnSlim']")));
        	switchProgram.click();
        	
        	try {
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(120));
            WebElement search = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'input ng-pristine')]")));
            search.sendKeys("Engagement");
        	} catch(TimeoutException e) {
        		System.out.println("unable to locate the element");
        	}
            List<WebElement> list = driver.findElements(By.xpath("//ul[@id='menu']/li"));
            for (WebElement options : list) {
                if (options.getText().equalsIgnoreCase("Engagement")) {
                    options.click();
                    break;
                }
            }}
       
       
        
        @Test(priority =2)
        public void checkGridView()  {
       	try {
       	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       WebElement viewDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Report Actions Menu']")));
           JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript( "window.scrollBy(0,1000)");
           viewDropdown.click();
           
         List  <WebElement> Grid = driver.findElements(By.xpath("//ul[@id='menu']/li"));
         for(WebElement GridViewoption:Grid) {
             if (GridViewoption.getText().equals("Grid")) {
       		  GridViewoption.click();
       	  }}}
                catch(TimeoutException e) {
               	 System.out.println("unable to find the element");
                }
       	
              WebElement Gridview = driver.findElement(By.cssSelector("section[class='soTableView']"));
              if(Gridview.isDisplayed()) {
           	   System.out.println("Strengths and Opportunities shown in grid view");
              }}

 @Test(priority=3)       
public void TakeAction() {
	    WebElement TakeAction = driver.findElement(By.xpath("(//button[contains(text(),'Take Action')])[3]"));
	    boolean displays = TakeAction.isDisplayed();
	    System.out.println("Take Action is " + displays);
	    
	    TakeAction.click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement createGoal= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Create Goal']")));
	    if(createGoal.isDisplayed()) {
	    	createGoal.click();
		    WebElement yesButton = driver.findElement(By.xpath("//button[normalize-space()='Yes, Create My Goal']"));
		    yesButton.click();
	    	System.out.println("Create Goal button is displayed");
	    }}
	    
	   
	    

} 

        

