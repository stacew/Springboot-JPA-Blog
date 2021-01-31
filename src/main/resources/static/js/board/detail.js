let index = {
	init: function() {
		$("#btn-delete").on("click", () => {
			this.delete();
		});
		$("#btn-reply-create").on("click", () => {
			this.replyCreate();
		});
	},

	delete: function() {
		let id = $("#board_id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/delete/" + id,
			dataType: "json"
		}).done(function(resp) {
			if (resp.status == "OK") {
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			} else {
				alert(resp.msg);
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replyCreate: function() {
		let boardId =$("#boardId").val(); 
		let data = {
			content: $("#reply-content").val(),
		};

		$.ajax({
			type: "POST",
			url: `/api/board/${boardId}/reply/create`, //` 아포스트로피 사용. 자바스크립트 변수를 사용 가능.
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.status == "OK") {
				alert("댓글 등록이 완료되었습니다.");
				location.href = `/board/${boardId}`; //` 아포스트로피 사용
			} else {
				alert(resp.msg);
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

}

index.init();