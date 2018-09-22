package method_trace_analyser.model.bo;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class TotalMethodTraceContainer {
	private String fileName;
	private List<IndividualMethodTraceContainer> methodTraceList;
	private int noOfTracePoints;
	private List<TracePoint> incompleteMethodList;
	private Map<String,List<StackTracePoint>> stackTraceMap;
	
	public TotalMethodTraceContainer() {
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<IndividualMethodTraceContainer> getMethodTraceList() {
		return methodTraceList;
	}
	public void setMethodTraceList(List<IndividualMethodTraceContainer> methodTraceList) {
		this.methodTraceList = methodTraceList;
	}
	public int getNoOfTracePoints() {
		return noOfTracePoints;
	}
	public void setNoOfTracePoints(int noOfTracePoints) {
		this.noOfTracePoints = noOfTracePoints;
	}
	
	public List<TracePoint> getIncompleteMethodList() {
		return incompleteMethodList;
	}
	public void setIncompleteMethodList(List<TracePoint> incompleteMethodList) {
		this.incompleteMethodList = incompleteMethodList;
	}
	public Map<String, List<StackTracePoint>> getStackTraceMap() {
		return stackTraceMap;
	}
	public void setStackTraceMap(Map<String, List<StackTracePoint>> stackTraceMap) {
		this.stackTraceMap = stackTraceMap;
	}
	@Override
	public String toString() {
		return "TotalMethodTraceContainer [fileName=" + fileName + ", methodTraceList=" + methodTraceList
				+ ", noOfTracePoints=" + noOfTracePoints + ", incompleteMethodList=" + incompleteMethodList
				+ ", stackTraceMap=" + stackTraceMap + "]";
	}
	 
	 
	

}
