<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/18
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/collegeadd.css"/>
    <link rel="stylesheet" href="../css/menu.css"/>
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
    <div class="right">
        <div class="inshuju">
            <p>文件名：&nbsp;&nbsp;&nbsp;<input class="xymc" type="text"/></p>
            <p><span style="display:inline-block;vertical-align: top;margin-top:50px;">文件介绍：</span><textarea name="introduce"></textarea></p>
        </div>
        <div class="anniu">
            <input type="submit"/>
            <a href="#">返回</a>
        </div>
    </div>
</div>
</body>
<script src="../js/menu.js"></script>
</html>