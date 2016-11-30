package cn.wemart.usermng;

public class IsNeedLogin {
	protected static boolean flag;
	/**
	 * isLogin 1:需要登录  0：不需登录
	 */
	public static boolean isNeedLogin(int isLogin){
			flag = false;
		if(0 == isLogin){
			return flag;
		}
		else{
			flag = true;
			return flag;
		}
	}

}
