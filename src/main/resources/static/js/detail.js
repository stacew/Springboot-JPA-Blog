let index = {
	init: function() {
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
	},

	//보낸 사람의 권한이나 id 체크도 필요..
	deleteById: function() {
		var id = $("#board_id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json"
		}).done(function(resp) {
			if (resp.data == 1) {
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			}
			else
				alert("삭제 실패");
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

}

index.init();