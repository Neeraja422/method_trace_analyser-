package method_trace_analyser.model.bo;

public class TracePoint {
	private String timeStamp;
	private String threadId;
	private String type;
	private String traceEntry;
	
	public TracePoint() {
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTraceEntry() {
		return traceEntry;
	}
	public void setTraceEntry(String traceEntry) {
		this.traceEntry = traceEntry;
	}
	@Override
	public String toString() {
		return "TracePoint="+"[timeStamp=" + timeStamp + ", threadId=" + threadId + ", type=" + type + ", traceEntry="
				+ traceEntry + "]";
	}
	@Override
	public boolean equals(Object o) {
		if(o==this) {
			return true;
		}
		if(!(o instanceof TracePoint)) {
			System.out.println("F1");
			return false;
		}
		TracePoint tracePoint=(TracePoint)o;
		if(this.getThreadId().equals(tracePoint.getThreadId()) && this.getType().equals(tracePoint.getType()) && this.getTraceEntry().equals(tracePoint.getTraceEntry()) && this.getTimeStamp().equals(tracePoint.getTimeStamp()) ) {
		return true;	
		}
		System.out.println("F2");
		return false;
	}
	 

}
