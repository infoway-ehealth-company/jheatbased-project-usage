package br.com.infowaypi.jheat.usage.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import sun.org.mozilla.javascript.json.JsonParser;

public class UsageData {

	public static final String FUNCAO = "funcao";
	public static final String SECAO = "secao";
	public static final String FLUXO = "fluxo";
	public static final String SEPARATOR = ",";

	private String funcao;
	private String secao;
	private String fluxo;

	public UsageData() {
		super();
	}

	public UsageData(String funcao, String secao, String fluxo) {
		super();
		this.funcao = funcao;
		this.secao = secao;
		this.fluxo = fluxo;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public String getFluxo() {
		return fluxo;
	}

	public void setFluxo(String fluxo) {
		this.fluxo = fluxo;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof UsageData))
			return false;
		UsageData usageData = (UsageData) obj;
		if(this.fluxo != null && !this.fluxo.equals(usageData.getFluxo()))
			return false;
		if(this.funcao != null && !this.funcao.equals(usageData.getFuncao()))
			return false;
		if(this.secao != null && !this.secao.equals(usageData.getSecao()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		
		return super.hashCode();
	}

	@Override
	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append(FUNCAO+":"+this.funcao);
//		sb.append(SEPARATOR );
//		sb.append(SECAO+":"+this.secao);
//		sb.append(SEPARATOR );
//		sb.append(FLUXO+":"+this.fluxo);
//		return sb.toString();
		return new Gson().toJson(this);
	}
	
}
