function draw(state, item) {
	/*
	 * var item = eventNode.parentNode.parentNode;
	 * 
	 * if(state == "TODO") state = "DOING"; else if(state == "DOING"){ state =
	 * "DONE"; eventNode.parentNode.removeChild(eventNode); }
	 */

	var changeParent = document.querySelector("ul[id=" + state + "]");
	changeParent.appendChild(item);
}
function changeState(id, eventNode) {
	var httpRequest = new XMLHttpRequest();
	var state = eventNode.parentNode.parentNode.parentNode.getAttribute("id");

	httpRequest.addEventListener("load", function() {
		if (this.responseText === "success") {
			// draw(state, eventNode);
			var item = eventNode.parentNode.parentNode;

			if (state == "TODO")
				state = "DOING";
			else if (state == "DOING") {
				state = "DONE";
				eventNode.remove();
			}

			/*
			 * var changeParent = document.querySelector("ul[id=" + state +
			 * "]"); changeParent.appendChild(item);
			 */
			draw(state, item);
		}
	});

	httpRequest.open("GET", "./move?id=" + id + "&state=" + state);
	httpRequest.send();
}

function deleteTodo(id, eventNode) {
	/*
	 * var result = confirm("삭제하시겠습니까?"); if(result === true){ var httpRequest =
	 * new XMLHttpRequest(); var state =
	 * eventNode.parentNode.parentNode.parentNode.getAttribute("id");
	 * 
	 * httpRequest.addEventListener("load", function(){ if(this.responseText ===
	 * "success"){ var item = eventNode.parentNode.parentNode; var parent =
	 * document.querySelector("ul[id=" + state + "]");
	 * 
	 * parent.removeChild(item); } }); httpRequest.open("GET", "./delete?id=" +
	 * id); httpRequest.send(); }
	 */
	Swal.fire({
		title : '삭제하시겠습니까?',
		text : '삭제하시면 다시 복구시킬 수 없습니다.',
		icon : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : '삭제',
		cancelButtonText : '취소'
	}).then(
			function(result) {
				if (result.value) {
					var httpRequest = new XMLHttpRequest();
					var state = eventNode.parentNode.parentNode.parentNode
							.getAttribute("id");

					httpRequest.addEventListener("load", function() {
						if (this.responseText === "success") {
							var item = eventNode.parentNode.parentNode;
							var parent = document.querySelector("ul[id="
									+ state + "]");

							parent.removeChild(item);
						}
					});
					httpRequest.open("GET", "./delete?id=" + id);
					httpRequest.send();
				}
			})
}

// 모달창 열기
function openEditModal() {
	var modal = document.querySelector("#editModal");
	modal.style.visibility = "visible";
}

// 모달창 닫기
function closeEditModal() {
	var modal = document.querySelector("#editModal");
	modal.style.visibility = "hidden";
}

function editTodo(id, eventNode) {
	openEditModal();

	// ... 내용 채우기 ...
	document.querySelector("#id").value = id;

	var titleInput = document.querySelector("#modalTitle");
	titleInput.value = eventNode.parentNode.previousElementSibling.innerHTML;

	var infoArr = eventNode.parentNode.innerHTML.replace(/ /g, '').split(
			/[:,\n]/);

	/*
	 * var dateInput = document.querySelector("#modalDate"); dateInput.value =
	 * infoArr[2].replaceAll('.', '-'); dateInput.max = new
	 * Date().toISOString().split('T')[0];
	 */

	var ownerInput = document.querySelector("#modalOwner");
	ownerInput.value = infoArr[3];

	var priorityNum = infoArr[4].replace(/[^0-9]/g, "");
	var priorityInput = document.querySelector("#modalPriority");
	priorityInput.options[priorityNum - 1].selected = true;

	var state = eventNode.parentNode.parentNode.parentNode.getAttribute("id");
	var stateInput = document.querySelector("#modalState");
	var i;
	for (i = 0; i <= stateInput.length - 1; i++) {
		if (stateInput.options[i].value === state)
			break;
	}
	stateInput.options[i].selected = true;
}