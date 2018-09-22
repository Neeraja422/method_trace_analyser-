package method_trace_analyser.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import method_trace_analyser.model.bo.IndividualMethodTraceContainer;
import method_trace_analyser.model.bo.TotalMethodTraceContainer;
import method_trace_analyser.model.bo.TracePoint;
import method_trace_analyser.model.dao.TotalMethodTraceContainerDAO;
import method_trace_analyser.model.dao.daofactory.TotalMethodTraceContainerDaoFactory;

public class TabPaneController {
	
	public static BarChart<String, Number> getBarChart(String traceLOGFileName) throws Exception {
		TotalMethodTraceContainerDAO totalMethodTraceContaierDao = TotalMethodTraceContainerDaoFactory.getTotalMethodTraceContaierDao();
		File traceLOGFile=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\tracefiles\\LOG\\test8.log");
		ArrayList<TracePoint> tracePointList = totalMethodTraceContaierDao.getTracePointList(traceLOGFile);
		TotalMethodTraceContainer totalMethodTraceContainer = totalMethodTraceContaierDao.getTraceDataFromFile(tracePointList, traceLOGFileName);
		Map<String, Integer> methodInvocationCountTable = totalMethodTraceContaierDao.generateMethodInvocationCountTable(totalMethodTraceContainer);
		List<String> methodNames = new ArrayList<>(methodInvocationCountTable.keySet());
		System.out.println(methodInvocationCountTable);
		CategoryAxis xAxis = new CategoryAxis();   
		xAxis.setCategories(FXCollections.<String>observableArrayList(methodNames)); 
		xAxis.setLabel("methods");
		xAxis.tickLabelFontProperty().set(Font.font(18));
		
		NumberAxis yAxis = new NumberAxis(); 
		//yAxis.setStyle("-fx-tick-label-text-fill:red");;
		yAxis.setLabel("Number of invocation times");
		yAxis.tickLabelFontProperty().set(Font.font(18));
		XYChart.Series<String, Number> series1 = new XYChart.Series<>(); 
		series1.setName("test8.log"); 
		for (String methodName : methodNames) {
			Integer noOfInvocations = methodInvocationCountTable.get(methodName);
			series1.getData().add(new XYChart.Data<>(methodName, noOfInvocations)); 
		} 
		BarChart<String, Number> graphicalBarChart = new BarChart<>(xAxis, yAxis);  
		graphicalBarChart.setTitle("graphical view"); 
		graphicalBarChart.getData().add(series1);
		//graphicalBarChart.setBarGap(50.0);

		return graphicalBarChart;
	}
	
	public static TableView getTableView(String traceLOGFileName) throws Exception {
		TotalMethodTraceContainerDAO totalMethodTraceContaierDao = TotalMethodTraceContainerDaoFactory.getTotalMethodTraceContaierDao();
		File traceLOGFile=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\tracefiles\\LOG\\test8.log");
		ArrayList<TracePoint> tracePointList = totalMethodTraceContaierDao.getTracePointList(traceLOGFile);
		TotalMethodTraceContainer totalMethodTraceContainer = totalMethodTraceContaierDao.getTraceDataFromFile(tracePointList, traceLOGFileName);
		Map<String, Integer> methodInvocationCountTable = totalMethodTraceContaierDao.generateMethodInvocationCountTable(totalMethodTraceContainer);
		TableView tableView = new TableView();
		tableView.setEditable(true);
		TableColumn threadId = new TableColumn("Thread Id");
	    TableColumn methodName = new TableColumn("MethodName");
	    TableColumn invocationTimes = new TableColumn("Invocation Times");
	    tableView.getColumns().addAll(threadId, methodName, invocationTimes);
		return tableView;
	}

}
