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
                                <%--<tr>
                                    <td>2.</td>
                                    <td><a href="#">Clean database</a></td>
                                    <td>
                                        우주인
                                    </td>
                                    <td>2018-10-30</td>
                                </tr>
                                <tr>
                                    <td>3.</td>
                                    <td><a href="#">Cron job running</a></td>
                                    <td>
                                        너어는죽인다
                                    </td>
                                    <td>2018-09-31</td>
                                </tr>
                                <tr>
                                    <td>4.</td>
                                    <td><a href="#">Fix and squish bugs</a></td>
                                    <td>
                                        애노테이션
                                    </td>
                                    <td>2018-08-12</td>
                                </tr>--%>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </c:forEach>
        <%--<div class="col-md-6 col-sm-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">자유 게시판</h3>
                    <a class="btn btn-default btn-xs pull-right" href="#">더 보기</a>
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                    <table class="table table-striped table-responsive">
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
                        <tr>
                            <td>1.</td>
                            <td><a href="#">Update software sssssssssssssssssssssasdasdasdasdasdasdasdasdasdasdasdsss</a></td>
                            <td>
                                우주인
                            </td>
                            <td>1분전</td>
                        </tr>
                        <tr>
                            <td>2.</td>
                            <td><a href="#">Clean database</a></td>
                            <td>
                                우주인
                            </td>
                            <td>2018-10-30</td>
                        </tr>
                        <tr>
                            <td>3.</td>
                            <td><a href="#">Cron job running</a></td>
                            <td>
                                너어는죽인다
                            </td>
                            <td>2018-09-31</td>
                        </tr>
                        <tr>
                            <td>4.</td>
                            <td><a href="#">Fix and squish bugs</a></td>
                            <td>
                                애노테이션
                            </td>
                            <td>2018-08-12</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <div class="col-md-6 col-sm-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">자유 게시판</h3>
                    <a class="btn btn-default btn-xs pull-right" href="#">더 보기</a>
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                    <table class="table table-striped table-responsive">
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
                        <tr>
                            <td>1.</td>
                            <td><a href="#">Update software sssssssssssssssssssssasdasdasdasdasdasdasdasdasdasdasdsss</a></td>
                            <td>
                                우주인
                            </td>
                            <td>1분전</td>
                        </tr>
                        <tr>
                            <td>2.</td>
                            <td><a href="#">Clean database</a></td>
                            <td>
                                우주인
                            </td>
                            <td>2018-10-30</td>
                        </tr>
                        <tr>
                            <td>3.</td>
                            <td><a href="#">Cron job running</a></td>
                            <td>
                                너어는죽인다
                            </td>
                            <td>2018-09-31</td>
                        </tr>
                        <tr>
                            <td>4.</td>
                            <td><a href="#">Fix and squish bugs</a></td>
                            <td>
                                애노테이션
                            </td>
                            <td>2018-08-12</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
        </div>--%>

    </div>
</section>

<%@ include file="include/footer.jsp" %>

