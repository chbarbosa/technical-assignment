package com.finder.servingwebcontent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.finder.servingwebcontent.exception.FileAccessException;

public class FileHelper {

	private FileHelper() {
		super();
	}

	/**
	 * Packs the items.
	 *
	 * @param filePath file path to the setup file.
	 * @return the package setup.
	 * @throws FileAccessException file reading error.
	 */
	public static String readString(String filePath) throws FileAccessException {
		// Read file
		try {
			return Files.readString(Paths.get(FileHelper.class.getClassLoader().getResource(filePath).toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new FileAccessException(e);
		}
	}
}
