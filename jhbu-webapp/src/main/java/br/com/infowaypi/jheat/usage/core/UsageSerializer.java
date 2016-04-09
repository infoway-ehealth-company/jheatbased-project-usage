package br.com.infowaypi.jheat.usage.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.infowaypi.jheat.usage.api.UsageData;

public class UsageSerializer extends JsonSerializer<UsageData>{

	@Override
	public void serialize(UsageData arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		arg1.writeFieldName("requisicao");
		arg1.writeStartObject();
		arg1.writeStringField(UsageData.FUNCAO, arg0.getFuncao());
		arg1.writeStringField(UsageData.FLUXO, arg0.getFluxo());
		arg1.writeStringField(UsageData.SECAO, arg0.getSecao());
		arg1.writeEndObject();
	}

}
