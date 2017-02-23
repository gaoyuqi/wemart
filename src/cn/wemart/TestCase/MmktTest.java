package cn.wemart.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
public class MmktTest {

	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";

	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase ST = new ObjectBase();

	public MmktTest() {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}

	@Test
	public void SupplyGoods() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt";
		ST.Init("post", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_goods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE seller_id = "+sellerId+" limit5";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("goodsIdList", goodsIdList);
		keyValueList.put("isSupplied", 1);
		
		String returnValue = ST.Test(httpClient, keyValueList);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("将商品加入供货市场成功！");
		} else {
			Reporter.log("将商品加入供货市场失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}

	@Test
	public void UpdateSupplyGoodsInfo() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt";
		ST.Init("put", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_goods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE seller_id = "+sellerId+" limit 5";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		for (String goodsId : goodsIdList) {
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("goodsId", goodsId);
			keyValueList.put("invoiceFlag", 1);
			keyValueList.put("retailpriceFixed", 0);
			
			String returnValue = ST.Test(httpClient, keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("修改供货商品信息成功！");
			} else {
				Reporter.log("修改供货商品  " + goodsId + " 信息失败！\n Response："+ ST.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
	@Test
	public void UpdateSupplyGoodsSku() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt/goodssku";
		ST.Init("put", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_goodssku";
		String outPutColumnName = "goodssku_id";
		String condition = "WHERE seller_id = "+sellerId+" limit 5";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsSkuIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		for (String goodsSkuId : goodsSkuIdList) {
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("goodsskuId",goodsSkuId);
			keyValueList.put("moneyUnit","CNY");
			keyValueList.put("retailPrice",1000);
			keyValueList.put("supplyPrice",500);
			keyValueList.put("supplyTaxprice",600);
			
			String returnValue = ST.Test(httpClient, keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("修改供货商品SKU成功！");
			} else {
				Reporter.log("修改供货商品SKU  " + goodsSkuId + " 失败！\n Response："+ ST.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}

	@Test
	public void DeleteSupplyGoods() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt";
		ST.Init("delete", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_goods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE seller_id = "+sellerId;
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("goodsIdList", goodsIdList);
		
		String returnValue = ST.Test(httpClient, keyValueList);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("撤销供货成功！");
		} else {
			Reporter.log("撤销供货失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}

	@Test
	public void GetSupplyGoodsCount() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt/aggregate/count";
		ST.Init("get", url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("sellerId", sellerId);
		
		String returnValue = ST.Test(httpClient, keyValueList);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("查询所在渠道供货市场商品数量成功！");
		} else {
			Reporter.log("查询所在渠道供货市场商品数量失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void GetSupplyGoodsInfo() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt/aggregate";
		ST.Init("get", url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
//		keyValueList.put("sellerId", sellerId);
		
		String returnValue = ST.Test(httpClient, keyValueList);
		System.out.println(ST.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("查询所在渠道供货市场商品数量成功！");
		} else {
			Reporter.log("查询所在渠道供货市场商品数量失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void GetSupplyGoodsVariable() {
		String url = LoadAPIInfo.url + "/api/goodsmng/mmkt/aggregate/variable";
		ST.Init("get", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_mmktgoods";
		String outPutColumnName = "goods_id";
		String condition = "limit 1";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("goodsIdList", goodsIdList);
		
		String returnValue = ST.Test(httpClient, keyValueList);
		System.out.println(ST.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("查询供货市场商品价格库存信息成功！");
		} else {
			Reporter.log("查询供货市场商品价格库存信息失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	

	public static void main(String[] args) {
		MmktTest mt = new MmktTest();
//		mt.SupplyGoods();
//		mt.UpdateSupplyGoodsSku();
//		mt.UpdateSupplyGoodsInfo();
//		mt.DeleteSupplyGoods();
//		mt.GetSupplyGoodsCount();
//		mt.GetSupplyGoodsInfo();
		mt.GetSupplyGoodsVariable();
	}

}
