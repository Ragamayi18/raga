package driverfactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunlibrary.Commonfunction;
import constant.PBconstant;
import uilities.ExcelFileUtil;

public class Driverscript  extends PBconstant{
	String inputpath="D:\\Decemeber_Selenium\\Workspace\\Framwork\\TestinputController.xlsx"	;
	String outputpath="D:\\December_Selenium\\Selenium_Frameworks\\TestOutput\\KeywoordResults.xlsx";
	String TCSheet="TestCases";
	String TSSheet="TestSteps";
	ExtentReports report;
	ExtentTest test;
	File screen;
	@Test
	public void startTest()throws Throwable
	{
		//generate reports folder
		report=new ExtentReports("./Reports/keyword.html");	
		
		//acceesing excel uitl methods
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);	
		boolean res=false;
		String tcres="";
		
		//count no of rows in TCSheet
		int TCcount=xl.rowCount(TCSheet);
		//count no of rows in TSSheet
		int TScount=xl.rowCount(TSSheet);
		for(int i=1;i<=TCcount;i++)
		{
			
			//start test case 
			test=report.startTest("Start Test");	
			//read execute column
			String execute=xl.getCellData(TCSheet, i, 2);
			if(execute.equalsIgnoreCase("Y"))
			{
				
				//read tcid column from TCSheet
				String Tcid=xl.getCellData(TCSheet, i, 0);	
				
				for(int j=1;j<=TScount;j++)
				{
					//read description column from TSSheet
					String Description=xl.getCellData(TSSheet, j, 2);	
					//read tsid from TSSheet
					String Tsid=xl.getCellData(TSSheet, j, 0);
					
					if(Tcid.equalsIgnoreCase(Tsid))
					{
						
						//read keyword column TSSheet
						String keyword=xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							res=Commonfunction.verifyLogin("Admin", "Admin");
							test.log(LogStatus.INFO, Description);
						}
						
						else if(keyword.equalsIgnoreCase("NewBranchCreation"))
						{
							Commonfunction.navigateBranches();
							res=Commonfunction.verifyBranchCreation("madanapalli1", "Hyderabad", "12345", 1, 1, 1);
							test.log(LogStatus.INFO, Description);
						}
						
						
						else if(keyword.equalsIgnoreCase("UpdateBranch"))
						{
							Commonfunction.navigateBranches();
							//res=Commonfunction.v("kadiri1", "kukatpalli");
							test.log(LogStatus.INFO, Description);
						}
						
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res=Commonfunction.verifyLogout();	
							test.log(LogStatus.INFO, Description);
						}
						
						String tsres="";
						if(res)
						{
							test.log(LogStatus.PASS, Description);
							tsres="Pass";
							//if res is true write as pass into results column in TSsheet
							xl.setCelldata(TSSheet, j, 4, tsres, outputpath);	
						}
						
						else
						{
							TakesScreenshot driver;
							//screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(screen, new File("D:\\December_Selenium\\Selenium_Frameworks\\Screens\\"+j+"primus.png"));
							tsres="Fail";
							//if res is false write as Fail into results column in TSsheet
							xl.setCelldata(TSSheet, j, 4, tsres, outputpath);
							test.log(LogStatus.FAIL, Description);
						}
						if(!tsres.equalsIgnoreCase("Fail"))
						{
							tcres=tsres;
						}
					}
					report.endTest(test);
					report.flush();
				}
				//write what tcres is holding
				xl.setCelldata(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as not executed into results column
				xl.setCelldata(TCSheet, i, 3, "Not Executed", outputpath);	
			}
		}
}
}
