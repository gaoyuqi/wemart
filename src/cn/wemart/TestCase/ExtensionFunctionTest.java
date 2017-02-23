package cn.wemart.TestCase;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.Channel;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;


@Listeners({com.TestNG.AssertionListener.class})
public class ExtensionFunctionTest {
	
	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";

	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase EFT = new ObjectBase();
	
	
	public ExtensionFunctionTest() {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}
	
	@Test
	public void getExtensionFunction(){
		String url = LoadAPIInfo.url + "/api/usermng/seller/product";
		EFT.Init("get", url);
		Map<String ,Object> keyValueList = new HashMap<String,Object>();
			String returnValue = EFT.Test(httpClient, keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询扩展功能成功！");
			}
			else{
				Reporter.log("查询扩展功能失败！Response："+EFT.response);
			}
	}
	
	
	

	/*
	 * 获取扩张功能申请列表
	 */
	public String getExtensionFunctionApplyList(String mobile, String password, String channelId){
		String response;
		String url = LoadAPIInfo.url+"/api/usermng/channel/product";
		Channel channel = new Channel("get", url);
		
		Map<String ,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("pageIndex",0);
		keyValueList.put("pageIndex",0);
		keyValueList.put("pageIndex",0);
		
		String returnValue = channel.test(mobile, password, channelId, keyValueList);
		response = channel.response;
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("获取扩展功能申请列表成功！");
		} else {
			Reporter.log("获取扩展功能申请列表失败！\n Response：" + channel.response);
		}
		return response;
	}
	
	
	@Test
	public void checkExtensionFunctionApply(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String channelIdList = ReadExcel.Do(0,4);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] channelId = channelIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller/product";
		Channel channel = new Channel("get", url);
		for(int j=0;j<mobile.length;j++){
			String response = getExtensionFunctionApplyList(mobile[j], password[j], channelId[j]);
			if(response.contains("applyNo")){
				JSONObject data = JSONObject.fromObject(response).getJSONObject("data");
				String 	applyNo = data.getJSONArray("productApplyBeanList").getJSONObject(0).getString("applyNo");
				String 	sellerId = data.getJSONArray("productApplyBeanList").getJSONObject(0).getString("sellerId");
				
				Map<String ,Object> keyValueList = new HashMap<String,Object>();
				keyValueList.put("applyNo",applyNo);
				keyValueList.put("sellerId",sellerId);
				keyValueList.put("approveStatus","3");
				keyValueList.put("approveRemark","这里是审核说明");
				String returnValue = channel.test(mobile[j], password[j], channelId[j], keyValueList);
				if (Assertion.verifyEqual(returnValue, "0")) {
					Reporter.log("审核扩展功能成功！");
				} else {
					Reporter.log("审核扩展功能失败！\n Response：" + channel.response);
				}
				Reporter.log(getCurrent.Time());
			}
			else{
				Reporter.log("该渠道没有未审核的扩展功能！");
				Reporter.log(getCurrent.Time());
			}
		}
	}
	
	

}
