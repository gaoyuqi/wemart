package cn.wemart.TestCase;

import net.sf.json.JSONObject;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.channelManagment.CreateChannel;
import cn.wemart.channelManagment.GetChannelInfo;
import cn.wemart.channelManagment.GetMyChannel;
import cn.wemart.channelManagment.IsAllowEnterWemartPlatform;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;

@Listeners({ com.TestNG.AssertionListener.class })
public class ExecuteChannelManagmentTest {

//	@Test
	public  void CreateChannelTest(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String channelNameList = ReadExcel.Do(2,0);
		String logoUrlList = ReadExcel.Do(2,1);
		String applyRemarkList = ReadExcel.Do(2,2);
		String allowSellerSelfList = ReadExcel.Do(2,3);
		String[] name = channelNameList.split(",");
		String[] logoUrl = logoUrlList.split(",");
		String[] applyRemark = applyRemarkList.split(",");
		String[] allowSellerSelf = allowSellerSelfList.split(",");
		CreateChannel createChannel = new CreateChannel();
		for(int i=0;i<name.length;i++){
			Object[][] keyValueList = new Object[][] { 
					{ "name", name[i]},
					{ "logoUrl", logoUrl[i]},
					{ "applyRemark", applyRemark[i] },
					{ "allowSellerSelf", allowSellerSelf[i] } // 0表示平台商户只能使用平台的收款方式, 1表示平台商户可以设置自己的收款方式
					};
			String returnValue = createChannel.test(mobile[i], password[i],keyValueList);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("创建渠道成功！");
			} else {
				Reporter.log("创建渠道失败！\n Response：" + createChannel.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
	
	@Test
	public void GetMyChannelTest() {

		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		GetMyChannel getMyChannel = new GetMyChannel();
		for(int i=0;i<mobile.length;i++){
			String returnValue = getMyChannel.test(mobile[i],password[i]);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("获取我的渠道成功！");
			} else {
				Reporter.log("获取我的渠道失败！\n Response：" + getMyChannel.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
//	@Test
	public void GetChannelTest() {
		String channelIdList = ReadExcel.Do(2,4);
		String[] channelId = channelIdList.split(",");
		GetChannelInfo getChannelInfo = new GetChannelInfo();
		for(int i=0;i<channelId.length;i++){
			String returnValue = getChannelInfo.test(channelId[i]);
			System.out.println(getChannelInfo.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("获取渠道信息成功！");
			} else {
				Reporter.log("获取渠道信息失败！\n Response：" + getChannelInfo.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
//	@Test
	public void IsAllowEnterWemartPlatformTest() {
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		IsAllowEnterWemartPlatform isAllowEnterWemartPlatform = new IsAllowEnterWemartPlatform();
		for(int i=0;i<mobile.length;i++){
			JSONObject responseObject = JSONObject.fromObject(isAllowEnterWemartPlatform.test(mobile[i],password[i]));
			String platformFlag = responseObject.getJSONObject("data").getString("platformFlag");
			System.out.println(isAllowEnterWemartPlatform.response);
			if (Assertion.verifyEqual(platformFlag, "0")||Assertion.verifyEqual(platformFlag, "1")) {
				if(platformFlag.equals("1")){
					Reporter.log("该管理员不是平台管理员！");
				}
			} else {
				Reporter.log("该管理员是平台管理员！\n Response：" + isAllowEnterWemartPlatform.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}


}
