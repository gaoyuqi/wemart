package cn.wemart.picmng.qiniu;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getPicListURL {

	/**
	 * 对应20405号接口（通过图片获取url）
	 * @param args
	 */
	public static void main(String[] args) {

//		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String url = LoadAPIInfo.url + "/api/shopping/pic/list";
		
		List<String> picList = new ArrayList<String>();
		picList.add("1");
		
		JSONArray picArray = JSONArray.fromObject(picList);
		
		Object[][] keyValueList = new Object[][]{
				{"picNos",picArray}
				};
		
		System.out.println(getCurrent.Time());
		String response = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取图片List URL成功!\n");
		}
		else{
			System.out.println("获取图片List URL失败!"+response+"\n");
		}
	}

}
