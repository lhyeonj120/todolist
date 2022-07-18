package kr.ac.hansung.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.hansung.todolist.dto.Todo;

public class TodoDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul";
	private static String dbUser = "connectuser";
	private static String dbPasswd = "connect123!@#";
	private static String driver = "com.mysql.cj.jdbc.Driver";

	// insert
	public int addList(Todo todo) {
		int insertCount = 0;

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO todo (title, owner, priority) VALUES (?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getOwner());
			ps.setInt(3, todo.getPriority());

			insertCount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertCount;
	}

	// select
	/*public Todo getTodo(String todoTitle) {
		Todo todo = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT id, title, owner, priority, date, state FROM todo WHERE title = ?";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, todoTitle);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String owner = rs.getString(3);
					int priority = rs.getInt(4);
					String date = rs.getString(5);
					int state = rs.getInt(6);
					todo = new Todo(id, title, owner, priority, date, state);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todo;
	}*/

	// all select
	public List<Todo> getTodoes() {
		List<Todo> list = new ArrayList<>();

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT id, title, owner, priority, date_format(date, '%Y.%m.%d'), state FROM todo order by date_format(date, '%Y.%m.%d') desc, priority";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Todo todo = new Todo();
					
					todo.setId(rs.getInt(1));
					todo.setTitle(rs.getString(2));
					todo.setOwner(rs.getString(3));
					todo.setPriority(rs.getInt(4));
					todo.setDate(rs.getString(5));
					todo.setState(rs.getString(6));
					
					list.add(todo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// update
	public int updateTodoState(int id, String state) {
		int updateCount = 0;

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "UPDATE todo SET state = ? WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, state);
			ps.setInt(2, id);

			updateCount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return updateCount;
	}
	
	public int updateTodo(Todo todo) {
		int updateCount = 0;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "UPDATE todo SET title = ?, date = ?, owner = ?, priority = ?, state = ? WHERE id = ?";
		try(Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getDate());
			ps.setString(3, todo.getOwner());
			ps.setInt(4, todo.getPriority());
			ps.setString(5, todo.getState());
			ps.setInt(6, todo.getId());
			
			updateCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	// delete
	public int deleteTodo(int id) {
		int deleteCount = 0;
		
		try {
			Class.forName(driver);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "DELETE FROM todo WHERE id = ?";
		try(Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, id);
			
			deleteCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleteCount;
	}

}