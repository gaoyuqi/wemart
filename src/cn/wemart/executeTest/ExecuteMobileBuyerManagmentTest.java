package cn.wemart.executeTest;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.TestNG.Assertion;

import cn.wemart.mobileBuyerManagment.CreateAddress;
import cn.wemart.mobileBuyerManagment.DeleteBuyerAddress;
import cn.wemart.mobileBuyerManagment.GetBuyerAddress;
import cn.wemart.mobileBuyerManagment.SetBuyerAddressIsDefault;
import cn.wemart.mobileBuyerManagment.UpdateAddress;
import cn.wemart.util.ReadExcel;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteMobileBuyerManagmentTest {
	
	@Test
	public void GetBuyerAddressTest() throws UnsupportedEncodingException{
		GetBuyerAddress getBuyerAddress = new GetBuyerAddress();
		String BuyerIdList = ReadExcel.Do(1,1);
		String ScenIdList = ReadExcel.Do(1,0);
		String[] ScenId = ScenIdList.split(",");
		String[] BuyerId = BuyerIdList.split(",");
		for (int j = 0; j < BuyerId.length; j++) {
			String returnValue = getBuyerAddress.test(ScenId[j],BuyerId[j]);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("获取买家地址成功！");
			}
			else{
				Reporter.log("获取买家地址失败！\n Response："+getBuyerAddress.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	

	
	@Test
	public void CreateBuyerAddressTest() throws UnsupportedEncodingException{
		CreateAddress createAddress = new CreateAddress();
		String ScenIdList = ReadExcel.Do(1,0);
		String BuyerIdList = ReadExcel.Do(1,1);
		String cityNoList = ReadExcel.Do(1,2);
		String districtList = ReadExcel.Do(1,3);
		String streetAddrList = ReadExcel.Do(1,4);
		String mobileNoList = ReadExcel.Do(1,5);
		String nameList = ReadExcel.Do(1,6);
		String isDefaultList = ReadExcel.Do(1,7);
		
		String[] ScenId = ScenIdList.split(",");
		String[] BuyerId = BuyerIdList.split(",");
		String[] cityNo = cityNoList.split(",");
		String[] district = districtList.split(",");
		String[] streetAddr = streetAddrList.split(",");
		String[] mobileNo = mobileNoList.split(",");
		String[] name = nameList.split(",");
		String[] isDefault = isDefaultList.split(",");
		
		for (int j = 0; j < BuyerId.length; j++) {
			boolean flag = false;
			if(isDefault[j].equals("true")){
				flag=true;
			}
			Object[][] keyValueList = new Object[][]{
					{"cityNo",cityNo[j]},
					{"district",district[j]},
					{"streetAddr",streetAddr[j]},
					{"mobileNo",mobileNo[j]},
					{"name",name[j]},
					{"isDefault",flag}
					};
			String returnValue = createAddress.test(ScenId[j],BuyerId[j],keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("创建买家地址成功！");
			}
			else{
				Reporter.log("创建买家地址失败！\n Response："+createAddress.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
	@Test
	public void UpdateBuyerAddressTest() throws UnsupportedEncodingException{
		GetBuyerAddress getBuyerAddress = new GetBuyerAddress();
		UpdateAddress updateAddress = new UpdateAddress();
		String ScenIdList = ReadExcel.Do(1,0);
		String BuyerIdList = ReadExcel.Do(1,1);
		String cityNoList = ReadExcel.Do(1,2);
		String districtList = ReadExcel.Do(1,3);
		String streetAddrList = ReadExcel.Do(1,4);
		String mobileNoList = ReadExcel.Do(1,5);
		String nameList = ReadExcel.Do(1,6);
		String isDefaultList = ReadExcel.Do(1,7);
		
		String[] ScenId = ScenIdList.split(",");
		String[] BuyerId = BuyerIdList.split(",");
		String[] cityNo = cityNoList.split(",");
		String[] district = districtList.split(",");
		String[] streetAddr = streetAddrList.split(",");
		String[] mobileNo = mobileNoList.split(",");
		String[] name = nameList.split(",");
		String[] isDefault = isDefaultList.split(",");
		
		for (int j = 0; j < BuyerId.length; j++) {
			boolean flag = false;
			if(isDefault[j].equals("true")){
				flag=true;
			}
			getBuyerAddress.test(ScenId[j],BuyerId[j]);
			JSONObject responseObject = JSONObject.fromObject(getBuyerAddress.response);
			JSONArray addrNoArray = responseObject.getJSONArray("data");
			String addrNo = addrNoArray.getJSONObject(0).getString("addrNo");
			
			Object[][] keyValueList = new Object[][]{
					{"cityNo",cityNo[j]},
					{"district",district[j]},
					{"streetAddr",streetAddr[j]},
					{"mobileNo",mobileNo[j]},
					{"name",name[j]},
					{"addrNo",addrNo},
					{"isDefault",flag}
					};
			String returnValue = updateAddress.test(ScenId[j],BuyerId[j],keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("创建买家地址成功！");
			}
			else{
				Reporter.log("创建买家地址失败！\n Response："+updateAddress.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
	@Test
	public void SetBuyerAddressIsDefaultTest() throws UnsupportedEncodingException{
		GetBuyerAddress getBuyerAddress = new GetBuyerAddress();
		SetBuyerAddressIsDefault setBuyerAddressIsDefault = new SetBuyerAddressIsDefault();
		String BuyerIdList = ReadExcel.Do(1,1);
		String ScenIdList = ReadExcel.Do(1,0);
		String[] ScenId = ScenIdList.split(",");
		String[] BuyerId = BuyerIdList.split(",");
		for (int j = 0; j < BuyerId.length; j++) {
			getBuyerAddress.test(ScenId[j],BuyerId[j]);
			JSONObject responseObject = JSONObject.fromObject(getBuyerAddress.response);
			JSONArray addrNoArray = responseObject.getJSONArray("data");
			String addrNo = addrNoArray.getJSONObject(0).getString("addrNo");
			String returnValue = setBuyerAddressIsDefault.test(ScenId[j],BuyerId[j],addrNo,getBuyerAddress.httpClient);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("设置买家默认地址成功！");
			}
			else{
				Reporter.log("设置买家默认地址失败！\n Response："+setBuyerAddressIsDefault.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
	
	@Test
	public void DeleteBuyerAddressTest() throws UnsupportedEncodingException{
		GetBuyerAddress getBuyerAddress =new GetBuyerAddress();
		DeleteBuyerAddress deleteBuyerAddress = new DeleteBuyerAddress();
		String ScenIdList = ReadExcel.Do(1,0);
		String BuyerIdList = ReadExcel.Do(1,1);
		String[] ScenId = ScenIdList.split(",");
		String[] BuyerId = BuyerIdList.split(",");
		
		for (int j = 0; j < BuyerId.length; j++) {
			getBuyerAddress.test(ScenId[j],BuyerId[j]);
			JSONObject responseObject = JSONObject.fromObject(getBuyerAddress.response);
			JSONArray addrNoArray = responseObject.getJSONArray("data");
			String addrNo = addrNoArray.getJSONObject(0).getString("addrNo");
			String returnValue = deleteBuyerAddress.test(ScenId[j],BuyerId[j],addrNo,getBuyerAddress.httpClient);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("删除买家地址成功！");
			}
			else{
				Reporter.log("删除买家地址失败！\n Response："+deleteBuyerAddress.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	


}
