package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinPageObjects {
	
	private WebDriver driver;
	private WebDriverWait driverwait;
	public LinkedinPageObjects(WebDriver driver) {
		this.driver=driver;
		driverwait= new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	By user=By.id("username");
	
	public void sendUserName(String name) {
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(user)).sendKeys(name);
	}

	By pass=By.id("password");
	
	public void sendpassword(String password) {
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(pass)).sendKeys(password);
	}
	
	By signIn = By.xpath("//a[contains(text(),'Sign in')]");
	public void clickOnSignIn() {
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(signIn)).click();
	}
	
	By signIncredentials = By.xpath("//button[contains(text(),'Sign in')]");
	public void clickOnSignInUsingGivenValues() {
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(signIncredentials)).click();
	}
	By role = By.xpath("//*[@class='text-body-medium break-words']");
	public String getDesignation() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(role)).getText().toString();
	}
	By opentowork=By.xpath("//strong[contains(text(),'Open to work')]");
	public boolean getOpenToWork() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(opentowork)).isDisplayed();
	}
	
	By profileName = By.xpath("//h1");
	public String getCandidateName() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(profileName)).getText().toString();
	}
	public void ClickOnHeader() {
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(profileName)).click();
	}
	By remeber= By.xpath("//*[contains(text(),'Keep me logged in')]");
	public boolean getRemeberMeLoggedInCheckBox() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(remeber)).isDisplayed();
	}
	public void clickOnRemeberMeLoggedInCheckBox() {
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(remeber)).click();
	}
	By rememberCheck= By.id("rememberMeOptIn-checkbox");
	public String getRemembercheck() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(rememberCheck)).getAttribute("checked value");
		
	}
	By logincheck=By.xpath("//*[@class=\"profile-card-member-details\"]");
	public boolean CheckLoginWithProfileTab() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(logincheck)).isDisplayed();
	}
	By frame=By.xpath("//div[@role='dialog']");
	public WebElement profileFrame() {
		return driver.findElement(frame);
	}
	By joindate= By.xpath("//div[@class=\"artdeco-modal__content ember-view\"]/section[1]/ul/li[1]/p/span[1]/span");
	public String getJoinedDate() {
//		JavascriptExecutor js= (JavascriptExecutor)driver;
//		return js.executeScript("return arguments[0].innerText;",joindate ).toString();
//		return js.executeScript("arguments[0].setAttribute('aria-hidden','false;')",joindate ).toString();

		return driverwait.until(ExpectedConditions.presenceOfElementLocated(joindate)).getText();
	}
	By dismissframe= By.xpath("//button[@aria-label=\"Dismiss\"]");
	public void ClickOnClose() {
		driverwait.until(ExpectedConditions.visibilityOfElementLocated(dismissframe)).click();
	}
	
	By degree = By.xpath("//h1//ancestor::span/following-sibling::span[contains(@class,'distance-badge')]");
	public String getProfileDegree() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(degree)).getText().toString();
	}
	
	By location = By.xpath("//div[contains(@class,'ph5')]/div[@class='mt2 relative']/div[2]/span[contains(@class,'break-words')]");
	public String getProfileLocation() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(location)).getText().toString();
	}
	By current_job_title = By.xpath("//*[@id='experience']//parent::section/div[3]/ul/li[1]/div/div[2]/div/div/div/div/div/div/span[1]");
	public String getPresentCompany() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(current_job_title)).getText().toString();
	}
	By current_job_title2 = By.xpath("//*[@id='experience']//parent::section/div[3]/ul/li[1]/div/div[2]/div[2]/ul//li[1]/div/div[2]/div/a/div/div/div/div/span[1]");
	public String getPresentCompany2() {
		return driverwait.until(ExpectedConditions.visibilityOfElementLocated(current_job_title2)).getText().toString();
	}
	
	By OverallExperience = By.xpath("//*[@id='experience']//parent::section/div[3]/ul/li//span[contains(@class,'pvs-entity__caption-wrapper')]");
	public List<WebElement> GetOverAllExperience()
	{
		List<WebElement> temp = driver.findElements(OverallExperience);
		return temp;
	}
	
	//Another role of current role xpath  //*[@id='experience']//parent::section/div[3]/ul/li[1]/div/div[2]/div[2]/ul//li[1]/div/div[2]/div/a/div/div/div/div/span[1]
	
	// current role xpath //*[@id="experience"]//parent::section/div[3]/ul/li[1]/div/div[2]/div/div/div/div/div/div/span[1]
	
	
	
	//   //div[@role="dialog"]//p[@class="text-body-small"]/span/span[1]
	//   //span[@class="tvm__text tvm__text--neutral"][1]
	// //div[@class="artdeco-modal__content ember-view"]/section[1]/ul/li[1]/p/span[1]/span
	// about section xpath
	//   (//div[@id='about']//following-sibling::div)[2]/div/div/div/span
	
	// Location Xpath 
	//div[@class="ph5 pb5"]/div[@class="mt2 relative"]/div[2]/span[contains(@class,'break-words')]
	
	// experience header xpath //section//*[contains(@class,'pvs-header')]/*[contains(text(),'Experience')][1]
	// experience company temp //*[@id="experience"]//parent::section/div[3]/ul/li/div/div[2]/div/div/span
	//*[@id="experience"]//parent::section/div[3]/ul/li/div/div[2]/div/div/span[1]/span
	
	// experience tabs   //*[@id="experience"]//parent::section/div[3]/ul/li/div/div[2]/div/div/span[2]
	//present company xpath   //*[@id="experience"]//parent::section/div[3]/ul/li[1]/div/div[2]/div/div/span[1]
	
	//degree xpath   //h1//ancestor::span/following-sibling::span[2]/span[2]
	
	// experience tabs new xpath //*[@id='experience']//parent::section/div[3]/ul/li//span[contains(@class,'pvs-entity__caption-wrapper')]
	
}
