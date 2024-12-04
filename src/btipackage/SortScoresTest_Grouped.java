package btipackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortScoresTest_Grouped {

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
            emailbox.sendKeys("qatester+12@glintinc.com");

            WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/glint-session-root/div/glint-session-auth/form/section/footer/button")));
            continueButton.click();

            // companyID
            WebElement CompanyID = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clientUuid")));
            CompanyID.sendKeys("qa20191129_1");

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
                WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(130));
                WebElement search = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class, 'input ng-pristine')]")));
                search.sendKeys("Nudges V1 - R31 Live");
            } catch (TimeoutException e) {
                System.out.println("unable to locate the element");
            }

            List<WebElement> list = driver.findElements(By.xpath("//ul[@id='menu']/li"));
            for (WebElement options : list) {
                if (options.getText().equalsIgnoreCase("Nudges V1 - R31 Live")) {
                    options.click();
                    break;
                }
            }
	}
	    

	    @Test(priority = 2)
	    public void SelectTeam() throws InterruptedException {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Scroll down incrementally
	        for (int i = 0; i < 5; i++) { // Adjust the number of iterations based on the page length
	            js.executeScript("window.scrollBy(0, 5000);"); // Scroll down by 1000 pixels
	            Thread.sleep(1000); // Wait for 1 second between scrolls
	        }
	        
	           try {
	            WebElement team = driver.findElement(By.xpath("//body[1]/glint-root[1]/div[1]/div[2]/div[1]/main[1]/div[1]/glint-dashboard[1]/glint-report-detail[1]/div[1]/div[1]/div[1]/section[2]/glint-report-section[8]/glint-scores-breakdown-by-attribute[1]/vg-section-layout[1]/div[1]/vg-layout[1]/vg-layout-area[1]/div[1]/vg-layout-cell[1]/vg-with-layout-area-restriction[1]/div[1]/div[1]/glint-scores-breakdown-table[1]/div[1]/glint-scores-breakdown-table-row-group[4]/glint-scores-breakdown-table-row[1]/div[2]/em[1]"));
	            team.click();
	        }catch(Exception e) {
	        	System.out.println("No such element exception");
	        }
	    }
	    

	}


