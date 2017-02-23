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
import org.apache.http.client.methods.HttpPut;
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
public class ExecutePut {
	private static Log log = LogFactory.getLog(ExecutePut.class);
	
	/**
	 * 获取接口返回字符串
	 * @param url,需要传入的参数键值对
	 */
	public static String getPutMethodResponse(CloseableHttpClient httpClient,String url,Map<String ,Object> keyValueList) {
		String xmlStr =null;
		String Str;
		try {
			JSONObject object = JSONObject.fromObject(keyValueList);
			List<NameValuePair> postPara = new ArrayList<NameValuePair>();
			postPara.add(new BasicNameValuePair("para",object.toString()));
//			log.info(url+"?para="+object.toString()+"");
			System.out.println(url+"?para="+object.toString()+"");
			
			HttpPut httpput = new HttpPut(url);
	        String sendstr = EntityUtils.toString(new UrlEncodedFormEntity(postPara,HTTP.UTF_8));  
	        httpput.setURI(new URI(httpput.getURI().toString() + "?" + sendstr));
			CloseableHttpResponse response = httpClient.execute(httpput);
			
			
			//解决中文编码问题
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
			}
		return xmlStr;
	}

}
