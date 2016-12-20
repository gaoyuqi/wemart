package cn.wemart.executeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.ShopManagement.ShopManagement;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteShopManagementTest {
	
	@Test
	public void getMyShopList(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller";
		ShopManagement SM = new ShopManagement("get", url);
		Object[][] keyValueList = new Object[][]{
				{"type","apply"}
				};
		for(int j=0;j<mobile.length;j++){
			String returnValue = SM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("获取我的店铺列表成功！");
			} else {
				Reporter.log("获取我的店铺列表失败！\n Response：" + SM.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
	@Test
	public void applyShop(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		
		String shopNameList = ReadExcel.Do(3,0);
		String logoUrlList = ReadExcel.Do(3,1);
		String applyRemarkList = ReadExcel.Do(3,2);
		String channelIdList = ReadExcel.Do(3,3);
		String[] shopName = shopNameList.split(",");
		String[] logoUrl = logoUrlList.split(",");
		String[] applyRemark = applyRemarkList.split(",");
		String[] channelId = channelIdList.split(",");
		
		String url = LoadAPIInfo.url+"/api/usermng/seller";
		ShopManagement SM = new ShopManagement("post", url);
		
		for(int j=0;j<mobile.length;j++){
			Object[][] keyValueList = new Object[][]{
					{"sellName",shopName[j]},
					{"logoUrl",logoUrl[j]},
					{"applyRemark",applyRemark[j]},
					{"channelId",channelId[j]}
					};
			String returnValue = SM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("申请店铺成功！");
			} else {
				Reporter.log("申请店铺失败！\n Response：" + SM.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
}
