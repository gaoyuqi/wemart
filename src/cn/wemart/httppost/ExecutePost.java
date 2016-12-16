package cn.wemart.httppost;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;


/**
 * 
 * @author sean
 *
 */
public class ExecutePost {
	
	private static Log log = LogFactory.getLog(ExecutePost.class);
	
	/**
	 * 组装请求参数
	 * @param keyVlaueList
	 */
	public static Map<Object ,Object> setPostPara(Object[][] keyValueList) throws DocumentException{
		Map<Object ,Object> map = new HashMap<Object,Object>();
		for(int i = 0;i<keyValueList.length;i++)
		{
			map.put(keyValueList[i][0],keyValueList[i][1]);
		}
		return map;
	}
	
	/**
	 * 获取接口返回字符串
	 * @param url,需要传入的参数键值对
	 */
	public static String getPostMethodResponse(CloseableHttpClient httpClient,String url,Object[][] keyValueList){
		String xmlStr =null;
		try {
			Map<Object ,Object> postMap = setPostPara(keyValueList);
			JSONObject object = JSONObject.fromObject(postMap);
			List<NameValuePair> postPara = new ArrayList<NameValuePair>();
			postPara.add(new BasicNameValuePair("para",object.toString()));
			HttpPost post = new HttpPost(url);

			System.out.println(url+"?para="+object.toString());
			post.setEntity(new UrlEncodedFormEntity(postPara,HTTP.UTF_8));
			CloseableHttpResponse response = httpClient.execute(post);
		
			String responseEntiy = EntityUtils.toString(response.getEntity(),"UTF-8");
			xmlStr = responseEntiy.trim();

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}catch (DocumentException e) {
				e.printStackTrace();
			}
		return xmlStr;
	}

}
