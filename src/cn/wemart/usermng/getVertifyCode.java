package cn.wemart.usermng;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class getVertifyCode {
	private static Log log = LogFactory.getLog(getVertifyCode.class);
	/**
	 * 对应20501号接口（获取验证码）
	 */
	@Test
	public static void getVertifyCodeTest() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String[] mobileNo = LoadUserInfo.adminAcct.split(",");
//		for(String x : mobileNo){
			
			Object[][] keyValueList = {{"mobileNo","13818815870"}};
			
			String url = LoadAPIInfo.url+LoadAPIInfo.vertifycodeAPI;
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url,keyValueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if( 0 == returnValue){
				System.out.println("获取验证码成功！\n");
			}
			else{
				System.out.println("获取验证码失败！"+responseEntiy+"\n");
			}
//		}
	}
}
