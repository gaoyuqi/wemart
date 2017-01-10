package cn.wemart.executeTest;


import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.ScenarioManagment.Scenario;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;

@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteScenarioManagmentTest {
	@Test
	public void createScenario(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		Scenario createScenario = new Scenario("post");
		Object[][] keyValueList = new Object[][]{
				{"scenType","1"},
				{"name","我的app场景"},
				{"params","{}"}
				};
		for(int j=0;j<mobile.length;j++){
			String returnValue = createScenario.test(mobile[j],password[j],sellerId[j], keyValueList);
			System.out.println(createScenario.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("创建场景成功！");
			} else {
				Reporter.log("创建场景失败！\n Response：" + createScenario.response);
			}
			Reporter.log(getCurrent.Time());
		}
		
	}
	
	@Test
	public void getScenario(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		Scenario createScenario = new Scenario("get");
		Object[][] keyValueList = new Object[][]{
				{"scenType","1"},
				};
		for(int j=0;j<mobile.length;j++){
			
			String returnValue = createScenario.test(mobile[j],password[j],sellerId[j], keyValueList);
			System.out.println(createScenario.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("获取场景成功！");
			} else {
				Reporter.log("获取场景失败！\n Response：" + createScenario.response);
			}
			Reporter.log(getCurrent.Time());
		}
		
	}

}
