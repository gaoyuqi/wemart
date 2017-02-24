package cn.wemart.TestCase.PC;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ShopTest {
	
	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";

	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase ST = new ObjectBase();
	String url = LoadAPIInfo.url+"/api/usermng/seller";
	
	public ShopTest() {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}
	
	@Test
	public void getMyShopList(){
		ST.Init("get", url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("type","apply");
		
		String returnValue = ST.Test(httpClient, keyValueList);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("获取我的店铺列表成功！");
		} else {
			Reporter.log("获取我的店铺列表失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void applyShop(){
		
		String shopNameList = ReadExcel.Do(3,0);
		String logoUrlList = ReadExcel.Do(3,1);
		String applyRemarkList = ReadExcel.Do(3,2);
		String channelIdList = ReadExcel.Do(3,3);
		String[] shopName = shopNameList.split(",");
		String[] logoUrl = logoUrlList.split(",");
		String[] applyRemark = applyRemarkList.split(",");
		String[] channelId = channelIdList.split(",");
		
		ST.Init("post", url);
		for(int j=0;j<shopName.length;j++){
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("sellName",shopName[j]);
			keyValueList.put("logoUrl",logoUrl[j]);
			keyValueList.put("applyRemark",applyRemark[j]);
			keyValueList.put("channelId",channelId[j]);
			
			String returnValue = ST.Test(httpClient, keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("申请店铺成功！");
			} else {
				Reporter.log("申请店铺失败！\n Response：" + ST.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
}
