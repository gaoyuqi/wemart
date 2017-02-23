package cn.wemart.TestCase;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
	//QsC0db1GY9D8j+g5tWjQj9qZQ9OAruzVpg1ZRBnD1ejTaw1JkOf3lA1hbU8lHbiM33FY7EVPSBKOP0bTMwzLjWs2YKg45lgjqHgc5XzL7LYWzq1BuPzCRG23s4kfqTygGJAtm2px4LmUy3DwST88EGKkDmB3e2gnQ1nb2iz2gBE=
	//bG8WKCILS8aleOgxM6FSoKkyN29og+Zt81pGEgbBgNrP4edhWOIvMmhLkWzooVW2cb3wqzomeszH+lZYN25jPKoHn6gAXdz1wvZs5iQRzEy/Z60gR2ChTr1rrmplD9PaztnRzJrnrfkIuhtq6tnVy9+KHyy0wxYE3jbzsRJ/H6k=

	
//	@Test
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
			
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("cityNo",cityNo[j]);
			keyValueList.put("district",district[j]);
			keyValueList.put("streetAddr",streetAddr[j]);
			keyValueList.put("mobileNo",mobileNo[j]);
			keyValueList.put("name",name[j]);
			keyValueList.put("isDefault",flag);
			
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
	
//	@Test
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
			if(isDefault[j].equals("1")){
				flag=true;
			}
			getBuyerAddress.test(ScenId[j],BuyerId[j]);
			JSONObject responseObject = JSONObject.fromObject(getBuyerAddress.response);
			JSONArray addrNoArray = responseObject.getJSONArray("data");
			String addrNo = addrNoArray.getJSONObject(0).getString("addrNo");
			
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("cityNo",cityNo[j]);
			keyValueList.put("district",district[j]);
			keyValueList.put("streetAddr",streetAddr[j]);
			keyValueList.put("mobileNo",mobileNo[j]);
			keyValueList.put("name",name[j]);
			keyValueList.put("addrNo",addrNo);
			keyValueList.put("isDefault",flag);
			
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
	
//	@Test
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
	
	
//	@Test
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
