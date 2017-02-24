package cn.wemart.TestCase.PC;

import java.util.HashMap;
import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.configureManagment.ConfigureManagment;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.ReadExcel;

@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteConfigureManagmentTest {
	
	@Test
	public void getCommConfigure(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/marketmng/config/middlemkt/commseller";
		ConfigureManagment CM = new ConfigureManagment("get",url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		for(int j=0;j<mobile.length;j++){
			String returnValue = CM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询分销配置成功！");
			}
			else{
				Reporter.log("查询分销配置失败！Response："+CM.response);
			}
		}
	}
	
	@Test
	public void putCommConfigure(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/marketmng/config/middlemkt/commseller";
		ConfigureManagment CM = new ConfigureManagment("put",url);
		int[] valueList ={1,0};
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("valueList",valueList);
		for(int j=0;j<mobile.length;j++){
			String returnValue = CM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("更新分销配置成功！");
			}
			else{
				Reporter.log("更新分销配置失败！Response："+CM.response);
			}
		}
	}
	
	@Test
	public void getShopConfigure(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller/config";
		ConfigureManagment CM = new ConfigureManagment("get",url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		for(int j=0;j<mobile.length;j++){
			String returnValue = CM.test(mobile[j], password[j], sellerId[j], keyValueList);
			System.out.println(CM.response);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询店铺装修配置成功！");
			}
			else{
				Reporter.log("查询店铺装修配置失败！Response："+CM.response);
			}
		}
	}
	
	@Test
	public void putShopConfigure(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller/config";
		ConfigureManagment CM = new ConfigureManagment("put",url);
		int[] valueList ={ 1,1,1,0,1,2,1,0};//参数说明请见WIKI： http://wiki.wemart.cn/pages/viewpage.action?pageId=2230410
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("valueList",valueList);
		keyValueList.put("bgColor","#ffffff");
		
		for(int j=0;j<mobile.length;j++){
			String returnValue = CM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("更新店铺装修配置成功！");
			}
			else{
				Reporter.log("更新店铺装修配置失败！Response："+CM.response);
			}
		}
	}
	
	@Test
	public void getCustomerServiceConfigure(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller/config/customer";
		ConfigureManagment CM = new ConfigureManagment("get",url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		for(int j=0;j<mobile.length;j++){
			String returnValue = CM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询客服配置成功！");
			}
			else{
				Reporter.log("查询客服配置失败！Response："+CM.response);
			}
		}
	}
	
	@Test
	public void putCustomerServiceConfigure(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller/config/customer";
		ConfigureManagment CM = new ConfigureManagment("put",url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("sellerCsLink","http://www.google.com");
		keyValueList.put("owner","0");
		keyValueList.put("openFlag","0");
		for(int j=0;j<mobile.length;j++){
			String returnValue = CM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("更新客服配置成功！");
			}
			else{
				Reporter.log("更新客服配置失败！Response："+CM.response);
			}
		}
	}
	
	@Test
	public void putSettlefeecfg(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/fundsmng/config/settlefeecfg";
		ConfigureManagment CM = new ConfigureManagment("put",url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("settleType","7");
		for(int j=0;j<mobile.length;j++){
			String returnValue = CM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("更新客服配置成功！");
			}
			else{
				Reporter.log("更新客服配置失败！Response："+CM.response);
			}
		}
	}

}
