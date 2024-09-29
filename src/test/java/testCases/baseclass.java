package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class baseclass  {

	public WebDriver driver;
    public Properties p;
	@BeforeClass(groups = {"sanity","regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		switch (br.toLowerCase()) {
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default :System.out.println("invalid browser...");return;
		}

		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appurl"));
		// driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"sanity","regression","Master"})
	public void teraDown() throws InterruptedException {
		Thread.sleep(5000);
		 driver.quit();
	}

	public String randomstring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomPwd() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + generatedNumber);
	}

}
