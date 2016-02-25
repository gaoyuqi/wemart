package cn.wemart.goodsmng.group;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.ExcelUtil;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.getCurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

	private static Log log = LogFactory.getLog(Group.class);


	/**
	 * 对应30401号接口（创建商品分组）
	 */
//	@Test
	public static void CreateGroupAPITest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		//组装json
		Map<Object ,Object> subGroupList1 = new HashMap<Object,Object>();
		subGroupList1.put("groupName", "For Tracy Group Empty SubGroup");
		subGroupList1.put("navSortno", 1);
		
		Map<Object ,Object> subGroupList2 = new HashMap<Object,Object>();
		subGroupList2.put("groupName", "For Tracy Group Empty SubGroup");
		subGroupList2.put("navSortno", 2);
		
		Map<Object ,Object> subGroupList3 = new HashMap<Object,Object>();
		subGroupList3.put("groupName", "For Tracy Group Empty SubGroup");
		subGroupList3.put("navSortno", 3);
		
		List<Object> subGroupList = new ArrayList<Object>();
//		subGroupList.add(subGroupList1);
//		subGroupList.add(subGroupList2);
//		subGroupList.add(subGroupList3);
					
		JSONArray subGroupListArray = JSONArray.fromObject(subGroupList);
		Object[][] keyValueList = new Object[][] {
				{"groupName","For Tracy Empty Group"},
				{"parentNo","0"},
				{"subGroupList",subGroupListArray}
				};

		String url = LoadAPIInfo.url + LoadAPIInfo.createGoodsGroupAPI;

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			String groupId = responsejson.getString("data");
			System.out.println("创建商品分组成功！商品分组id为："+groupId+"\n");
			log.info("test");
		}
		else{
			System.out.println("创建商品分组失败！"+responseEntiy+"\n");
		}
	}
	
	/**
	 * 对应30404号接口（获取商户自定义分组）
	 */
	@Test
	public static void GetGroupAPITest() {
		log.info(LoadAPIInfo.createGoodsGroupAPI+" 执行开始！");
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		Object[][] keyVlaueList = new Object[][] {
				{"groupNo","723"}
				};

		String url = LoadAPIInfo.url + LoadAPIInfo.createGoodsGroupAPI;

		log.info(getCurrent.Time());
		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyVlaueList);
		log.info("response=" + responseEntiy);
		System.out.println("response=" + responseEntiy);
		log.info(getCurrent.Time());
		System.out.println(getCurrent.Time());

		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		
		ExcelUtil.ExcelUtilWrite(LoadAPIInfo.createGoodsGroupAPI, responseEntiy, returnValue);
		
		if (0 == returnValue) {
			log.info("获取商品分组成功！");
			System.out.println("获取商品分组成功！\n");
			JSONArray data = JSONArray.fromObject(responsejson.getString("data"));
			for(Object o:data){
//				log.info(o.toString());
				System.out.println(o.toString());
			}
		}
		else{
			log.info("获取商品分组失败！"+responseEntiy);
			System.out.println("获取商品分组失败！"+responseEntiy+"\n");
		}
		log.info(LoadAPIInfo.createGoodsGroupAPI+" 执行结束！\n");
	}
	
	/**
	 * 对应30402号接口（编辑商品分组）
	 */
//	@Test
	public static void EditGroupAPITest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		Object[][] keyVlaueList = new Object[][] {
				{"groupName","groupName[i]"},
				{"groupNo","723"},
				{"navSortno","456"},
				};

		String url = LoadAPIInfo.url + LoadAPIInfo.createGoodsGroupAPI;

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyVlaueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("修改商品分组成功！\n");
		}
		else{
			System.out.println("修改商品分组失败！"+responseEntiy+"\n");
		}
	}
	
	/**
	 * 对应30403号接口（删除商品分组）
	 */
//	@Test
	public static void DeleteGroupAPITest() {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		Object[][] keyVlaueList = new Object[][] {
//				{"groupNo","723"}
				};

		String url = LoadAPIInfo.url + LoadAPIInfo.createGoodsGroupAPI;

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteDelete.getDeleteMethodResponse(httpclient,url, keyVlaueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());

		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("删除商品分组成功！\n");
		}
		else{
			System.out.println("删除商品分组失败！"+responseEntiy+"\n");
		}
	}

}
