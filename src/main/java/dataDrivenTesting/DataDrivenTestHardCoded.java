package dataDrivenTesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTestHardCoded
{
	WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mS\\Documents\\MonaQAjars\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pwd, String exp) 
	{
		System.out.println(user + pwd + exp);
		
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
		driver.close();
	}
	
}



