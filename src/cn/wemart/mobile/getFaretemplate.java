package cn.wemart.mobile;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getFaretemplate {
	
	/**
	 * 对应30706号接口（获取邮费模板）
	 */
	@Test()
	public static void getFaretemplateTest(){
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String url = LoadAPIInfo.url+"/api/shopping/shop/goodsdetail";
		
		Object[][] keyValueList = new Object[][]{
				{"goodsId","gd2050"},
//				{"sellerId","6"}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取商品邮费模板成功! \n");
		}
		else{
			System.out.println("获取商品邮费模板失败!"+responseEntiy+"\n");
		}
	}

}
