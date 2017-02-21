package cn.wemart.TestCase;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.testng.annotations.Test;

import cn.wemart.util.ReadExcel;

public class test {
	@Test
	public void getExtensionFunction(){
		String mobileList = ReadExcel.Do(4,0);
		System.out.println(mobileList);
		String[] mobile = mobileList.split(",");
		for(int j=0;j<mobile.length;j++){
			System.out.println(mobile[j]);
		}
	}
	
	public Map<Object ,Object> setPostPara(Object[][] keyValueList) throws DocumentException{
		Map<Object ,Object> map = new HashMap<Object,Object>();
		for(int i = 0;i<keyValueList.length;i++)
		{
			map.put(keyValueList[i][0],keyValueList[i][1]);
		}
		return map;
	}
	
//	Map<Object, Object> postMap;
//	try {
//		postMap = setPostPara(keyValueList);
//		JSONObject object = JSONObject.fromObject(postMap);
//		System.out.println(url+"?para="+object.toString());
//	} catch (DocumentException e) {
//		e.printStackTrace();
//	}
//	/*

}
