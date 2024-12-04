package btipackage;

import java.io.File;
import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class Manager_Concierge_Status {


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
        public void switchProgram() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
            WebElement switchProgram = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='menuButton glintButton btnSlim']")));
            switchProgram.click();

            try {
                WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(120));
                WebElement search = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'input ng-pristine')]")));
                search.sendKeys("Concierge Visiblity Live");
            } catch (TimeoutException e) {
                System.out.println("unable to locate the element");
            }

            List<WebElement> list = driver.findElements(By.xpath("//ul[@id='menu']/li"));
            for (WebElement options : list) {
                if (options.getText().equalsIgnoreCase("Concierge Visiblity Live")) {
                    options.click();
                    break;
                }

            }
        }
        
        
        @Test(priority = 2)
        public void checkStatus() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            
            try {
                WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='What have you done recently to make progress on Accountability and For testing purpose?']")));
                System.out.println("Status text: " + status.getText());
                if (status.getText().equals("What have you done recently to make progress on Accountability and For testing purpose?")) {
                    System.out.println("status changed");
                } else {
                    System.out.println("Status text did not match.");
                }
                
                WebElement status2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'You have committed to 1 focus area')]")));
                System.out.println("Status2 text: " + status2.getText());
                if (status2.getText().equals("You have committed to 1 focus area and 1 action item.")) {
                    System.out.println("status 2 changed");
                } else {
                    System.out.println("Status2 text did not match.");
                }
                
            } catch (TimeoutException e) {
                System.out.println("Element not found within the given time.");
            } catch (Exception e) {
                e.printStackTrace();  // Print any other exception
            }
        }
        
        @Test(priority = 3)
        public void PeopleGoalCreationPage() {
    		WebElement viewResults = driver.findElement(By.xpath("//button[normalize-space()='View Results']"));
    		JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", viewResults);
    		viewResults.click();
    		
        }
        
        @Test(priority = 4)
        public void ConversationKit() throws InterruptedException {
        	WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(60));
        	WebElement ConversationKitbutton = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//glint-broader-team-summary-manager-concierge-step[2]//div[1]//div[2]//div[2]//button[1]")));
        	ConversationKitbutton.click();
            TimeUnit.SECONDS.sleep(20);
            
           
            File fileLocation = new File("C:\\Users\\vpaulp01\\Downloads");
            File [] totalFiles = fileLocation.listFiles();
            
            for(File file: totalFiles) {
            	if(file.getName().equals("viva_glint_report_Qatest65_User65's_Team_2024Aug23.zip"));
            	System.out.println("file downloaded");
            	break;
            }
        }
}
            