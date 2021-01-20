let index = {
	init: function() {
		$("#btn-update").on("click", () => {
			this.update();
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
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.status == "OK") {
				alert("글 수정이 완료되었습니다.");
				location.href = "/";
			} else {
				alert(resp.msg);
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();