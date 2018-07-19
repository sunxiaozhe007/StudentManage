<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/16
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/menu.css"/>
    <link rel="stylesheet" href="../css/studentjianli.css"/>
</head>
<body>
<script src="../jquery/jquery-3.3.1.min.js"></script>

<div class="bg"></div>
<div class="top">
    <img src="../image/xayd.jpg" alt=""/>
    <p>西&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;安&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学</p>
        <a href="/loginForm" >退出</a>
</div>
<div class="bottom">
    <div class="menulist">
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/main">学校简介</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/college/list">学院管理</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/major/list">专业管理</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/student/list">学生管理</a></div>
        <div class="menu"><a class="a" href="${pageContext.request.contextPath}/file/list">文件上传/下载</a></div>
    </div>
    <div class="jianli">
        <div class="grxx">
            <p class="xx_top">-->个人信息</p>
            <div class="zhaopian">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr>
                        <td height="101" align="center">
                            <div id="localImag">
                                <img id="preview" alt="照片" src="${pageContext.request.contextPath}/photo/${student.photo}" style="display: block;" />
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="xx">
                <ul>
                    <li>姓名：<input type="text" value="${student.name}" readonly/></li>
                    <li>学号：<input type="text" value="${student.num}" readonly/></li>
                    <li>性别：<input type="text" value="${student.sex}" readonly/></li>
                    <li>年龄：<input type="text" value="${student.age}" readonly/></li>
                    <li>民族：<input type="text" value="${student.nation}" readonly/></li>
                    <li>籍贯：<input type="text" value="${student.natives}" readonly/></li>
                    <li>班级：<input type="text" value="${student.grade.name}" readonly/></li>
                    <li>专业：<input type="text" value="${student.major.name}" readonly/></li>
                    <li>学院：<input type="text" value="${student.college.name}" readonly/></li>
                    <li>政治面貌：<input style="width: 70px" type="text" value="${student.pol}" readonly/></li>
                    <li style="width: 95%">出生日期：<input style="width:220px;" type="text" value="${student.birthday}" readonly/></li>
                    <li style="width: 95%;">联系电话：<input style="width:220px;" type="text" value="${student.phone}" readonly/></li>
                </ul>
            </div>
        </div>
        <div class="jieshao">
            <ul>
                <li><span>特长：</span><textarea  name=""cols="30" rows="10" readonly>${student.spec}</textarea></li>
                <li><span>爱好：</span><textarea  name=""cols="30" rows="10" readonly>${student.hobby}</textarea></li>
                <li><span>备注：</span><textarea  name=""cols="30" rows="10" readonly>${student.remark}</textarea></li>
            </ul>
        </div>
        <div class="sub">
            <a href="javascript:history.go(-1)">返回</a>
        </div>
    </div>
</div>
</body>
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
