<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/18
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/menu.css"/>
    <link rel="stylesheet" href="../css/file.css"/>
    <script src="../jquery/jquery-3.3.1.min.js"></script>

    <script>
        //全选
        function selectAll()
        {
            var chckBoxSign = document.getElementById("ckb");       //ckb 全选/反选的选择框id
            var chckBox = document.getElementsByName("chckBox");
            var num = chckBox.length;
            if(chckBoxSign.checked)
            {
                for(var index =0 ; index<num;index++)
                    chckBox[index].checked = true;
            }
            else{
                for(var index =0 ; index<num;index++)
                    chckBox[index].checked = false;
            }
        }

        //批量删除
        function deleteCheck(){
            var chckBox = document.getElementsByName("chckBox");
            var num = chckBox.length;
            var ids = "";
            for(var index =0 ; index<num;index++)
            {
                if(chckBox[index].checked){
                    ids += chckBox[index].value + ",";
                }
            }
            if(ids!="")
            {
                if(window.confirm("确定删除所选记录？"))
                {
                    //ids为数组里面存有选中的记录的id
                    location="/file/delete?ids="+ids;
                }
            }
            else
            {
                alert("请选择要删除的记录");
            }
        }
    </script>
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
        <form action="/file/select" method="post">
        <div class="r_t">
            <input type="text" name="name"/>
            <input type="submit" value="查询">
            <input id="delete" type="button" value="删除" onclick="deleteCheck()"/>
            <a href="/file/insert?flag=1">添加</a>
        </div>
        </form>
        <div class="r_m">
            <div class="r_m_c">
                <p><input type="checkbox" id="ckb"  onclick="selectAll()" /> 全选/全不选</p>
            </div>
            <div class="r_m_l">
                <p>文件名</p>
            </div>
            <div class="r_m_m">
                <p>文件介绍</p>
            </div>
            <div class="r_m_r">
                <p>操作</p>
            </div>
        </div>
        <div class="shuju">
            <c:forEach items="${list}" var="list">
             <ul class="shuju_ul">
                <li><input type="checkbox" name="chckBox" value="${list.id}"/></li>
                <li>${list.name}</li>
                <li>${list.introduce}</li>
                <li><a href="/file/downLoad?id=${list.id}">下载</a>&nbsp;&nbsp;&nbsp;
                    <a href="/file/delete?ids=${list.id}">删除</a>&nbsp;&nbsp;&nbsp;
                    <a href="#">修改</a></li>
            </ul>
            </c:forEach>
        </div>
    </div>
</div>
</body>
<script src="../js/menu.js"></script>
</html>
