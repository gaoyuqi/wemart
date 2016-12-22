package cn.wemart.executeTest;

import net.sf.json.JSONObject;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.extensionFunction.ExtensionFunction;
import cn.wemart.userManagment.Channel;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;


@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteExtensionFunctionTest {
	
	@Test
	public void getExtensionFunction(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		ExtensionFunction EF = new ExtensionFunction("get");
		Object[][] keyValueList = new Object[][]{};
		for(int j=0;j<mobile.length;j++){
			String returnValue = EF.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询扩展功能成功！");
			}
			else{
				Reporter.log("查询扩展功能失败！Response："+EF.response);
			}
		}
	}
	
	
	

	/*
	 * 获取扩张功能申请列表
	 */
	public String getExtensionFunctionApplyList(String mobile, String password, String channelId){
		String response;
		String url = LoadAPIInfo.url+"/api/usermng/channel/product";
		Channel channel = new Channel("get", url);
		Object[][] keyValueList = new Object[][]{
				{"pageIndex",0},
				{"pageSize",10},
				{"status",1}
				};
		String returnValue = channel.test(mobile, password, channelId, keyValueList);
		response = channel.response;
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("获取扩展功能申请列表成功！");
		} else {
			Reporter.log("获取扩展功能申请列表失败！\n Response：" + channel.response);
		}
		return response;
	}
	
	
	@Test
	public void checkExtensionFunctionApply(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String channelIdList = ReadExcel.Do(0,4);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] channelId = channelIdList.split(",");
		String url = LoadAPIInfo.url+"/api/usermng/seller/product";
		Channel channel = new Channel("get", url);
		for(int j=0;j<mobile.length;j++){
			String response = getExtensionFunctionApplyList(mobile[j], password[j], channelId[j]);
			if(response.contains("applyNo")){
				JSONObject data = JSONObject.fromObject(response).getJSONObject("data");
				String 	applyNo = data.getJSONArray("productApplyBeanList").getJSONObject(0).getString("applyNo");
				String 	sellerId = data.getJSONArray("productApplyBeanList").getJSONObject(0).getString("sellerId");
				Object[][] keyValueList = new Object[][]{
						{"applyNo",applyNo},
						{"sellerId",sellerId},
						{"approveStatus","3"},    // 2未通过，3通过
						{"approveRemark","这里是审核说明"}
						};
				String returnValue = channel.test(mobile[j], password[j], channelId[j], keyValueList);
				if (Assertion.verifyEqual(returnValue, "0")) {
					Reporter.log("审核扩展功能成功！");
				} else {
					Reporter.log("审核扩展功能失败！\n Response：" + channel.response);
				}
				Reporter.log(getCurrent.Time());
			}
			else{
				Reporter.log("该渠道没有未审核的扩展功能！");
				Reporter.log(getCurrent.Time());
			}
		}
	}
	
	

}
