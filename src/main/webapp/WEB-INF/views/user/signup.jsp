<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>One Week Board</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style -->
    <link rel="stylesheet" href="/resources/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/resources/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <!-- jQuery 3 -->
    <script src="/resources/plugins/validator/jquery-1.11.1.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- validation check -->
    <script src="/resources/plugins/validator/jquery.validate.min.js"></script>
    <script src="/resources/plugins/validator/messages_ko.min.js"></script>

    <!-- iCheck -->
    <script src="/resources/plugins/iCheck/icheck.min.js"></script>
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">
        <a href="/"><b>One Week Board</b></a>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg">Register a new membership</p>

        <form id="signupForm">
            <div class="form-group has-feedback">
                <input type="text" name="id" class="form-control" placeholder="ID">
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="passwordCheck" class="form-control" placeholder="Retype password">
            </div>
            <div class="form-group has-feedback">
                <input type="text" name="nickname" class="form-control" placeholder="Nickname">
            </div>
            <div class="form-group has-feedback">
                <input type="email" name="email" class="form-control" placeholder="Email">
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label class="">
                            <div class="icheckbox_square-blue" aria-checked="false" aria-disabled="false" style="position: relative;"><input type="checkbox" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div> I agree to the <a href="#">terms</a>
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Register</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <a href="/signin" class="text-center">I already have a membership</a>
    </div>
    <!-- /.form-box -->
</div>

<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function () {
            var form = $('#signupForm');
            form.attr("action", "/signup");
            form.attr("method", "post");
            form.submit();
        }
    });

    $(document).ready(function () {

        /*var result = "${result}";

        if (result != null && result != "") {
            alert(result);
            result = null;
        }*/

        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });

        $("#signinForm").validate({
            rules: {
                /*firstname: "required",
                lastname: "required",*/
                id: {
                    required: true
                },
                password: {
                    required: true
                }/*,
                    confirm_password: {
                        required: true,
                        minlength: 5,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    agree: "required"*/
            },
            messages: {
                id: {
                    required: "아이디를 입력해 주세요."
                },
                password: {
                    required: "비밀번호를 입력해 주세요."
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");

                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".has-feedback").addClass("has-error").removeClass("has-success");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".has-feedback").addClass("has-success").removeClass("has-error");
            }
        });
    });
</script>


</body>

<script>

    /*$(function () {
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

    });*/

</script>

</html>
