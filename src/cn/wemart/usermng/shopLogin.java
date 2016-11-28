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
/**
 * 
 * @author sean
 *
 */
public class shopLogin {
	
	private static Log log = LogFactory.getLog(shopLogin.class);

	/**
	 * 对应20508号接口（商铺登录）
	 */
	@Test
	public static void shopLoginTest() {
		String[] adminAcct = LoadUserInfo.adminAcct.split(",");
		String[] password = LoadUserInfo.password.split(",");
		String[] type = LoadUserInfo.type.split(",");
		String[] objectId = LoadUserInfo.objetcId.split(",");
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = LoadAPIInfo.url+LoadAPIInfo.userLoginAPI;
		
		for (int i = 0;i<adminAcct.length;i++) {

			Object[][] keyValueList = new Object[][] { 
					{ "adminAcct",  adminAcct[i]},
					{ "password", password[i] },
					{ "type", type[i] },
					{ "objectId", objectId[i] }
					};
			
			log.info(getCurrent.Time()); 
			String response = ExecutePost.getPostMethodResponse(httpclient,url, keyValueList);
			log.info("response="+response);
			log.info(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(response);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				log.info("登录店铺成功！\n");
			}
			else{
				log.info("登录店铺失败！"+response+"\n");
			}
		}
	}
	
}
