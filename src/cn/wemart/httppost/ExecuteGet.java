package cn.wemart.httppost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;


/**
 * 
 * @author sean
 *
 */
public class ExecuteGet {
	
	private static Log log = LogFactory.getLog(ExecuteGet.class);

	/**
	 * 组装请求参数
	 * @param keyVlaueList
	 */
	public static Map<Object ,Object> setPostPara(Object[][] keyValueList) throws DocumentException{
		Map<Object ,Object> map = new HashMap<Object ,Object>();
		for(int i = 0;i<keyValueList.length;i++)
		{
			map.put(keyValueList[i][0],keyValueList[i][1]);
		}
		return map;
	}
	
	/**
	 * 获取接口返回字符串
	 * @param url
	 * @throws DocumentException 
	 * @throws URISyntaxException 
	 */
	public static String getGetMethodResponse(CloseableHttpClient httpClient,String url,Object[][] keyValueList) {
		String xmlStr =null;
		String Str;
		try {
			
			//把键值对转换成JSON格式串
			Map<Object, Object> postMap;
			postMap = setPostPara(keyValueList);
			JSONObject object = JSONObject.fromObject(postMap);
			
			List<NameValuePair> postPara = new ArrayList<NameValuePair>();
			postPara.add(new BasicNameValuePair("para",object.toString()));
			log.info(url+"?para="+object.toString()+"");
			System.out.println(url+"?para="+object.toString()+"");
			
			//发送转码过的字符串，发送
//			httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
	        String sendstr = EntityUtils.toString(new UrlEncodedFormEntity(postPara,HTTP.UTF_8));  
			httpget.setURI(new URI(httpget.getURI().toString() + "?" + sendstr));
			CloseableHttpResponse response = httpClient.execute(httpget);
			
			
			//处理response的中文编码
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
			StringBuffer sb = new StringBuffer();
			while ((Str = reader.readLine()) != null) {
				sb.append(Str).append("\n");
			}
			xmlStr = sb.toString().trim();

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		return xmlStr;
	}
	
}
