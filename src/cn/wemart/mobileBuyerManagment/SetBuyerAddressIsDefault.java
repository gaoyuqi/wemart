package cn.wemart.mobileBuyerManagment;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class SetBuyerAddressIsDefault {
	
	public String response;
	@Test
	public String test(String scenId,String BuyerId,String addrNo,CloseableHttpClient httpClient) throws UnsupportedEncodingException{
		Object[][] keyValueList = new Object[][]{
				{"addrNo",addrNo},
				};
		
		String url = LoadAPIInfo.url + "/api/usermng/buyer/defaultaddr";
		
		Reporter.log(getCurrent.Time());
		response = ExecutePut.getPutMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}
