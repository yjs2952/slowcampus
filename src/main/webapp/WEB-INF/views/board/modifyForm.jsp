<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}" />

<%@ include file="../include/header.jsp" %>
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">글 수정</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form id="articleModifyForm" role="form" class="form-horizontal" method="post" enctype="multipart/form-data">
            <input type="hidden" name="userId" value="${requestScope.board.userId}">
            <div class="box-body">
                <div class="form-group">
                    <label for="title" class="col-lg-2 col-md-2">제목</label>
                    <div class="col-lg-10 col-md-10 has-feedback">
                    <input type="text" class="form-control" name="title" id="title" value="${requestScope.board.title}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="content" class="col-lg-2 col-md-2">내용</label>
                    <div class="col-lg-10 col-md-10 has-feedback">
                        <textarea class="form-control" name="content" id="content" placeholder="내용" rows="10">${requestScope.board.content}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="exampleInputFile" class="col-lg-2 col-md-2">파일 첨부</label>
                    <div class="col-lg-10 col-md-10">
                    <input type="file" name="file" id="exampleInputFile">
                </div>
                </div>
            </div>
            <!-- /.box-body -->

            <div class="box-footer text-center">
                <button type="submit" class="btn btn-primary">확인</button>
                <a href="${path.replace("modify", "detail")}?id=${param.id}">
                    <button type="button" class="btn btn-warning">취소</button>
                </a>
            </div>
        </form>
    </div>
</section>
<script type="text/javascript">

    /*$.validator.addMethod("regex", function (value, element, regex) {
        return regex.test(value);
    });*/

    $(function(){

        var message = "${error}";

        if (message != null && message != "") {
            alert(message);
            return;
        }

        $("#articleModifyForm").validate({
            onclick: false,
            onfocusout: false,
            onkeyup: false,
            rules: {
                title: {
                    required: true,
                    minlength: 2,
                    maxlength: 50
                },
                content: {
                    required: true,
                    minlength: 2,
                    maxlength: 1000
                },
                file: {

                },
                agree: "required"
            },
            messages: {
                title: {
                    required: "제목을 입력해 주세요."
                },
                content: {
                    required: "내용을 입력해 주세요."
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element

                error.addClass("help-block");

                /*if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parents("label"));
                } else */
                if (element.prop("name") === "id") {
                    error.insertAfter(element.parent("div"));
                } else {
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".has-feedback").addClass("has-error").removeClass("has-success");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".has-feedback").addClass("has-success").removeClass("has-error");
            }/*,
            submitHandler: function () {
                var form = $('#articleWriteForm');
                /!*form.attr("action", "/write");*!/
                form.attr("method", "post");
                form.submit();
            }, invalidHandler: function (event, validator) {
                console.log(event);
                console.log(validator);
                console.log(validator.numberOfInvalids());
            }*/
        });
    });
</script>
<%@ include file="../include/footer.jsp" %>