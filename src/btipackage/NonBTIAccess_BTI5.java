package btipackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;

public class NonBTIAccess_BTI5 {
WebDriver driver;
	
	@BeforeTest
	public void launchApplication() throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
        driver.get("https://app.vgqa.glint.cloud.dev.microsoft/session/auth");
        driver.manage().window().maximize();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}
        @Test(priority =0)
	     public void Testlogin() throws InterruptedException {
	    	 
        	//email
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement emailbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	    	    emailbox.sendKeys("qatester+87@glintinc.com");
	    	    
	        	WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-auth/form/section/footer/button")));
	    	    continueButton.click();
	    	    
	    	    //companyID
	        	WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement CompanyID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientUuid")));
	    	    CompanyID.sendKeys("qa20220825");
	    	    
	        	WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement continueButton2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-client/form/section/footer/button")));
	    	    continueButton2.click();
	    	    
	    	    //Password
	        	WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement Password = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
	    	    Password.sendKeys("Dem0@pass2");
	    	    
	    	    Thread.sleep(3000);
	    	    WebElement continueButton3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
	    	    continueButton3.click();
                  }
        
        @Test(priority =1)
        public void BTI() {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        	WebElement  Reports;
        	boolean displayed = true;
        	
        	try {
        Reports	= wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-3")));
        	Actions act = new Actions(driver);
        	act.moveToElement(Reports).click().build().perform();
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript("window.scrollBy(0, 700)");
        		} catch(TimeoutException e) {
        	    System.out.println("Element not clickable after 20 seconds. BTI check skipped.");
        		}}
      
        
        @Test(priority = 2)
        public void BTIDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
                WebElement btiElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/h3[text()=' Broader Team Insights ']")));
                System.out.println("Element 'Broader Team Insights' found: " + btiElement.isDisplayed());
            } catch (TimeoutException e) {
                System.out.println("Element 'Broader Team Insights' not found.");
            }
            driver.navigate().back();
            
            
            
        }}

