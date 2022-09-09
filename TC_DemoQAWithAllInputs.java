package scripts;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import contexts.DemoQAContext;
import testBase.BasePageObject;

public class TC_DemoQAWithAllInputs extends BasePageObject {

	DemoQAContext demoQAContext = new DemoQAContext();

	@BeforeMethod @Parameters({@Optional("http://demoqa.taas.infor.com")"url","username","password"
	})

	public void login(String url, String username, String password) { throws Exception
		log().info(">>>>Data mapping Initiated <<<<");
		//load property file steps
		String propFile = System.getProperty("user.dir") + "\\src\\main\\java\\data\\TC_DemoQA.properties";
		File fileType = new File(propFile);
		FileInputStream fis = new FileInputStream(fileType);
		Properties propActions = new Properties();
		propActions.load(fis);
		
		
		log().info(">>>>Login Initiated <<<<");
		DemoQA loginPg = initElements(DemoQA.class);
		getDriver().get(url);
		loginPg.username.sendkeys(username);
		loginPg.password.sendkeys(password);
		loginPg.submit.click();
		pause(2);
		Assertions.assertThat(loginPg.loginText.getText()).as("Login is failed").containsOnlyOnce("Login Page");
		log().info(">>>>Login Completed <<<<");
	}

	@Test
	public void registration() {
		DemoQA demoPageWithInputsPg = initElements(DemoQA.class);
		// REGISTRATION: STEP1
		demoPageWithInputsPg.demoPageWithAllInputs.click();
		demoPageWithInputsPg.emailId.sendkeys("abc@infor.com");
		demoPageWithInputsPg.passwordInput.sendkeys("password123");
		demoPageWithInputsPg.rememberMe.click();
		pause(1);
		executor().executeScript("arguments[0].scrollIntoView(true);", demoPageWithInputsPg.submitButton);
		demoPageWithInputsPg.submitButton.click();
		pause(3);
		executor().executeScript("arguments[0].scrollIntoView(true);", demoPageWithInputsPg.newTabWindow);
		demoPageWithInputsPg.inforTaaS.click();
		pause(2);
		getDriver().navigate().back();
		pause(2);

//		demoPageWithInputsPg.inforTaaSDemoHome.click();
//		executor.executeScript("Arguments[0].scrollIntoView(true);", demoPageWithInputsPg.newTabWindow);
//		demoPageWithInputsPg.newTabWindow.click();

		// Window Handles
//		String parentWindow = getDriver().getWindowHandle(); // It will return current WINDOW id
//		
//
//		log().info("Parent window id is " + parentWindow);
//		log().info("Parent window title is " + getDriver().getTitle());
//
//		Set<string> windowHandles = getDriver().getWindowHandles(); // SET: will store unique values
//		List<string> windowHandleList = new ArrayList<>(windowHandles);
//
//		log().info("Window IDs " + windowHandlesList);
//		log().info("Window IDs " + windowHandlesList.size());
//		pause(15);
//
//		getDriver().switchTo().window(windowHandlesList.get(1)); // switching to new window that is opened by link
//
//		log().info("Window is switched to child " + getDriver().getTitle());
//		demoPageWithInputsPg.emailId.sendKeys("almira.gorgonia@infor.com");
//		pause(2);
//		demoPageWithInputsPg.passwordInput.sendKeys("welcome123");
//		pause(2);
//		demoPageWithInputsPg.done.click();
//		pause(2);
//		getDriver().switchTo().window(windowHandlesList.get(0));
//		log().info("Window title " + getDriver().getTitle())

//		demoPageWithInputsPg.popUpWindow.click();
//		Set<String> windowHandles2.size();
//		log().info("No of windows opened is " + noOfWindows); 
//		
//		switchToWindow("Infor TaaS: Pop Up");
//		pause(3);
//		demoPageWithInputsPg.emailId.sendKeys("abc@infor.com");
//		pause(3);
//		demoPageWithInputsPg.passwordInput.sendKeys("password123");
//		pause(3);
//		demoPageWithInputsPg.done.click();
//		pause(3);
//		switchToWindow("Infor TaaS Demo");.
//		log().info("current window title is " + getDriver().getTitle());
		pause(5);
		demoPageWithInputsPg.alertButton.click();
		Alert alert = getDriver().switchTo().alert();
		pause(5);
		alert.accept();
//		alert.dismiss();
		pause(5);

	}

	@AfterMethod
	public void browserClose() {
		// Logout
		getDriver().close();
	}
}
