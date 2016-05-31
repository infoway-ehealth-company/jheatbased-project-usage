package br.com.infowaypi.jheat.usage.servlet;

//import static br.com.infowaypi.jheat.usage.api.UsageData.FLUXO;
//import static br.com.infowaypi.jheat.usage.api.UsageData.FUNCAO;
//import static br.com.infowaypi.jheat.usage.api.UsageData.SECAO;
import static br.com.infowaypi.jheat.usage.api.UsageData.PROJECT_ID;
import static br.com.infowaypi.jheat.usage.api.UsageData.DATA;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.infowaypi.jheat.usage.api.Result;
import br.com.infowaypi.jheat.usage.api.UsageData;
import br.com.infowaypi.jheat.usage.core.AppManager;

import com.google.gson.Gson;

public class UsageServlet extends HttpServlet {

	private static final long serialVersionUID = -6263906898656627010L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projId = req.getParameter(PROJECT_ID);
		String data = req.getParameter(DATA);
		Gson gson = new Gson();
		UsageData usageData = gson.fromJson(data, UsageData.class);//TODO verificar se os tres parametros foram passados e passados da forma correta 
		boolean result = AppManager.getInstance().storeUsageData(projId, usageData );
		resp.getWriter().write(gson.toJson(new Result( String.valueOf(result), 
				HttpServletResponse.SC_OK, usageData)));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projId = req.getParameter(PROJECT_ID);
		Map<Integer, UsageData> stats = AppManager.getInstance().getStats(projId);
		resp.getWriter().write( new Gson().toJson(new Result("OK", HttpServletResponse.SC_OK, stats.values())));
	}
}
