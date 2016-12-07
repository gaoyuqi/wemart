package cn.wemart.mobileBuyerManagment;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class GetBuyerAddress {
	
	public static String response;
	@Test
	public static String test(String scenId,String BuyerId) throws UnsupportedEncodingException{
		CloseableHttpClient httpClient = BuyerLogin.Do(scenId, BuyerId);
		Object[][] keyValueList = new Object[][]{
				{"addrNo",""},
				};
		
		String url = LoadAPIInfo.url + "/api/usermng/buyer/address";
		
		Reporter.log(getCurrent.Time());
		response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}
