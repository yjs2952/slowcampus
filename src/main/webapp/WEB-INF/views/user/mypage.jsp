<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="../include/header.jsp" %>
<p>${memberInfo.id}님의 회원 정보</p>
<table>
    <thead>
        <tr>
            <th>닉네임</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${memberInfo.nickname}</td>
        </tr>
    </tbody>
</table>

<br>
<br>
<c:forEach var="authority" items="${memberInfo.authorityList}">
    <c:if test="${authority.authorityId eq 1}">
        <table>
            <caption>관리자 모드 권한 설정</caption>
            <form:form method="post" name="searchMemberId" action="/mypage">
                <thead>
                    <tr>
                        <th>검색하고자 하는 아이디</th>
                        <th>권한</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" name="userId"/></td>
                        <td><select name="authorityName">
                            <option value="normal">일반 권한</option>
                            <option value="admin">관리자 권한</option>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" name="submit" value="확인"/></td>
                    </tr>
                </tbody>
            </form:form>
        </table>
    </c:if>
</c:forEach>
<%@ include file="../include/footer.jsp" %>