package cn.wemart.executeTest;

import java.io.UnsupportedEncodingException;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.mobileBuyerManagment.GetBuyerAddress;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteMobileBuyerManagmentTest {
	
	@Test
	public static void GetAddress() throws UnsupportedEncodingException{
		String BuyerIdList = ReadExcel.Do(1);
		String ScenIdList = ReadExcel.Do(0);
		String[] ScenId = ScenIdList.split(",");
		String[] BuyerId = BuyerIdList.split(",");
		for (int j = 0; j < BuyerId.length; j++) {
			GetBuyerAddressTest(ScenId[j],BuyerId[j]);
		}
	}
	
	public static void GetBuyerAddressTest(String ScenId,String BuyerId) throws UnsupportedEncodingException{
		String returnValue = GetBuyerAddress.test(ScenId,BuyerId);
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("获取买家地址成功！");
		}
		else{
			Reporter.log("获取买家地址失败！\n Response："+GetBuyerAddress.response);
		}
		Reporter.log(getCurrent.Time());
	}
	

}
