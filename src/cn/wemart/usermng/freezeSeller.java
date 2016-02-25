package cn.wemart.usermng;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.goodsmng.group.Group;
import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class freezeSeller {

	private static Log log = LogFactory.getLog(freezeSeller.class);

	/**
	 * 对应20203号接口（冻结店铺）
	 */
	@Test
	public static void freezeSellerAPITest(){
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.freezeSellerAPI;
		
		Object[][] keyValueList = new Object[][]{
				{"sellId","74"},
				{"status","2"}
				}; 
		
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy.trim());
		System.out.println(getCurrent.Time()); 
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("冻结店铺成功！\n");
		}
		else{
			System.out.println("冻结店铺失败！"+responseEntiy+"\n");
		}
	}

}
