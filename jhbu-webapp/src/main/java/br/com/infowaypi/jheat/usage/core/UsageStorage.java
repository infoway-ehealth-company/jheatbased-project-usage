package br.com.infowaypi.jheat.usage.core;

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
 * @since 28/03/2016
 */
public class UsageStorage extends Observable{
	
	/**
	 * Mapa para gravação das estatísticas dos respectivos fluxos. Possui tamanho máximo definido - para limite de uso de memória -
	 * permitindo um total de 100 features mapeadas.
	 */
	private Map<Integer, UsageData> stats = new HashMap<Integer, UsageData>();
	/**
	 * Data de início da contagem de acessos. Atribuída uma vez, quando da inicialização do container de aplicação e instanciação
	 * do singleton UsageStorage.
	 */
	private final Date startDate = new Date();
	private Date endDate;
	
	/**
	 * Índice a ser incrementado a cada novo registro de requisição gravado
	 */
	private int acc;
	/**
	 * Número máximo de novos registros pré report. Funciona como um gatilho para os reports.
	 */
	private int maxAcc;
	
	protected boolean storeUsageData(UsageData usageData){
		boolean retVal = false;
		synchronized (stats) {
			int key = usageData.hashCode();
			UsageData data = this.stats.get(key);
			if(data != null){
				data.setRequisicoes(data.getRequisicoes().add(BigInteger.valueOf(1l)));
			} else {
				usageData.setRequisicoes(usageData.getRequisicoes().add(BigInteger.valueOf(1l)));
				stats.put(key, usageData);
			}
			retVal = true;
			acc++;
			if(acc >= maxAcc){
				notifyObservers();
				acc = 0;
			}
		}
		return retVal;
	}
	
	protected Map<Integer, UsageData> getStats(){
		return this.stats;
	}

	protected Date getEndDate() {
		return endDate;
	}

	protected void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}
	
}
