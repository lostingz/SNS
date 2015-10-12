package com.common;

public class HtmlUtil {
	// HTML5
	public static String bodyBuild(String title, String body) {
		if (StringUtil.isEmpty(title)) {
			title = "Default html";
		}
		String code = "<!DOCTYPE html><html><head><title>"
				+ title
				+ "</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /></head><body>"
				+ body + "</body></html>";
		return code;
	}

	public static String buildResCateory(String resourceType, String url,
			String[] folders) {
		String body = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <Connector resourceType=\""
				+ resourceType
				+ "\"> <Error number=\"0\" />"
				+ "<CurrentFolder path=\"/\" url=\"" + url + "\" /><Folders>";
		if (folders.length > 0) {
			for (String string : folders) {
				String folder = "<Folder name=\"" + string
						+ "\" hasChildren=\"false\" />";
				body += folder;
			}
		}
		body += "</Folders></Connector>";
		return body;
	}

	public static String buildResList() {

		return null;
	}

}
