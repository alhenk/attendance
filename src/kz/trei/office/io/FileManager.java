package kz.trei.office.io;

import java.io.InputStream;

import kz.trei.office.Runner;

public final class FileManager {
	private FileManager(){
	}

	public static InputStream getResourceAsStream(String name) {
		return Runner.class.getClassLoader().getResourceAsStream(name);
	}

}
