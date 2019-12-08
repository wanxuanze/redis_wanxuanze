package com.bawei.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;





/**
 * @author 一枚忧郁的男子
 *
 * 2019年11月7日
 * 下午2:30:54
 */

@SuppressWarnings("all")
public class StringUtils {

	
	public static boolean isEmpty(String str){
		
		if(str != null || str.trim().equals("")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNumber(String str){
		str.replace(".", ".");
		String reg = "(\\d+.\\d+|\\d+)";
		boolean matches = str.matches(reg);
		return matches;
		
	}
	
	public static Integer getAge(String birthday) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			Date birth = sdf.parse(birthday);
			Date date = new Date();
			long l = birth.getTime();
			long m = date.getTime();
			int y = (int)((m-l)/1000/60/60/24/365);
			return y;
			
	}
	
	public static String getEmail() {
		
		int length = new Random().nextInt(17)+3;
		String[] arr = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com"};
		String str = "";
		for(int i=0;i<length;i++) {
			str += (char)(new Random().nextInt(24)+97);
		}
		
		return str+arr[new Random().nextInt(arr.length)];
	}

	
	/**
	 * 随机生成汉字
	 * @param count 汉字个数
	 * @return
	 */
	public static String getRandomChar(Integer count) {
		String str = "";
		for (int i = 0; i < count; i++) {
			str += (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 	1)));
		}
        return str;
    }
	
	public static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);
            
            if(start.getTime() >= end.getTime()){
                return null;
            }
            
            long date = random(start.getTime(),end.getTime());
            
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	 private static long random(long begin,long end){
	        long rtn = begin + (long)(Math.random() * (end - begin));
	        if(rtn == begin || rtn == end){
	            return random(begin,end);
	        }
	        return rtn;
	    }
}
