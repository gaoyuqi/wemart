package cn.wemart.goodsmng.goods;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.goodsmng.cat.getCat;
import cn.wemart.goodsmng.group.Group;
import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadInfo;
import cn.wemart.util.LoadUserInfo;
import cn.wemart.util.getCurrent;

/**
 * 
 * @author sean
 *
 */
public class goods {
	
	private static Log log = LogFactory.getLog(Group.class);


	/**
	 * 对应30201号接口（创建商品）
	 */
//	@Test()
	public static void CreateGoodsAPITest(){

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.createGoodsAPI;
		JSONObject spuvalContentempty = JSONObject.fromObject("{}");
		List<String> picList = new ArrayList<String>();
		picList.add("2");
		picList.add("3");
		JSONArray picListArray = JSONArray.fromObject(picList);
		
		List<String> catNoList = getCat.CatList();
		for(String catNO:catNoList){
		
			Object[][] keyVlaueList = new Object[][]{
				{"catNo",catNO},
				{"goodsName","sean's"+catNO+" goods"},
				{"spuvalContent",spuvalContentempty},
				{"goodsCode",89757},
				{"mainpicNo",1},
				{"picNos",picListArray},
				{"goodsBrief","brief Link"},
				{"detail","sean's goods "+catNO+" detail"}
				};
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy.trim());
			System.out.println(getCurrent.Time()); 
		
			
			JSONObject responseObject = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			
			if(0 == returnValue){
				String goodsId = responseObject.getString("data");
				System.out.println("添加商品成功! 商品id为："+goodsId+"\n");
			}
			else{
				System.out.println("添加商品失败!"+responseEntiy+"\n");
			}
		}
	}
	
	
	/**
	 * 对应30202号接口（编辑商品）
	 */
//	@Test()
	public static void EditGoodsAPITest(){

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.createGoodsAPI;
		JSONObject spuvalContentempty = JSONObject.fromObject("{}");
		List<String> picList = new ArrayList<String>();
		picList.add("2");
		picList.add("3");
		JSONArray picListArray = JSONArray.fromObject(picList);
		List<String> catNoList = getCat.CatList();
		for(String catNO:catNoList){
		
			Object[][] keyVlaueList = new Object[][]{
				{"goodsId","gd248"},
				{"catNo",catNO},
				{"goodsName","sean's "+catNO+" goods"},
				{"spuvalContent",spuvalContentempty},
				{"goodsCode",89757},
				{"mainpicNo",1},
				{"picNos",picListArray},
				{"goodsBrief","brief Link "+catNO},
				{"detail","sean's goods "+catNO+" detail"}
				};
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()); 
		
			
			JSONObject responseObject = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			
			if(0 == returnValue){
				System.out.println("编辑商品成功! \n");
			}
			else{
				System.out.println("编辑商品失败!"+responseEntiy+"\n");
			}
		}
	}
	
	
	/**
	 * 对应30204号接口（查询商品列表）
	 */
	@Test()
	public static void getGoodsAPITest(){
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		String url = LoadAPIInfo.url+LoadAPIInfo.createGoodsAPI;
		
		//组装json
		List<Object> goodsList = new ArrayList<Object>();
//		goodsList.add("gd1948");
//		goodsList.add("gd248");
		
		JSONArray goodsListJSONArray = JSONArray.fromObject(goodsList);
		
		Object[][] keyVlaueList = new Object[][]{
					{"goodsIdList",goodsListJSONArray},
					{"goodsStatus",-1}, //-1：全部；1：上架；0：下架；默认-1
					{"sourceType",-1}, //-1：全部；1：自营；2：分销；默认-1
					{"goodsName",""},
//					{"groupNo","11"},
					{"pageIndex",0},
					{"pageSize",1000}}; //查询商品
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()+"\n"); 
		
			JSONObject responseObject = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			if(0 == returnValue){
				System.out.println("查询商品成功! \n");
			}
			else{
				System.out.println("查询商品失败!"+responseEntiy+"\n");
			}
	}
	
	
	/**
	 * 对应30203号接口（删除商品列表）
	 */
//	@Test()
	public static void deleteGoodsAPITest(){
		CloseableHttpClient httpclient = shopLogin.setHttpClient();

		String url = LoadAPIInfo.url+LoadAPIInfo.createGoodsAPI;
		
		//组装json
		List<Object> goodsList = new ArrayList<Object>();
//		goodsList.add("gd1948");
		goodsList.add("gd248");
		
		JSONArray goodsListJSONArray = JSONArray.fromObject(goodsList);
		
		Object[][] keyVlaueList = new Object[][]{
					{"goodsIdList",goodsListJSONArray}
					};
			
			System.out.println(getCurrent.Time()); 
			String responseEntiy = ExecuteDelete.getDeleteMethodResponse(httpclient,url, keyVlaueList);
			System.out.println("response="+responseEntiy);
			System.out.println(getCurrent.Time()+"\n"); 
		
			JSONObject responseObject = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
			if(0 == returnValue){
				System.out.println("删除商品成功! \n");
			}
			else{
				System.out.println("删除商品失败!"+responseEntiy+"\n");
			}
	}

}
