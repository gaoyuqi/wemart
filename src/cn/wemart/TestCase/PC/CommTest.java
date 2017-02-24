package cn.wemart.TestCase.PC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.GetDataDB;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({ com.TestNG.AssertionListener.class })
public class CommTest {

	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";

	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase CT = new ObjectBase();

	public CommTest() {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}

	@Test
	public void CreateCommGoods() {
		String url = LoadAPIInfo.url + "/api/goodsmng/comm";
		CT.Init("post", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_mmktgoods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE mmkt_id = 110 AND seller_id != "+sellerId+" limit 1";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		for (String goodsId : goodsIdList) {
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("goodsId", goodsId);
			keyValueList.put("invoiceFlag", 2);
			
			String returnValue = CT.Test(httpClient, keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("创建分销商品成功！");
				System.out.println("创建分销商品成功！");
			} else {
				Reporter.log("创建分销商品  " + goodsId + " 失败！\n Response："+ CT.response);
				System.out.println("创建分销商品  " + goodsId + " 失败！\n Response："+ CT.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}

	@Test
	public void UpdateCommGoods() {
		String url = LoadAPIInfo.url + "/api/goodsmng/comm";
		CT.Init("put", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_commgoods";
		String outPutColumnName = "goods_id";
		String condition = "where commseller_id = "+sellerId+" limit 1";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		for (String goodsId : goodsIdList) {
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("goodsId", goodsId);
			keyValueList.put("redefineFlag", 0);
			
			String returnValue = CT.Test(httpClient, keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("修改分销商品成功！");
			} else {
				Reporter.log("修改分销商品  " + goodsId + " 失败！\n Response："+ CT.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
	@Test
	public void UpdateCommGoodsSku() {
		String url = LoadAPIInfo.url + "/api/goodsmng/comm/goodssku";
		CT.Init("put", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_commgoodssku";
		String outPutColumnName = "commgoodssku_id";
		String condition = "where commseller_id = "+sellerId+" limit 1";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsSkuIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		ArrayList<Map<String,Object>> goodsSkuList = new ArrayList<Map<String,Object>>();
		for (String goodsSkuId : goodsSkuIdList) {
			Map<String,Object> goodsSku = new HashMap<String,Object>();
			goodsSku.put("goodsskuId", goodsSkuId);
			goodsSku.put("moneyUnit", "CNY");
			goodsSku.put("marketPrice", 2000);
			goodsSkuList.add(goodsSku);
		}
		JSONArray commGoodsSkuList = JSONArray.fromObject(goodsSkuList);
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("goodsskuList", commGoodsSkuList);
		keyValueList.put("redefineFlag", 0);
		
		String returnValue = CT.Test(httpClient, keyValueList);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("修改分销商品SKU成功！");
		} else {
			Reporter.log("修改分销商品SKU失败！\n Response："+ CT.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void GetMycommGoods() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt/aggregate/mycomm";
		CT.Init("get", url);
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			String returnValue = CT.Test(httpClient, keyValueList);
			System.out.println(CT.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("查询我已分销商品成功！");
			} else {
				Reporter.log("查询我已分销商品失败！\n Response："+ CT.response);
			}
			Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void GetSupplyGoodsCommSellers() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt/goods/commsellers";
		CT.Init("get", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_commgoods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE supplyseller_id = "+sellerId+" AND mmkt_id = 110 limit 1";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("goodsIdList", goodsIdList);
		
		String returnValue = CT.Test(httpClient, keyValueList);
		System.out.println(CT.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("查询商品的分销商成功！");
		} else {
			Reporter.log("查询商品的分销商失败！\n Response：" + CT.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void CancelCommGoods() {
		String url = LoadAPIInfo.url + "/api/goodsmng/comm";
		CT.Init("delete", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_commgoods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE commseller_id = "+sellerId+" AND mmkt_id = 110 limit 1";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		for(String goodsId:goodsIdList){
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("goodsId", goodsId);
			
			String returnValue = CT.Test(httpClient, keyValueList);
			System.out.println(CT.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("取消分销商品成功！");
			} else {
				Reporter.log("取消分销商品失败！\n Response：" + CT.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}

	public static void main(String[] args) {
		CommTest ct = new CommTest();
//		ct.UpdateCommGoodsSku();
		ct.GetMycommGoods();
	}

}
