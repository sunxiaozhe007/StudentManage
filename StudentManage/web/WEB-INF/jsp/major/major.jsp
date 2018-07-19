<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by IntelliJ IDEA.
User: Administrator
Date: 2018/6/10
Time: 16:03
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">

    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/menu.css"/>
    <link rel="stylesheet" href="../css/major.css"/>
    <script src="../jquery/jquery-3.3.1.min.js">
    </script>

    <script type="text/javascript">
        function deleteitems(id){
            var flag=alert("确定要删除吗？");
            if(!flag){
                window.location.href="deleteItems.action?id="+id;
            }
        }

        function firstPage(){
            var pageNum=document.getElementById("pageNum").value;
            if(1==pageNum){
                alert("已经是首页了");
            }else{
                window.location.href="<%=request.getContextPath()%>/major/list?pageNum="+1;
            }
        }
        function prePage(){
            var pageNum=document.getElementById("pageNum").value;
            pageNum--;
            if (pageNum < 1){
                alert("已经是第一页了");
            } else {
                window.location.href="<%=request.getContextPath()%>/major/list?pageNum="+pageNum;
            }
        }
        function nextPage(){
            var pageNum=document.getElementById("pageNum").value;
            var totalPage=document.getElementById("totalPage").value;
            pageNum++;
            if (pageNum > totalPage) {
                alert("已经是最后一页！")
            }else {
                window.location.href="<%=request.getContextPath()%>/major/list?pageNum="+pageNum;
            }

        }
        function lastPage(){
            var pageNum=document.getElementById("pageNum").value;
            var totalPage=document.getElementById("totalPage").value;
            if(pageNum==totalPage){
                alert("已经是尾页了");
            }else{
                window.location.href="<%=request.getContextPath()%>/major/list?pageNum="+totalPage;
            }
        }

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
                    location="/major/delete?ids="+ids;
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
        <div class="r_t">
            <form action="/major/list" method="post">
                <span>
                    <input type="text" name="name" placeholder="专业查询"/>
                    </span>
                <span>
                    <select style="width:150px;height: 33px" name="college_id">
                        <option value="0">--学院--</option>
                        <c:forEach items="${college}" var="college">
                            <option value="${college.id}">${college.name}</option>
                        </c:forEach>
                    </select>
                </span>
                <input type="submit" value="查询">
                <input id="delete" type="button" onclick="deleteCheck()" value="删除"/>

            </form>
            <a href="/major/add?flag=1" style="float: right">添加</a>
        </div>
        <div class="r_m">
            <div class="r_m_c">
                <p><input type="checkbox" id="ckb"  onclick="selectAll()" /> 全选/全不选</p>
            </div>
            <div class="r_m_l">
                <p>专业名称</p>
            </div>
            <div class="r_m_m">
                <p>专业介绍</p>
            </div>
            <div class="r_m_ck">
                <p>所属学院</p>
            </div>
            <div class="r_m_tiao">
                <p>班级信息</p>
            </div>
            <div class="r_m_r">
                <p>操作</p>
            </div>
        </div>
        <c:forEach items="${major.getList()}" var="major">
            <div class="shuju">
                <ul class="shuju_ul">
                    <li><input type="checkbox" name="chckBox" value="${major.id}"/></li>
                    <li>${major.name}</li>
                    <li>${major.introduce}</li>
                    <li>${major.college.name}</li>
                    <li><a href="/grade/selectByMajor?id=${major.id}">查看班级</a></li>
                    <li><a class="shanchu" href="/major/delete?ids=${major.id}">删除</a>&nbsp;&nbsp;&nbsp;<a class="shanchu" href="/major/update?flag=1&id=${major.id}">修改</a></li>
                    </li>
                </ul>
            </div>
        </c:forEach>
        <div class="b">
            <input type="hidden" id="pageNum" value="${major.getPages()}">
            <input type="hidden" id="totalPage" value="${major.getPages()}">
            <span onclick="firstPage()">首页</span> <span onclick="prePage()">上一页</span>
            <span onclick="nextPage()">下一页</span> <span onclick="lastPage()">尾页</span>
        </div>
    </div>
</div>

</body>
<script src="../js/menu.js"></script>
</html>
