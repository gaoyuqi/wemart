package cn.wemart.TestCase;

import net.sf.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.shelfManagement.ShelfManagement;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.ReadExcel;
import com.TestNG.Assertion;


@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteSetHomePageTest {
	
//	@Test
	public String getShelf(String mobile,String password,String sellerId){
		ShelfManagement SM = new ShelfManagement("get");
		Object[][] keyValueList = new Object[][]{};
		String returnValue = SM.test(mobile, password, sellerId, keyValueList);
		String shelfNo = JSONObject.fromObject(SM.response).getJSONArray("data").getJSONObject(0).getString("shelfNo");
		System.out.println(shelfNo);
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("查询货架成功！");
		}
		else{
			Reporter.log("查询货架失败！Response："+SM.response);
		}
		return shelfNo;
	}
	
	
	@Test
	public void setHomePage(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		String url = LoadAPIInfo.url + "/api/usermng/seller/decoration/shelf/homepage";
		ShelfManagement SM = new ShelfManagement("put",url);
		
		for(int j=0;j<mobile.length;j++){
			String shelfNo = getShelf(mobile[j], password[j], sellerId[j]);
			Object[][] keyValueList = new Object[][]{
					{"shelfNo",shelfNo}
					};
			String returnValue = SM.test(mobile[j], password[j], sellerId[j], keyValueList);
			System.out.println(SM.response);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("设置主页成功！");
			}
			else{
				Reporter.log("设置主页失败！Response："+SM.response);
			}
		}
	}
}
