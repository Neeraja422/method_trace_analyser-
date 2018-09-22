package method_trace_analyser.controller;

import java.io.File;

import javafx.application.Platform;
import javafx.stage.DirectoryChooser;

public class MenuBarController {
	
	public static void fileOpenFolderAction() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Open Trace Folder");
		File selectedDirectory = directoryChooser.showDialog(null);
		if ( selectedDirectory!=null && selectedDirectory.exists()) {
			 
				System.out.println("yey");
			 
		}
	}
	 
	public static void fileExitAction() {
		 Platform.exit();
		 System.exit(0);
	}
	

}
