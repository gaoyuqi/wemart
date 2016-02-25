package cn.wemart.goodsmng.faretmplt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class faretmplt {

	/**
	 * 对应30601号接口（添加邮费模板）
	 */
//	@Test
	public static void addTemplateTest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		// 组装json
		Map<Object,Object> fareList1map = new HashMap<Object,Object>();
		fareList1map.put("cityNo",0);
		fareList1map.put("increFare",0);
		fareList1map.put("increNum",0);
		fareList1map.put("initFare",0);
		fareList1map.put("initNum",0);
		
		Map<Object,Object> fareList2map = new HashMap<Object,Object>();
		fareList2map.put("cityNo",1);
		fareList2map.put("increFare",0);
		fareList2map.put("increNum",0);
		fareList2map.put("initFare",0);
		fareList2map.put("initNum",0);
		
		List<Object> fareList = new ArrayList<Object>();
		fareList.add(fareList1map);
		fareList.add(fareList2map);

		JSONArray fareListArray = JSONArray.fromObject(fareList);
		
		String url = LoadAPIInfo.url+LoadAPIInfo.fareTmpltAPI;
		Object[][] keyValueList = new Object[][]{
				{"fareList",fareListArray},
				{"faretmpltName","fareTmpltName"}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			String data = responseObject.getString("data");
			System.out.println("创建商品邮费模板成功! 模板ID为："+data+"\n");
		}
		else{
			System.out.println("创建商品邮费模板失败!"+responseEntiy+"\n");
		}

	}
	
	
	/**
	 * 对应30604号接口（查询邮费模板）
	 */
//	@Test
	public static void getFaretemplateTest() {
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.fareTmpltAPI;
		
		Object[][] keyValueList = new Object[][]{};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			JSONArray data = JSONArray.fromObject(responseObject.getString("data"));
			for(Object o : data){
				System.out.println(o.toString());
			}
			System.out.println("获取邮费模板成功! \n");
		}
		else{
			System.out.println("获取商品邮费模板失败!"+responseEntiy+"\n");
		}

	}
	
	
	/**
	 * 对应30602号接口（编辑邮费模板）
	 */
//	@Test
	public static void editFaretemplate() {
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		// 组装json
		Map<Object,Object> fareList1map = new HashMap<Object,Object>();
		fareList1map.put("cityNo",0);
		fareList1map.put("increFare",0);
		fareList1map.put("increNum",0);
		fareList1map.put("initFare",0);
		fareList1map.put("initNum",0);
		
		Map<Object,Object> fareList2map = new HashMap<Object,Object>();
		fareList2map.put("cityNo",1);
		fareList2map.put("increFare",0);
		fareList2map.put("increNum",0);
		fareList2map.put("initFare",0);
		fareList2map.put("initNum",0);
		
		List<Object> fareList = new ArrayList<Object>();
		fareList.add(fareList1map);
		fareList.add(fareList2map);
	
		JSONArray fareListArray = JSONArray.fromObject(fareList);
		String url = LoadAPIInfo.url+LoadAPIInfo.fareTmpltAPI;
		
		Object[][] keyValueList = new Object[][]{
				{"fareList",fareListArray},
				{"faretmpltNo",93},
				{"faretmpltName","fareTmpltName"}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("编辑邮费模板成功! \n");
		}
		else{
			System.out.println("编辑商品邮费模板失败!"+responseEntiy+"\n");
		}
	}
	
	
	/**
	 * 对应30603号接口（删除邮费模板）
	 */
	@Test
	public static void deleteFaretemplate() {
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.fareTmpltAPI;
		
		Object[][] keyValueList = new Object[][]{{"faretmpltNo",93}};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteDelete.getDeleteMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("删除邮费模板成功! \n");
		}
		else{
			System.out.println("删除商品邮费模板失败!"+responseEntiy+"\n");
		}
	}

}
