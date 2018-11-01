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





<h3> Comments </h3>







<%@ include file="include/footer.jsp" %>
