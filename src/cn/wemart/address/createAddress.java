package cn.wemart.address;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.buyerLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class createAddress {

	/**
	 * 对应20703号接口（创建买家地址）
	 * @param args
	 */
//	@Test
	public static void createBuyerAddress() {

		String scenId = "10";
		String buyerId = "123000";
		String scenType = "2";
		CloseableHttpClient httpclient = buyerLogin.setHttpClient(scenId, buyerId, scenType);
		String url = LoadAPIInfo.url + "/api/usermng/buyer/address";

		Object[][] keyValueList = new Object[][] {
				{ "cityNo", "73" }, // 城市ID // (必填)
				{ "district", "浦东新区" }, // 地区（必填）
				{ "streetAddr", "博霞路" }, // 街道地址
				{ "mobileNo", "15800979816" },// 联系人手机号", （必填）
				{ "name", "西谚" },// 联系人名称（必填）
				{ "isDefault", false },// 是否默认地址（必填）
//				{ "scenType", scenId },// 场景类型（必填）
//				{ "scenId", scenId }, // 场景ID（必填）
//				{ "buyerId", buyerId },// 买家ID（必填）
				};
		
//		  "cityNo" : "1", // 城市ID (必填)
//		    “district": "浦东新区", // 地区（必填）
//		    "streetAddr : "1",    // 街道地址
//		    "mobileNo" : "15800979816”，//联系人手机号",    （必填）
//		    “name” ："西谚"，//联系人名称（必填）
//		    "isDefault"：false,//是否默认地址（必填）
		System.out.println(getCurrent.Time());
		String response = ExecutePost.getPostMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
		
		JSONObject responseobject = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responseobject.getString("returnValue"));
		
		if(0 == returnValue){
			System.out.println("创建买家地址成功！\n");
		}
		else{
			System.out.println("创建买家地址失败！"+response+"\n");
		}

	}
	
	/**
	 * 对应20704号接口（编辑买家地址）
	 */
	@Test
	public static void editBuyerAddress() {

		String scenId = "10";
		String buyerId = "123000";
		String scenType = "2";
		CloseableHttpClient httpclient = buyerLogin.setHttpClient(scenId, buyerId, scenType);
		String url = LoadAPIInfo.url + "/api/usermng/buyer/address";

		Object[][] keyValueList = new Object[][] {
				{ "addrNo", "117" },
				{ "cityNo", "1" }, // 城市ID // (必填)
				{ "district", "海淀区" }, // 地区（必填）
				{ "streetAddr", "博霞路44号" }, // 街道地址
				{ "mobileNo", "15800979816" },// 联系人手机号", （必填）
				{ "name", "西谚" },// 联系人名称（必填）
				{ "isDefault", 1 },// 是否默认地址（必填）
//				{ "scenType", scenId },// 场景类型（必填）
//				{ "scenId", scenId }, // 场景ID（必填）
//				{ "buyerId", buyerId },// 买家ID（必填）
				};
		
		System.out.println(getCurrent.Time());
		String response = ExecutePut.getPutMethodResponse(httpclient, url, keyValueList);
		System.out.println("response="+response);
		System.out.println(getCurrent.Time());
		
		JSONObject responseobject = JSONObject.fromObject(response);
		Integer returnValue = Integer.parseInt(responseobject.getString("returnValue"));
		
		if(0 == returnValue){
			System.out.println("修改买家地址成功！\n");
		}
		else{
			System.out.println("修改买家地址失败！"+response+"\n");
		}

	}

}
