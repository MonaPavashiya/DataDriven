package dataDrivenTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTestHardCoded2
{
	WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mS\\Documents\\MonaQAjars\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pwd, String exp) 
	{
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		WebElement userName = driver.findElement(By.xpath("//input[@id='txtUsername']"));
		userName.clear();
		userName.sendKeys(user);
		
		WebElement passWord = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		passWord.clear();
		passWord.sendKeys(pwd);
		
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
		
		String exp_title = "OrangeHRM";
		String act_title = driver.getTitle();	
		
		if(exp.equals("valid")) {
			if(exp_title.equals(act_title))
			{
				driver.findElement(By.xpath("//a[@id='welcome']")).click();
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		String exp_title1 = "OrangeHRM1";
		String act_title1 = driver.getTitle();	
		if(exp.equals("invalid")) 
		{
			if(exp_title1.equals(act_title1))
			{
				driver.findElement(By.xpath("//a[@id='welcome']")).click();
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	}
	
	
	@DataProvider(name="LoginData")
	public String[][] getData()
	{
		 String logindata[][]= {
				                 {"Admin", "admin123", "valid"},
				                 {"Admin", "admn", "invalid"},
				                 {"admn", "admin123","invalid"},
				                 { "addm", "adm", "invalid"}
		                       };
		return logindata;
	}
	
	@AfterClass
	void tearDown() 
	{
		driver.quit();
	}
	
}



