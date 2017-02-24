package cn.wemart.TestCase.Mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.TestNG.Assertion;
import cn.wemart.objectbase.ObjectBase;
import cn.wemart.util.GetDataDB;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class GetSellerGroupTest {
	CloseableHttpClient httpClient = HttpClients.createDefault();
	ObjectBase GSGT = new ObjectBase();
	String url = LoadAPIInfo.url+"/api/shopping/shop/group";
	
	@Test
	public void GetSellerGroup() {
		GSGT.Init("get", url);
		String dataBaseName = "wmplatform_um";
		String tableName = "tbl_um_seller";
		String outPutColumnName = "sell_id";
		String condition = "where sell_id >=1 limit 5";
		GetDataDB getData = new GetDataDB();
		ArrayList<String> sellerIdList = getData.sql(dataBaseName, tableName, outPutColumnName, condition);
		
		for(String sellerId: sellerIdList){
			Map<String,Object> keyValueList = new HashMap<String,Object>();
			keyValueList.put("sellerId", sellerId);
			String returnValue = GSGT.Test(httpClient, keyValueList);
			System.out.println(GSGT.response);
			if (Assertion.verifyEqual(returnValue, "0")) {
				Reporter.log("获取店铺分组成功！");
			} else {
				Reporter.log("获取店铺分组失败！\n Response：" + GSGT.response);
			}
			Reporter.log(getCurrent.Time());
		}
	}
	
}
