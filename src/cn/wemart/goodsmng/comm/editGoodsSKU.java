package cn.wemart.goodsmng.comm;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;

import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class editGoodsSKU {
	private static Log log = LogFactory.getLog(editGoodsSKU.class);

	/**
	 * 对应30508号接口（编辑分销商品SKU）
	 */
	public static void main(String[] args) {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+"/api/goodsmng/comm/goodssku";
		
		Object[][] keyValueList = new Object[][]{
				{"goodsskuId","gd248"},
				{"moneyUnit","CNY"},
				{"marketPrice","111"},
				{"retailPrice","100"},
				{"salesVolDelta","15"}
				};
		
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("修改分销商品SKU成功！\n");
		}
		else{
			System.out.println("修改分销商品SKU失败！"+responseEntiy+"\n");
		}
	}
		

}
