<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>

    <p>게시물을 삭제하시겠습니까?</p>

    <form method="post" name="deleteButton" action="/boards/${board.category}/articles/delete">
        <input type="hidden" name="id" value="${board.id}">
        <button type="reset" id="cancel" name="cancel" value="cancel">취소</button>
        <button type="submit" id="submit" name="submit" value="submit">삭제</button>
    </form>

<%@ include file="../include/footer.jsp" %>