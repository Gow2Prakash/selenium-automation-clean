package miniProject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testng {
	
	    WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        System.out.println("Setting up the Project");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    @BeforeMethod
	    public void launchApp() {
	        driver.get("https://automationexercise.com/");
	    }

	    @Test
	    public void signupTest() throws InterruptedException {
	        driver.findElement(By.linkText("Signup / Login")).click();
	        driver.findElement(By.name("name")).sendKeys("Gowtham");
	        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
	              .sendKeys("gowtham.prakash@indegene.com");
	        driver.findElement(By.xpath("//button[text()='Signup']")).click();
	        List<WebElement> Errormsg = driver.findElements(By.xpath("//p[text()='Email Address already exist!']"));
	        Thread.sleep(3000);
	        
	        
	        if (!Errormsg.isEmpty()){
	        	 System.out.println("Account already exists. Proceeding with Signin...");
	        	
	        	 JavascriptExecutor js = (JavascriptExecutor)driver;
	  	       WebElement webeelement = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
	  	       js.executeScript("arguments[0].scrollIntoView(true);", webeelement);
	             // Perform Signin flow
	             driver.findElement(By.xpath("//input[@data-qa='login-email']"))
	                   .sendKeys("gowtham.prakash@indegene.com");
	             driver.findElement(By.xpath("//input[@data-qa='login-password']"))
	                   .sendKeys("Gowtham@123");
	             driver.findElement(By.xpath("//button[text()='Login']")).click();
	        }
	        else {
	        	System.out.println("Signed up with new Account");
	        	driver.findElement(By.id("uniform-id_gender1")).click();
		        driver.findElement(By.id("password")).sendKeys("Gowtham@123");
		        driver.findElement(By.id("first_name")).sendKeys("Gowtham");
		        driver.findElement(By.id("last_name")).sendKeys("Prakash");
		        driver.findElement(By.id("address1")).sendKeys("Hosur");
		        driver.findElement(By.id("state")).sendKeys("Tamilnadu");
		        driver.findElement(By.id("city")).sendKeys("Krishnagiri");
		        driver.findElement(By.id("zipcode")).sendKeys("635126");
		        driver.findElement(By.id("mobile_number")).sendKeys("8526224614");

		        driver.findElement(By.xpath("//button[text()='Create Account']")).click();
		        // Assertion: Verify account creation
		        String actualText = driver.findElement(By.tagName("b")).getText();
		        Assert.assertEquals(actualText, "Account Created!", "Account creation failed!");
		        driver.findElement(By.linkText("Continue")).click();
	        }
	        
	        
	    }

	    @AfterMethod
	    public void clearCookies() {
	        driver.manage().deleteAllCookies();
	        driver.findElement(By.linkText("Logout")).click();
	        System.out.println("Succesfully loggedout");
	    }

	    @AfterClass
	    public void teardown() {
	        driver.quit();
	        System.out.println("Project execution completed");
	    }
	}


