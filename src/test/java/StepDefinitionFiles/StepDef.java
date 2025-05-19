package StepDefinitionFiles;


import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LinkedinPageObjects;

public class StepDef {
	WebDriver driver ;
	Properties data = new Properties();
	String file ="src\\main\\java\\Resources\\excelFileFolder\\MasterSheet.xlsx";
	String path ="src\\main\\java\\Resources\\Logindata.properties";
	LinkedinPageObjects linkedin;
	boolean loginVerify;
	int countlogin;
	int countlogin2;
	int waitTime;
	
	 
	
	@Given("Intial set up for browser")
	public void intial_set_for_browser() throws Exception {
		InputStream input= new FileInputStream(path);
		data.load(input);
		 waitTime=Integer.parseInt(data.getProperty("waitTime")+"000");
		if(data.getProperty("browser").equalsIgnoreCase("chrome")) {
//		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\Resources\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		options.addArguments("--disable-cookies");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		linkedin=new LinkedinPageObjects(driver);
		}
		if(data.getProperty("browser").equalsIgnoreCase("edge")) {
//			System.setProperty("webdriver.edge.driver","src\\main\\java\\Resources\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
	         EdgeOptions op=new EdgeOptions();
	                 op.addArguments("headless");
	                 op.addArguments("--disable-cookies");
	                 op.addArguments("--remote-allow-origins=*");
	                 driver = new EdgeDriver(op);
	                 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
	                 linkedin=new LinkedinPageObjects(driver);
		}
	}

	@Given("^Log in to site \"(.*)\"$")
	public void log_in_to_site(String userNumber) throws InterruptedException,Exception {
		
		countlogin++;
		InputStream input= new FileInputStream(path);
		data.load(input);
		boolean rememberCheckBox ;
//		boolean rememberCheckBoxelemet ;
	
		try {
	 
	  if(userNumber.equalsIgnoreCase("first")) {
		  if(data.getProperty("username"+"1").equalsIgnoreCase(null)||data.getProperty("password"+"1").equalsIgnoreCase(null)||data.getProperty("username"+"1").equalsIgnoreCase("")||data.getProperty("password"+"1").equalsIgnoreCase("")) {
			  System.out.println("Please provide valid credentials");
			  Assert.fail();
		  }
		  driver.get("https://in.linkedin.com/");
		  Thread.sleep(waitTime);
		  linkedin.clickOnSignIn();
//		  Thread.sleep(5000);
	  linkedin.sendUserName(data.getProperty("username"+"1"));
	  linkedin.sendpassword(data.getProperty("password"+"1"));
	  }
	  if(userNumber.equalsIgnoreCase("two")) {
		  if(data.getProperty("username"+"2").equalsIgnoreCase(null)||data.getProperty("password"+"2").equalsIgnoreCase(null)||data.getProperty("username"+"2").equalsIgnoreCase("")||data.getProperty("password"+"2").equalsIgnoreCase("")) {
			  System.out.println("Please provide valid credentials");
				 throw new Exception();
		  }
		  intial_set_for_browser();
		  driver.get("https://in.linkedin.com/");
		  Thread.sleep(waitTime);
		  linkedin.clickOnSignIn();
//		  Thread.sleep(5000);
		  linkedin.sendUserName(data.getProperty("username"+"2"));
		  linkedin.sendpassword(data.getProperty("password"+"2"));
		  }
	  if(userNumber.equalsIgnoreCase("three")) {
		  if(data.getProperty("username"+"3").equalsIgnoreCase(null)||data.getProperty("password"+"3").equalsIgnoreCase(null)||data.getProperty("username"+"3").equalsIgnoreCase("")||data.getProperty("password"+"3").equalsIgnoreCase("")) {
			  System.out.println("Please provide valid credentials");
			  throw new Exception();
		  }
		  intial_set_for_browser();
		  driver.get("https://in.linkedin.com/");
		  Thread.sleep(waitTime);
		  linkedin.clickOnSignIn();
//		  Thread.sleep(5000);
		  linkedin.sendUserName(data.getProperty("username"+"3"));
		  linkedin.sendpassword(data.getProperty("password"+"3"));
		  }
	  try {
		  rememberCheckBox=linkedin.getRemeberMeLoggedInCheckBox();
//		  rememberCheckBoxelemet=linkedin.getRemembercheck().equalsIgnoreCase("true");
		  rememberCheckBox=true;
	  }catch(Exception e) {
		  rememberCheckBox=false;
	  }
	  if(rememberCheckBox) {
		  linkedin.clickOnRemeberMeLoggedInCheckBox();
		  System.out.println("UnCheked the Remeber me logged in CheckBox");
	  }
	  Thread.sleep(waitTime);
	  linkedin.clickOnSignInUsingGivenValues();
//	  Thread.sleep(5000);
	  if(driver.getTitle().toLowerCase().contains("security")||driver.getTitle().toLowerCase().contains("restriction")||driver.getTitle().toString().toLowerCase().contains("verification")) {
			System.out.println(driver.getTitle());
			System.out.println("Linkedin Went to security mode or restricted your account");
			 throw new Exception();
		}
	  try {
		  loginVerify=linkedin.CheckLoginWithProfileTab();
		  loginVerify=true;
	  }catch(Exception e) {
		  loginVerify=false;
		  System.out.println(e);
	  }
	 if(loginVerify) {
		 System.out.println("successfully Loggedin to Linkedin ");
	 }else {
		 System.out.println("Failed to Login to Linkedin");
		 System.out.println(linkedin.getCandidateName());
//		 if(linkedin.getCandidateName().equalsIgnoreCase("Let’s do a quick security check")) {
//			 System.out.println("Linkedin asking : Let’s do a quick security check");
//		 }
		 throw new Exception();
	 }
		 }catch(Exception e) {
			System.out.println(e);
			System.out.println(" Failed to login to Linkedin (Login Flow may change, Run again or contact dev)");
			if(countlogin==1) {
				Assert.fail();
			}
			countlogin2++;
//			 System.out.println(linkedin.getCandidateName());
//			 System.exit(0);
		}
	  
	}

	@When("User get the profile links and checks status and writes info to excel")
	public void user_get_the_profile_links() throws InterruptedException, Exception {
		 
		
		try {
			FileInputStream fs= new FileInputStream(file);	
			Workbook book =new XSSFWorkbook(fs) ; 
		Sheet sheet = book.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		boolean check;
		String status = null;
		String CandidateName=null;
		System.out.println( "Number of Profiles in given Excel sheet : "+rowCount);
		String title;
		String joinDate;
		String degree;
		String location;
		String current_job_title;
		String OverAllExperience;
		Row row;
		Row HeadRow;
		Cell HeadCell;
		Cell HeadCell2;
		Cell HeadCell3;
		Cell HeadCell4;
		Cell HeadCell5;
		Cell HeadCell6;
		Cell HeadCell7;
		Cell HeadCell8;
		Cell cell;
		Cell cell2;
		Cell cell3;
		Cell cell4;
		Cell cell5;
		Cell cell6;
		Cell cell7;
		Cell cell8;
		HeadRow=sheet.getRow(0);
		HeadCell=HeadRow.getCell(1);
		if(HeadCell==null) {
			HeadCell=HeadRow.createCell(1);
		}
		// write the Header value column 1
		HeadCell.setCellValue("Open To Work Badge");
		HeadCell2=HeadRow.getCell(2);
		if(HeadCell2==null) {
			HeadCell2=HeadRow.createCell(2);
		}
		// write the Header value column 2
		HeadCell2.setCellValue("Candidate Name");
		
		HeadCell3=HeadRow.getCell(3);
		if(HeadCell3==null) {
			HeadCell3=HeadRow.createCell(3);
		}
		// write the Header value column 3
		HeadCell3.setCellValue("Designation");
		HeadCell4=HeadRow.getCell(4);
		if(HeadCell4==null) {
			HeadCell4=HeadRow.createCell(4);
		}
		HeadCell4.setCellValue("Join Date");
		
		HeadCell5=HeadRow.getCell(5);
		if(HeadCell5==null) {
			HeadCell5=HeadRow.createCell(5);
		}
		HeadCell5.setCellValue("Verified Degree");
		
		HeadCell6=HeadRow.getCell(6);
		if(HeadCell6==null) {
			HeadCell6=HeadRow.createCell(6);
		}
		HeadCell6.setCellValue("Location");
		
		HeadCell7=HeadRow.getCell(7);
		if(HeadCell7==null) {
			HeadCell7=HeadRow.createCell(7);
		}
		HeadCell7.setCellValue("Current Job Title");
		
		HeadCell8=HeadRow.getCell(8);
		if(HeadCell8==null) {
			HeadCell8=HeadRow.createCell(8);
		}
		HeadCell8.setCellValue("Over All Experience");
		
		for(int i=1;i<=rowCount;i++) {
			String profile=sheet.getRow(i).getCell(0).toString();
			 row= sheet.getRow(i);
			 
			 cell = row.getCell(1);
			 cell2 = row.getCell(2);
			 cell3 = row.getCell(3);
			 cell4 = row.getCell(4);
			 cell5 = row.getCell(5);
			 cell6 = row.getCell(6);
			 cell7 = row.getCell(7);
			 cell8 = row.getCell(8);
			 try {
			driver.get(profile);
			 Thread.sleep(waitTime);
			if(linkedin.getCandidateName().equalsIgnoreCase("Join LinkedIn")) {
				System.out.println("Lost Access for Linkedin");
				break;
			}
			if(driver.getTitle().toLowerCase().contains("security")||driver.getTitle().toLowerCase().contains("restriction")||driver.getTitle().toString().toLowerCase().contains("verification")) {
				System.out.println(driver.getTitle());
				System.out.println("Linkedin Went to security mode or restricted your account");
				break;
			}
			
			 }catch(Exception e){
				 System.out.println("Please provide proper likidin profile link at rown no : "+ (i+1));
				 continue;
			 }
//			Thread.sleep(5000);
			if(i==100) {
				driver.close();
				log_in_to_site("two");
				if(!loginVerify) {
					break;
				}
				if(countlogin2<=1) {
					break;
				}
				 try {
						driver.get(profile);
						 Thread.sleep(waitTime);
						if(linkedin.getCandidateName().equalsIgnoreCase("Join LinkedIn")) {
							System.out.println("Lost Access for Linkedin");
							break;
						}
						if(driver.getTitle().toLowerCase().contains("security")||driver.getTitle().toLowerCase().contains("restriction")||driver.getTitle().toString().toLowerCase().contains("verification")) {
							System.out.println(driver.getTitle());
							System.out.println("Linkedin Went to security mode or restricted your account");
							break;
						}
						
						 }catch(Exception e){
							 System.out.println("Please provide proper likidin profile link at rown no : "+ (i+1));
							 continue;
						 }
			}
			if(i==200) {
				driver.close();
				log_in_to_site("three");
				if(!loginVerify) {
					break;
				}
				if(countlogin2<=1) {
					break;
				}
				 try {
						driver.get(profile);
						 Thread.sleep(waitTime);
						if(linkedin.getCandidateName().equalsIgnoreCase("Join LinkedIn")) {
							System.out.println("Lost Access for Linkedin");
							break;
						}
						if(driver.getTitle().toString().toLowerCase().contains("security")||driver.getTitle().toLowerCase().contains("restriction")||driver.getTitle().toString().toLowerCase().contains("verification")) {
							System.out.println(driver.getTitle());
							System.out.println("Linkedin Went to security mode");
							break;
						}
						
						 }catch(Exception e){
							 System.out.println("Please provide proper likidin profile link at rown no : "+ (i+1));
							 continue;
						 }
			}
			if(i==301) {
				System.out.println("Profile viewing limit 301 is exeeded");
				break;
			}
			try {
				CandidateName=linkedin.getCandidateName();
				 status=linkedin.getDesignation();
				check=linkedin.getOpenToWork();
				check=true;
			}catch(NoSuchElementException se) {
				title=driver.getTitle();
				System.out.println(title +  " failed at profile row number "+ (i+1));
				break;
			} catch(Exception e) {
				check= false;
			}
			if(cell==null) {
			  	cell=row.createCell(1);
		  } if(cell2==null) {
			  	cell2=row.createCell(2);
		  }
		  if(cell3==null) {
			  	cell3=row.createCell(3);
		  }
		  if(cell4==null) {
			  	cell4=row.createCell(4);
		  }
		  if(cell5==null) {
			  	cell5=row.createCell(5);
		  }
		  if(cell6==null) {
			  	cell6=row.createCell(6);
		  }
		  if(cell7==null) {
			  	cell7=row.createCell(7);
		  }
		  if(cell8==null) {
			  	cell8=row.createCell(8);
		  }
		  if(cell !=null) {
			    cell.setCellValue("");
		  }
		  if(cell2 !=null) {
			    cell2.setCellValue("");
		  }
		  if(cell3 !=null) {
			    cell3.setCellValue("");
		  }
		  if(cell4 !=null) {
			    cell4.setCellValue("");
		  }
		  if(cell5 !=null) {
			    cell5.setCellValue("");
		  }
		  if(cell6 !=null) {
			    cell6.setCellValue("");
		  }
		  if(cell7 !=null) {
			    cell7.setCellValue("");
		  }
		  if(cell8 !=null) {
			    cell8.setCellValue("");
		  }
		  try {
		  linkedin.ClickOnHeader();
		  }catch(Exception e) {
			  System.out.println(" Not able to click on Candidate name to check ");
			  joinDate="Need to check Manually";
			  continue;
		  }

		  try {
		  joinDate=linkedin.getJoinedDate();
		  }catch(Exception e) {
			  joinDate="Need to check Manually";
		  }
		  try {
			  Thread.sleep(waitTime);
		  linkedin.ClickOnClose();
		  }catch(Exception e) {
			  System.out.println(e+ "candidate join date popup is not opened");
			  continue;
		  }
		  try {
			  degree= linkedin.getProfileDegree(); 
		  }catch(Exception e) {
			  degree= "No degree tag" ;
		  }
		  try {
			  location= linkedin.getProfileLocation(); 
		  }catch(Exception e) {
			  location= "Location not updated" ;
		  }
		  try {
			  current_job_title= linkedin.getPresentCompany(); 
		  
		  }
		  catch(Exception e) {
			  try {
				  current_job_title= linkedin.getPresentCompany2();
				  }catch(Exception am) {
					  current_job_title= "No Title available/ check manually" ;
				  }
			 
		  }
		  try {
			  
			  List<String> explist = new ArrayList<>();
			  for(WebElement element : linkedin.GetOverAllExperience() ) {
				  if(element.getText().matches(".*\\d+.*")) {
				  explist.add(element.getText());
//				  System.out.println(element.getText());
				  }
//				  System.out.println(element.getText());
			  }
			  OverAllExperience=getOverAllExperience(explist);
			  
		  }catch(Exception e) {
//			  System.out.println(e);
			  OverAllExperience="Failed to get Experience/ check manually ";
		  }
		  Thread.sleep(waitTime);

			  if(check || CandidateName.toLowerCase().contains("open to work")) {
				  System.out.println(profile+" Profile has Open to Work Tag "+" at row"+ i);
				  		cell.setCellValue("true");
				  		cell2.setCellValue(CandidateName);
				  		cell3.setCellValue(status);
				  		cell4.setCellValue(joinDate);
				  		cell5.setCellValue(degree);
				  		cell6.setCellValue(location);
				  		cell7.setCellValue(current_job_title);
				  		cell8.setCellValue(OverAllExperience);
			  }else  {
				  System.out.println(profile+" Profile do not have Open to work Tag "+" at row"+ i);
				  		cell.setCellValue("false");
				  		cell2.setCellValue(CandidateName);
				  		cell3.setCellValue(status);
				  		cell4.setCellValue(joinDate);
				  		cell5.setCellValue(degree);
				  		cell6.setCellValue(location);
				  		cell7.setCellValue(current_job_title);
				  		cell8.setCellValue(OverAllExperience);
			  }
			  
				}

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		sheet.autoSizeColumn(8);
		FileOutputStream out= new FileOutputStream(file);
		book.write(out);
		Thread.sleep(5000);
		book.close();
		out.close();
		fs.close();
	}catch(Exception e) {
		System.out.println(e);
		System.out.println(" Reading and writing of excel is having some issues due to linkedin Access");

	}

		}
	
	
	public static String getOverAllExperience(List<String> exp) {
		double overAllexp = 0;
		for(String temp: exp) {
			int seperateExp=temp.indexOf("·");
			int lengh=temp.length();
			String expFromGiven=temp.substring(seperateExp+1,lengh).trim();
			
			if(expFromGiven.contains(" ")) {
//				System.out.println("yes");
				expFromGiven=expFromGiven.replace(" ", "");
			}
			if(!expFromGiven.matches(".*\\d+.*")) {
				continue;
			}
//			expFromGiven=expFromGiven.replaceAll("[^0-9]", "");
//			System.out.println(expFromGiven.indexOf("mo"));
			if(expFromGiven.contains("yrs")) {
//				System.out.println("yes");
				expFromGiven=expFromGiven.replace("yrs", ".");
			}else if(expFromGiven.contains("yr")) {
//				System.out.println("yes");
				expFromGiven=expFromGiven.replace("yr", ".");
			}
			if(!expFromGiven.contains(".")) {
				expFromGiven="0."+expFromGiven;
			}
			int a=expFromGiven.indexOf(".");
			int b=expFromGiven.indexOf("m");
//			System.out.println(b-a);
			if((b-a)<3) {
				expFromGiven=expFromGiven.replace(".", ".0");
			}
			
			
			if(expFromGiven.contains("mos")) {
//				System.out.println("yes");
				expFromGiven=expFromGiven.replace("mos", "");
			}else if(expFromGiven.contains("mo")) {
//				System.out.println("yes");
				expFromGiven=expFromGiven.replace("mo", "");
			}
			
			double covert=Double.parseDouble(expFromGiven.trim());
			overAllexp=overAllexp+covert;
//			System.out.println(overAllexp);
//			System.out.println(expFromGiven.trim());
		}
		int years = (int) overAllexp;
        double decimalMonths = overAllexp - years;
        int months = (int) Math.round(decimalMonths * 100);

        // Adjust years and months
        years += months / 12;
//        System.out.println(years);
        months = months % 12;
//        System.out.println(months);

        // Format the result
        String result = String.format("%d.%02d", years, months);
//        System.out.println("Converted value: " + result);
		
		return result;
	}

	@Then("Tear Down")
	public void tear_down() {
	 driver.quit();
	}


}
