let index = {
	init: function() {
		$("#btn-delete").on("click", () => {
			this.delete();
		});
	},

	delete: function() {
		let id = $("#board_id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
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

}

index.init();