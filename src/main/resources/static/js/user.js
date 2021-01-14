let index = {

	init: function() {
		$("#btn-join").on("click", () => { //function(){}을 안 쓰고 ()=> 쓰는 이유는 this를 binding 하기 위해서 
			this.join();
		});
	},

	join: function() {
		//alert("user의 join함수 호출됨");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		//console.log(data); //자바스크립트 오브젝트
		//console.log(JSON.stringify(data)); //Json Data

		// $.ajax().done().fail();  
		// ajax는 default가 비동기 호출		
		// ajax 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		$.ajax({//회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 MIME 타입인지
			dataType: "json" // default : json?, 요청에 대한 응답이 문자열인데 (생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp) { //전송 성공 시,
			if (resp.data == 1) {
				alert("회원 가입이 완료되었습니다.");
				location.href = "/";
			}
			else
				alert("회원 가입 실패");

		}).fail(function(error) { //전송 실패 시,
			alert(JSON.stringify(error));
		});
	},



}

index.init();