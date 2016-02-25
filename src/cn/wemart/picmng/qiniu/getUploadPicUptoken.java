package cn.wemart.picmng.qiniu;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getUploadPicUptoken {
	public static String uptoken = null;

	public static void main(String[] args) {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + "/api/authmng/qiniutoken";
		Object[][] keyValueList = new Object[][]{
				{"bucketName","imgcache5"}
				};
		
		System.out.println(getCurrent.Time());
		String response = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			uptoken = responseObject.getString("uptoken");
			System.out.println("获取七牛上传图片Token成功! Token为："+uptoken+"\n");
		}
		else{
			System.out.println("获取七牛上传图片Token失败!"+response+"\n");
		}
	}

}
