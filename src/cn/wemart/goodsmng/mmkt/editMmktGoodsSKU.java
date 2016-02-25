package cn.wemart.goodsmng.mmkt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class editMmktGoodsSKU {
	
	
	/**
	 * 对应30505号接口（编辑供货市场商品SKU）
	 */
	@Test
	public static void editMmktGoodsSKUTest(){
		
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
//		mmktGoodsSkuListObjectArray.add(mmktGoodsSkuList2);
//		mmktGoodsSkuListObjectArray.add(mmktGoodsSkuList3);
		
		
		JSONArray mmktGoodsSkuList = JSONArray.fromObject(mmktGoodsSkuListObjectArray);

		Object[][]	keyVlaueList = new Object[][]{
				{"goodsskuId", "sku392"},
				{"mmktId","1"},
				{"moneyUnit", "NMB"},
				{"retailPrice", 999},
				{"retailpriceFixed", 3},
				{"supplyPrice", 999},
				{"supplyTaxprice", 999}
				};
			
			String url = LoadAPIInfo.url+LoadAPIInfo.mmktAPI;
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
			
			JSONObject responsejson = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
			if (0 == returnValue) {
				System.out.println("编辑供货市场商品成功！\n");
			}
			else{
				System.out.println("编辑供货市场商品失败！"+responseEntiy+"\n");
			}
	}

}
