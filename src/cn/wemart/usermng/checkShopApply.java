package cn.wemart.usermng;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

public class checkShopApply {
	
	private static Log log = LogFactory.getLog(getShopInfo.class);
	
	/**
	 * 对应20512号接口（通过店铺申请审核）
	 */
	public static void main(String[] args) {
		
		String[] applyNo = LoadUserInfo.applyNo.split(",");
		String[] status = LoadUserInfo.status.split(",");
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.checkShopApplyAPI;
		
//		for (int i=0 ;i<applyNo.length;i++) {
		Object[][] keyVlaueList = new String[][] { 
					{ "applyNo", "101asdf" },
					{ "status", "3" },
					{ "approveRemark", "pass!" }
					};
		
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url,keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time());
			
			JSONObject object = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(object.getString("returnValue"));
			if (0 == returnValue){
				System.out.println("审核店铺成功！\n");
			}
			else{
				System.out.println("审核店铺失败！"+ responseEntiy +"\n");
			}
//		}
	}

}
