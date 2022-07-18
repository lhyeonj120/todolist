package kr.ac.hansung.todolist.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hansung.todolist.dao.TodoDao;
import kr.ac.hansung.todolist.dto.Todo;

/**
 * Servlet implementation class EditTodoServlet
 */
@WebServlet("/edit")
public class EditTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTodoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = simpleDateFormat.format(new Date());
		//String date = new Date().toString();
		
		String owner = request.getParameter("owner");
		int priority = Integer.parseInt(request.getParameter("priority"));
		String state = request.getParameter("state");
		
		Todo todo = new Todo();
		TodoDao dao = new TodoDao();
		
		todo.setId(id);
		todo.setTitle(title);
		todo.setDate(date);
		todo.setOwner(owner);
		todo.setPriority(priority);
		todo.setState(state);
		
		dao.updateTodo(todo);
		
		response.sendRedirect("./");
	}

}
