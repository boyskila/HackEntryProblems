package com.boyko.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;

public interface FileManagerInterface {
	String readFile(File fileName) throws FileNotFoundException;

	void copyFiles(File source, File destination) throws IOException, JSONException;
}
