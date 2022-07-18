<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/addList.css">
<meta charset="UTF-8">
<title>Add To Do List</title>
</head>
<body> 	
	<header>
		<h1>할일 등록</h1>
	</header>
	<main>
		<form action="/TodoList/add" method="post">
			<label>
				어떤일인가요?
				<input type="text" name="title" placeholder="swift 공부하기(24자까지)" maxlength="24" required/>
			</label>
			<br/>
			
			<label>
				누가 할일인가요?
				<input type="text" name="owner" placeholder="홍길동" required/>
 			</label>
			
			우선순위를 선택하세요
			<br/>
			<label><input type="radio" name="priority" value="1" required/> <span>1순위</span></label>
			<label><input type="radio" name="priority" value="2" required/> <span>2순위</span></label>
			<label><input type="radio" name="priority" value="3" required/> <span>3순위</span></label>
			<br/>
			
			<input id="prevBtn" class="btn" type="button" value="< 이전" onclick="location.href='todoes'"/>
			<div>
			<input id="submitBtn" class="btn" type="submit" value="제출"/>
			<input id="removeBtn" class="btn" type="button" value="내용지우기" onclick="location.href='addForm'"/>
			</div>
			
		</form>
	</main>
</body>
</html>