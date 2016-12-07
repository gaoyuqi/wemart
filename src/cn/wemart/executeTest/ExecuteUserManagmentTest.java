package cn.wemart.executeTest;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.userManagment.ChangePassword;
import cn.wemart.userManagment.GetMenuList;
import cn.wemart.userManagment.GetRuleAuth;
import cn.wemart.userManagment.DeleteRuleAuth;
import cn.wemart.userManagment.GetAdminInfoByPhoneNO;
import cn.wemart.userManagment.SaveUserInfo;
import cn.wemart.util.getCurrent;

import com.TestNG.Assertion;

@Listeners({com.TestNG.AssertionListener.class})
public class ExecuteUserManagmentTest {
	
//	@Test
	public static void ChangePasswordTest(){
		String returnValue = ChangePassword.test("13818881111", "123456", "3", "234");
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("修改密码成功");
		}
		else{
			Reporter.log("修改密码失败\n Response："+ChangePassword.response);
		}
		Reporter.log(getCurrent.Time());
		
	}
	
//	@Test
	public static void GetMenuListTest(){
		String returnValue = GetMenuList.test("13818881111", "123456", "3", "234");
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("获取菜单成功！");
		}
		else{
			Reporter.log("获取菜单失败！\n Response："+GetMenuList.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
//	@Test
	public static void GetRuleAuthTest(){
		String returnValue = GetRuleAuth.test("13818881111", "123456", "3", "234");
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("获取角色权限树成功！");
		}
		else{
			Reporter.log("获取角色权限树失败！\n Response："+GetRuleAuth.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
	@Test
	public static void GetAdminInfoByPhoneNOTest(){
		String returnValue = GetAdminInfoByPhoneNO.test("13818881111", "123456", "3", "234");
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("通过手机号码获取管理员信息成功！\n");
		}
		else{
			Reporter.log("通过手机号码获取管理员信息失败！\n Response："+GetAdminInfoByPhoneNO.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
//	@Test
	public static void DeleteRuleAuthTest(){
		String returnValue = DeleteRuleAuth.test("13818881111", "123456", "3", "234","216");
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("删除管理员成功");
		}
		else{
			Reporter.log("删除管理员失败！\n Response："+GetAdminInfoByPhoneNO.response);
		}
		Reporter.log(getCurrent.Time());
	}
	
//	@Test
	public static void SaveUserInfoTest(){
		String returnValue = SaveUserInfo.test("13818881111", "123456", "3", "234");
		if(Assertion.verifyEqual(returnValue, "0")){
			Reporter.log("保存用户信息成功");
		}
		else{
			Reporter.log("保存用户信息失败！\n Response："+GetAdminInfoByPhoneNO.response);
		}
		Reporter.log(getCurrent.Time());
	}


}
