package cn.wemart.mobileBuyerManagment;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.RSASignature;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class BuyerLogin {
	

	/**
	 * 对应20510号接口（买家登录）
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public CloseableHttpClient Do(String scenId,String buyerId) throws UnsupportedEncodingException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = LoadAPIInfo.url+"/api/shopping/buyer";
		String scenType = "1";
		String sign = RSASignature.getSign(scenId, buyerId);
		if(null != sign){
			
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("scenId" , scenId);
			keyValueList.put("scenType" , scenType);
			keyValueList.put("buyerId" , buyerId);
			keyValueList.put("sign" , sign);
			
			Reporter.log(getCurrent.Time()); 
			String response = ExecutePost.getPostMethodResponse(httpClient,url,keyValueList);
			String returnValue = JSONObject.fromObject(response).getString("returnValue");
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("买家登录成功！");
			}
			else{
				Reporter.log("买家登录失败！\n Response："+response+"");
				System.out.println("买家登录失败！\n Response："+response+"");
			}
			Reporter.log(getCurrent.Time()); 
		}
		return httpClient;
	}
}
