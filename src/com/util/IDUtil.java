package com.util;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.UUID;

public class IDUtil {
	
	
	/**
	 * ����һ�����ƺ�
	 */
	public static String createToken(){
		String str =createId();
		return str.replaceAll("-", "");
	}
	
	/**
	 * ����UUID�㷨����һ��Ψһ�Ե��ַ���
	 */
	public static String createId(){
//		UUID uuid=UUID.randomUUID();
//		String id=uuid.toString();
		Calendar calendar = Calendar.getInstance();
		String id=String.valueOf(calendar.getTime().getTime());
		return id;
	}
	
	public static void main(String[] args) {
		System.out.println(createId());
	}
}
