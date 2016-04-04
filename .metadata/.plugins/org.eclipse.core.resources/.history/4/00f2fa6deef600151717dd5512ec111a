package br.com.infowaypi.jheat.usage.core;

import java.util.HashMap;
import java.util.Map;

import br.com.infowaypi.jheat.usage.reports.UsageReportEngine;

public class ReportManager {

	private static ReportManager manager = new ReportManager();
	private Map<String, UsageReportEngine> reportEngines = new HashMap<String, UsageReportEngine>();
	
	private ReportManager(){}
	
	public static ReportManager getInstance(){
		return manager;
	}
	
	public void registerReportEngine(String reportEngineId, UsageReportEngine reportEngine){
		reportEngines.put(reportEngineId, reportEngine);
	}
}
