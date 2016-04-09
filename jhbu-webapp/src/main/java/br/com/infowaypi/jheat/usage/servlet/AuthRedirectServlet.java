package br.com.infowaypi.jheat.usage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.infowaypi.jheat.usage.core.AppManager;

public class AuthRedirectServlet extends HttpServlet {

	private static final long serialVersionUID = -553751587556822709L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("report_engine_id");
//		AppManager.getInstance().registerReportEngine(reportEngineId, reportEngine);
		super.doGet(req, resp);
	}

}
