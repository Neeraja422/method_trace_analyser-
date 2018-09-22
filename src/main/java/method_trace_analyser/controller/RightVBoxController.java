package method_trace_analyser.controller;

import java.io.File;
import java.util.Collection;


import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RightVBoxController {
	
	public static File getTRCFromFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Upload Trace file");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("trace files","*.trc"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile!=null && selectedFile.exists()) {
			System.out.println("yey");
			return selectedFile;
		}
		return null;
	}
	public static File getLOGFromFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Upload Trace file");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("trace files","*.log"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile!=null && selectedFile.exists()) {
			System.out.println("yey");
			return selectedFile;
		}
		return null;
	}

}
