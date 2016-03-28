package br.com.infowaypi.jheat.usage.api;

/**
 * Funciona como chave para a gravação dos dados de uso.
 */
public class ClientId {

	/**
	 * O nome do cliente
	 */
	private String key;
	/**
	 * Versão do cliente
	 */
	private String version;

	public ClientId() {
		super();
	}

	public ClientId(String key, String version) {
		super();
		this.key = key;
		this.version = version;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
