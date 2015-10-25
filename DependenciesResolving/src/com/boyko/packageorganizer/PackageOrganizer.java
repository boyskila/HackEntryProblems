package com.boyko.packageorganizer;

import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import com.boyko.filemanager.FileManager;
import com.boyko.messageorganizer.MessageOrganizer;
import com.boyko.repository.PathsRepository;

public class PackageOrganizer {
	private FileManager fileManager;
	private PackageInstaller packageInstaller;

	public PackageOrganizer(File pathToPackages, File pathToDependencies) throws JSONException, IOException {
		fileManager = new FileManager();
		packageInstaller = new PackageInstaller();
		runOrganizer(pathToPackages, pathToDependencies);
	}

	private void runOrganizer(File pathToPackages, File pathToDependencies) throws JSONException, IOException {
		// get project dependencies
		JSONArray projectDependencies = fileManager.extractData(pathToDependencies);
		int size = projectDependencies.length();
		for (int i = 0; i < size; i++) {
			String packageName = projectDependencies.getString(i);
			File packageDirectory = new File(PathsRepository.PATH_TO_INSTALLED_MODULES + "/" + packageName);
			if (packageDirectory.exists()) {
				// Status: already installed
				MessageOrganizer.statusMessage(true, packageName);
				continue;
			}
			// pass path to 'DataBase' + name of the first package
			jsonWalker(pathToPackages, packageName);
		}
		MessageOrganizer.finalStatus();
	}

	private void jsonWalker(File dir, String packageName) throws JSONException, IOException {
		// full path to the package where is JSON
		File pathToDependencie = new File(dir + "/" + packageName + "/" + packageName + ".json");
		// array with current Package dependencies
		JSONArray dependencies = fileManager.extractData(pathToDependencie);
		// Directory with all packages
		File packagePath = new File(dir + "/" + packageName);
		// where to be installed(perhaps not been created yet)
		File destination = new File(PathsRepository.PATH_TO_INSTALLED_MODULES + packageName);
		if (!destination.exists()) {
			// if not exist, create this directory and copy the content of the
			// package from the Database
			packageInstaller.packageInstaller(packagePath, destination);
			int length = dependencies.length();
			for (int i = 0; i < length; i++) {
				/*
				 * when some package has no dependencies (empty array), then
				 * recursion stop and the loop send the next package(if there
				 * is) which must be checked and so on..
				 * 
				 */
				String name = dependencies.getString(i);
				// print message only once
				if (i == 0) {
					// Status: Installing <packageName>
					MessageOrganizer.statusMessage(dependencies, packageName);
				}
				// recursively find and install all non installed dependencies
				// @param dir - path to packages
				// @param name - current package name
				jsonWalker(dir, name);
			}
		} else {
			// Status: already installed
			MessageOrganizer.statusMessage(true, packageName);
		}
	}
}
