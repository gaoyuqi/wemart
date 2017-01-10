package cn.wemart.channelManagment;

import java.util.Random;

public class test {
	public static String getfangfa() {

		String str = "";
		Random random=new Random();
		for(int i=0;i<8;i++){
		    str += random.nextInt(10);
		}
		return str;
	}

	public static void main(String[] args) {
		
		int[] abc = {138,172,158};
		for(int i =0;i<abc.length;i++){
			System.out.println(abc[i] + getfangfa());
		}
	}

}
