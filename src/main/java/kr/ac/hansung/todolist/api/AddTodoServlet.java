package kr.ac.hansung.todolist.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hansung.todolist.dao.TodoDao;
import kr.ac.hansung.todolist.dto.Todo;

/**
 * Servlet implementation class AddTodoServlet
 */
@WebServlet("/add")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTodoServlet() {
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

		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String owner = request.getParameter("owner");
		int priority = Integer.parseInt(request.getParameter("priority"));
		
		Todo todo = new Todo();
		TodoDao dao = new TodoDao();
		
		todo.setTitle(title);
		todo.setOwner(owner);
		todo.setPriority(priority);
		
		dao.addList(todo);
		
		response.sendRedirect("./todoes");
	}

}
