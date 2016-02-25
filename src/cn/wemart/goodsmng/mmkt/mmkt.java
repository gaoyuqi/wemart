package cn.wemart.goodsmng.mmkt;

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

import cn.wemart.goodsmng.group.Group;
import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class mmkt {

	private static Log log = LogFactory.getLog(Group.class);
	
	
	/**
	 * 对应30501号接口（将商品加入供销市场）
	 */
	@Test
	public static void AddMmktTest(){
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		Map<Object ,Object> mmktGoodsSkuList1 = new HashMap<Object,Object>();
		mmktGoodsSkuList1.put("goodsskuId", "sku3");
		mmktGoodsSkuList1.put("moneyUnit", "CNY");
		mmktGoodsSkuList1.put("retailPrice", 999.10);
		mmktGoodsSkuList1.put("retailpriceFixed", 999);
		mmktGoodsSkuList1.put("supplyPrice", 999);
		mmktGoodsSkuList1.put("supplyTaxprice", 999);
		
		Map<Object ,Object> mmktGoodsSkuList2 = new HashMap<Object,Object>();
		mmktGoodsSkuList2.put("goodsskuId", "sku5");
		mmktGoodsSkuList2.put("moneyUnit", "USD");
		mmktGoodsSkuList2.put("retailPrice", 999);
		mmktGoodsSkuList2.put("retailpriceFixed", 3);
		mmktGoodsSkuList2.put("supplyPrice", 999);
		mmktGoodsSkuList2.put("supplyTaxprice", 999);
		
		Map<Object ,Object> mmktGoodsSkuList3 = new HashMap<Object,Object>();
		mmktGoodsSkuList3.put("goodsskuId", "sku392");
		mmktGoodsSkuList3.put("moneyUnit", "NMB");
		mmktGoodsSkuList3.put("retailPrice", 999);
		mmktGoodsSkuList3.put("retailpriceFixed", 3);
		mmktGoodsSkuList3.put("supplyPrice", 999);
		mmktGoodsSkuList3.put("supplyTaxprice", 999);
		
		List<Object> mmktGoodsSkuListObjectArray = new ArrayList<Object>();
		mmktGoodsSkuListObjectArray.add(mmktGoodsSkuList1);
		mmktGoodsSkuListObjectArray.add(mmktGoodsSkuList2);
		mmktGoodsSkuListObjectArray.add(mmktGoodsSkuList3);
		
		
		JSONArray mmktGoodsSkuList = JSONArray.fromObject(mmktGoodsSkuListObjectArray);

		Object[][]	keyVlaueList = new Object[][]{
					{"goodsId","gd248"},
					{"invoiceFlag",3},
					{"mmktGoodsSkuList",mmktGoodsSkuList}};
			
			String url = LoadAPIInfo.url+LoadAPIInfo.mmktAPI;
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("商品加入供货市场成功！\n");
			}
			else{
				System.out.println("商品加入供货市场失败！"+responseEntiy+"\n");
			}
	}
	
	
	
	/**
	 * 对应30504号接口（编辑供货市场商品）
	 */
//	@Test
	public static void EditMmktTest(){
		


		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		Object[][]	keyVlaueList = new Object[][]{
					{"mmktId","1"},
					{"invoiceFlag",2},
					{"goodsId","gd248"}
					};
			
			String url = LoadAPIInfo.url+LoadAPIInfo.mmktAPI;
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyVlaueList);
//			String responseEntiy = ExecuteDelete.getDeleteMethodResponse(url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("修改供货市场商品成功！\n");
			}
			else{
				System.out.println("修改供货市场商品失败！"+responseEntiy+"\n");
			}
	}
	
	
	/**
	 * 对应30503号接口（撤销供货市场商品）
	 */
//	@Test
	public static void DeleteMmktTest(){

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		Object[][]	keyVlaueList = new Object[][]{
					{"goodsId","gd248"}
					};
			
			String url = LoadAPIInfo.url+LoadAPIInfo.mmktAPI;
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecuteDelete.getDeleteMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("撤销供货市场商品成功！\n");
			}
			else{
				System.out.println("撤销供货市场商品失败！"+responseEntiy+"\n");
			}
	}

}
