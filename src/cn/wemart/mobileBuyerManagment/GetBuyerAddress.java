package cn.wemart.mobileBuyerManagment;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
	
	public String response;
	public CloseableHttpClient httpClient;
	@Test
	public String test(String scenId,String BuyerId) throws UnsupportedEncodingException{
		BuyerLogin buyerLogin = new BuyerLogin();
		httpClient = buyerLogin.Do(scenId, BuyerId);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("addrNo","");
		
		String url = LoadAPIInfo.url + "/api/usermng/buyer/address";
		
		Reporter.log(getCurrent.Time());
		response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}
