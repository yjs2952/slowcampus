<%--
  Created by IntelliJ IDEA.
  User: choijaeyong
  Date: 31/10/2018
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
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
        background: #f7f7f7;

    }
    .box-footer {
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 3px;
        border-bottom-left-radius: 3px;
        border-top: 1px solid #f4f4f4;
        padding: 10px;
        background-color: #fff;
    }

    .box-comments .username {
        color: #444;
        display: block;
        font-weight: 600;
    }
    .box-comments .comment-text {
        margin-left : 40px;
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
</style>


<script>

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


<h3 class="box-title">일단은 FreeBoard</h3>

<div class ="box-body">
    <div class="form-group">
        <label>Title</label>
        <br>

        <textarea class="form-control" rows="1" disabled>${board.title}</textarea>
        <br>

        <label>Content</label>
        <br>

        <textarea class="form-control" rows="5" disabled>${board.content}</textarea>
        <br>
    </div><!-- /.form-group -->

    <div class="form-group">
        <label>Images</label>
        <br>

        <c:forEach items="${images}" var="image">
            <img width="100" height="100"
                 src="https://slowcampus.blob.core.windows.net/quickstartcontainer${image.path}"
                 style="cursor: pointer;" onclick="doImgPop('https://slowcampus.blob.core.windows.net/quickstartcontainer${image.path}')" />
        </c:forEach>


    </div>




    <div class="form-group pull-right">
        <button type="submit" class="btn btn-primary">수정</button>
        <button type="submit" class="btn btn-primary">삭제</button>
    </div>

</div> <!-- /.box-body-->



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
                <button type="button" class="btn btn-default btn-xs"><i class="fa fa-share"></i> Share</button>
                <button type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up"></i> Like</button>
                <span class="pull-right text-muted">45 likes - 2 comments</span>
            </div>

            <h3> Comments </h3>


            <!-- /.box-body -->
            <div class="box-footer box-comments">
                <div class="box-comment">
                    <!-- User image -->
                    <img class="img-circle img-sm" width="30" height="30" src="https://slowcampus.blob.core.windows.net/quickstartcontainer/2018_11_01/a623e6f3-20d0-43ca-98a0-4b8fa9b94e8a_dahyun.jpg" alt="User Image">
                    <div class="comment-text">
                          <span class="username">
                            Maria Gonzales
                            <span class="text-muted pull-right">8:03 PM Today</span>
                          </span><!-- /.username -->
                        It is a long established fact that a reader will be distracted
                        by the readable content of a page when looking at its layout.
                    </div>
                    <!-- /.comment-text -->
                </div>
                <!-- /.box-comment -->

                <div class="box-comment">
                    <%--<!-- User image -->--%>
                    <img class="img-circle img-sm" width="30" height="30"  src="https://slowcampus.blob.core.windows.net/quickstartcontainer/2018_11_01/a623e6f3-20d0-43ca-98a0-4b8fa9b94e8a_dahyun.jpg" alt="User Image">
                    <div class="comment-text">
                          <span class="username">
                            Nora Havisham
                            <span class="text-muted pull-right">8:03 PM Today</span>
                          </span><!-- /.username -->
                        The point of using Lorem Ipsum is that it has a more-or-less
                        normal distribution of letters, as opposed to using
                        'Content here, content here', making it look like readable English.
                    </div>
                    <!-- /.comment-text -->
                </div>
                <!-- /.box-comment -->
            </div>
            <!-- /.box-footer -->
            <div class="box-footer">
                <form action="#" method="post">
                    <img class="img-responsive img-circle img-sm" width="30" height="30" src="https://slowcampus.blob.core.windows.net/quickstartcontainer/2018_11_01/a623e6f3-20d0-43ca-98a0-4b8fa9b94e8a_dahyun.jpg" alt="Alt Text">
                    <!-- .img-push is used to add margin to elements next to floating images -->
                    <div class="img-push">
                        <input type="text" class="form-control input-sm" placeholder="Press enter to post comment">
                    </div>
                </form>
            </div> <!-- /.box-footer -->

        </div>


    </div> <!-- /.col-md-6 -->

</div>  <!-- /.row -->













<%@ include file="include/footer.jsp" %>
