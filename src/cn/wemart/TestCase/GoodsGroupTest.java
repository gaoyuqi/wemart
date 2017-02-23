package cn.wemart.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.GetDataDB;
import cn.wemart.util.LoadAPIInfo;

@Listeners({com.TestNG.AssertionListener.class})
public class GoodsGroupTest {
	static String groupId = null;
	String goodsSkuId = null;
	String mobile="13818881111";
	String password="123456";
	String sellerId="234";
	
	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase GM = new ObjectBase();
	
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
		
		Map<Object ,Object> SubGoodsGroup2 = new HashMap<Object,Object>();
		SubGoodsGroup2.put("groupName", "lv2-2");
		SubGoodsGroup2.put("navSortno", 2);
		
		ArrayList<Object> subGroupList = new ArrayList<Object>();
		subGroupList.add(SubGoodsGroup1);
		subGroupList.add(SubGoodsGroup2);
		
		JSONArray ListEmpty = JSONArray.fromObject("[]");
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("groupName","group-level2");
		keyValueList.put("parentNo",0);
		keyValueList.put("subGroupList",subGroupList);
		
		
		
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

		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("groupName","groupTest");
		keyValueList.put("groupNo",groupId);
		keyValueList.put("navSortno",0);
		
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
		Map<String,Object> keyValueList = new HashMap<String,Object>();
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

		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("groupNo",groupId);
		
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
	
	@Test
	public void GoodsAddtoGroup(){
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_goods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE seller_id = 234";
		String tableName2 = "tbl_gm_group";
		String condition2 = condition+" and group_no not in (SELECT parent_no FROM "+tableName2+" "+condition+" and parent_no != 0);";
		String outPutColumnName2 = "group_no";
		String url = LoadAPIInfo.url+"/api/goodsmng/group/goods";
		GM.Init("post", url);
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName, outPutColumnName, condition);
		ArrayList<String> groupIdList = getData.sql(dataBaseName, tableName2, outPutColumnName2, condition2);
		ArrayList<Map<String,Object>> goodsList = new ArrayList<Map<String,Object>>();
		for(String goods : goodsIdList){
			Map<String,Object> goodsId = new HashMap<String,Object>();
			goodsId.put("goodsId", goods);
			goodsId.put("ingrpSortno", 0);
			goodsList.add(goodsId);
		}
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("groupNoList",groupIdList);
		keyValueList.put("goodsList",goodsList);
		
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("商品添加分组成功! \n");
		}
		else{
			System.out.println("商品添加分组失败!"+GM.response+"\n");
		}
	}
	
	
	@Test
	public void GroupDeleteGoods(){
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_goods";
		String outPutColumnName = "goods_id";
		String condition = "WHERE seller_id = 234";
		String tableName2 = "tbl_gm_group";
		String condition2 = condition+" and group_no not in (SELECT parent_no FROM "+tableName2+" "+condition+" and parent_no != 0);";
		String outPutColumnName2 = "group_no";
		String url = LoadAPIInfo.url+"/api/goodsmng/group/goods";
		GM.Init("delete", url);
		GetDataDB getData = new GetDataDB();
		ArrayList<String> goodsIdList = getData.sql(dataBaseName, tableName, outPutColumnName, condition);
		ArrayList<String> groupIdList = getData.sql(dataBaseName, tableName2, outPutColumnName2, condition2);
		ArrayList<Map<String,Object>> goodsList = new ArrayList<Map<String,Object>>();
		for(String goods : goodsIdList){
			Map<String,Object> goodsId = new HashMap<String,Object>();
			goodsId.put("goodsId", goods);
			goodsList.add(goodsId);
		}
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("groupNoList",groupIdList);
		keyValueList.put("goodsList",goodsList);
		
		
		GM.Test(httpClient, keyValueList);
		System.out.println("response="+GM.response);
		JSONObject responseObject = JSONObject.fromObject(GM.response);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("分组删除商品成功! \n");
		}
		else{
			System.out.println("分组删除商品失败!"+GM.response+"\n");
		}
	}
	
	
	public static void main(String[] args) {
		GoodsGroupTest GGT = new GoodsGroupTest();
		GGT.CreateGoodsGroup();
	}
}