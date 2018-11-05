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

    <!-- iCheck -->
    <script src="/resources/plugins/iCheck/icheck.min.js"></script>

    <!-- validation check -->
    <script src="/resources/plugins/validator/jquery.validate.min.js"></script>
    <script src="/resources/plugins/validator/messages_ko.min.js"></script>
    <script src="/resources/plugins/validator/additional-methods.min.js"></script>

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
                <div class="input-group">
                    <input type="text" name="id" id="id" class="form-control" placeholder="ID">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-warning btn-flat">중복 확인</button>
                    </span>
                </div>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="password" name="password" class="form-control" placeholder="Password">
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="passwordCheck" name="passwordCheck" class="form-control"
                       placeholder="Retype password">
            </div>
            <div class="form-group has-feedback">
                <input type="text" id="nickname" name="nickname" class="form-control" placeholder="Nickname">
            </div>
            <div class="form-group has-feedback">
                <input type="email" id="email" name="email" class="form-control" placeholder="Email">
            </div>
            <div class="row">
                <%--<div class="col-xs-8">
                    <div class="checkbox icheck has-feedback">
                        <label class="label-check">
                            <div class="icheckbox_square-blue" aria-checked="false" aria-disabled="false"
                                 style="position: relative;">
                                <input type="checkbox" name="agree"
                                       style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;">
                                <ins class="iCheck-helper"
                                     style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins>
                            </div>
                            I agree to the terms
                        </label>
                    </div>
                </div>--%>
                <!-- /.col -->
                <div class="col-xs-4 pull-right">
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
    $.validator.setDefaults({});

    $.validator.addMethod("regex", function (value, element, regex) {
        return regex.test(value);
    });

    $(document).ready(function () {

        $("#signupForm").validate({
            onclick: false,
            onfocusout: false,
            onkeyup: false,
            rules: {
                id: {
                    required: true,
                    minlength: 6,
                    maxlength: 20,
                    regex: /^[A-Za-z0-9+]*$/
                },
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 20
                },
                passwordCheck: {
                    required: true,
                    minlength: 8,
                    equalTo: "#password"
                },
                email: {
                    required: true,
                    email: true
                },
                nickname: {
                    required: true,
                    minlength: 2,
                    maxlength: 20
                },
                agree: "required"
            },
            messages: {
                id: {
                    required: "아이디를 입력해 주세요.",
                    regex: "영문 및 숫자만 입력 가능합니다."
                },
                password: {
                    required: "비밀번호를 입력해 주세요."
                },
                passwordCheck: {
                    required: "비밀번호 확인을 입력해 주세요.",
                    equalTo: "비밀번호가 일치하지 않습니다."
                },
                email: {
                    required: "이메일을 입력해 주세요."
                },
                nickname: {
                    required: "닉네임을 입력해 주세요."
                },
                agree: {
                    required: "약관 동의를 체크해 주세요."
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
            },
            submitHandler: function () {
                var form = $('#signupForm');
                form.attr("action", "/signup");
                form.attr("method", "post");
                form.submit();
            }, invalidHandler: function (event, validator) {
                console.log(event);
                console.log(validator);
                console.log(validator.numberOfInvalids());
            }
        });
    });
</script>


</body>

</html>
