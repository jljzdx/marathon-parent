package com.newera.marathon.common.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class RandomUtil {

	public static String UUID32() {
		String str = UUID.randomUUID().toString();
		return str.replaceAll("-", "");
	}

	public static String UUID36() {
		return UUID.randomUUID().toString();
	}
	/**
	 * 获取随机验证码
	 * 
	 * @param length
	 * @return
	 *
	 */
	public static String getRandom(Integer length) {
		if (length == 6) {
			return getRandom6();
		} else if (length == 12) {
			return getRandom12();
		}
		return getRandom6();
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 *
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static Set<Integer> getRandom(int length, int max) {
		Random ran = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < length) {
			int num = ran.nextInt(max) + 1;
			set.add(num);
		}
		return set;
	}

	public static String getRandom6() {
		Random random = new Random();
		return random.nextInt(899999) + 100000 + "";
	}

	public static String getRandom12() {
		SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
		return sf.format(new Date()) + getRandom6();
	}

}
