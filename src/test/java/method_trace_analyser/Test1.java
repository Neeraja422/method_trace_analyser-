package method_trace_analyser;

import java.io.File;
import java.util.ArrayList;

import java.util.Arrays;

import method_trace_analyser.model.bo.TotalMethodTraceContainer;
import method_trace_analyser.model.bo.TracePoint;
import method_trace_analyser.model.dao.TotalMethodTraceContainerDAO;
import method_trace_analyser.model.dao.daofactory.TotalMethodTraceContainerDaoFactory;
import method_trace_analyser.util.TraceUtil;

public class Test1 {

	public static void main(String[] args) {
		File traceLOGFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\tracefiles\\LOG\\testFile1.log");
		TotalMethodTraceContainerDAO totalMethodTraceContaierDao = TotalMethodTraceContainerDaoFactory.getTotalMethodTraceContaierDao();
		ArrayList<TracePoint> tracePointList = totalMethodTraceContaierDao.getTracePointList(traceLOGFile);
		try {
			TotalMethodTraceContainer totalMethodTraceContainer = totalMethodTraceContaierDao.getTraceDataFromFile(tracePointList,"test1.log");
			System.out.println(totalMethodTraceContainer.getMethodTraceList());
			System.out.println(totalMethodTraceContaierDao.generateMethodInvocationCountTable(totalMethodTraceContainer));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
