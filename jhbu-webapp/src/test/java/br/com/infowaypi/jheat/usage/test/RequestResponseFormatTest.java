package br.com.infowaypi.jheat.usage.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.infowaypi.jheat.usage.api.UsageData;
import br.com.infowaypi.jheat.usage.core.AppManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(BlockJUnit4ClassRunner.class)
public class RequestResponseFormatTest {

	@Test
	public void testResponseFormat() throws JsonProcessingException{
		String projId = "projId";
		UsageData data1 = new UsageData("faturista", "cobranca", "cobrardevedor");
		UsageData data2 = new UsageData("regulador", "regulacao", "regularGuia");
		UsageData data3 = new UsageData("regulador", "regulacao", "regularGuia");
		UsageData data4 = new UsageData("regulador", "regulacao", "regularGuia");
		AppManager appManager = AppManager.getInstance();
		appManager.storeUsageData(projId, data1);
		appManager.storeUsageData(projId, data2);
		appManager.storeUsageData(projId, data3);
		appManager.storeUsageData(projId, data4);
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();;
//		Type type = new TypeToken<Map<UsageData, BigInteger>>() {}.getType();
//		String json = new Gson().toJson(appManager.getStats(), type);
		String json  = gson.toJson(appManager.getStats(projId).values());
		
//		SimpleModule sm = new SimpleModule();
//		ObjectMapper om = new ObjectMapper();
//		sm.addKeySerializer(UsageData.class, new UsageSerializer());
//		om.registerModule(sm);
//		
//		MapType mapType = TypeFactory.defaultInstance().constructMapType(HashMap.class, UsageData.class, BigInteger.class);
//		ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
//		String json2 = ow.writeValueAsString(data1);
		
		
		System.out.println(json);
	}
}
