let index = {

	init: function() {
		$("#btn-saveWrite").on("click", () => { 
			this.saveWrite();
		});
		$("#btn-update").on("click", () => { 
			this.update();
		});
	},

	saveWrite: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		$.ajax({
			type: "POST",
			url: "/api/saveWriteBoard",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.data == 1) {
				alert("글쓰기가 완료되었습니다.");
				location.href = "/";
			}
			else
				alert("글쓰기 실패");
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.data == 1) {
				alert("글 수정이 완료되었습니다.");
				location.href = "/";
			}
			else
				alert("글 수정 실패");
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
}

index.init();