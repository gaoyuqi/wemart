package cn.wemart.TestCase.PC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.navigatorManagement.NavigatorManagement;
import cn.wemart.util.ReadExcel;

import com.TestNG.Assertion;


@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteNagivatorManagementTest {
	
	public List<String> shelfNo = new ArrayList<String>();
	
	@Test
	public void getNavigator(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		NavigatorManagement NM = new NavigatorManagement("get");
		Map<String, Object> keyValueList = new HashMap<String, Object>();
		for(int j=0;j<mobile.length;j++){
			String returnValue = NM.test(mobile[j], password[j], sellerId[j], keyValueList);
			System.out.println(NM.response);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询导航成功！");
			}
			else{
				Reporter.log("查询导航失败！Response："+NM.response);
			}
		}
	}
	
	@Test
	public void createNavigator(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		NavigatorManagement NM = new NavigatorManagement("post");
		Map<String, Object> navSettingmap = new HashMap<String, Object>();
		navSettingmap.put("navStyle", 2);
		navSettingmap.put("navBgColor", "#000000");
		navSettingmap.put("navHome", true);
		navSettingmap.put("navGroup", false);
		Map<String, Object> navigatorContentmap = new HashMap<String, Object>();
		navigatorContentmap.put("navSetting", navSettingmap);
		navigatorContentmap.put("navData", JSONArray.fromObject("[]"));
		navigatorContentmap.put("isOpen", true);

		for(int j=0;j<mobile.length;j++){
			Map<String, Object> keyValueList = new HashMap<String, Object>();
			keyValueList.put("navigatorContent",navigatorContentmap);
			String returnValue = NM.test(mobile[j], password[j], sellerId[j], keyValueList);
			System.out.println(NM.response);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("创建导航成功！");
			}
			else{
				Reporter.log("创建导航失败！Response："+NM.response);
			}
		}
	}
	
	@Test
	public void updateNavigator(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		NavigatorManagement NM = new NavigatorManagement("put");
		Map<Object, Object> navSettingmap = new HashMap<Object, Object>();
		navSettingmap.put("navStyle", 2);
		navSettingmap.put("navBgColor", "#000000");
		navSettingmap.put("navHome", true);
		navSettingmap.put("navGroup", false);
		Map<Object, Object> navigatorContentmap = new HashMap<Object, Object>();
		navigatorContentmap.put("navSetting", navSettingmap);
		navigatorContentmap.put("navData", JSONArray.fromObject("[]"));
		navigatorContentmap.put("isOpen", true);

		for(int j=0;j<mobile.length;j++){
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("navigatorContent",navigatorContentmap);
			keyValueList.put("bgColor","#eeffee");
			String returnValue = NM.test(mobile[j], password[j], sellerId[j], keyValueList);
			System.out.println(NM.response);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("创建导航成功！");
			}
			else{
				Reporter.log("创建导航失败！Response："+NM.response);
			}
		}
	}
}