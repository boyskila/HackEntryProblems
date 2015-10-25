package com.boyko.main;

import java.io.IOException;
import org.json.JSONException;
import com.boyko.packageorganizer.PackageOrganizer;
import com.boyko.repository.PathsRepository;

public class Main {
	public static void main(String[] args) throws JSONException, IOException {
		new PackageOrganizer(PathsRepository.PATH_TO_PACKAGES, PathsRepository.PATH_TO_DEPENDENCIES);
	}
}
