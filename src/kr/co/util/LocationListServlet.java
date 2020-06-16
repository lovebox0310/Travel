package kr.co.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import kr.co.dao.Board4DAO;
import kr.co.dto.Board4LocationDTO;

/**
 * Servlet implementation class LocationListServlet
 */

public class LocationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LocationListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Board4LocationDTO> list = new ArrayList<>();
		Board4DAO dao = new Board4DAO();
		list = dao.locationList();

		PrintWriter writer = response.getWriter();
		JSONArray jsonList = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("code", "200");
		jsonList.add(jsonObject);
		
		if (list.size() > 0) {
			jsonObject = new JSONObject();
			jsonObject.put("msg", "success");
			jsonList.add(jsonObject);
			
			String json = new Gson().toJson(list);
			jsonObject = new JSONObject();
			jsonObject.put("list", json);
			jsonList.add(jsonObject);
		} else {
			jsonObject = new JSONObject();
			jsonObject.put("msg", "fail");
			jsonList.add(jsonObject);
		}

		writer.print(jsonList);

//		System.out.println(jsonList);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
