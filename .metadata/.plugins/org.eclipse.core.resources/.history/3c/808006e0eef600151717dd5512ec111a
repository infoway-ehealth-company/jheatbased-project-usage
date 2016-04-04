package br.com.infowaypi.jheat.usage.servlet;

import static br.com.infowaypi.jheat.usage.api.UsageData.FLUXO;
import static br.com.infowaypi.jheat.usage.api.UsageData.FUNCAO;
import static br.com.infowaypi.jheat.usage.api.UsageData.SECAO;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.infowaypi.jheat.usage.api.Result;
import br.com.infowaypi.jheat.usage.api.UsageData;
import br.com.infowaypi.jheat.usage.core.UsageStorage;

public class UsageServlet extends HttpServlet {

	private static final long serialVersionUID = -6263906898656627010L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter(FUNCAO);
		String secao = req.getParameter(SECAO);
		String fluxo = req.getParameter(FLUXO);
		UsageData usageData = new UsageData(funcao, secao, fluxo);
		boolean result = UsageStorage.getInstance().storeUsageData(usageData );
		resp.getWriter().write(new Gson().toJson(new Result( String.valueOf(result), 
				HttpServletResponse.SC_OK, usageData)));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<UsageData, BigInteger> stats = UsageStorage.getInstance().getStats();
		String json = new Gson().toJson(stats);
		resp.getWriter().write(json);
	}

	
}
