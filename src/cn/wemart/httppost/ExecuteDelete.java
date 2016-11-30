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
import org.apache.http.client.methods.HttpDelete;
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
public class ExecuteDelete {
	
	private static Log log = LogFactory.getLog(ExecuteDelete.class);
	
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
	 * @param url,需要传入的参数键值对
	 */
	public static String getDeleteMethodResponse(CloseableHttpClient httpclient,String url,Object[][] keyValueList) {
		String xmlStr =null;
		String Str;
		try {
			
			Map<Object, Object> postMap;
			postMap = setPostPara(keyValueList);
			JSONObject object = JSONObject.fromObject(postMap);
			List<NameValuePair> postPara = new ArrayList<NameValuePair>();
			postPara.add(new BasicNameValuePair("para",object.toString()));
			log.info(url+"?para="+object.toString()+"");
			System.out.println(url+"?para="+object.toString()+"");
			
//			httpclient = HttpClients.createDefault();
			HttpDelete httpdelete = new HttpDelete(url);
	        String sendstr = EntityUtils.toString(new UrlEncodedFormEntity(postPara,HTTP.UTF_8));  
	        httpdelete.setURI(new URI(httpdelete.getURI().toString() + "?" + sendstr));
			CloseableHttpResponse response = httpclient.execute(httpdelete);
			
			
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
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		return xmlStr;
	}
	

}
