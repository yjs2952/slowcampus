<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/ -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 가입</title>
    <!-- Bootstrap -->
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
    <script src="http://code.jquery.com/jquery.js"></script>
    <!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container"><!-- 좌우측의 공간 확보 -->
    <!-- 헤더 들어가는 부분 -->

    <div class="row">
        <p></p>
    </div>
    <!--// 헤더 들어가는 부분 -->
    <!-- 모달창 -->
    <div class="modal fade" id="defaultModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">알림</h4>
                </div>
                <div class="modal-body">
                    <p class="modal-contents"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!--// 모달창 -->
    <hr/>
    <!-- 본문 들어가는 부분 -->


    <form class="form-horizontal" role="form" method="post" action="/signup">
        <div class="form-group" id="divId">
            <label class="col-lg-2 control-label">아이디</label>
            <div class="col-lg-10">
                <input type="text" class="form-control onlyAlphabetAndNumber" id="id" name="id" maxlength="30">
            </div>
        </div>
        <div class="form-group" id="divPassword">
            <label class="col-lg-2 control-label">패스워드</label>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="password" name="password" maxlength="30">
            </div>
        </div>
        <div class="form-group" id="divPasswordCheck">
            <label class="col-lg-2 control-label">패스워드 확인</label>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="passwordCheck" maxlength="30">
            </div>
        </div>
        <div class="form-group" id="divNickname">
            <label class="col-lg-2 control-label">닉네임</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="nickname" name="nickname" maxlength="15">
            </div>
        </div>

        <div class="form-group" id="divEmail">
            <label class="col-lg-2 control-label">이메일</label>
            <div class="col-lg-10">
                <input type="email" class="form-control" id="email" name="email" maxlength="40">
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">회원 가입</button>
            </div>
        </div>
    </form>


    <script>

        $(function () {
            //모달을 전역변수로 선언
            var modalContents = $(".modal-contents");
            var modal = $("#defaultModal");

            $('.onlyAlphabetAndNumber').keyup(function (event) {
                if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
                    var inputVal = $(this).val();
                    $(this).val($(this).val().replace(/[^_a-z0-9]/gi, '')); //_(underscore), 영어, 숫자만 가능
                }
            });

            $(".onlyHangul").keyup(function (event) {
                if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
                    var inputVal = $(this).val();
                    $(this).val(inputVal.replace(/[a-z0-9]/gi, ''));
                }
            });

            $(".onlyNumber").keyup(function (event) {
                if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
                    var inputVal = $(this).val();
                    $(this).val(inputVal.replace(/[^0-9]/gi, ''));
                }
            });

            //------- 검사하여 상태를 class에 적용
            $('#id').keyup(function (event) {

                var divId = $('#divId');

                if ($('#id').val() == "") {
                    divId.removeClass("has-success");
                    divId.addClass("has-error");
                } else {
                    divId.removeClass("has-error");
                    divId.addClass("has-success");
                }
            });

            $('#password').keyup(function (event) {

                var divPassword = $('#divPassword');

                if ($('#password').val() == "") {
                    divPassword.removeClass("has-success");
                    divPassword.addClass("has-error");
                } else {
                    divPassword.removeClass("has-error");
                    divPassword.addClass("has-success");
                }
            });

            $('#passwordCheck').keyup(function (event) {

                var passwordCheck = $('#passwordCheck').val();
                var password = $('#password').val();
                var divPasswordCheck = $('#divPasswordCheck');

                if ((passwordCheck == "") || (password != passwordCheck)) {
                    divPasswordCheck.removeClass("has-success");
                    divPasswordCheck.addClass("has-error");
                } else {
                    divPasswordCheck.removeClass("has-error");
                    divPasswordCheck.addClass("has-success");
                }
            });

            $('#nickname').keyup(function (event) {

                var divNickname = $('#divNickname');

                if ($.trim($('#nickname').val()) == "") {
                    divNickname.removeClass("has-success");
                    divNickname.addClass("has-error");
                } else {
                    divNickname.removeClass("has-error");
                    divNickname.addClass("has-success");
                }
            });

            $('#email').keyup(function (event) {

                var divEmail = $('#divEmail');

                if ($.trim($('#email').val()) == "") {
                    divEmail.removeClass("has-success");
                    divEmail.addClass("has-error");
                } else {
                    divEmail.removeClass("has-error");
                    divEmail.addClass("has-success");
                }
            });

            $('#phoneNumber').keyup(function (event) {

                var divPhoneNumber = $('#divPhoneNumber');

                if ($.trim($('#phoneNumber').val()) == "") {
                    divPhoneNumber.removeClass("has-success");
                    divPhoneNumber.addClass("has-error");
                } else {
                    divPhoneNumber.removeClass("has-error");
                    divPhoneNumber.addClass("has-success");
                }
            });


            //------- validation 검사
            $("form").submit(function (event) {

                var provision = $('#provision');
                var memberInfo = $('#memberInfo');
                var divId = $('#divId');
                var divPassword = $('#divPassword');
                var divPasswordCheck = $('#divPasswordCheck');
                var divName = $('#divName');
                var divNickname = $('#divNickname');
                var divEmail = $('#divEmail');

                //아이디 검사
                if ($('#id').val() == "") {
                    modalContents.text("아이디를 입력하여 주시기 바랍니다.");
                    modal.modal('show');

                    divId.removeClass("has-success");
                    divId.addClass("has-error");
                    $('#id').focus();
                    return false;
                } else {
                    divId.removeClass("has-error");
                    divId.addClass("has-success");
                }

                //패스워드 검사
                if ($('#password').val() == "") {
                    modalContents.text("패스워드를 입력하여 주시기 바랍니다.");
                    modal.modal('show');

                    divPassword.removeClass("has-success");
                    divPassword.addClass("has-error");
                    $('#password').focus();
                    return false;
                } else {
                    divPassword.removeClass("has-error");
                    divPassword.addClass("has-success");
                }

                //패스워드 확인
                if ($('#passwordCheck').val() == "") {
                    modalContents.text("패스워드 확인을 입력하여 주시기 바랍니다.");
                    modal.modal('show');

                    divPasswordCheck.removeClass("has-success");
                    divPasswordCheck.addClass("has-error");
                    $('#passwordCheck').focus();
                    return false;
                } else {
                    divPasswordCheck.removeClass("has-error");
                    divPasswordCheck.addClass("has-success");
                }

                //패스워드 비교
                if ($('#password').val() != $('#passwordCheck').val() || $('#passwordCheck').val() == "") {
                    modalContents.text("패스워드가 일치하지 않습니다.");
                    modal.modal('show');

                    divPasswordCheck.removeClass("has-success");
                    divPasswordCheck.addClass("has-error");
                    $('#passwordCheck').focus();
                    return false;
                } else {
                    divPasswordCheck.removeClass("has-error");
                    divPasswordCheck.addClass("has-success");
                }

                //닉네임
                if ($('#nickname').val() == "") {
                    modalContents.text("닉네임을 입력하여 주시기 바랍니다.");
                    modal.modal('show');

                    divNickname.removeClass("has-success");
                    divNickname.addClass("has-error");
                    $('#nickname').focus();
                    return false;
                } else {
                    divNickname.removeClass("has-error");
                    divNickname.addClass("has-success");
                }

                //이메일
                if ($('#email').val() == "") {
                    modalContents.text("이메일을 입력하여 주시기 바랍니다.");
                    modal.modal('show');

                    divEmail.removeClass("has-success");
                    divEmail.addClass("has-error");
                    $('#email').focus();
                    return false;
                } else {
                    divEmail.removeClass("has-error");
                    divEmail.addClass("has-success");
                }
            });

        });

    </script>
    <!--// 본문 들어가는 부분 -->
    <hr/>
</div>
</body>
</html>
