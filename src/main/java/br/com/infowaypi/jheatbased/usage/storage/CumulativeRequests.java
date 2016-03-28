package br.com.infowaypi.jheatbased.usage.storage;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.infowaypi.jheatbased.usage.api.UsageData;

public class CumulativeRequests {
	
	private Map<UsageData, BigInteger> stats = new HashMap<>();
	private static CumulativeRequests storage = new CumulativeRequests();
	/**
	 * Data de início da contagem de acessos. 
	 */
	private Date startDate;
	private Date endDate;
	
	private CumulativeRequests(){
		
	}
	
	public static CumulativeRequests getInstance(){
		return storage;
	}
	
	public boolean storeUsageData(UsageData usageData){
		synchronized (stats) {
			BigInteger value = this.stats.containsKey(usageData) ? stats.get(usageData).add(BigInteger.valueOf(1l)) : BigInteger.valueOf(1l);
			stats.put(usageData, value); 
		}
		return true;
	}
	
	public Map<UsageData, BigInteger> getStats(){
		return this.stats;
	}
}
