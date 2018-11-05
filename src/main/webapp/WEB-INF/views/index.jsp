<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="include/header.jsp" %>
<!-- Main Content -->
<section class="content">
    <div class="row">
        <!-- left column -->

        <!-- general form element -->
        <c:forEach items="${list}" var="boards" varStatus="var">
            <div class="col-md-6 col-sm-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">${categoryList.get(var.index).name} 게시판</h3>
                        <a class="btn btn-default btn-xs pull-right" href="/articles/list?category=${var.index + 1}">더 보기</a>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body no-padding">
                        <table class="table table-responsive table-hover">
                            <colgroup>
                                <col width="10%">
                                <col width="50%">
                                <col width="20%">
                                <col width="20%">
                            </colgroup>
                            <tbody>
                            <tr>
                                <th>#</th>
                                <th>제목</th>
                                <th>닉네임</th>
                                <th>시간</th>
                            </tr>
                            <c:forEach items="${boards}" var="article">
                                <tr>
                                    <td>${article.id}</td>
                                    <td><a href="/boards/${article.category}/articles/detail?id=${article.id}">${article.title}</a></td>
                                    <td>
                                        ${article.nickname}
                                    </td>
                                    <td>${article.regDate}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </c:forEach>
    </div>
</section>

<%@ include file="include/footer.jsp" %>

