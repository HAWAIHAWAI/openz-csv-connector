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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;


public class UpdateService {
	
	Settings settings;
	
	/**
	 * @return
	 * @throws IOException When file can't be read
	 */
	public void settingsInstantiation() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("settings.xml"));
		
		StringBuilder fileAsString = new StringBuilder();
	    String line;
	    while((line = reader.readLine()) != null) {
	       fileAsString.append(line);
	    }
		
		XStream xstream = new XStream();
		xstream.alias("settings", Settings.class);
		settings =  (Settings)xstream.fromXML(fileAsString.toString());	
		System.out.println("Settings: FolderLocation: " + settings.getFolderLocation() + ", url: " + settings.getURL() + ", updateInterval in seconds: " + settings.getUpdateInterval() );
	}

	/**
	 * @throws IOException When settings file can't be read
	 */
	public UpdateService() throws IOException{
		settingsInstantiation();
        
        final TrayIcon trayIcon;

        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("images/palm.jpg");

            MouseListener mouseListener = new MouseListener() {
                
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");                 
                }
                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");                 
                }
                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");                 
                }
                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");                 
                }
                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");                 
                }

            };

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

            trayIcon = new TrayIcon(image, "HAWAI-Excel Rechnungsupdate" , popup);

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("Action Event", 
                        "An Action Event Has Been Peformed!",
                        TrayIcon.MessageType.INFO);
                }
            };
            
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);

            //    Depending on which Mustang build you have, you may need to uncomment
            //    out the following code to check for an AWTException when you add 
            //    an image to the system tray.

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
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try {
			UpdateService main = new UpdateService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}//end of class
