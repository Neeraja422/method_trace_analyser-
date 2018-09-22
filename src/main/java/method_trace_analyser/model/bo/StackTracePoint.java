package method_trace_analyser.model.bo;

public class StackTracePoint {
	private int sNo;
	private String methodName;
	private String javaFileName;
	private int lineNumber;
	private long timestamp;
	
	
	public StackTracePoint() {
	}
	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}


	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getJavaFileName() {
		return javaFileName;
	}

	public void setJavaFileName(String javaFileName) {
		this.javaFileName = javaFileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	
	@Override
	public String toString() {
		return "StackTracePoint [sNo=" + sNo + ", methodName=" + methodName + ", javaFileName=" + javaFileName
				+ ", lineNumber=" + lineNumber + ", timestamp=" + timestamp + "]";
	}

	 
	

}
