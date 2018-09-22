package method_trace_analyser.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import method_trace_analyser.model.bo.StackTracePoint;

public class TraceUtil {
	public static String[] getLogFiles() {
		String[] fileNames = null;
		try {// 
			File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\tracefiles\\LOG");
			fileNames = file.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileNames;
	}

	public static long toNanoSeconds(String timeStamp) {
		Pattern pattern = Pattern.compile("(?<hours>\\d{2}):(?<minutes>\\d{2}):(?<seconds>\\d{2})\\.(?<nanos>\\d{9})");
		Matcher matcher = pattern.matcher(timeStamp);
		if (matcher.find()) {
			long hours;
			long minutes;
			long seconds;
			long nanos;
			try {
				hours = Long.parseLong(matcher.group("hours"));
				minutes = Long.parseLong(matcher.group("minutes"));
				seconds = Long.parseLong(matcher.group("seconds"));
				nanos = Long.parseLong(matcher.group("nanos"));
				hours = hours * (3600L * (long) Math.pow(10, 9));
				minutes = minutes * (60L * (long) Math.pow(10, 9));
				seconds = seconds * (long) Math.pow(10, 9);
				return (hours + minutes + seconds + nanos);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return -1L;
	}
	public static StackTracePoint getStackPoint(String traceEntry) {
		Pattern pattern = Pattern.compile("\\[(?<sNo>\\d+?)\\]\\s(?<methodName>.*?)\\s*?\\((?<javaFileName>.*?):(?<lineNumber>\\d+)\\)");
		Matcher matcher = pattern.matcher(traceEntry);
		StackTracePoint stackTracePoint = null;
		if (matcher.find()) {
			stackTracePoint = new StackTracePoint();
			stackTracePoint.setsNo(Integer.parseInt(matcher.group("sNo")));
			stackTracePoint.setMethodName(matcher.group("methodName"));
			stackTracePoint.setJavaFileName(matcher.group("javaFileName"));
			stackTracePoint.setLineNumber(Integer.parseInt(matcher.group("lineNumber")));
		}
		return stackTracePoint;
	}
}