<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../include/header.jsp" %>
<section class="content">
    <div class="box box-primary">

        <!-- /.box-header -->
        <div class="box-body">
            <table class="table table-responsive table-hover table-striped">
                <colgroup>
                    <col width="10%">
                    <col width="50%">
                    <col width="15%">
                    <col width="10%">
                    <col width="15%">
                </colgroup>
                <thead>
                <tr role="row">
                    <th class="text-center">번호</th>
                    <th class="text-center">제목</th>
                    <th class="text-center">닉네임</th>
                    <th class="text-center">조회수</th>
                    <th class="text-center">날짜</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${boardList}" var="article">
                <tr>
                    <td class="text-center"><c:out value="${article.id}"/></td> &nbsp;
                    <td><c:choose>
                        <c:when test="${article.isDeleted eq 0}">
                            <a href="/boards/${article.category}/articles/detail?id=${article.id}">
                                <c:choose>
                                    <c:when test="${article.depth gt 0}">
                                        <c:forEach begin="2" end="${article.depth}" step="1">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
                                        <c:out value="ㄴRE : ${article.title}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${article.title}"/>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </c:when>
                        <c:otherwise>
                            삭제된 게시물 입니다
                        </c:otherwise>
                    </c:choose></td>
                    <td class="text-center"><c:out value="${article.nickname}"/></td>
                    <td class="text-center"><c:out value="${article.readCount}"/></td>
                    <td class="text-center">
                            <fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd"/>
                        </c:forEach>
                </tbody>
            </table>
            <div class="pull-right">
                <a href="/boards/${param.category}/articles/write">
                    <button type="button" class="btn btn-primary margin">글 쓰기</button>
                </a>
            </div>

        </div>
        ${pageList}
        <!-- /.box-body -->
    </div>
</section>
<%--<div class="container">
    <table class="articleList" name="articleListForm">
        <thead>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>등록일</th>
        </thead>
        <tbody>
        <c:forEach var="article" items="${boardList}">
            <tr>
                <td><c:out value="${article.id}"/></td> &nbsp;
                <td><c:choose>
                        <c:when test="${article.isDeleted eq 0}">
                            <a href="/boards/${article.category}/articles/detail?id=${article.id}">
                                <c:choose>
                                    <c:when test="${article.depth gt 0}">
                                        <c:forEach begin="1" end="${article.depth}" step="1">&nbsp;</c:forEach>
                                        <c:out value="ㄴRE : ${article.title}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${article.title}"/>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </c:when>
                        <c:otherwise>
                            삭제된 게시물 입니다
                        </c:otherwise>
                    </c:choose></td>
                <td><c:out value="${article.nickname}"/></td>
                <td><c:out value="${article.readCount}"/></td>
                <td><c:out value="${article.regDate}"/></td></tr>
        </c:forEach>
        </tbody>
    </table>
    ${pageList}
</div>--%>
<%@ include file="../include/footer.jsp" %>