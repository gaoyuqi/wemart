package cn.wemart.function;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.usermng.IsNeedLogin;
import cn.wemart.usermng.ShopLogin;

public class getUserInfomation extends IsNeedLogin {

	@Test
	public static void test() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if (flag) {
			ShopLogin.EnterShop("13818881111", "123456", "234");
			httpclient = ShopLogin.httpclient;
		}
		ShopLogin.EnterShop("13818881111", "123456", "234");
	}
}
