package com.bootdo.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderCodeUtils {

	public static String getTenNumberCode(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%010d", hashCode);
        return value;
    }
	
	public static String getOrderCode(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);
        return value;
    }
	
	public static void main(String[] args) {
		List<String> ids = new ArrayList<String>();
		System.out.println(getTenNumberCode());
		for( int i = 0; i < 1000000; i++) {
			String code = getTenNumberCode();
			if( ids.contains(code) ) {
				System.out.println(code);
			} else {
				ids.add(code);
			}
		}
		
	}
	
}
