package cn.wemart.TestCase.PC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.GetDataDB;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class BrandTest {
	
	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";
	String brandNo = null;
	
	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase BT = new ObjectBase();
	String url = LoadAPIInfo.url + "/api/goodsmng/brand";
	
	public BrandTest(){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}
	
	@Test
	public void CreateBrand(){
		BT.Init("post", url);
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("cnName", "品牌1");
		keyValueList.put("enName", "pinpai1");
		keyValueList.put("logo", "121");
		keyValueList.put("content", "品牌1");
		
		String returnValue = BT.Test(httpClient, keyValueList);
		System.out.println(BT.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			brandNo = JSONObject.fromObject(BT.response).getString("data");
			Reporter.log("创建品牌成功！");
			System.out.println("创建品牌成功");
		} else {
			Reporter.log("创建品牌失败！\n Response：" + BT.response);
			System.out.println("创建品牌失败！\n Response：" + BT.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void UpdateBrand(){
		BT.Init("put", url);
		if(brandNo != null){
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("brandNo", brandNo);
			keyValueList.put("cnName", "品牌2");
			keyValueList.put("enName", "pinpai2");
			keyValueList.put("logo", "121");
			keyValueList.put("content", "品牌2");
	
			String returnValue = BT.Test(httpClient, keyValueList);
			System.out.println(BT.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				
				Reporter.log("修改品牌成功！");
				System.out.println("修改品牌成功！");
			} else {
				Reporter.log("修改品牌失败！\n Response：" + BT.response);
				System.out.println("修改品牌失败！\n Response：" + BT.response);
			}
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void GetBrand(){
		BT.Init("get", url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("pageIndex",0);  //页码          (必选)
		keyValueList.put("pageSize",10); //分页大小    (必选)
		
		String returnValue = BT.Test(httpClient, keyValueList);
		System.out.println(BT.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("查询品牌库成功！");
			System.out.println("查询品牌库成功");
		} else {
			Reporter.log("查询品牌库失败！\n Response：" + BT.response);
			System.out.println("查询品牌库失败！\n Response：" + BT.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void GetBrandList(){
		String url = LoadAPIInfo.url + "/api/goodsmng/brand/item";
		BT.Init("get", url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		
		String returnValue = BT.Test(httpClient, keyValueList);
		System.out.println(BT.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("查询品牌库成功！");
			System.out.println("查询品牌库成功");
		} else {
			Reporter.log("查询品牌库失败！\n Response：" + BT.response);
			System.out.println("查询品牌库失败！\n Response：" + BT.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public void DeleteBrand(){
		BT.Init("delete", url);
		if(brandNo != null){ 
			ArrayList<String> brandNoList = new ArrayList<String>();
			brandNoList.add(brandNo);
			
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("brandNos", brandNoList);
			
			String returnValue = BT.Test(httpClient, keyValueList);
			System.out.println(BT.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("删除品牌成功！");
				System.out.println("删除品牌成功");
			} else {
				Reporter.log("删除品牌失败！\n Response：" + BT.response);
				System.out.println("删除品牌失败！\n Response：" + BT.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}

	
	public static void main(String[] args) {
		BrandTest bt = new BrandTest();
		bt.CreateBrand();
		bt.UpdateBrand();
		bt.GetBrand();
		bt.GetBrandList();
		bt.DeleteBrand();
	}
}

