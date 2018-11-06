<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../include/header.jsp" %>
<div class="container">
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
</div>
<%@ include file="../include/footer.jsp" %>