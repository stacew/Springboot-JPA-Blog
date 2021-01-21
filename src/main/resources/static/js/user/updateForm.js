let index = {
	init: function() {
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	update: function() {
		let data = {
			originalPassword: $("#originalPassword").val(),
			newPassword: $("#newPassword").val(),
			email: $("#email").val(),
		};
		$.ajax({
			type: "PUT",
			url: "/api/user/update",
			data: data,
			//contentType: "application/json; charset=utf-8",
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json"	
		}).done(function(resp) {
			if (resp.status == "OK") {
				alert("회원 정보 수정이 완료되었습니다.");
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