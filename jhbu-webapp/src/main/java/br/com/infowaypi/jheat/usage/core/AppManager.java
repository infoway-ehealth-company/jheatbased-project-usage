package br.com.infowaypi.jheat.usage.core;

import java.util.HashMap;
import java.util.Map;

import br.com.infowaypi.jheat.usage.api.UsageData;
import br.com.infowaypi.jheat.usage.reports.UsageReportEngine;

public class AppManager {

	private static AppManager manager = new AppManager();
	private Map<String, UsageReportEngine> reportEngines = new HashMap<String, UsageReportEngine>();
	/**
	 * Storages of usage data mapped by a string, which is the <b>project Id</b>
	 */
	private Map<String, UsageStorage> storages = new HashMap<String, UsageStorage>();
	
	private AppManager(){
//		loadReportEngines();
		for (UsageReportEngine engine : reportEngines.values()) {
//			storage.addObserver(engine);
		}
	}
	
	public static AppManager getInstance(){
		return manager;
	}
	
	public void registerReportEngine(String reportEngineId, UsageReportEngine reportEngine){
		reportEngines.put(reportEngineId, reportEngine);
	}

	public boolean storeUsageData(String projectId, UsageData usageData) {
		UsageStorage storage = storages.get(projectId);
		if(storage == null){
			storage = new UsageStorage();
			storages.put(projectId, storage);
		}
		return storage.storeUsageData(usageData);
	}

	public Map<Integer, UsageData> getStats(String projectId) {
		UsageStorage storage = storages.get(projectId);
		if(storage == null){
			storage = new UsageStorage();
			storages.put(projectId, storage);
		}
		return storage.getStats();
	}
}
