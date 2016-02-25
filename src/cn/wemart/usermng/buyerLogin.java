package cn.wemart.usermng;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.RSASignature;
import cn.wemart.util.getCurrent;

public class buyerLogin {
	
	private static Log log = LogFactory.getLog(buyerLogin.class);

	/**
	 * 对应20510号接口（买家登录）
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public static void freezeSellerAPITest() throws UnsupportedEncodingException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = LoadAPIInfo.url+LoadAPIInfo.buyerLoginAPI;
		String scenId = "10";
		String buyerId = "123000";
		String scenType = "2";
		String sign = RSASignature.getSign(scenId, buyerId);
		if(null != sign){
			Object[][] keyValueList = new Object[][]{
					{"scenId" , scenId},
					{"scenType" , scenType},
					{"buyerId" , buyerId},
					{"sign" , sign}
					}; 
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url,keyValueList);
			System.out.println("response="+responseEntiy.trim());
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("买家登录成功！\n");
			}
			else{
				System.out.println("买家登录失败！"+responseEntiy+"\n");
			}
		}
		//j3/NBDgB2smeeWxoGnRtrFd3P6pKxHSBRmA5htohPxOOZqy59pAuOKvJ9IK/9RQ3BK1T1c555w+AVZM6QJYi92qklmPpImTAVu65FTs0aUZpqNaSpVLy/FjASTLRiW3UhNABQYOmxU4Jc6N114OC3joW3LrjNbMkC7gFvbF7dO8=
		//j3/NBDgB2smeeWxoGnRtrFd3P6pKxHSBRmA5htohPxOOZqy59pAuOKvJ9IK/9RQ3BK1T1c555w+AVZM6QJYi92qklmPpImTAVu65FTs0aUZpqNaSpVLy/FjASTLRiW3UhNABQYOmxU4Jc6N114OC3joW3LrjNbMkC7gFvbF7dO8=
	}
	
	public static CloseableHttpClient setHttpClient(String scenId,String buyerId,String scenType){
		CloseableHttpClient httpclient = null;
		try {
			String sign = RSASignature.getSign(scenId, buyerId);
			Object[][] loginkeyValueList = new Object[][]{
					{"scenId" , scenId},
					{"scenType" , scenType},
					{"buyerId" , buyerId},
					{"sign" , sign}
					}; 
		String loginurl = LoadAPIInfo.url+LoadAPIInfo.buyerLoginAPI;
		httpclient = cn.wemart.httppost.getHttpClient.Login(loginurl, loginkeyValueList);
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpclient;
	}

}
