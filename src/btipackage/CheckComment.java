package btipackage;

import static org.testng.Assert.assertTrue;

import java.io.File;
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
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckComment {

    WebDriver driver;

    @BeforeTest
    public void launchApplication() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://app.vgqa.glint.cloud.dev.microsoft/session/auth");
        driver.manage().window().maximize();
    }

    @Test(priority = 0)
    public void Testlogin() throws InterruptedException {

        // email
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement emailbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        emailbox.sendKeys("qatester+87@glintinc.com");

        WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-auth/form/section/footer/button")));
        continueButton.click();

        // companyID
        WebElement CompanyID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientUuid")));
        CompanyID.sendKeys("qa20220825");

        WebElement continueButton2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-client/form/section/footer/button")));
        continueButton2.click();

        // Password
        WebElement Password = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        Password.sendKeys("Dem0@pass2");

        Thread.sleep(3000);
        WebElement continueButton3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
        continueButton3.click();
    }

    @Test(priority = 1)
    public void switchProgram() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        WebElement switchProgram = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='menuButton glintButton btnSlim']")));
        switchProgram.click();

        try {
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(120));
            WebElement search = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'input ng-pristine')]")));
            search.sendKeys("BTI Rollup Hierarchy");
        } catch (TimeoutException e) {
            System.out.println("unable to locate the element");
        }

        List<WebElement> list = driver.findElements(By.xpath("//ul[@id='menu']/li"));
        for (WebElement options : list) {
            if (options.getText().equalsIgnoreCase("BTI Rollup Hierarchy")) {
                options.click();
                break;
            }
        }
    }

    @Test(priority = 2)
    public void Managerdropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement Managerdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//glint-dropdown[@buttonclass='glintButton']")));
        
        // Scroll to the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", Managerdropdown);
        
        // Click the dropdown
        Managerdropdown.click();
        
        // Locate the list of managers
        List<WebElement> ManagerList = driver.findElements(By.xpath("//ul[@class='dropdownMenuList']/li"));
        
        // Attempt to click on the desired manager
        boolean clicked = false;
        int attempts = 0;
        
        while (!clicked && attempts < 3) { // Retry up to 3 times
            try {
                for (WebElement Manager : ManagerList) {
                    if (Manager.getText().equals("Qatest2 User2's Team")) {
                        Manager.click();
                        clicked = true;
                        break;
                    }
                }
            } catch (StaleElementReferenceException e) {
                // Re-locate the dropdown and retry
                Managerdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//glint-dropdown[@buttonclass='glintButton']")));
                ManagerList = driver.findElements(By.xpath("//ul[@class='dropdownMenuList']/li"));
            }
            attempts++;
        }
    }

        
    
    
    @Test(priority = 3)
    public void ViewResults() {
		WebElement viewResults = driver.findElement(By.xpath("//button[normalize-space()='View Results']"));
		viewResults.click();

    }

    
   
        
        @Test(priority=4)
        public void Comments() {
        	
        	    try {
        	        WebElement comment;
        	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        	        comment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Comments']")));
        	        
        	        JavascriptExecutor js = (JavascriptExecutor) driver;
        	        js.executeScript("window.scrollBy(0, 800);");
        	        
        	        boolean displayed = comment.isDisplayed();
        	        
        	        if (displayed) {
        	            System.out.println("Comment section is displayed");
        	        }
        	        
        	    } catch (TimeoutException | StaleElementReferenceException e) {
        	        // Handle specific exceptions if needed
        	        e.printStackTrace();
        	    }
        	}} 
        
      




        
  




        		
   

