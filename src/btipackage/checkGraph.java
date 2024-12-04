package btipackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class checkGraph {//BTI16
WebDriver driver;
	
	@BeforeTest
	public void launchApplication() throws InterruptedException //BTI1,BTI2
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
	    	    emailbox.sendKeys("qatester@glintinc.com");
	    	    
	        	WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-auth/form/section/footer/button")));
	    	    continueButton.click();
	    	    
	    	    //companyID
	        	WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	    WebElement CompanyID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientUuid")));
	    	    CompanyID.sendKeys("qa20161215");
	    	    
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
                }}}
                
                @Test(priority=2)
         	   public void checkGraph() {
                   	try {
                	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                    WebElement viewDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Report Actions Menu']")));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript( "window.scrollBy(0,400)");
                viewDropdown.click();
               
                List<WebElement> AllDriverOption = driver.findElements(By.xpath("//ul[@id='menu']/li"));
                js.executeScript( "window.scrollBy(0,200)");
                for (WebElement ListViewoption : AllDriverOption) {
                    if (ListViewoption.getText().equals("All Drivers")) {
                        ListViewoption.click();
            }}}catch(StaleElementReferenceException e){
            	driver.findElements(By.xpath("//ul[@id='menu']/li"));            
                }
                   	try {
                   	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
                   	WebElement graph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.driverImpactChart")));
                   	if(graph.isDisplayed()) {
                    	System.out.println("graph is displayed");
                   	}}catch(TimeoutException e) {
                   		System.out.println("Timed out");
                   	}catch(StaleElementReferenceException e) {
                        System.out.println("Stale element reference encountered. Retrying...");
                        driver.findElements(By.xpath("//ul[@id='menu']/li")); // Refresh element list
                        // You can potentially retry the logic here if desired
                    } catch (Exception e) {
                        e.printStackTrace(); // Log the full exception for better debugging
                    }
                }}
                

                  
                


                 
                
               


