package cn.wemart.executeTest;

import cn.wemart.goodsManagement.GoodsManagement;
import cn.wemart.util.LoadAPIInfo;

public class CustomSKU {
	
	public void createCustomSKU(){
		String url = LoadAPIInfo.url + "/api/goodsmng/sku";
		GoodsManagement GM = new GoodsManagement("post", url);

		Object[][] keyValueList = new Object[][]{
				{"catNo","536897997"},      //必需参数，商品所在类目。注意该字段仅用于判定sku定义是否在系统分类之内，对自定义sku定义，创建时并不绑定catNo。
				{"goodsId","gd11"},   //可选参数，sku定义绑定的商品
				{"hasPicture",0},       //可选参数，默认值0，sku定义是否允许包含图片
				{"skuclsNo",111},       //可选参数，不填表示新增sku定义，填写表示新增sku定义包含值（即skudomainList）
				{"skuclsName","user define skucls"},
				{"skudomainList","[\"白色\",\"黑色\"]"}
				};
	}

}
