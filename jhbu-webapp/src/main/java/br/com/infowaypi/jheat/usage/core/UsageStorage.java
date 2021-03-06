package br.com.infowaypi.jheat.usage.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import br.com.infowaypi.jheat.usage.api.UsageData;

/**
 * Cont�m o mapa com dados estat�sticos acumulados dentro do ciclo. Dado que este projeto n�o utiliza banco de dados, com vista � simplicidade,
 * o ciclo de storage dos dados � iniciado com o lan�amento do container de aplica��o e finaliza com o desligamento deste.
 * 
 * @since 28/03/2016
 */
public class UsageStorage extends Observable{
	
	/**
	 * Mapa para grava��o das estat�sticas dos respectivos fluxos. Possui tamanho m�ximo definido - para limite de uso de mem�ria -
	 * permitindo um total de 100 features mapeadas.
	 */
	private Map<Integer, UsageData> stats = new HashMap<Integer, UsageData>();
	/**
	 * Data de in�cio da contagem de acessos. Atribu�da uma vez, quando da inicializa��o do container de aplica��o e instancia��o
	 * do singleton UsageStorage.
	 */
	private final Date startDate = new Date();
	private Date endDate;
	
	/**
	 * �ndice a ser incrementado a cada novo registro de requisi��o gravado
	 */
	private int acc;
	/**
	 * N�mero m�ximo de novos registros pr� report. Funciona como um gatilho para os reports.
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
