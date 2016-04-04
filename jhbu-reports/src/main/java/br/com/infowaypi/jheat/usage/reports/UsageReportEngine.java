package br.com.infowaypi.jheat.usage.reports;

import java.util.Observer;

/**
 * Relat�rio contendo os dados estat�siticos de uso do sistema cliente
 * @since 03/16
 */
public interface UsageReportEngine extends Observer{

	/**
	 * Despacha os dados para o destino de acordo com a configura��o.
	 *  
	 * @return true se o relat�rio estat�stico foi despachado com sucesso 
	 */
//	boolean report();
	
	void proceedAuthorization(Object arg1);
}
