package cn.wemart.TestCase.PC;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.userManagment.Channel;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;


@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteShopApplyManagementTest {
	public String getShopApplyList(String mobile, String password, String channelId){
		String response;
		String url = LoadAPIInfo.url+"/api/usermng/channel/sellerapplylist";
		Channel channel = new Channel("get", url);
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("pageIndex",0);
		keyValueList.put("pageSize",10);
		keyValueList.put("status",1);
		String returnValue = channel.test(mobile, password, channelId, keyValueList);
		response = channel.response;
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("获取店铺申请列表成功！");
		} else {
			Reporter.log("获取店铺申请列表失败！\n Response：" + channel.response);
		}
		return response;
	}
	
	@Test
	public void checkApplyShop(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String channelIdList = ReadExcel.Do(0,4);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] channelId = channelIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller";
		Channel channel = new Channel("get", url);
		for(int j=0;j<mobile.length;j++){
			String response = getShopApplyList(mobile[j], password[j], channelId[j]);
			if(response.contains("applyNo")){
				JSONObject data = JSONObject.fromObject(response).getJSONObject("data");
				String 	applyNo = data.getJSONArray("sellerApplyList").getJSONObject(0).getString("applyNo");
				
				Map<String,Object> keyValueList = new HashMap<String,Object>();
				keyValueList.put("applyNo",applyNo);
				keyValueList.put("status","2");
				keyValueList.put("approveRemark","这里是审核说明");
				String returnValue = channel.test(mobile[j], password[j], channelId[j], keyValueList);
				if (Assertion.verifyEqual(returnValue, "0")) {
					Reporter.log("审核店铺成功！");
				} else {
					Reporter.log("审核店铺失败！\n Response：" + channel.response);
				}
				Reporter.log(getCurrent.Time());
			}
			else{
				Reporter.log("该渠道没有未审核的店铺！");
				Reporter.log(getCurrent.Time());
			}
		}
	}

}
