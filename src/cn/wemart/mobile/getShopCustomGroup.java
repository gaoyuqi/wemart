package cn.wemart.mobile;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getShopCustomGroup {

	/**
	 * 对应30701号接口（获取商铺分组）
	 */
	@Test()
	public static void getShopCustomGroupMobile() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String url = LoadAPIInfo.url+"/api/shopping/shop/group";
		Object[][] keyValueList = new Object[][]{
				{"parentNo","849"},
				{"sellerId","6"}};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取商铺分组成功! \n");
		}
		else{
			System.out.println("获取商铺分组失败!"+responseEntiy+"\n");
		}

	}

}
