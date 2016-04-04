package br.com.infowaypi.jheat.usage.core;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import br.com.infowaypi.jheat.usage.api.UsageData;
import br.com.infowaypi.jheat.usage.reports.UsageReportEngine;

public class AppManager {

	private static AppManager manager = new AppManager();
	private Map<String, UsageReportEngine> reportEngines;
	private UsageStorage storage = new UsageStorage();
	
	private AppManager(){
//		loadReportEngines();
		for (UsageReportEngine engine : reportEngines.values()) {
			storage.addObserver(engine);
		}
	}
	
	public static AppManager getInstance(){
		return manager;
	}
	
	public void registerReportEngine(String reportEngineId, UsageReportEngine reportEngine){
		reportEngines.put(reportEngineId, reportEngine);
	}

	public boolean storeUsageData(UsageData usageData) {
		return storage.storeUsageData(usageData);
	}

	public Map<UsageData, BigInteger> getStats() {
		return storage.getStats();
	}
}
