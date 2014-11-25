package io;

import global.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import common.FileOperations;

public class SettingsInstantiation {
	
	private SettingsInstantiation(){}
	
	/**
	 * Reads settings from File
	 * @throws IOException
	 *             When file can't be read
	 */
	public static Settings getSettings() throws IOException {
		String path = FileOperations.getProgramDirectory() + File.separator +  "settings.xml";
		System.out.println("Trying to read settings file from: " + path);
		BufferedReader reader = new BufferedReader(new FileReader(path));
		StringBuilder fileAsString = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			fileAsString.append(line);
		}
		reader.close();

		XStream xstream = new XStream();
		xstream.alias("settings", Settings.class);
		Settings settings = (Settings) xstream.fromXML(fileAsString.toString());
		System.out.println("Settings: FolderLocation: "
				+ settings.getFolderLocation() + ", url: " + settings.getURL()
				+ ", updateInterval in seconds: "
				+ settings.getUpdateInterval());
		
		return settings;
	}

}
