<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>To Do List</title>
</head>
<body>
	<header>
		<h1>나의 해야할 일들</h1>
		<button id="add" onclick="location.href='addForm'">새로운 TODO 등록</button>
	</header>
	<main>
		<c:forEach var="state" items="${todostate}">
			<ul id="${state}">
				<li class="list">${state}</li>
				<c:forEach var="list" items="${todolist}">
					<c:if test="${list.state eq state}">
						<li>
							<p class="title">${list.title}</p>
							<p class="info">
								등록날짜 : ${list.date}, ${list.owner}, 우선순위 ${list.priority}
								<br/>
								
								
								<c:if test="${list.state ne 'DONE'}">
									<i id="move" class="btn fa-solid fa-arrow-right" onclick="changeState(${list.id}, this)"></i>
								</c:if>
								
								<i id="delete" class="btn fa-regular fa-trash-can" onclick="deleteTodo(${list.id}, this)"></i>	
								<i id="edit" class="btn fa-solid fa-pencil" onclick="editTodo(${list.id}, this)"></i>

							</p>
						</li>
					</c:if>
				</c:forEach>

			</ul>
		</c:forEach>
		
		<form action="/TodoList/edit" method="post">
			<div id="editModal">
				<div id="content">
					<input id="id" name="id"/>
					<i id="closeBtn" class="fa-solid fa-xmark" onclick="closeEditModal()"></i>
					<label>
						제목
						<input id="modalTitle" type="text" name="title" maxlength="24" required/>
					</label>
					<br/>
					<!-- <label>
						등록날짜
						<input id="modalDate" type="date" name="date" required/>
					</label>
					<br/> -->
					<label>
						담당
						<input id="modalOwner" type="text" name="owner" required/>
					</label>
					<br/>
					<label>
						우선순위
						<select id="modalPriority" name="priority">
							<option value="1">1순위</option>
							<option value="2">2순위</option>
							<option value="3">3순위</option>
						</select>
					</label>
					<br/>
					<label>
						진행상태
						<select id="modalState" name="state">
							<option value="TODO">TODO</option>
							<option value="DOING">DOING</option>
							<option value="DONE">DONE</option>
						</select>
					</label>
					<br/>
					<input id="editUpdateBtn" type="submit" value="수정완료"/>
				</div>
			</div>
		</form>
	</main>
	
	<script type="text/javascript" src="js/main.js"></script>
	<script src="https://kit.fontawesome.com/71df5a7578.js" crossorigin="anonymous"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>