package kr.ac.hansung.todolist.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hansung.todolist.dao.TodoDao;

/**
 * Servlet implementation class MoveTodoServlet
 */
@WebServlet("/move/*")
public class MoveTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveTodoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String state = request.getParameter("state");
		
		if(state.equals("TODO"))
			state = "DOING";
		else if(state.equals("DOING"))
			state = "DONE";
		
		TodoDao dao = new TodoDao();
		dao.updateTodoState(id, state);
		
		PrintWriter out = response.getWriter();
		out.print("success");
		out.close();
		
		//response.sendRedirect("./todoes");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
