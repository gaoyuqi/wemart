package cn.wemart.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import com.TestNG.Assertion;
import cn.wemart.objectbase.ObjectBase;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.GetDataDB;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class PostTemplateTest {
	
	String mobile = "13818881111";
	String password = "123456";
	String sellerId = "234";
	
	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase PTT = new ObjectBase();
	String url = LoadAPIInfo.url + "/api/goodsmng/faretmplt";
	
	public PostTemplateTest(){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.EnterShop(mobile, password, sellerId);
		httpClient = shopLogin.httpClient;
	}
	
	public void CreatePostTemplate(){
		PTT.Init("post", url);
		String dataBaseName = "wmplatform_gm";
		String tableName = "tbl_gm_citydef";
		String outPutColumnName = "city_no";
		String condition = "limit 5";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> cityNoList = getData.sql(dataBaseName, tableName,outPutColumnName, condition);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cityNoList", cityNoList);
		map.put("increFare", 5);
		map.put("increNum", 1);
		map.put("initFare", 10);
		map.put("initNum", 2);
		JSONArray fareList = JSONArray.fromObject(map);
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("fareList", fareList);
		keyValueList.put("faretmpltName", "faretmpltNameTest");

		String returnValue = PTT.Test(httpClient, keyValueList);
		System.out.println(PTT.response);
		if (Assertion.verifyEqual(returnValue, "0")) {
			Reporter.log("创建邮费模板成功！");
			System.out.println("创建邮费模板成功");
		} else {
			Reporter.log("创建邮费模板失败！\n Response：" + PTT.response);
			System.out.println("创建邮费模板失败！\n Response：" + PTT.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	public void UpdatePostTemplate(){
		
	}
	
	public void GetPostTemplate(){
		
	}
	
	public void DeletePostTemplate(){
		
	}

	public void GetGoodsPostTemplate(){
		
	}
	
	public static void main(String[] args) {
		PostTemplateTest ptt = new PostTemplateTest();
		ptt.CreatePostTemplate();
	}

}
