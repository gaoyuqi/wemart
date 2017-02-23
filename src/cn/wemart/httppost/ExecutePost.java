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
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
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
	 * 获取接口返回字符串
	 * @param url,需要传入的参数键值对
	 */
	public static String getPostMethodResponse(CloseableHttpClient httpClient,String url,Map<String,Object> keyValue){
		String xmlStr =null;
		try {
			JSONObject object = JSONObject.fromObject(keyValue);
			List<NameValuePair> postPara = new ArrayList<NameValuePair>();
			postPara.add(new BasicNameValuePair("para",object.toString()));
			HttpPost post = new HttpPost(url);
			System.out.println(url+"?para="+object.toString());
			post.setEntity(new UrlEncodedFormEntity(postPara,HTTP.UTF_8));
			CloseableHttpResponse response = httpClient.execute(post);
		
//			Header[] header = response.getAllHeaders();
//			for(Header x :header){
//				System.out.println(x);
//			}
			String responseEntiy = EntityUtils.toString(response.getEntity(),"UTF-8");
			xmlStr = responseEntiy.trim();

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return xmlStr;
	}

}
