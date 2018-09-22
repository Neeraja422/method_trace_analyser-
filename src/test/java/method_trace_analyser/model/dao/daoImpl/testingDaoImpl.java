package method_trace_analyser.model.dao.daoImpl;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import method_trace_analyser.model.bo.TotalMethodTraceContainer;
import method_trace_analyser.model.bo.TracePoint;
import method_trace_analyser.model.dao.TotalMethodTraceContainerDAO;
import method_trace_analyser.model.dao.daofactory.TotalMethodTraceContainerDaoFactory;

class testingDaoImpl {
	TotalMethodTraceContainerDaoImpl TotalMethodTraceContainerDaoInstance=new TotalMethodTraceContainerDaoImpl();
	
	
	@Test
	public void testGetTracePointList_tracePoints() {
	
		TracePoint expectedTracePoint1=new TracePoint();
		TracePoint expectedTracePoint2=new TracePoint();
		TracePoint expectedTracePoint3=new TracePoint();
		TracePoint expectedTracePoint4=new TracePoint();
		
		expectedTracePoint1.setTimeStamp("16:54:55.570210750");
		expectedTracePoint1.setThreadId("0x2926700");
		expectedTracePoint1.setTraceEntry("Dice.<init>()V bytecode");
		expectedTracePoint1.setType("Entry");
		
		expectedTracePoint2.setTimeStamp("16:54:55.570217150");
		expectedTracePoint2.setThreadId("0x2926700");
		expectedTracePoint2.setTraceEntry("[1] Dice.<init> (Dice.java:5)");
		expectedTracePoint2.setType("Event");
		
		expectedTracePoint3.setTimeStamp("16:54:55.570219283");
		expectedTracePoint3.setThreadId("0x2926700");
		expectedTracePoint3.setTraceEntry("[2] SnakeGame.main (SnakeGame.java:29)");
		expectedTracePoint3.setType("Event");
		
		expectedTracePoint4.setTimeStamp("16:54:55.570223122");
		expectedTracePoint4.setThreadId("0x2926700");
		expectedTracePoint4.setTraceEntry("Dice.<init>()V bytecode");
		expectedTracePoint4.setType("Exit");
		
		ArrayList<TracePoint> ExpectedtracePointsList=new ArrayList<TracePoint>();
		ExpectedtracePointsList.add(expectedTracePoint1);
		ExpectedtracePointsList.add(expectedTracePoint2);
		ExpectedtracePointsList.add(expectedTracePoint3);
		ExpectedtracePointsList.add(expectedTracePoint4);
		
		File testFile = null;
		try{
			testFile=new File("src//main//resources//tracefiles//LOG//testFile1.log");
		     }
		     catch(Exception e) {}
		ArrayList<TracePoint> ActualtracePointsList=TotalMethodTraceContainerDaoInstance.getTracePointList(testFile);
		
	TracePoint[] ExpectedtracePointsArray=ExpectedtracePointsList.toArray(new TracePoint[ExpectedtracePointsList.size()]);
	TracePoint[] ActualtracePointsArray=ActualtracePointsList.toArray(new TracePoint[ActualtracePointsList.size()]);	
		
	assertArrayEquals(ExpectedtracePointsArray,ActualtracePointsArray);
	
	}
	

	@Test
	public void testDeleteTraceTRCFile_whenFileNotExists() {
		assertFalse(TotalMethodTraceContainerDaoInstance.deleteTraceTRCFile("testFile"));
			
	}
	@Test //This deletes the trace files..If wanted to test remove the comment at the assert Statements 
	public void testDeleteTraceTRCFile_whenFileExists() {
		File file=new File("src//main//resources//tracefiles//LOG//testFile1.log");
	//	assertTrue(TotalMethodTraceContainerInstance.deleteTraceTRCFile("src//main//resources//tracefiles//LOG//testDelete.log"));
		
	}
	
	@Test
	public void testDeleteTraceLogFile_whenFileNotExists() {
		assertFalse(TotalMethodTraceContainerDaoInstance.deleteTraceTRCFile("testFile"));
	}
	@Test
	public void testDeleteTraceLogFile_whenFileExists() {
		File file=new File("src//main//resources//tracefiles//LOG//testFile1.log");
	//	assertTrue(TotalMethodTraceContainerInstance.deleteTraceTRCFile("src//main//resources//tracefiles//LOG//testDelete2.log"));
		
	}
	
	@Test
	public void testGenerateMethodInvocationCountTable() {
		File testFile=new File("src//main//resources//tracefiles//LOG//test8.log");
		ArrayList<TracePoint> ActualtracePointsList=TotalMethodTraceContainerDaoInstance.getTracePointList(testFile);
		TotalMethodTraceContainer totalMethodTraceContainerInstance=null;
		try {
			totalMethodTraceContainerInstance = TotalMethodTraceContainerDaoInstance.getTraceDataFromFile(ActualtracePointsList,"src//main//resources//tracefiles//LOG//test8.log");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TreeMap<String, Integer> InvocationTableMap=(TreeMap<String, Integer>) TotalMethodTraceContainerDaoInstance.generateMethodInvocationCountTable(totalMethodTraceContainerInstance);		
		System.out.println(InvocationTableMap);
		Integer[] ActualInvocationTimes=InvocationTableMap.values().toArray(new Integer[InvocationTableMap.size()]);
		Integer[] expectedInvocationTimes= {1,7};
		assertArrayEquals(expectedInvocationTimes, ActualInvocationTimes);
	}
	
}

		
	
	


