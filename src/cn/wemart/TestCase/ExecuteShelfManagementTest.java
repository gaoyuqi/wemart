package cn.wemart.TestCase;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import cn.wemart.shelfManagement.ShelfManagement;
import cn.wemart.util.ReadExcel;
import com.TestNG.Assertion;


@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteShelfManagementTest {
	public List<String> shelfNo = new ArrayList<String>();
	
	@Test
	public void getShelf(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		ShelfManagement SM = new ShelfManagement("get");
		Object[][] keyValueList = new Object[][]{};
		for(int j=0;j<mobile.length;j++){
			String returnValue = SM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("查询货架成功！");
			}
			else{
				Reporter.log("查询货架失败！Response："+SM.response);
			}
		}
	}
	
	@Test
	public void createShelf(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		ShelfManagement SM = new ShelfManagement("post");
		JSONArray empty = JSONArray.fromObject("[]");
		Object[][] keyValueList = new Object[][]{
				{"shelfType","3"},//货架类型：1、系统生成主页货架 2、分组货架  3、自定义   4 视频页面     (必填)
				{"shelfName","自定义页面"},
				{"bgColor","#eeeeee"},
				{"shareText","微信分享"},
				{"imageUrl" ,"http://imgcache8.qiniudn.com/de7088f1-f856-18c4-0382-692a7fe4ceb6"},
				{"shelfContent",empty}
				};
		for(int j=0;j<mobile.length;j++){
			String returnValue = SM.test(mobile[j], password[j], sellerId[j], keyValueList);
			shelfNo.add(JSONObject.fromObject(SM.response).getString("data"));
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("创建货架成功！");
			}
			else{
				Reporter.log("创建货架失败！Response："+SM.response);
			}
		}
	}
	
	@Test
	public void updateShelf(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		ShelfManagement SM = new ShelfManagement("put");
		JSONArray empty = JSONArray.fromObject("[]");
		for(int j=0;j<mobile.length;j++){
			Object[][] keyValueList = new Object[][]{
					{"shelfType","3"},//货架类型：1、系统生成主页货架 2、分组货架  3、自定义   4 视频页面     (必填)
					{"shelfNo",shelfNo.get(j)},
					{"shelfName","自定义页面"},
					{"bgColor","#eeeeee"},
					{"shareText","微信分享"},
					{"imageUrl" ,"http://imgcache8.qiniudn.com/de7088f1-f856-18c4-0382-692a7fe4ceb6"},
					{"shelfContent",empty}
					};
			String returnValue = SM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("更新货架成功！");
			}
			else{
				Reporter.log("更新货架失败！Response："+SM.response);
			}
		}
	}
	
	@Test
	public void deleteShelf(){
		String mobileList = ReadExcel.Do(0,0);
		String passwordList = ReadExcel.Do(0,1);
		String sellerIdList = ReadExcel.Do(0,3);
		String[] mobile = mobileList.split(",");
		String[] password = passwordList.split(",");
		String[] sellerId = sellerIdList.split(",");
		ShelfManagement SM = new ShelfManagement("delete");
		
		for(int j=0;j<mobile.length;j++){
			Object[][] keyValueList = new Object[][]{
					{"shelfNo",shelfNo.get(j)}
					};
			String returnValue = SM.test(mobile[j], password[j], sellerId[j], keyValueList);
			if(Assertion.verifyEqual(returnValue, "0")){
				Reporter.log("删除货架成功！");
			}
			else{
				Reporter.log("删除货架失败！Response："+SM.response);
			}
		}
	}
	
}
