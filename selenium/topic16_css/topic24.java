package topic16_css;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic24 {

	WebDriver driver;
	Select select;
	Random rand;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		rand = new Random();
		
	}

	@Test
	public void TC_01_Default_Dropdown() {
		String emailAddress = "dungntt" + rand.nextInt(9999) + "@gmail.com";
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		//Input firstname, lastname
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Joe");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Biden");
		
		//Check dropdown day
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText("13");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");
		Assert.assertFalse(select.isMultiple());
		
		//Check dropdown month
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("March");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "March");
		Assert.assertFalse(select.isMultiple());
		
		//Check dropdown year
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1942");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1942");
		Assert.assertFalse(select.isMultiple());		
		
		//Input email
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		
		//Input company name
		driver.findElement(By.cssSelector("input#Company")).sendKeys("White House");
		
		//Input password 
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		//Click register button
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		//Verify text after register
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		//Click my account
		driver.findElement(By.xpath("//a[text()='My account']")).click();
	
		//Login again
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		//Verify information
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), "Joe");
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), "Biden");
		
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "March");	
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1942");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), "White House");
	
	}
	
	public void sleepInSecond(long timeInSecond) {
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}