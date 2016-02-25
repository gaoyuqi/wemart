package cn.wemart.goodsmng.goods;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class skuPrice {
	
	/**
	 * 对应30302号接口（编辑商品SKU价格）
	 */
	@Test
	public static void EditPriceTest() {
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		String url = LoadAPIInfo.url+LoadAPIInfo.editSKUPriceAPI;
		Object[][] keyValueList = new Object[][]{
				{"goodsskuId","sku3"},
				{"price",100},
				{"marketPrice",111},
				{"moneyUnit","CNY"}
				};
		
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("修改商品SKU价格成功! \n");
		}
		else{
			System.out.println("修改商品SKU价格失败!"+responseEntiy+"\n");
		}
	}

}
