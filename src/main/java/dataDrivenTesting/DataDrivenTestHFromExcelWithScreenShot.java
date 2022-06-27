package dataDrivenTesting;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataDrivenTestHFromExcelWithScreenShot
{
	WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mS\\Documents\\MonaQAjars\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
	}
	
	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pwd, String exp) throws IOException 
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
		
		System.out.println(user + pwd + exp);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\Users\\mS\\Documents\\Mona_Practice_QA\\TestNGDataDriven"
				+ "\\ScreenShots\\" + user + ".jpg"));
	
	}
	
	
	@DataProvider(name="LoginData")
	public Object[][] getData()
	{
		Object[][] testObjArray = null;
		try {
			testObjArray = ExcelUtility.getTableArray("C:\\Users\\mS\\Documents\\Mona_QA material\\LoginData.xlsx", "Sheet1");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return testObjArray;
	}
	
	
	
	@AfterClass
	void tearDown() 
	{
		driver.quit();
	}
	
}



