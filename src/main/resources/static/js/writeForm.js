let index = {

	init: function() {
		$("#btn-saveWrite").on("click", () => { 
			this.saveWrite();
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
	}
	
}

index.init();