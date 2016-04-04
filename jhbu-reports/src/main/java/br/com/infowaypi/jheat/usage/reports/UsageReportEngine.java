package br.com.infowaypi.jheat.usage.reports;

import java.util.Observer;

/**
 * Relatório contendo os dados estatísiticos de uso do sistema cliente
 * @since 03/16
 */
public interface UsageReportEngine extends Observer{

	/**
	 * Despacha os dados para o destino de acordo com a configuração.
	 *  
	 * @return true se o relatório estatístico foi despachado com sucesso 
	 */
//	boolean report();
	
	void proceedAuthorization(Object arg1);
}
