package cn.wemart.channelManagment;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Listeners;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;

@Listeners({com.TestNG.AssertionListener.class})
public class GetChannelInfo {

	public String response;
	public String test(String channelId){
	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = LoadAPIInfo.url + "/api/shopping/channel";
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("channelId",channelId);
		response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}
}
