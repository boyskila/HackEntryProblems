package com.boyko.messageorganizer;

import org.json.JSONArray;
import org.json.JSONException;

public class MessageOrganizer {
	public static void statusMessage(JSONArray dep, String packageName) throws JSONException {
		if (!dep.isNull(0)) {
			System.out.println("In order to install " + packageName + ", we need " + printJSONArray(dep));
		}
	}

	private static String printJSONArray(JSONArray arr) throws JSONException {
		int len = arr.length();
		if (len == 1) {
			return arr.getString(0);
		}
		String res = "";
		for (int i = 0; i < len - 1; i++) {
			res += arr.getString(i) + ", ";
		}
		res = res.trim().replaceAll(",$", "");
		res += " and " + arr.getString(len - 1);
		return res;
	}

	public static void statusMessage(boolean isInstalled, String name) {
		if (!isInstalled) {
			System.out.printf("Installing %s%n", name);
		} else {
			System.out.printf("%s is already installed.%n", name);
		}

	}

	public static void finalStatus() {
		System.out.println("All done");
	}
}
