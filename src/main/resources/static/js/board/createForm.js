let index = {
	init: function() {
		$("#btn-create").on("click", () => {
			this.create();
		});
	},

	create: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		$.ajax({
			type: "POST",
			url: "/api/board/create",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.status == "OK") {
				alert("글쓰기가 완료되었습니다.");
				location.href = "/";
			} else {
				alert(resp.msg);
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
}

index.init();