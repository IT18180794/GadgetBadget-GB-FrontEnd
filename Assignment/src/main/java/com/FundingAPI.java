package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Funding;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class FundingAPI
 */
@WebServlet("/FundingAPI")
public class FundingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Funding FundingObj = new Funding();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = FundingObj.SetFunding(request.getParameter("fundingId"),
				 request.getParameter("rate"),
				request.getParameter("description"),
				request.getParameter("period"),
				request.getParameter("totalfunding"));
				response.getWriter().write(output);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = FundingObj.UpdatingFunding(paras.get("hidFundingIdSave").toString(),
		 paras.get("rate").toString(),
		 paras.get("description").toString(),
		paras.get("period").toString(),
		paras.get("totalfunding").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = FundingObj.DeleteFunding(paras.get("fundingId").toString());
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request){
		
		 Map<String, String> map = new HashMap<String, String>();
		try {
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			 String queryString = scanner.hasNext() ?
			 scanner.useDelimiter("\\A").next() : "";
			 scanner.close();
			 String[] params = queryString.split("&");
			 for (String param : params){ 
				 String[] p = param.split("=");
				 map.put(p[0], p[1]);
		     }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}


}
