package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rating;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class RatingAPI
 */
@WebServlet("/RatingAPI")
public class RatingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 Rating RatingObj = new Rating();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingAPI() {
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
		String output = RatingObj.AddRating(request.getParameter("productId"),
				 request.getParameter("ratingId"),
				request.getParameter("ratingValue"),
				request.getParameter("overallRating"));
				response.getWriter().write(output);
				
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = RatingObj.UpdateRatings(paras.get("ratingId").toString(),
		 paras.get("ratingValue").toString(),
		 paras.get("review").toString(),
		paras.get("overallRating").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = RatingObj.DeleteRating(paras.get("ratingid").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
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
