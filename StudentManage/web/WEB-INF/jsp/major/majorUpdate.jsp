<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/10
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/menu.css"/>
    <link rel="stylesheet" href="../css/majoradd.css"/>
    <script src="../jquery/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="bg"></div>
<div class="top">
    <img src="../image/xayd.jpg" alt=""/>
    <p>西&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;安&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学</p>
</div>
<div class="bottom">
    <div class="menulist">
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/main">学校简介</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/college/list">学院管理</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/major/list">专业管理</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/student/list">学生管理</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/file/list">文件上传/下载</a></div>
    </div>
    <form action="/major/update" method="post">



        <div class="right">
            <input type="hidden" name="flag" value="2">
            <div class="inshuju">
                <input type="hidden" name="id" value="${major.id}">
                <p>专业名称：<input name="name" type="text" value="${major.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    所属学院：
                    <select style="width:225px;height:35px;" name="college_id" >
                        <option value="0">--学院--</option>
                        <c:forEach items="${college}" var="college">
                            <c:choose>
                                <c:when test="${major.college.id == college.id}">
                                    <option value="${college.id }" selected="selected">${college.name }</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${college.id}">${college.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                <p><span style="display:inline-block;vertical-align: top;margin-top:50px;">专业介绍：</span><textarea name="introduce">${major.introduce}</textarea></p>
                <div class="anniu">
                    <input  type="submit"/>
                    <a href="javascript:history.go(-1)">返回</a>
                </div>
            </div>
        </div>

    </form>


</div>
</body>
<script src="../js/menu.js">
</script>
</html>


