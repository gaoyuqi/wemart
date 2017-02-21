package cn.wemart.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.goodsManagement.GoodsManagement;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class GoodsAndSKUTest {
	String goodsId = null;
	String goodsSkuId = null;
	String mobile="13818881111";
	String password="123456";
	String sellerId="234";
	
	CloseableHttpClient httpClient = HttpClients.createDefault();
	GoodsManagement GM = new GoodsManagement();
	
	public GoodsAndSKUTest(){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}
	
//	@Test
	public void CreateGoods(){
		String url = LoadAPIInfo.url+"/api/goodsmng/goods/goodsandsku";
		GM.Init("post", url);
		JSONArray ListEmpty = JSONArray.fromObject("[]");
		
		Map<Object ,Object> retailGoodsSkuList1 = new HashMap<Object,Object>();
		retailGoodsSkuList1.put("marketPrice", 10000);
		retailGoodsSkuList1.put("moneyUnit", "CNY");
		retailGoodsSkuList1.put("retailPrice", 10000);
		retailGoodsSkuList1.put("stocks", 1000);
		JSONArray retailGoodsSku1 = JSONArray.fromObject(retailGoodsSkuList1);
		
		Map<Object ,Object> skuContentMap1 = new HashMap<Object,Object>();
		skuContentMap1.put("skuSetId", 1075741873);
		skuContentMap1.put("skuSetName", "高矮");
		skuContentMap1.put("skuId", 1075741821);
		skuContentMap1.put("skuValue", "高");
		JSONArray skuContent1 = JSONArray.fromObject(skuContentMap1);
		
		Map<Object ,Object> goodsSku1 = new HashMap<Object,Object>();
		goodsSku1.put("skupicNo", 0);
		goodsSku1.put("barcode", "");
		goodsSku1.put("retailGoodsSkuList", retailGoodsSku1);
		goodsSku1.put("skuContent", skuContent1);
		JSONObject goodsSku1Object = JSONObject.fromObject(goodsSku1);
		
		Map<Object ,Object> retailGoodsSkuList2 = new HashMap<Object,Object>();
		retailGoodsSkuList2.put("marketPrice", 10000);
		retailGoodsSkuList2.put("moneyUnit", "CNY");
		retailGoodsSkuList2.put("retailPrice", 10000);
		retailGoodsSkuList2.put("stocks", 1000);
		JSONArray retailGoodsSku2 = JSONArray.fromObject(retailGoodsSkuList2);
		
		Map<Object ,Object> skuContentMap2 = new HashMap<Object,Object>();
		skuContentMap2.put("skuSetId", 1075741873);
		skuContentMap2.put("skuSetName", "高矮");
		skuContentMap2.put("skuId", 1075741822);
		skuContentMap2.put("skuValue", "矮");
		JSONArray skuContent2 = JSONArray.fromObject(skuContentMap2);
		
		Map<Object ,Object> goodsSku2 = new HashMap<Object,Object>();
		goodsSku2.put("skupicNo", 0);
		goodsSku2.put("barcode", "");
		goodsSku2.put("retailGoodsSkuList", retailGoodsSku2);
		goodsSku2.put("skuContent", skuContent2);
		JSONObject goodsSku2Object = JSONObject.fromObject(goodsSku2);
		
		ArrayList<Object> goodsSkuArrayList = new ArrayList<Object>();
		goodsSkuArrayList.add(goodsSku1Object);
		goodsSkuArrayList.add(goodsSku2Object);
		
		JSONArray goodsSkuList = JSONArray.fromObject(goodsSkuArrayList);
		
		Map<Object ,Object> component = new HashMap<Object,Object>();
		component.put("text", "test");
		JSONObject componentObject = JSONObject.fromObject(component);
		
		Map<Object ,Object> detail = new HashMap<Object,Object>();
		detail.put("type", "richText");
		detail.put("component", componentObject);
		JSONArray detailList = JSONArray.fromObject(detail);
		
		Object[][] keyValueList = new Object[][]{
				{"goodsName","dxssdfs"},
				{"catNo","536898771"},
				{"mainpicNo","5708"},
				{"picNos",ListEmpty},
				{"goodsBrief",""},
				{"onshelf","1"},
				{"isFarefree","1"},
				{"faretmpltNo",""},
				{"brandNo",""},
				{"detail",detailList},
				{"goodsSkuList",goodsSkuList}
				}; 
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			goodsId = responseObject.getString("data");
			System.out.println("添加商品成功! 商品id为："+goodsId+"\n");
		}
		else{
			System.out.println("创建商品失败!"+GM.response+"\n");
		}
	}
	
//	@Test
	public void UpdateGoods(){
		String url = LoadAPIInfo.url+"/api/goodsmng/goods";
		GM.Init("put",url);
		JSONArray ListEmpty = JSONArray.fromObject("[]");
		Map<Object ,Object> component = new HashMap<Object,Object>();
		component.put("text", "<iframe src='https://v.qq.com/iframe/player.html?vid=c0374ggk8h2&tiny=0&auto=0'></iframe>");
		JSONObject componentObject = JSONObject.fromObject(component);
		Map<Object ,Object> detail = new HashMap<Object,Object>();
		detail.put("type", "richText");
		detail.put("component", componentObject);
		JSONArray detailList = JSONArray.fromObject(detail);
		
		Object[][] keyValueList = new Object[][]{
				{"goodsId",goodsId},
				{"goodsName","xooxxoox"},
				{"catNo","536898771"},
				{"mainpicNo","5708"},
				{"picNos",ListEmpty},
				{"goodsBrief",""},
				{"faretmpltNo",""},
				{"brandNo",""},
				{"detail",detailList},
				{"redefineFlag",1}
				}; 
		
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("修改商品成功! \n");
		}
		else{
			System.out.println("修改商品失败!"+GM.response+"\n");
		}
	}
	
//	@Test
	public void GetGoods(){
		System.out.println(getCurrent.Time());
		String url = LoadAPIInfo.url+"/api/goodsmng/goods";
		GM.Init("get",url);
		Object[][] keyValueList = new Object[][]{}; 
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("查询商品成功! \n");
		}
		else{
			System.out.println("查询商品失败!"+GM.response+"\n");
		}
		System.out.println(getCurrent.Time());
	}
	
//	@Test
	public void GetGoodsDetail(){
		System.out.println(getCurrent.Time());
		String url = LoadAPIInfo.url+"/api/goodsmng/goods/goodsdetail";
		GM.Init("get",url);
		Object[][] keyValueList = new Object[][]{
				{"goodsId",goodsId}
				}; 
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("查询商品详情成功! \n");
		}
		else{
			System.out.println("查询商品详情失败!"+GM.response+"\n");
		}
		System.out.println(getCurrent.Time());
	}
	
//	@Test
	public void PutGoodsOnshelf(){
		System.out.println(getCurrent.Time());
		String url = LoadAPIInfo.url+"/api/goodsmng/goods";
		GM.Init("put",url);
		ArrayList<String> goodsList = new ArrayList<String>();
		goodsList.add(goodsId);
		JSONArray goodsIdList = JSONArray.fromObject(goodsList);
		
		Object[][] keyValueList = new Object[][]{
				{"goodsIdList",goodsIdList},
				{"onshelf",1}
				}; 
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("上架商品成功! \n");
		}
		else{
			System.out.println("上架商品失败!"+GM.response+"\n");
		}
		System.out.println(getCurrent.Time());
	}
	
	
//	@Test
	public void GetGoodsAggregate(){
		System.out.println(getCurrent.Time());
		String url = LoadAPIInfo.url+"/api/goodsmng/goods/aggregate";
		GM.Init("get",url);
		ArrayList<String> goodsList = new ArrayList<String>();
		goodsList.add(goodsId);
		JSONArray goodsIdList = JSONArray.fromObject(goodsList);
		
		Object[][] keyValueList = new Object[][]{
				{"goodsIdList",goodsIdList}
				}; 
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("查询商品价格信息成功! \n");
		}
		else{
			System.out.println("查询商品价格信息失败!"+GM.response+"\n");
		}
		System.out.println(getCurrent.Time());
	}
	
	//gd103170
//	@Test
	public void getGoodsSku(){
		String url = LoadAPIInfo.url+"/api/goodsmng/goods/goodssku";
		GM.Init("get",url);
		if(goodsId != null ){
			Object[][] keyValueList = new Object[][]{
					{"goodsId",goodsId}
					}; 

			GM.Test(httpClient, keyValueList);
			System.out.println("response="+GM.response);
			goodsSkuId = GM.response.substring(GM.response.indexOf("goodsskuId")+13, GM.response.indexOf("goodsskuId")+21);
			JSONObject responseObject = JSONObject.fromObject(GM.response);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			if(0 == returnValue){
				System.out.println("获取商品SKU成功! \n");
			}
			else{
				System.out.println("获取商品SKU失败!"+GM.response+"\n");
			}
		}
	}
	
//	@Test
	public void updateGoodsSkuSales(){
		
		String url = LoadAPIInfo.url+"/api/goodsmng/goods/goodssku/sales";
		GM.Init("put",url);
		if(goodsSkuId != null){
			Object[][] keyValueList = new Object[][]{
					{"deltaSalesVol",2},
					{"goodsskuId",goodsSkuId},
					{"moneyUnit","CNY"}
					}; 

			GM.Test(httpClient, keyValueList);
			System.out.println("response="+GM.response);
			JSONObject responseObject = JSONObject.fromObject(GM.response);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			if(0 == returnValue){
				System.out.println("增加商品SKU销量成功! \n");
			}
			else{
				System.out.println("增加商品SKU销量失败!"+GM.response+"\n");
			}
		}
	}
	
//	@Test
	public void updateGoodsSkuStocks(){
		
//		String url = LoadAPIInfo.url+"/api/goodsmng/goods/goodssku/stock";
		String url = "https://uat.wemart.cn/api/goodsmng/goods/goodssku/stock";
		GM.Init("put",url);
		if(goodsSkuId != null){
			Object[][] keyValueList = new Object[][]{
					{"deltaStock",-4},
					{"goodsskuId",goodsSkuId},//"sku12818"
					{"moneyUnit","CNY"}
					}; 

			GM.Test(httpClient, keyValueList);
			System.out.println("response="+GM.response);
			JSONObject responseObject = JSONObject.fromObject(GM.response);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("修改商品SKU库存成功! \n");
			} else {
				Reporter.log("修改商品SKU库存失败!"+GM.response+"\n");
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
//	@Test
	public void updateSkuPrice(){
		String url = LoadAPIInfo.url+"/api/goodsmng/goods/goodssku/price";
		GM.Init("put",url);
		if(goodsSkuId != null){
//			GetDataDB DB = new GetDataDB();
//			String database = "wmplatform_gm";
//			String table = "tbl_gm_retailgoodssku";
//			String target = "goodssku_id";
//			String condition = "goods_id = '"+goodsId+"'";
//			ArrayList<String> SkuList = DB.sql(database, table, target, condition);
			
			Map<Object ,Object> goodsSkuPriceListMap = new HashMap<Object,Object>();
			goodsSkuPriceListMap.put("goodsskuId", goodsSkuId);
			goodsSkuPriceListMap.put("moneyUnit", "CNY");
			goodsSkuPriceListMap.put("price", 9999);
			goodsSkuPriceListMap.put("marketPrice", 10000);
			JSONArray goodsSkuPriceList = JSONArray.fromObject(goodsSkuPriceListMap);
			
			Object[][] keyValueList = new Object[][]{
					{"goodsSkuPriceList",goodsSkuPriceList},
					}; 

			GM.Test(httpClient, keyValueList);
			System.out.println("response="+GM.response);
			JSONObject responseObject = JSONObject.fromObject(GM.response);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			if(0 == returnValue){
				goodsId = responseObject.getString("data");
				System.out.println("更新商品SKU价格成功! \n");
			}
			else{
				System.out.println("更新商品SKU价格失败!"+GM.response+"\n");
			}
		}
	}
	
	@Test
	public void DeleteGoods(){
		System.out.println(getCurrent.Time());
		String url = LoadAPIInfo.url+"/api/goodsmng/goods";
		GM.Init("delete",url);
		ArrayList<String> goodsList = new ArrayList<String>();
		goodsList.add(goodsId);
		JSONArray goodsIdList = JSONArray.fromObject(goodsList);
		
		Object[][] keyValueList = new Object[][]{
				{"goodsIdList",goodsIdList}
				}; 
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("删除商品成功! \n");
		}
		else{
			System.out.println("删除商品失败!"+GM.response+"\n");
		}
		System.out.println(getCurrent.Time());
	}
	
}