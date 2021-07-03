package com.finder.servingwebcontent;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.finder.servingwebcontent.exception.FileAccessException;

/**
 * A helper for file usage.
 * @author chbarbosa
 *
 */
public class FileHelper {

	/**
	 * Private constructor.
	 */
	private FileHelper() {
		super();
	}


	/**
	 * Reads all data from a specified file.
	 * @param filePath the file path
	 * @return file content
	 * @throws FileAccessException error to access the file
	 */
	public static String readString(String filePath) throws FileAccessException {
		try {
			// Read file
			return Files.readString(Paths.get(FileHelper.class.getClassLoader().getResource(filePath).toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new FileAccessException(e);
		}
	}
}
