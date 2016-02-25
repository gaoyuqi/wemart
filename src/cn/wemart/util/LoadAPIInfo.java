package cn.wemart.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoadAPIInfo {

	public static String path = "./URL.properties";
	public static String url;
	public static String checkUserRegisterAPI;
	public static String saveUserAPI;
	public static String applyShopAPI;
	public static String checkShopApplyAPI;
	public static String getCatListAPI;
	public static String getCatSPUAPI;
	public static String getCatSKUAPI;
	public static String createGoodsGroupAPI;
	public static String userLoginAPI;
	public static String getMenuAPI;
	public static String createScenarioAPI;
	public static String getAllSellerAPI;
	public static String getMyChannelAPI;
	public static String getSellerListAPI;
	public static String logoutAPI;
	public static String vertifycodeAPI;
	public static String shopRegisterAPI;
	public static String checkPhoneNoRegisterAPI;
	public static String shopNavigatorAPI;
	public static String createChannelAPI;
	public static String getRoleAPI;
	public static String freezeSellerAPI;
	public static String getSellerApplyListAPI;
	public static String buyerLoginAPI;
	public static String createGoodsAPI;
	public static String createGoodsSkuAPI;
	public static String editSKUPriceAPI;
	public static String editSKUStockAPI;
	public static String addSKUSaleValueAPI;
	public static String getGoodsDetailsAPI;
	public static String mmktAPI;
	public static String commAPI;
	public static String groupAddGoodsAPI;
	public static String goodsAddToGroupAPI;
	public static String fareTmpltAPI;
	public static String createShelfAPI;
	public static String setHomePageAPI;
	public static String onShelfAPI;
	public static String getProvinceAPI;
	public static String getProvinceCityAPI;
	public static String getCityDistrict;
	
	
	
	
	/**
	 * 加载测试数据
	 */
    static {
        Properties pro = readPropertiesFileObj(path); // 读取properties文件
        url = pro.getProperty("url");
        checkUserRegisterAPI = pro.getProperty("checkUserRegisterAPI");//用户是否注册
        saveUserAPI = pro.getProperty("saveUserAPI"); //保存用户信息
        applyShopAPI = pro.getProperty("applyShopAPI"); //申请店铺
        userLoginAPI = pro.getProperty("userLoginAPI"); //店铺登录
        checkShopApplyAPI = pro.getProperty("checkShopApplyAPI"); //审核店铺
        getCatListAPI = pro.getProperty("getCatListAPI"); //获取类目列表
        getCatSPUAPI = pro.getProperty("getCatSPUAPI"); //获取类目SPU
        getCatSKUAPI = pro.getProperty("getCatSKUAPI"); //获取类目SKU
        createGoodsGroupAPI = pro.getProperty("createGoodsGroupAPI"); //获取类目SKU
        getMenuAPI = pro.getProperty("getMenuAPI"); //获取菜单
        createScenarioAPI = pro.getProperty("createScenarioAPI"); //创建场景
        getAllSellerAPI = pro.getProperty("getAllSellerAPI"); //获取全部店铺列表
        getMyChannelAPI = pro.getProperty("getMyChannelAPI");//获取我的渠道列表
        getSellerListAPI = pro.getProperty("getSellerListAPI");//批量获取店铺详细信息
        logoutAPI = pro.getProperty("logoutAPI");//退出登录
        vertifycodeAPI = pro.getProperty("vertifycodeAPI"); //获取验证码
        shopRegisterAPI = pro.getProperty("shopRegisterAPI"); //商铺注册
        checkPhoneNoRegisterAPI = pro.getProperty("checkPhoneNoRegisterAPI"); //检查手机是否注册
        shopNavigatorAPI = pro.getProperty("shopNavigatorAPI"); //商铺导航
        createChannelAPI = pro.getProperty("createChannelAPI"); //创建渠道
        getRoleAPI = pro.getProperty("getRoleAPI"); //获取权限
        freezeSellerAPI = pro.getProperty("freezeSellerAPI"); //冻结店铺
        getSellerApplyListAPI = pro.getProperty("getSellerApplyListAPI"); //获取开店审核列表
        buyerLoginAPI = pro.getProperty("buyerLoginAPI"); //买家登录
        createGoodsAPI = pro.getProperty("createGoodsAPI"); //创建商品
        createGoodsSkuAPI = pro.getProperty("createGoodsSkuAPI"); //创建商品sku
        editSKUPriceAPI = pro.getProperty("editSKUPriceAPI"); //编辑商品sku销量
        editSKUStockAPI = pro.getProperty("editSKUStockAPI"); //编辑商品sku库存
        addSKUSaleValueAPI = pro.getProperty("addSKUSaleValueAPI"); //增加商品sku销量
        getGoodsDetailsAPI = pro.getProperty("getGoodsDetailsAPI"); //获取商品详情
        mmktAPI = pro.getProperty("mmktAPI"); //供货市场
        commAPI = pro.getProperty("commAPI"); //分销市场
        groupAddGoodsAPI = pro.getProperty("groupAddGoodsAPI"); //分组中加入商品
        goodsAddToGroupAPI = pro.getProperty("goodsAddToGroupAPI"); //将商品加入到分组中
        fareTmpltAPI = pro.getProperty("fareTmpltAPI"); //邮费模板
        createShelfAPI = pro.getProperty("createShelfAPI"); //创建货架
        setHomePageAPI = pro.getProperty("setHomePageAPI"); //设置主页
        onShelfAPI = pro.getProperty("onShelfAPI"); //将商品上架下架
        getProvinceAPI = pro.getProperty("getProvinceAPI"); //获取省份列表
        getProvinceCityAPI = pro.getProperty("getProvinceCityAPI"); //获取省份城市列表
        getCityDistrict = pro.getProperty("getCityDistrict"); //获取城市区域列表
    }


    public static Properties readPropertiesFileObj(String filename) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filename);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));//utf-8解决中文乱码问题
            properties.load(bf);
            inputStream.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
	
}
