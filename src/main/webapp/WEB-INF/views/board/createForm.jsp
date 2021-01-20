<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<div class="container">

	<div class="form-group">
		<input type="text" class="form-control" placeholder="Enter Title" id="title">
	</div>

	<div class="form-group">
		<textarea class="form-control summernote" rows="5" id="content"></textarea>
	</div>

	<div class="form-group">
		<button id="btn-create" class="btn btn-primary">저장</button>
	</div>

</div>

<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/board/createForm.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>