package ui;

import global.Settings;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

public class UpdateService {

	Settings settings;

	/**
	 * @throws IOException
	 *             When settings file can't be read
	 */
	public UpdateService() throws IOException {
		settingsInstantiation();

		final TrayIcon trayIcon;

		if (SystemTray.isSupported()) {

			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(
					"images/palm.jpg");

			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting...");
					System.exit(0);
				}
			};

			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);

			trayIcon = new TrayIcon(image, "HAWAI-Excel Rechnungsupdate", popup);
			trayIcon.setImageAutoSize(true);

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println("TrayIcon could not be added.");
			}

		} else {
			System.err.println("System tray is currently not supported.");
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		try {
			UpdateService main = new UpdateService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads settings from File
	 * @throws IOException
	 *             When file can't be read
	 */
	public void settingsInstantiation() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(
				"settings.xml"));

		StringBuilder fileAsString = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			fileAsString.append(line);
		}
		reader.close();

		XStream xstream = new XStream();
		xstream.alias("settings", Settings.class);
		settings = (Settings) xstream.fromXML(fileAsString.toString());
		System.out.println("Settings: FolderLocation: "
				+ settings.getFolderLocation() + ", url: " + settings.getURL()
				+ ", updateInterval in seconds: "
				+ settings.getUpdateInterval());
	}

}
