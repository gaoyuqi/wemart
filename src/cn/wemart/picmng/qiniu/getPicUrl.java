package cn.wemart.picmng.qiniu;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getPicUrl {
	public static String remoteUrl = null;
	public static String bucketName = null;

	public static void main(String[] args) {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + "/api/authmng/qiniupicurl";
		Object[][] keyValueList = new Object[][]{
				};
		System.out.println(getCurrent.Time());
		String response = ExecutePost.getPostMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			bucketName = responseObject.getString("bucketName");
			remoteUrl = responseObject.getString("remoteUrl");
			System.out.println("获取图片remoteUrl、bucketName成功! remoteUrl为："+remoteUrl+"，bucketName为："+bucketName+"\n");
		}
		else{
			System.out.println("获取图片remoteUrl、bucketName成功失败!"+response+"\n");
		}
	}

}
