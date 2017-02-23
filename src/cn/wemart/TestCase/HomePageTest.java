package cn.wemart.TestCase;
import java.util.HashMap;
import java.util.Map;

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

@Listeners({ com.TestNG.AssertionListener.class })
public class HomePageTest {

	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";

	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase HT = new ObjectBase();

	public HomePageTest() {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}

	// @Test
	public String getShelf() {
		String url = LoadAPIInfo.url + "/api/usermng/seller/decoration/shelf";
		HT.Init("get", url);
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		String returnValue = HT.Test(httpClient, keyValueList);
		String shelfNo = JSONObject.fromObject(HT.response).getJSONArray("data").getJSONObject(0).getString("shelfNo");
		System.out.println(shelfNo);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("查询货架成功！");
		} else {
			Reporter.log("查询货架失败！Response：" + HT.response);
		}
		return shelfNo;
	}

	@Test
	public void setHomePage() {
		String url = LoadAPIInfo.url + "/api/usermng/seller/decoration/shelf/homepage";
		HT.Init("put", url);
		String shelfNo = getShelf();
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("shelfNo", shelfNo);
		
		String returnValue = HT.Test(httpClient, keyValueList);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("设置主页成功！");
		} else {
			Reporter.log("设置主页失败！Response：" + HT.response);
		}
	}
	
	public static void main(String[] args) {
		HomePageTest ht = new HomePageTest();
		ht.setHomePage();
	}
}
