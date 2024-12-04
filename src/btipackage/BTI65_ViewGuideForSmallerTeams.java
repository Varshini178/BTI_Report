package btipackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BTI65_ViewGuideForSmallerTeams {
	
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
	    	    emailbox.sendKeys("qatester+100@glintinc.com");
	    	    
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
        public void checkViewGuideForSmallerTeams() {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        	WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='View Guide for Smaller Teams']")));

        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript("window.scrollBy(0, 700)");

        	boolean buttonDisplayed = button.isDisplayed();
        	System.out.println("View Guide for smaller teams displayed");
        }
        


}
