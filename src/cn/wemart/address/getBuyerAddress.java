package cn.wemart.address;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.usermng.buyerLogin;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getBuyerAddress {
	
	/**
	 * 对应20701/20702号接口（查询买家地址）
	 * @param args
	 */
	public static void main(String[] args) {
		
		String scenId = "1";
		String buyerId = "spp";
		String scenType = "1";
		CloseableHttpClient httpclient = buyerLogin.setHttpClient(scenId, buyerId, scenType);
		
		Object[][] keyValueList = new Object[][]{
				{"addrNo","91"}
				};
		
		String url = LoadAPIInfo.url + "/api/usermng/buyer/address";
		
		System.out.println(getCurrent.Time());
		String response = ExecuteGet.getGetMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
		
		JSONObject responseobejct = JSONObject.fromObject(response);
		Integer returnValue = Integer.valueOf(responseobejct.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("获取买家地址成功！\n");
		}
		else{
			System.out.println("获取买家地址失败！"+response+"\n");
		}
		
	}

}
