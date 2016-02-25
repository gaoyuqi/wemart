package cn.wemart.decorationmng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.Loadshelf;
import cn.wemart.util.getCurrent;

public class shelf {

	private static Log log = LogFactory.getLog(shelf.class);

	/**
	 * 对应601号接口（创建货架）
	 */
//	@Test
	public static void addShelfTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + LoadAPIInfo.createShelfAPI;

		List<String> shelfDataList = new ArrayList<String>();
		shelfDataList.add(Loadshelf.textNavigate);
		shelfDataList.add(Loadshelf.separator);
		shelfDataList.add(Loadshelf.shopSign);
		shelfDataList.add(Loadshelf.slide);
		shelfDataList.add(Loadshelf.picNavigate4);
		shelfDataList.add(Loadshelf.picNavigate8);
		shelfDataList.add(Loadshelf.goodsGroup);
		shelfDataList.add(Loadshelf.announcement);
		
		List<String> shelfNameList = new ArrayList<String>();
		shelfNameList.add("textNavigate");
		shelfNameList.add("separator");
		shelfNameList.add("shopSign");
		shelfNameList.add("slide");
		shelfNameList.add("picNavigate4");
		shelfNameList.add("picNavigate8");
		shelfNameList.add("goodsGroup");
		shelfNameList.add("announcement");
		
		
		for(int i =0;i<shelfDataList.size();i++)
		{
			JSONObject dataObject = JSONObject.fromObject(shelfDataList.get(i));
			JSONArray shelfContent = JSONArray.fromObject(dataObject);
			
			Object[][] keyValueList = new Object[][] {
					{ "shelfType", "3" },
					{ "shelfName", shelfNameList.get(i)}, 
					{ "groupNo", "975" },
					{ "bgColor", "#11eedd" }, 
					{ "shelfContent", shelfContent } 
					};
			
			System.out.println(getCurrent.Time());
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url,keyValueList);
			System.out.println("response=" + responseEntiy);
			System.out.println(getCurrent.Time());
			
			JSONObject responseObject = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			if(0 == returnValue){
				System.out.println("创建货架 "+shelfNameList.get(i)+" 成功! \n");
			}
			else{
				System.out.println("创建货架 "+shelfNameList.get(i)+" 失败!"+responseEntiy+"\n");
			}
		}
		
	}
	
	
	
	/**
	 * 对应602号接口（修改货架）
	 */
	@Test
	public static void editShelfTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		List<String> shelfDataList = new ArrayList<String>();
		shelfDataList.add(Loadshelf.textNavigate);
		
		List<String> shelfNameList = new ArrayList<String>();
		shelfNameList.add("textNavigate");
		
		JSONObject dataObject = JSONObject.fromObject(shelfDataList.get(0));
		JSONArray shelfContent = JSONArray.fromObject(dataObject);
		
		Object[][] keyValueList = new Object[][] {
				{ "shelfType", "3" },
				{ "shelfName", shelfNameList.get(0)}, 
				{ "groupNo", "940" },
				{ "shelfNo", "235" },
				{ "bgColor", "#11eedd" }, 
				{ "shelfContent", shelfContent } 
				};

		String url = LoadAPIInfo.url + LoadAPIInfo.createShelfAPI;
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient, url,keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("编辑货架信息成功! \n");
		}
		else{
			System.out.println("编辑货架信息失败!"+responseEntiy+"\n");
		}
	}
	
	
	/**
	 * 对应604号接口（查询货架）
	 */
//	@Test
	public static void getShelfTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();


		Object[][] keyValueList = new Object[][]{
//				{"shelfNo",215}
				};

		String url = LoadAPIInfo.url + LoadAPIInfo.createShelfAPI;
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url,keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			JSONArray data = JSONArray.fromObject(responseObject.getString("data"));
			for(Object o : data){
				System.out.println(o.toString());
			}
			System.out.println("获取货架信息成功! \n");
		}
		else{
			System.out.println("获取货架信息失败!"+responseEntiy+"\n");
		}
	}
	
	/**
	 * 对应603号接口（删除货架）
	 */
//	@Test
	public static void deleteShelfTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + LoadAPIInfo.createShelfAPI;

		Object[][] keyValueList = new Object[][] {
				{ "shelfNo", "203" }
				};

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteDelete.getDeleteMethodResponse(httpclient,url,keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("删除货架信息成功! \n");
		}
		else{
			System.out.println("删除货架信息失败!"+responseEntiy+"\n");
		}

	}

}
