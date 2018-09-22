package method_trace_analyser.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import method_trace_analyser.model.dao.TotalMethodTraceContainerDAO;
import javafx.scene.control.TreeItem;
import method_trace_analyser.model.bo.IndividualMethodTraceContainer;
import method_trace_analyser.model.bo.TotalMethodTraceContainer;
import method_trace_analyser.model.bo.TracePoint;
import method_trace_analyser.model.dao.daoImpl.TotalMethodTraceContainerDaoImpl;
import method_trace_analyser.model.dao.daofactory.TotalMethodTraceContainerDaoFactory;
import method_trace_analyser.util.TraceUtil;

public class LeftTreeViewController {

	public static TreeItem<String> initTreeView() throws Exception {
		TreeItem<String> root = new TreeItem<>("Trace Explorer");
		root.setExpanded(true);
		TreeItem<String> tracefile = null;
		String fileNames[] = TraceUtil.getLogFiles();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\tracefiles\\LOG\\";
		for (String filename : fileNames) {
			tracefile = new TreeItem<>(filename);
			root.getChildren().add(tracefile);
			TotalMethodTraceContainerDAO totalMethodTraceContaierDao = TotalMethodTraceContainerDaoFactory.getTotalMethodTraceContaierDao();
			ArrayList<TracePoint> tracePointList = totalMethodTraceContaierDao.getTracePointList(new File(filePath+filename));
			TotalMethodTraceContainer methodTraceContainer = totalMethodTraceContaierDao.getTraceDataFromFile(tracePointList, filename);
			List<IndividualMethodTraceContainer> methodTraceList = methodTraceContainer.getMethodTraceList();
			for (IndividualMethodTraceContainer individualMethodTraceContainer : methodTraceList) {
				TreeItem<String> methodName = new TreeItem<>(individualMethodTraceContainer.getMethodName());
				tracefile.getChildren().add(methodName);
			}
		}

		return root;
	}
}
/*
 * T
 * 
 */
