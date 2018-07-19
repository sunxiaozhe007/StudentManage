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
    <link rel="stylesheet" href="../css/student.css"/>
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
                window.location.href="<%=request.getContextPath()%>/student/list?pageNum="+1;
            }
        }
        function prePage(){
            var pageNum=document.getElementById("pageNum").value;
            pageNum--;
            if (pageNum < 1){
                alert("已经是第一页了");
            } else {
                window.location.href="<%=request.getContextPath()%>/student/list?pageNum="+pageNum;
            }
        }
        function nextPage(){
            var pageNum=document.getElementById("pageNum").value;
            var totalPage=document.getElementById("totalPage").value;
            pageNum++;
            if (pageNum > totalPage) {
                alert("已经是最后一页！")
            }else {
                window.location.href="<%=request.getContextPath()%>/student/list?pageNum="+pageNum;
            }

        }
        function lastPage(){
            var pageNum=document.getElementById("pageNum").value;
            var totalPage=document.getElementById("totalPage").value;
            if(pageNum==totalPage){
                alert("已经是尾页了");
            }else{
                window.location.href="<%=request.getContextPath()%>/student/list?pageNum="+totalPage;
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
                    location="/student/delete?ids="+ids;
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
<script src="../jquery/jquery-3.3.1.min.js"></script>
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
        <form action="${pageContext.request.contextPath}/student/selectStudent" method="post">
            <div class="r_t">
                <input type="text" name="name" placeholder="姓名"/>
                <input type="text" name="num" placeholder="学号"/>
                <select class="select" name="college_id" id="1">
                    <option value="0">--学院--</option>
                    <c:forEach items="${college}" var="college">
                        <option value="${college.id}">${college.name}</option>
                    </c:forEach>
                </select>
                <select class="select" name="major_id" id="2">
                    <option value="0">--专业--</option>
                    <c:forEach items="${major}" var="major">
                        <option value="${major.id}">${major.name}</option>
                    </c:forEach>
                </select>
                <select class="select" name="grade_id" id="3">
                    <option value="0">--班级--</option>
                    <c:forEach items="${grade}" var="grade">
                        <option value="${grade.id}">${grade.name}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="查询">
                <input id="delete" type="button" value="删除" onclick="deleteCheck()"/>
                <a href="${pageContext.request.contextPath}/student/add?flag=1">添加</a>
            </div>
        </form>
        <div class="r_m">
            <div class="r_m_c">
                <p><input type="checkbox" id="ckb"  onclick="selectAll()" /> 全选/全不选</p>
            </div>
            <div class="r_m_l">
                <p>学生姓名</p>
            </div>
            <div >
                <p>性别</p>
            </div>
            <div class="r_m_m">
                <p>学号</p>

            </div>
            <div class="r_m_tiao">
                <p>从属班级</p>
            </div>
            <div class="r_m_r">
                <p>详细信息</p>
            </div>
            <div class="r_m_r">
                <p>操作</p>
            </div>
        </div>

        <c:forEach items="${student.getList()}" var="student">
            <div class="shuju">
                <ul class="shuju_ul">
                    <li><input type="checkbox" name="chckBox" value="${student.id}"/></li>
                    <li>${student.name}</li>
                    <li>${student.sex}</li>
                    <li>${student.num}</li>
                    <li>${student.grade.name}</li>
                    <li  id="show"><a href="/student/selectById?id=${student.id}">详细信息</a> </li>
                    <li><a class="shanchu" href="/student/delete?ids=${student.id}">删除</a>&nbsp;&nbsp;&nbsp;<a class="shanchu" href="/student/update?id=${student.id}&flag=1">修改</a></li>
                </ul>
            </div>
        </c:forEach>



        <br>
        <div class="b">
            <input type="hidden" id="pageNum" value="${student.getPageNum()}">
            <input type="hidden" id="totalPage" value="${student.getPages()}">
            <span onclick="firstPage()">首页</span> <span onclick="prePage()">上一页</span>
            <span onclick="nextPage()">下一页</span> <span onclick="lastPage()">尾页</span>
        </div>
    </div>
</div>

</body>
<script src="../js/menu.js"></script>
<script>
    var show=document.getElementById("show");
    var jianli=document.getElementsByClassName("jianli")[0];
    var shut=document.getElementById("shut");
    show.onclick=function(){
        $("div.jianli").show()
        jianli.style.animationName="xuanzhuan";
        jianli.style.animationDuration="1s";
        jianli.style.animationTimingFunction="linear";
    };
    shut.onclick=function(){
        $("div.jianli").hide(1000)
    }
</script>
<script>
    function setImagePreview(avalue) {
        //input
        var docObj = document.getElementById("doc");
        //img
        var imgObjPreview = document.getElementById("preview");
        //div
        var divs = document.getElementById("localImag");
        if (docObj.files && docObj.files[0]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '100px';
            imgObjPreview.style.height = '100px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        } else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("localImag");
            //必须设置初始大小
            localImagId.style.width = "100px";
            localImagId.style.height = "100px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            } catch(e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
        return true;
    }
</script>
</html>