package com.boyko.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileManager implements FileManagerInterface {

	@Override
	public String readFile(File fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(fileName));
		String content = "";
		while (sc.hasNext()) {
			String row = sc.next();
			content += row;
		}
		sc.close();
		return content;
	}

	@Override
	public void copyFiles(File src, File dest) throws IOException, JSONException {
		// Use bytes stream to support all file types
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dest);
		byte[] buffer = new byte[1024];
		int length;
		// copy the file content in bytes
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}

	public JSONArray extractData(File path) throws JSONException, FileNotFoundException {
		String json = readFile(path);
		return new JSONObject(json).getJSONArray("dependencies");
	}
}
