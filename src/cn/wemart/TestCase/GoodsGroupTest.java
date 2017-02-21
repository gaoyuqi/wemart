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
import cn.wemart.mobileBuyerManagment.UpdateAddress;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class GoodsGroupTest {
	static String groupId = null;
	String goodsSkuId = null;
	String mobile="13818881111";
	String password="123456";
	String sellerId="234";
	
	CloseableHttpClient httpClient = HttpClients.createDefault();
	GoodsManagement GM = new GoodsManagement();
	
	public GoodsGroupTest(){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}
	
	@Test
	public void CreateGoodsGroup(){
		String url = LoadAPIInfo.url+"/api/goodsmng/group";
		GM.Init("post", url);

		Map<Object ,Object> SubGoodsGroup1 = new HashMap<Object,Object>();
		SubGoodsGroup1.put("groupName", "lv2-1");
		SubGoodsGroup1.put("navSortno", 1);
		JSONObject SubGoodsGroup1Object = JSONObject.fromObject(SubGoodsGroup1);
		
		Map<Object ,Object> SubGoodsGroup2 = new HashMap<Object,Object>();
		SubGoodsGroup2.put("groupName", "lv2-2");
		SubGoodsGroup2.put("navSortno", 2);
		JSONObject SubGoodsGroup2Object = JSONObject.fromObject(SubGoodsGroup2);
		
		ArrayList<Object> subGroup = new ArrayList<Object>();
		subGroup.add(SubGoodsGroup1Object);
		subGroup.add(SubGoodsGroup2Object);
		JSONArray subGroupList = JSONArray.fromObject(subGroup);
		
		JSONArray ListEmpty = JSONArray.fromObject("[]");
		
		Object[][] keyValueList = new Object[][]{
				{"groupName","group-level2"},
				{"parentNo",0},
				{"subGroupList",subGroupList}
				}; 
		
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			groupId = JSONObject.fromObject(responseObject.getString("data")).getString("groupNo");
			System.out.println("创建商品分组成功! 商品分组id为："+groupId+"\n");
		}
		else{
			System.out.println("创建商品分组失败!"+GM.response+"\n");
		}
	}
	
	@Test
	public void UpdateGoodsGroup(){
		String url = LoadAPIInfo.url+"/api/goodsmng/group";
		GM.Init("put", url);

		Object[][] keyValueList = new Object[][]{
				{"groupName","groupTest"},
				{"groupNo",groupId},
				{"navSortno",0}
				}; 
		
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("更新商品分组成功! \n");
		}
		else{
			System.out.println("更新商品分组失败!"+GM.response+"\n");
		}
	} 
	
	@Test
	public void GetGoodsGroup(){
		String url = LoadAPIInfo.url+"/api/goodsmng/group";
		GM.Init("get", url);

		Object[][] keyValueList = new Object[][]{}; 
		
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取商品分组成功! \n");
		}
		else{
			System.out.println("获取商品分组失败!"+GM.response+"\n");
		}
	}
	
	@Test
	public void DeleteGoodsGroup(){
		String url = LoadAPIInfo.url+"/api/goodsmng/group";
		GM.Init("delete", url);

		Object[][] keyValueList = new Object[][]{
				{"groupNo",groupId}
				}; 
		
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("删除商品分组成功! \n");
		}
		else{
			System.out.println("删除商品分组失败!"+GM.response+"\n");
		}
	}
	
	public static void main(String[] args) {
		GoodsGroupTest GGT = new GoodsGroupTest();
		GGT.CreateGoodsGroup();
		GGT.UpdateGoodsGroup();
		GGT.GetGoodsGroup();
		GGT.DeleteGoodsGroup();
	}
}