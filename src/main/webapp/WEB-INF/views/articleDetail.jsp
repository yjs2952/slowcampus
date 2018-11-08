<%--
  Created by IntelliJ IDEA.
  User: choijaeyong
  Date: 31/10/2018
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/header.jsp" %>

<style>
    div { display: block; }
    col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
        position: relative;
        min-height: 1px;
        padding-right: 15px;
        padding-left: 15px;
    }
    .user-block .username {
        font-size: 16px;
        font-weight: 600;
    }
    .user-block .description {
        color: #999;
        font-size: 13px;
    }

    .user-block .username, .user-block .description, .user-block .comment {
        display: block;
        margin-left: 50px;
    }

    .box-widget {
        border: none;
        position: relative;
    }

    .box {
        position: relative;
        border-radius: 3px;
        background: #ffffff;
        border-top: 3px solid #d2d6de;
        margin-bottom: 20px;
        width: 100%;
        box-shadow: 0 1px 1px rgba(0,0,0,0.1);
    }

    .box-comments {
        background: #ff77ff;

    }
    .box-footer {
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 3px;
        border-bottom-left-radius: 3px;
        border-top: 1px solid #f4f4f4;
        padding: 10px;
        background-color: #f7f7f7;
    }

    .box-comments .username {
        color: #444;
        display: block;
        font-weight: 600;
    }
    .box-comments .comment-text {
        margin-left : 40px;
        display: block;
    }

    .box-comments .box-comment:first-of-type {
        padding-top: 0;
    }

    .box-comments .box-comment {
        padding: 8px 0;
        border-bottom: 1px solid;
    }

    .box-comments .text-muted {
        font-weight: 400;
        font-size: 12px;
    }

    .text-muted {
        color: #777;
    }

    form {
        display: block;
        margin-top: 0em;
    }
    .img-sm+.img-push {
        margin-left: 40px;
    }

</style>


<script>

    var isOpend=false;
    var loginMember = "${sessionScope.login.nickname}";
    var parentUser="";



// 이미지 팝업창으로 원본 보기!!!!
    function doImgPop(img){
        img1= new Image();
        img1.src=(img);
        imgControll(img);
    }

    function imgControll(img){
        if((img1.width!=0)&&(img1.height!=0)){
            viewImage(img);
        }
        else{
            controller="imgControll('"+img+"')";
            intervalID=setTimeout(controller,20);
        }
    }

    function viewImage(img){
        W=img1.width;
        H=img1.height;
        O="width="+W+",height="+H+",scrollbars=yes";
        imgWin=window.open("","",O);
        imgWin.document.write("<html><head><title>:*:*:*: 이미지상세보기 :*:*:*:*:*:*:</title></head>");
        imgWin.document.write("<body topmargin=0 leftmargin=0>");
        imgWin.document.write("<img src="+img+" onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
        imgWin.document.close();
    }


</script>

<script type="text/javascript">

    // var boardid = 3;
    var link = document.location.href.split("?");
    console.log(link[1]);

    // 파리미터값 가져오는 메소드!!!
    function getParameterByName(name , url) {
        if(!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex=new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results=regex.exec(url);
        return results[2];
    }
    var boardid = getParameterByName('id');
    console.log(boardid);

    getComments(boardid);




    console.log("ddfdafdaf : "+loginMember);

    console.log("getComments() 끝.");



    // 댓글 리스트 불러오는 메소드.
    function getComments(boardid) {

        $.getJSON("/comment/list/"+boardid, function(data) {
            var str="";
            console.log(data.length);

            $(data).each(
                function() {
                    var myDate = new Date(this.moddate);
                    var outputDate = myDate.getFullYear() + "-" +  (myDate.getMonth()+1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();


                    str += "<div class='box-comment' >" +
                        "<img style='float:left;' class='img-circle img-sm' width='30' height='30' src='https://slowcampus.blob.core.windows.net/quickstartcontainer/2018_11_01/a623e6f3-20d0-43ca-98a0-4b8fa9b94e8a_dahyun.jpg'>" +
                        "<div class='comment-text'>\n" +
                        " <span data-commentNo='" + this.id +"' id='commentUser' class='username'>\n"+ this.userNickname;

                    // 글쓴이랑 로그인 한 멤버의 닉네임이 같을 경우에만 버튼이 나오게!.
                    if(this.userNickname == loginMember) {
                        str += " <button id='modifyComment' type='button' class='btn btn-box-tool' data-toggle='modal' data-target='#modifyModal' title data-widget='chat-pane-toggle' data-original-title='update'><i class='fa fa-edit'></i></button>\n" +
                            " <button id='deleteComment' type='button' class='btn btn-box-tool' data-toggle='tooltip' title data-widget='chat-pane-toggle' data-original-title='remove'><i class='fa fa-times'></i></button>\n";
                    }

                    // 로그인이 안됐을땐 대댓글 다는 버튼을 비활성화 해야한다.
                    if(loginMember != ""){
                        str += " <button id='reComment' type='button' onclick='reCommentClick("+ this.id+",isOpend);' class='btn btn-box-tool' data-toggle='tooltip' title data-widget='chat-pane-toggle' data-original-title=''><i class='fa fa-align-left'></i></button>\n";
                    }


                    str += "                            </span><!-- /.username -->\n" +
                        "                            <span class='text-muted pull-right'>" + outputDate + "</span>\n" +
                        "\n" +
                        "                            <span class='commentContent' id='commentContent'>" + this.content + "</span>\n" +
                        "                        </div>\n" +
                        "                        <!-- /.comment-text -->\n" +
                        "                    </div>\n" +
                        "                    <!-- /.box-comment -->" +
                        " <div data-recommentNo='"+ this.id + "'  id='reCommentGroup'>\n " +

                        " </div> ";
                }
            );
            $("#comments").html(str);
            <%--<fmt:formatDate value='${this.moddate}' pattern='yyyyMMdd' />--%>
        });

    }


</script>

<script type="text/javascript">


</script>


<h3 class="box-title">일단은 FreeBoard</h3>

<%--<div class ="box-body">--%>
    <%--<div class="form-group">--%>
        <%--<label>Title</label>--%>
        <%--<br>--%>

        <%--<textarea class="form-control" rows="1" disabled>${board.title}</textarea>--%>
        <%--<br>--%>

        <%--<label>Content</label>--%>
        <%--<br>--%>

        <%--<textarea class="form-control" rows="5" disabled>${board.content}</textarea>--%>
        <%--<br>--%>
    <%--</div><!-- /.form-group -->--%>

    <%--<div class="form-group">--%>
        <%--<label>Images</label>--%>
        <%--<br>--%>

        <%--<c:forEach items="${images}" var="image">--%>
            <%--<img width="100" height="100"--%>
                 <%--src="https://slowcampus.blob.core.windows.net/quickstartcontainer${image.path}"--%>
                 <%--style="cursor: pointer;" onclick="doImgPop('https://slowcampus.blob.core.windows.net/quickstartcontainer${image.path}')" />--%>
        <%--</c:forEach>--%>


    <%--</div>--%>



    <div class="form-group pull-right">
        <button type="submit" class="btn btn-primary">수정</button>
        <button type="submit" class="btn btn-primary" onclick="location.href='/boards/${board.category}/articles/delete?id=${board.id}'">삭제</button>
    </div>


<%--</div> <!-- /.box-body-->--%>



<div class="row">

    <div class="col-md-12">
        <div class="box box-widget">
            <div class="box-header with-border">
                <div class="user-block">
                    <span class="username"><a href="">${board.title}</a></span>
                    <span class="description">${board.regDate}</span>
                </div>
                <!-- /.user-block -->
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <!-- post text -->

                <p>${board.content}
                </p>

                <br>
                <!-- Attachment -->
                <div class="attachment-block clearfix">

                    <c:forEach items="${images}" var="image">
                        <img width="100" height="100"
                             src="https://slowcampus.blob.core.windows.net/quickstartcontainer${image.path}"
                             style="cursor: pointer;" onclick="doImgPop('https://slowcampus.blob.core.windows.net/quickstartcontainer${image.path}')" />
                    </c:forEach>

                </div>
                <!-- /.attachment-block -->

                <!-- Social sharing buttons -->
                <%--<button type="button" class="btn btn-default btn-xs"><i class="fa fa-share"></i> Share</button>--%>
                <%--<button type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up"></i> Like</button>--%>
                <%--<span class="pull-right text-muted">45 likes - 2 comments</span>--%>
            </div>

            <h3> Comments </h3>
            <!-- 사진이랑 코멘트랑 일렬로 안되던거... 사진 옆에 float: left 해주니까 됐다.-->

            <!-- /.box-body -->
            <div id="comments" class="box-footer box-comments">


            </div>
            <!-- /.box-footer -->

            <!-- Write -->
            <!-- 이것도 왜 된건지 모르겠는데 위에 push와 form 써있는 부분 추가해주고 float:left 설정해주니 됐네...???-->
            <!-- 로그인 안되어 있으면 로그인 하시오 메시지가 보이게 작성해야함!-->

            <!-- member 가 있을때만 입력폼을 보여줘라. -->
            <c:if test="${not empty member}">
                <div class="box-footer">
                    <img style="float: left;"class="img-circle img-sm" width="30" height="30" src="https://slowcampus.blob.core.windows.net/quickstartcontainer/2018_11_01/a623e6f3-20d0-43ca-98a0-4b8fa9b94e8a_dahyun.jpg">
                    <!-- .img-push is used to add margin to elements next to floating images -->
                    <div class="input-group margin">
                        <input type="text" name="commentWriteContent" id="commentWriteContent" class="form-control" placeholder="Press enter to post comment">
                        <span class="input-group-btn">
                                <button type="button" id="commentWriteBtn"class="btn btn-info btn-flat">Input!</button>
                            </span>
                    </div>
                </div> <!-- /.box-footer -->

            </c:if>

            <c:if test="${empty member}">
                <div class="box-body">
                    <div><a href="/signin">Login Please...</a></div>
                </div>
            </c:if>

        </div>


    </div> <!-- /.col-md-6 -->

</div>  <!-- /.row -->

<div class="modal fade" id="modifyModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">댓글 수정창</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="modalCommentId">댓글 번호</label>
                    <input class="form-control" id="modalCommentId" name="modalCommentId" readonly>
                </div>
                <div class="form-group">
                    <label for="modalCommentContent">댓글 내용</label>
                    <input class="form-control" id="modalCommentContent" name="modalCommentContent" placeholder="댓글 내용을 입력해주세요">
                </div>
                <div class="form-group">
                    <label for="modalCommentUser">댓글 작성자</label>
                    <input class="form-control" id="modalCommentUser" name="modalCommentUser" readonly>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                <button type="button" id="modalCommentModify" class="btn btn-success modalModBtn">수정</button>
                <button type="button" id="modalCommentDelete"class="btn btn-danger modalDelBtn">삭제</button>
            </div>
        </div>
    </div>
</div>






<script type="text/javascript">


    // 위에 있을땐 실행 안되더니... 맨 아래로 옮기니까 된다?
    $("#commentWriteBtn").on("click", function() {

        var commentContent = $("#commentWriteContent").val();
        var commentUser= "${member.nickname}";
        console.log("write content : " + commentContent);
        console.log("write user : " + commentUser);

        $.ajax({
            type : 'post',
            url : '/comment/write',
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'text',
            data : JSON.stringify({
                boardId : boardid,
                content : commentContent,
                userNickname : commentUser,
                parentNickname : commentUser,
                ipAddr : "192.168.0.123"

            }),
            success : function(result) {
                if (result == 'SUCCESS') {
                    alert("등록완료!");
                    getComments(boardid);
                    $("#commentWriteContent").val("");
                }
            }
        });
    });


    // modal 창에 값 넣기.
    $("#comments").on("click", ".box-comment", function() {
        var reply = $(this);
        var commentUser = reply.find(".username").text().trim();
        var commentContent = reply.find(".commentContent").text();
        var commentId = reply.find(".username").attr("data-commentNo");


        // 댓글 옆에 버튼들 누르기 전에.
        // 버튼위에 커서 올리면 나타나는 글자??  remove는 commentUser 에 포함이 안되는데.
        // 계속 Re 뜨는것만 포함길래... 지움...
        // 이유 모르겠다...
        parentUser = commentUser;

        // console.log("this : " + reply);
        // console.log(commentUser);
        // console.log(commentContent);
        // console.log(commentId);


        $("#modalCommentId").val(commentId);
        $("#modalCommentUser").val(commentUser);
        $("#modalCommentContent").val(commentContent);


    });

    // 댓글 아이디 옆에 나타나는 삭제 아이콘 누르면 실행되는 메소드.
    $("#comments").on("click", "#deleteComment", function() {

        var boardid4 = getParameterByName('id');
        var commentId = $("#modalCommentId").val();

        console.log("commentId :   " + commentId );

        if(confirm("정말로 삭제할꺼야!?") == true) {
            $.ajax({
                type: 'post',
                url: '/comment/delete/'+commentId,
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "DELETE"
                },
                dataType: 'text',
                success: function(result) {
                    console.log("result: " + result);
                    if(result=='SUCCESS') {
                        alert("삭제했삼!");
                        $("#modifyModal").modal("hide");
                        getComments(boardid4);
                    }
                }
            });
        } else {
            return;
        }
    });

    $("#modalCommentModify").on("click",function() {
        var commentContent = $("#modalCommentContent").val();
        var commentId = $("#modalCommentId").val();
        var boardid2 = getParameterByName('id');

        console.log("commentmodify click : " + commentContent);

        $.ajax({
            type:'put',
            url:'/comment/update/'+commentId,
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "PUT" },
            data:JSON.stringify({
                content: commentContent,
                ipAddr: "192.168.0.132"
            }),
            dataType: 'text',
            success:function(result) {
                console.log("result: " + result);
                if(result=='SUCCESS') {
                    alert("수정 했어용~");
                    $("#modifyModal").modal("hide");
                    getComments(boardid2);
                }
            }
        });

    });


    $("#modalCommentDelete").on("click", function() {
        var boardid3 = getParameterByName('id');
        var commentId = $("#modalCommentId").val();

        console.log("commentId :   " + commentId );

        if(confirm("정말로 지우실겁니까?? ") == true) {
            $.ajax({
                type: 'post',
                url: '/comment/delete/'+commentId,
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "DELETE"
                },
                dataType: 'text',
                success: function(result) {
                    console.log("result: " + result);
                    if(result=='SUCCESS') {
                        alert("삭제했삼!");
                        $("#modifyModal").modal("hide");
                        getComments(boardid3);
                    }
                }
            });
        } else {
            return;
        }

    });


    var commentId;

    // 댓글 목록에서 대댓글 달기 버튼 누르면 보이는 것!
    function reCommentClick(idid) {
        commentId= idid;
        if(isOpend == false) {
            console.log("commentid : " + idid + "   check false -> true");
            var str = "<div id='reCommentDiv' class=\"box-footer\">\n" +
                "       <img style=\"float: left;\"class=\"img-circle img-sm\" width=\"30\" height=\"30\" src=\"https://slowcampus.blob.core.windows.net/quickstartcontainer/2018_11_01/a623e6f3-20d0-43ca-98a0-4b8fa9b94e8a_dahyun.jpg\">\n" +
                "       <!-- .img-push is used to add margin to elements next to floating images -->\n" +
                "       <div class=\"input-group margin\">\n" +
                "           <input type=\"text\" name=\"commentWriteReContent\" id=\"commentWriteReContent\" class=\"form-control\" placeholder=\"Press enter to post comment\">\n" +
                "           <span class=\"input-group-btn\">\n" +
                "               <button type='button' onclick='reCommentWrite();'  id=\"recommentWriteBtn\"class=\"btn btn-info btn-flat\">Input!</button>\n" +
                "           </span>\n" +
                "       </div>\n" +
                " </div> <!-- /.box-footer -->"

            $("#comments").find("[data-recommentNo='" + idid + "']").html(str);
        } else {
            console.log("대댓글 입력창 지워보리기~");
            $("#comments").find("[data-recommentNo='" + idid + "']").html("");
        }

        // isOpend 가 전역변수라서... 문제가 발생함.
        // 1번 댓글의 대댓글창 열고 2번 댓글의 대댓글창 열려고 하면
        // 안열림. 한번 더 눌러야 열림.
        isOpend = !isOpend;

    }





    // 대댓글 입력하기.
    function reCommentWrite() {

        var recommentContent = $("#commentWriteReContent").val();
        var recommentUser= loginMember;  // 맨위에.
        var parentCommentNickname = parentUser;
        console.log("recomment write content : " + recommentContent);
        console.log("recomment write user : " + recommentUser);
        console.log("recomment write parent id : " + commentId);
        console.log("recomment write parent nick : " + parentCommentNickname);


        // $.ajax({
        //     type : 'post',
        //     url : '/comment/write/recomment',
        //     headers : {
        //         "Content-Type" : "application/json",
        //         "X-HTTP-Method-Override" : "POST"
        //     },
        //     dataType : 'text',
        //     data : JSON.stringify({
        //         boardId : boardid,
        //         content : recommentContent,
        //         userNickname : recommentUser,
        //         parentNickname : recommentUser,
        //         depth : 1,
        //         ipAddr : "192.168.0.123"
        //
        //     }),
        //     success : function(result) {
        //         if (result == 'SUCCESS') {
        //             alert("등록완료!");
        //             getComments(boardid);
        //             $("#commentWriteContent").val("");
        //         }
        //     }
        // });



    }



</script>










<%@ include file="include/footer.jsp" %>
