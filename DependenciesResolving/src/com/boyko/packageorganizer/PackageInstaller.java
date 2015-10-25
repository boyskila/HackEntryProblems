package com.boyko.packageorganizer;

import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import com.boyko.filemanager.FileManager;
import com.boyko.messageorganizer.MessageOrganizer;

public class PackageInstaller {
	private FileManager fileManager;

	public PackageInstaller() {
		fileManager = new FileManager();
	}

	public void packageInstaller(File src, File destination) throws IOException, JSONException {
		if (src.isDirectory()) {
			// if directory not exists, create it
			if (isDirectoryPrepared(destination, src)) {
				String files[] = src.list();
				for (String file : files) {
					// construct the src and destination file structure
					File srcFile = new File(src, file);
					File destFile = new File(destination, file);
					// recursive copy
					packageInstaller(srcFile, destFile);
				}
			}
			// list all the directory contents
		} else {
			// if file, then copy it
			fileManager.copyFiles(src, destination);
		}
	}

	private boolean isDirectoryPrepared(File destination, File src) {
		if (!destination.exists()) {
			destination.mkdir();
			MessageOrganizer.statusMessage(false, src.getName());
			return true;
		}
		MessageOrganizer.statusMessage(true, src.getName());
		return false;
	}
}
