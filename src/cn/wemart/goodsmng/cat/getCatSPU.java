package cn.wemart.goodsmng.cat;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

/**
 * 
 * @author sean
 */

public class getCatSPU {
	
	/**
	 * 对应30102号接口（获取类目SPU）
	 */
	@Test
	public static void getCatSPUAPITest(){
		int catSPUCount = 0;
		String[] catNoList = LoadInfo.catName.split(",");
		List<String> list = new ArrayList<String>();
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

//		for (String x : catNoList){
		
		Object[][] keyValueList = new Object[][]{{"catNo",537114620}};
		
		String url = LoadAPIInfo.url+LoadAPIInfo.getCatSPUAPI;
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(returnValue == 0){
			JSONArray ResponseArray = responseObject.getJSONArray("data");
			for(Object o : ResponseArray){
				String catno = JSONObject.fromObject(o).getString("catNo");
				list.add(catno);
				catSPUCount += 1;
				System.out.println(o.toString());
			}
		}
		else{
			System.out.println("获取类目SPU失败"+responseEntiy);
		}
		System.out.println("获取的类目SPU数量为："+catSPUCount);
//			return list;
			
	}

}
