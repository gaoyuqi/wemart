package cn.wemart.TestCase;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;
import com.TestNG.Assertion;

@Listeners({ com.TestNG.AssertionListener.class })
public class ScenarioTest {
	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";

	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase ST = new ObjectBase();
	String url = LoadAPIInfo.url + "/api/usermng/scenario";

	public ScenarioTest() {
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}

	@Test
	public void createScenario() {
		ST.Init("post", "url");
		Object[][] keyValueList = new Object[][] { { "scenType", "1" },
				{ "name", "我的app场景" }, { "params", "{}" } };
		String returnValue = ST.Test(httpClient, keyValueList);
		System.out.println(ST.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("创建场景成功！");
		} else {
			Reporter.log("创建场景失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}

	@Test
	public void getScenario() {
		ST.Init("get", "url");
		Object[][] keyValueList = new Object[][] { { "scenType", "1" }, };
		String returnValue = ST.Test(httpClient, keyValueList);
		System.out.println(ST.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("获取场景成功！");
		} else {
			Reporter.log("获取场景失败！\n Response：" + ST.response);
		}
		Reporter.log(getCurrent.Time());
	}

}
