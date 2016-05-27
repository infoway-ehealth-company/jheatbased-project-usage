package br.com.infowaypi.jheat.usage.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectServlet extends HttpServlet{

	private static final long serialVersionUID = 6803018699844309003L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathForDispatch = "/html/project.html";
		if(getServletContext().getResource(pathForDispatch) != null){
			RequestDispatcher dispatcher = req.getRequestDispatcher(pathForDispatch);
			dispatcher.forward(req, resp);
		} else{
			//log error
		}
	}

	
}
