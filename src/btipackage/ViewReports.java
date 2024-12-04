package btipackage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ViewReports {
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
        public void switchProgram() {
        	
        	//click switch program dropdown
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
            WebElement switchProgram = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//glint-survey-selector/glint-dropdown/div/div/div")));
            switchProgram.click();

            try {
                WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class, 'input ng-pristine')]")));
                search.sendKeys("BTI Rollup");
            } catch (TimeoutException e) {
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
        public void Managerdropdown() throws InterruptedException {
        	
        	// scroll down
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
            WebElement scrolldown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

            //select userdropdown
            WebElement selectUser;
            try {
                selectUser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//glint-broader-team-summary-cta/div/div[2]/glint-dropdown/div/div/div ")));
                selectUser.click();
            }
             catch (TimeoutException e) 
            {
                System.out.println("Search element not found after waiting for 120 seconds for select user");
             }
            
             
            try {
                WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement  users = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='dropdownMenuList']/li")));

            List<WebElement> userlist = driver.findElements(By.xpath("//ul[@class='dropdownMenuList']/li"));
            for(WebElement user:driver.findElements(By.xpath("//ul[@class='dropdownMenuList']/li"))) {
                if(user.getText().equals("Qatest44 User44's Team")) {
            		user.click();
            	
            	}}}
            	catch(StaleElementReferenceException e) {
            	}
            
                WebElement viewResultbtn = driver.findElement(By.xpath("//button[contains(@class,'ctaButton ')]"));
                viewResultbtn.click();
            	}
      

        @Test(priority = 3)//BTI12
        public void BTIReportTitle() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Ensure element is present

            WebElement title= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main/div[1]/glint-engagement-report/glint-report-detail/section[2]/glint-report-header/div[1]/div[1]/div[1]/h1")));
            String reportTitle = title.getText();
            System.out.println(reportTitle);
            if(title.getText().equals("Qatest44 User44's Team")) {
            	System.out.println("Respective team name is displayed as title");
            }}
            		
        @Test(priority = 4)
        public void SectionsofBTI() {//BTI13
        	
           //test survyoverview
        	WebElement surveyOverview;
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             
             try {
             surveyOverview= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Survey Overview']")));
             JavascriptExecutor js = (JavascriptExecutor) driver;
         	js.executeScript("window.scrollBy(0, 200)");
           if(surveyOverview.isDisplayed()){
            System.out.println("Survey Overview  is present");
            }}
             catch(TimeoutException e){
            	 System.out.println("Element is not found");
             }

             //strength and opportunities
             WebElement StrengthsOpportunities = driver.findElement(By.xpath("//h2[text()=' Strengths and Opportunities ']"));
             JavascriptExecutor js1 = (JavascriptExecutor)driver;
             js1.executeScript("window.scrollBy(0, 300)");
             if(StrengthsOpportunities.isDisplayed()){
                 System.out.println("Strengths and Opportunities is present");
             
             
             //scores
            WebElement Scores = driver.findElement(By.xpath("//span[text()=' Score ']"));
             JavascriptExecutor js2 = (JavascriptExecutor)driver;
             js2.executeScript("window.scrollBy(0, 400)");
             if(Scores.isDisplayed()) {
            	 System.out.println("Score section is present");
             }
             
             //questions
             WebElement Questions = driver.findElement(By.xpath("//span[text()=' Question ']"));
            if(Questions.isDisplayed()) {
            	   System.out.println("Questions section is present");
               }}
               driver.navigate().back();
        }
             
             @Test(priority = 4)//BTI10
            public void checkManagerdropdown() {
            	 WebElement Managerdropdown;
            	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            	 Managerdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//vg-icon[@class='downIcon ng-star-inserted'])[3]")));
            	 JavascriptExecutor js = (JavascriptExecutor) driver;
                 js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
                 if(Managerdropdown.isDisplayed()) {
                	 System.out.println("Manager dropdown should be visible");
                 }
             }
        }

        


            

       
	    

        

         	          
                    	 
                    	 
  






	    	    		
	    	    	

        
        
        
        



