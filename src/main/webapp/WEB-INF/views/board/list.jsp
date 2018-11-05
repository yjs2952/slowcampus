<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                            <a href="/list/article/detail?id=${article.id}">
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
                    </c:choose></td> &nbsp;
                <td><c:out value="${article.nickname}"/></td> &nbsp;
                <td><c:out value="${article.readCount}"/></td> &nbsp;
                <td><c:out value="${article.regDate}"/></td>
                <br>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    ${pageList}
</div>
</body>
</html>