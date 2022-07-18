package kr.ac.hansung.todolist.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hansung.todolist.dao.TodoDao;
import kr.ac.hansung.todolist.dto.Todo;

/**
 * Servlet implementation class TodoesServlet
 */
@WebServlet("/todoes")
public class TodoesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TodoDao dao = new TodoDao();	

		List<Todo> list = dao.getTodoes();
		request.setAttribute("todolist", list);
		
		String[] state = {"TODO", "DOING", "DONE"};
		request.setAttribute("todostate", state);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("main.jsp");
		requestDispatcher.forward(request, response);

	}
	
}
