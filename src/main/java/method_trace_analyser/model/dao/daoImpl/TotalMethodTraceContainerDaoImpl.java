package method_trace_analyser.model.dao.daoImpl;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import method_trace_analyser.model.bo.IndividualMethodTraceContainer;
import method_trace_analyser.model.bo.StackTracePoint;
import method_trace_analyser.model.bo.TotalMethodTraceContainer;
import method_trace_analyser.model.bo.TracePoint;
import method_trace_analyser.model.dao.TotalMethodTraceContainerDAO;
import method_trace_analyser.util.TraceUtil;

public class TotalMethodTraceContainerDaoImpl implements TotalMethodTraceContainerDAO {

	@Override
	public boolean createTraceFile(String traceCommand) {
		boolean isCreated = false;
		List<String> command = new ArrayList<String>();
		command.add("CMD");
		command.add("/C");
		command.add("java " + traceCommand);
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.directory(new File("path/to/jar_or_class_files"));
		Process process = null;
		try {
			process = processBuilder.start();
			process.waitFor();
			/*
			 * if(tracefileName.exists()){ isCreated = true; }
			 */
			// else false
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (process.isAlive()) {
				process.destroy();
			}
		}
		return isCreated;
	}

	@Override
	public boolean convertBinaryTraceFileToLog(File traceTRCFile) {
		boolean isconverted = false;
		List<String> command = new ArrayList<String>();
		command.add("CMD");
		command.add("/C");
		command.add("java com.ibm.jvm.format.TraceFormat " + traceTRCFile.getName() + ".trc " + traceTRCFile.getName()
				+ ".log");
		ProcessBuilder processBuilder = null;
		Process process = null;
		try {
			processBuilder = new ProcessBuilder(command);
			processBuilder.directory(new File("path/to/trace file"));
			process = processBuilder.start();
			process.waitFor();
			/*
			 * if(tracelogfileName.exists()){ isconverted = true;
			 * 
			 * }
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (process.isAlive()) {
				process.destroy();
			}
		}
		return isconverted;
	}

	@Override
	public ArrayList<TracePoint> getTracePointList(File traceLOGFile) {
		ArrayList<TracePoint> tracePoints = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(traceLOGFile));
			String data;
			Pattern pattern = Pattern.compile("(?<timeStamp>\\d{2}:\\d{2}:\\d{2}\\.\\d{9})\\s+\\*?(?<threadId>0x\\d{7})\\s+.+\\s+(?<traceType>Entry|Exit|Event)\\s+[><]?(?<traceEntry>(.*?\\sbytecode|.*?\\(.*?\\.java:\\d+\\))).*?");
			while ((data = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(data);
				if (matcher.find()) {
					TracePoint tracePoint = new TracePoint();
					tracePoint.setTimeStamp(matcher.group("timeStamp"));
					tracePoint.setThreadId(matcher.group("threadId"));
					tracePoint.setType(matcher.group("traceType"));
					tracePoint.setTraceEntry(matcher.group("traceEntry"));
					tracePoints.add(tracePoint);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("File not found Exception++++");
		} catch (Exception e) {System.out.println("EXee");
			// TODO: handle exception
		e.printStackTrace();
		} finally {
			try {
				if (bufferedReader!=null) {
					bufferedReader.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("closing exc");e.printStackTrace();
			}
		}
		return tracePoints;
	}

	@Override
	public TotalMethodTraceContainer getTraceDataFromFile(ArrayList<TracePoint> tracePointList, String fileName)throws Exception {
		TotalMethodTraceContainer totalMethodTraceContainer = new TotalMethodTraceContainer();
		totalMethodTraceContainer.setFileName(fileName);
		IndividualMethodTraceContainer individualMethodTraceContainer = null;
		ArrayList<IndividualMethodTraceContainer> methodTraceList = new ArrayList<IndividualMethodTraceContainer>();
		totalMethodTraceContainer.setMethodTraceList(methodTraceList);
		Stack<TracePoint> tracePointStack = new Stack<>();
		Map<String,List<StackTracePoint>> stackTraceMap = new TreeMap<>();
		totalMethodTraceContainer.setStackTraceMap(stackTraceMap);
		List<StackTracePoint> stackTracePoints = new ArrayList<>();
		for (TracePoint tracePoint : tracePointList) {
			if (tracePoint.getType().equals("Entry")) {
				tracePointStack.push(tracePoint);
			}else if (tracePoint.getType().equals("Event")) {
				StackTracePoint stackTracePoint =  new StackTracePoint();
				stackTracePoint.setTimestamp(TraceUtil.toNanoSeconds(tracePoint.getTimeStamp()));
				String uniqueMethodName = tracePointStack.peek().getTraceEntry()+tracePointStack.peek().getTimeStamp();
					if (!totalMethodTraceContainer.getStackTraceMap().containsKey(uniqueMethodName)) {
						totalMethodTraceContainer.getStackTraceMap().put(uniqueMethodName, stackTracePoints);
					}
					TraceUtil.getStackPoint(tracePoint.getTraceEntry());
					totalMethodTraceContainer.getStackTraceMap().get(uniqueMethodName).add(stackTracePoint);
				
			} else if (tracePoint.getType().equals("Exit")) {
				 
				if (tracePoint.getTraceEntry().equals(tracePointStack.peek().getTraceEntry())) {
					individualMethodTraceContainer = new IndividualMethodTraceContainer();
					individualMethodTraceContainer.setEntry_time(TraceUtil.toNanoSeconds(tracePointStack.pop().getTimeStamp()));
					individualMethodTraceContainer.setExit_time(TraceUtil.toNanoSeconds(tracePoint.getTimeStamp()));
					individualMethodTraceContainer.setMethodName(tracePoint.getTraceEntry());
					methodTraceList.add(individualMethodTraceContainer);
				} else {
					totalMethodTraceContainer.setIncompleteMethodList(new ArrayList<TracePoint>(tracePointStack));
					return totalMethodTraceContainer;
				}
			}
		}
		return totalMethodTraceContainer;
	}

	@Override
	public boolean deleteTraceTRCFile(String trcTraceFileName) {
		File trcTraceFile = new File(trcTraceFileName);
		if (trcTraceFile.exists()) {
			return trcTraceFile.delete();
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteTraceLogFile(String logtraceFileName) {
		File logtraceFile = new File(logtraceFileName);
		if (logtraceFile.exists()) {
			return logtraceFile.delete();
		} else {
			return false;
		}
	}

	@Override
	public Map<String, Integer> generateMethodInvocationCountTable(
			TotalMethodTraceContainer totalMethodTraceContainer) {
		List<IndividualMethodTraceContainer> methodTraceList = totalMethodTraceContainer.getMethodTraceList();
		Map<String, Integer> methodInvocationCountTable = new TreeMap<>();

		for (IndividualMethodTraceContainer individualMethodTraceContainer : methodTraceList) {
			String methodName = individualMethodTraceContainer.getMethodName();
			if (!methodInvocationCountTable.containsKey(methodName)) {
				methodInvocationCountTable.put(methodName, 1);
			} else {
				Integer invocationCount = methodInvocationCountTable.get(methodName) + 1;
				methodInvocationCountTable.replace(methodName, invocationCount);
			}

		}
		return methodInvocationCountTable;
	}

}
