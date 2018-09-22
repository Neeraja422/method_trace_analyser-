package method_trace_analyser.model.dao.daofactory;

import method_trace_analyser.model.dao.TotalMethodTraceContainerDAO;
import method_trace_analyser.model.dao.daoImpl.TotalMethodTraceContainerDaoImpl;

public class TotalMethodTraceContainerDaoFactory {
	public static TotalMethodTraceContainerDAO getTotalMethodTraceContaierDao() {
		return new TotalMethodTraceContainerDaoImpl();
	}

}
//
