package com.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static boolean matches(String patternStr, String matcherStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(matcherStr);
		boolean b = matcher.matches();
		return b;
	}

	public static String[] split(String patterStr, String matcherStr) {
		Pattern pattern = Pattern.compile(patterStr);
		String[] strs = pattern.split(matcherStr);
		return strs;
	}
}
