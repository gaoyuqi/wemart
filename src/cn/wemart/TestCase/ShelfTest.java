package cn.wemart.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;

import com.TestNG.Assertion;


@Listeners({com.TestNG.AssertionListener.class})
public class ShelfTest {
	public List<String> shelfNo = new ArrayList<String>();
	
	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";

	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase ST = new ObjectBase();
	String url = LoadAPIInfo.url + "/api/usermng/seller/decoration/shelf";
	
	public ShelfTest() {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}
	
	@Test
	public void getShelf(){
		ST.Init("get", url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
			String returnValue = ST.Test(httpClient,keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询货架成功！");
			}
			else{
				Reporter.log("查询货架失败！Response："+ST.response);
			}
	}
	
	@Test
	public void createShelf(){
		ST.Init("post", url);
		JSONArray empty = JSONArray.fromObject("[]");
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("shelfType","3");
		keyValueList.put("shelfName","自定义页面");
		keyValueList.put("bgColor","#eeeeee");
		keyValueList.put("shareText","微信分享");
		keyValueList.put("imageUrl" ,"http://imgcache8.qiniudn.com/de7088f1-f856-18c4-0382-692a7fe4ceb6");
		keyValueList.put("shelfContent",empty);
		
			String returnValue = ST.Test(httpClient,keyValueList);
			shelfNo.add(JSONObject.fromObject(ST.response).getString("data"));
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("创建货架成功！");
			}
			else{
				Reporter.log("创建货架失败！Response："+ST.response);
			}
	}
	
	@Test
	public void updateShelf(){
		ST.Init("put", url);
		JSONArray empty = JSONArray.fromObject("[]");
		for(int j=0;j<shelfNo.size();j++){
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("shelfNo",shelfNo.get(j));
			keyValueList.put("shelfType","3");
			keyValueList.put("shelfName","自定义页面");
			keyValueList.put("bgColor","#eeeeee");
			keyValueList.put("shareText","微信分享");
			keyValueList.put("imageUrl" ,"http://imgcache8.qiniudn.com/de7088f1-f856-18c4-0382-692a7fe4ceb6");
			keyValueList.put("shelfContent",empty);
		
			String returnValue = ST.Test(httpClient,keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("更新货架成功！");
			}
			else{
				Reporter.log("更新货架失败！Response："+ST.response);
			}
		}
	}
	
	@Test
	public void deleteShelf(){
		ST.Init("delete", url);
		for(int j=0;j<shelfNo.size();j++){
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("shelfNo",shelfNo.get(j));
			String returnValue = ST.Test(httpClient,keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("删除货架成功！");
			}
			else{
				Reporter.log("删除货架失败！Response："+ST.response);
			}
		}
	}
	
	public static void main(String[] args) {
		ShelfTest st = new ShelfTest();
		st.getShelf();
	}
	
}
