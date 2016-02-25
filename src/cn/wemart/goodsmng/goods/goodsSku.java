package cn.wemart.goodsmng.goods;

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
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadInfo;
import cn.wemart.util.getCurrent;

public class goodsSku {
	
	private static Log log = LogFactory.getLog(Group.class);


	/**
	 * 对应30301号接口（创建商品SKU）
	 */
	@Test()
	public static void CreateGoodsSkuAPITest(){

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		String url = LoadAPIInfo.url+LoadAPIInfo.createGoodsSkuAPI;
			
		Map<Object ,Object> skuContent = new HashMap<Object,Object>();
		skuContent.put("xxx", "value");
			
		Map<Object ,Object> retailGoodsSkuList2 = new HashMap<Object,Object>();
		retailGoodsSkuList2.put("marketPrice", 999);
		retailGoodsSkuList2.put("moneyUnit", "CNY");
		retailGoodsSkuList2.put("retailPrice", 999);
		retailGoodsSkuList2.put("stocks", 999);
			
		Map<Object ,Object> retailGoodsSkuList1 = new HashMap<Object,Object>();
		retailGoodsSkuList1.put("marketPrice", 789);
		retailGoodsSkuList1.put("moneyUnit", "USD");
		retailGoodsSkuList1.put("retailPrice", 789);
		retailGoodsSkuList1.put("stocks", 789);
			
		List<Object> retailGoodsSkuListObjectArray = new ArrayList<Object>();
		retailGoodsSkuListObjectArray.add(retailGoodsSkuList1);
		retailGoodsSkuListObjectArray.add(retailGoodsSkuList2);
			
		JSONArray retailGoodsSkuList = JSONArray.fromObject(retailGoodsSkuListObjectArray);
		JSONObject skuContentObject = JSONObject.fromObject(skuContent);
			
		
		Object[][] keyVlaueList = new Object[][]{
				{"barcode","123456"},
				{"goodsId","gd100"},
				{"skupicNo","1"},
				{"retailGoodsSkuList",retailGoodsSkuList},
				{"skuContent",skuContentObject}}; 
			
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyVlaueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			String goodsSKUId = responseObject.getString("data");
			System.out.println("添加商品SKU成功! 商品id为："+goodsSKUId+"\n");
		}
		else{
			System.out.println("创建商品SKU失败!"+responseEntiy+"\n");
		}
	}	
	
	/**
	 * 对应30304号接口（获取商品SKU）
	 */
//	@Test()
	public static void GetGoodsSkuAPITest(){

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		String url = LoadAPIInfo.url+LoadAPIInfo.createGoodsSkuAPI;
		
//		String url = LoadAPIInfo.url+"/api/goodsmng/goodssku";
		
		Object[][] keyVlaueList = new Object[][]{
				{"goodsId","gd2036"}
				};
			
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyVlaueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取商品SKU成功! \n");
		}
		else{
			System.out.println("获取商品SKU失败!"+responseEntiy+"\n");
		}
	}
	

	
	/**
	 * 对应30303号接口（删除商品SKU）
	 */
//	@Test()
	public static void DeleteGoodsSkuAPITest(){

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		String url = LoadAPIInfo.url+LoadAPIInfo.createGoodsSkuAPI;
			
		//组装json
		Map<Object ,Object> DeleteGoodsSkuList = new HashMap<Object,Object>();
		DeleteGoodsSkuList.put("goodsskuId", 789);
		DeleteGoodsSkuList.put("goodsskuId", "789");
			
		JSONArray DeleteGoodsSkuListArray = JSONArray.fromObject(DeleteGoodsSkuList);
			
		Object[][] keyVlaueList = new Object[][]{{"barcode","123456"},
				{"goodsskuId","sku370"},
				};
			
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyVlaueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("删除商品SKU成功! \n");
		}
		else{
			System.out.println("删除商品SKU失败!"+responseEntiy+"\n");
		}
	}
}
