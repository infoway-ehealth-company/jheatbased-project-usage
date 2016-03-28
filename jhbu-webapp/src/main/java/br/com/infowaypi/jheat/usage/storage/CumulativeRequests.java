package br.com.infowaypi.jheat.usage.storage;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import br.com.infowaypi.jheat.usage.api.UsageData;

/**
 * Contém o mapa com dados estatísticos acumulados dentro do ciclo. Dado que este projeto não utiliza banco de dados, com vista à simplicidade,
 * o ciclo de storage dos dados é iniciado com o lançamento do container de aplicação e finaliza com o desligamento deste.
 * 
 * @since 28/03/2015
 */
public class CumulativeRequests extends Observable{
	
	/**
	 * Mapa para gravação das estatísticas dos respectivos fluxos. Possui tamanho máximo definido - para limite de uso de memória -
	 * permitindo um total de 100 features mapeadas.
	 */
	private Map<UsageData, BigInteger> stats = new HashMap<>();
	private static CumulativeRequests storage = new CumulativeRequests();
	/**
	 * Data de início da contagem de acessos. Atribuída uma vez, quando da inicialização do container de aplicação e instanciação
	 * do singleton CumulativeRequests.
	 */
	private final Date startDate = new Date();
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}
	
}
