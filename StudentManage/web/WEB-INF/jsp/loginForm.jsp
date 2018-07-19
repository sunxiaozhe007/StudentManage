<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/5
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>学生管理系统</title>
  <script src="../../js/jquery.min.js"></script>





  <script src="../../js/jquery-2.2.4.min.js"></script>
  <script>
      $(document).ready(function() {

          $(".form").slideDown(500);

          $("#landing").addClass("border-btn");

          $("#registered").click(function() {
              $("#landing").removeClass("border-btn");
              $("#landing-content").hide(500);
              $(this).addClass("border-btn");
              $("#registered-content").show(500);

          })

          $("#landing").click(function() {
              $("#registered").removeClass("border-btn");
              $(this).addClass("border-btn");

              $("#landing-content").show(500);
              $("#registered-content").hide(500);
          })
      });
  </script>

  <style>
    * {
      margin: 0;
      padding: 0;
      font-family: "微软雅黑";
    }

    body {
      background: #F7F7F7;
    }

    .form {
      position: absolute;
      top: 50%;
      left: 50%;
      margin-left: -185px;
      margin-top: -210px;
      height: 500px;
      width: 340px;
      font-size: 18px;
      -webkit-box-shadow: 0px 0px 10px #A6A6A6;
      background: #fff;
    }

    .border-btn {
      border-bottom: 1px solid #ccc;
    }

    #landing,
    #registered {
      float: left;
      text-align: center;
      width: 170px;
      padding: 15px 0;
      color: #545454;
    }

    #landing-content {
      clear: both;
    }

    .inp {
      height: 30px;
      margin: 0 auto;
      margin-bottom: 30px;
      width: 200px;
    }

    .inp>input {
      text-align: center;
      height: 30px;
      width: 200px;
      margin: 0 auto;
      transition: all 0.3s ease-in-out;
    }

    .login {
      border: 1px solid #A6A6A6;
      color: #a6a6a6;
      height: 30px;
      width: 202px;
      text-align: center;
      font-size: 13.333333px;
      margin-left: 70px;
      line-height: 30px;
      margin-top: 30px;
      transition: all 0.3s ease-in-out;
    }

    .login:hover {
      background: #A6A6A6;
      color: #fff;
    }

    #bottom {
      margin-top: 30px;
      font-size: 13.333333px;
      color: #a6a6a6;
    }

    #registeredtxt {
      float: left;
      margin-left: 80px;
    }

    #forgotpassword {
      float: right;
      margin-right: 80px;
    }

    #photo {
      border-radius: 80px;
      border: 1px solid #ccc;
      height: 80px;
      width: 80px;
      margin: 0 auto;
      overflow: hidden;
      clear: both;
      margin-top: 30px;
      margin-bottom: 30px;
    }

    #photo>img:hover {
      -webkit-transform: rotateZ(360deg);
      -moz-transform: rotateZ(360deg);
      -o-transform: rotateZ(360deg);
      -ms-transform: rotateZ(360deg);
      transform: rotateZ(360deg);
    }

    #photo>img {
      height: 80px;
      width: 80px;
      -webkit-background-size: 220px 220px;
      border-radius: 60px;
      -webkit-transition: -webkit-transform 1s linear;
      -moz-transition: -moz-transform 1s linear;
      -o-transition: -o-transform 1s linear;
      -ms-transition: -ms-transform 1s linear;
    }


    #registered-content {
      margin-top: 40px;
      display: none;
    }

    .fix {
      clear: both;
    }
    .form{
      display: none;
    }
  </style>



















  <style>
    html, body{
      width: 100%;
      height: 100%;
      margin: 0;
      padding: 0;
      overflow: hidden;
    }
    .tanchu{
        border:1px solid black ;
    }
    .container{
      width: 100%;
      height: 100%;
      margin: 0;
      padding: 0;
      background-color: #000000;
    }</style>
</head>
<body>
<div id="jsi-cherry-container" class="container"></div>

<script>
    var RENDERER = {
        INIT_CHERRY_BLOSSOM_COUNT : 30,
        MAX_ADDING_INTERVAL : 10,

        init : function(){
            this.setParameters();
            this.reconstructMethods();
            this.createCherries();
            this.render();
        },
        setParameters : function(){
            this.$container = $('#jsi-cherry-container');
            this.width = this.$container.width();
            this.height = this.$container.height();
            this.context = $('<canvas />').attr({width : this.width, height : this.height}).appendTo(this.$container).get(0).getContext('2d');
            this.cherries = [];
            this.maxAddingInterval = Math.round(this.MAX_ADDING_INTERVAL * 1000 / this.width);
            this.addingInterval = this.maxAddingInterval;
        },
        reconstructMethods : function(){
            this.render = this.render.bind(this);
        },
        createCherries : function(){
            for(var i = 0, length = Math.round(this.INIT_CHERRY_BLOSSOM_COUNT * this.width / 1000); i < length; i++){
                this.cherries.push(new CHERRY_BLOSSOM(this, true));
            }
        },
        render : function(){
            requestAnimationFrame(this.render);
            this.context.clearRect(0, 0, this.width, this.height);

            this.cherries.sort(function(cherry1, cherry2){
                return cherry1.z - cherry2.z;
            });
            for(var i = this.cherries.length - 1; i >= 0; i--){
                if(!this.cherries[i].render(this.context)){
                    this.cherries.splice(i, 1);
                }
            }
            if(--this.addingInterval == 0){
                this.addingInterval = this.maxAddingInterval;
                this.cherries.push(new CHERRY_BLOSSOM(this, false));
            }
        }
    };
    var CHERRY_BLOSSOM = function(renderer, isRandom){
        this.renderer = renderer;
        this.init(isRandom);
    };
    CHERRY_BLOSSOM.prototype = {
        FOCUS_POSITION : 300,
        FAR_LIMIT : 600,
        MAX_RIPPLE_COUNT : 100,
        RIPPLE_RADIUS : 100,
        SURFACE_RATE : 0.5,
        SINK_OFFSET : 20,

        init : function(isRandom){
            this.x = this.getRandomValue(-this.renderer.width, this.renderer.width);
            this.y = isRandom ? this.getRandomValue(0, this.renderer.height) : this.renderer.height * 1.5;
            this.z = this.getRandomValue(0, this.FAR_LIMIT);
            this.vx = this.getRandomValue(-2, 2);
            this.vy = -2;
            this.theta = this.getRandomValue(0, Math.PI * 2);
            this.phi = this.getRandomValue(0, Math.PI * 2);
            this.psi = 0;
            this.dpsi = this.getRandomValue(Math.PI / 600, Math.PI / 300);
            this.opacity = 0;
            this.endTheta = false;
            this.endPhi = false;
            this.rippleCount = 0;

            var axis = this.getAxis(),
                theta = this.theta + Math.ceil(-(this.y + this.renderer.height * this.SURFACE_RATE) / this.vy) * Math.PI / 500;
            theta %= Math.PI * 2;

            this.offsetY = 40 * ((theta <= Math.PI / 2 || theta >= Math.PI * 3 / 2) ? -1 : 1);
            this.thresholdY = this.renderer.height / 2 + this.renderer.height * this.SURFACE_RATE * axis.rate;
            this.entityColor = this.renderer.context.createRadialGradient(0, 40, 0, 0, 40, 80);
            this.entityColor.addColorStop(0, 'hsl(330, 70%, ' + 50 * (0.3 + axis.rate) + '%)');
            this.entityColor.addColorStop(0.05, 'hsl(330, 40%,' + 55 * (0.3 + axis.rate) + '%)');
            this.entityColor.addColorStop(1, 'hsl(330, 20%, ' + 70 * (0.3 + axis.rate) + '%)');
            this.shadowColor = this.renderer.context.createRadialGradient(0, 40, 0, 0, 40, 80);
            this.shadowColor.addColorStop(0, 'hsl(330, 40%, ' + 30 * (0.3 + axis.rate) + '%)');
            this.shadowColor.addColorStop(0.05, 'hsl(330, 40%,' + 30 * (0.3 + axis.rate) + '%)');
            this.shadowColor.addColorStop(1, 'hsl(330, 20%, ' + 40 * (0.3 + axis.rate) + '%)');
        },
        getRandomValue : function(min, max){
            return min + (max - min) * Math.random();
        },
        getAxis : function(){
            var rate = this.FOCUS_POSITION / (this.z + this.FOCUS_POSITION),
                x = this.renderer.width / 2 + this.x * rate,
                y = this.renderer.height / 2 - this.y * rate;
            return {rate : rate, x : x, y : y};
        },
        renderCherry : function(context, axis){
            context.beginPath();
            context.moveTo(0, 40);
            context.bezierCurveTo(-60, 20, -10, -60, 0, -20);
            context.bezierCurveTo(10, -60, 60, 20, 0, 40);
            context.fill();

            for(var i = -4; i < 4; i++){
                context.beginPath();
                context.moveTo(0, 40);
                context.quadraticCurveTo(i * 12, 10, i * 4, -24 + Math.abs(i) * 2);
                context.stroke();
            }
        },
        render : function(context){
            var axis = this.getAxis();

            if(axis.y == this.thresholdY && this.rippleCount < this.MAX_RIPPLE_COUNT){
                context.save();
                context.lineWidth = 2;
                context.strokeStyle = 'hsla(0, 0%, 100%, ' + (this.MAX_RIPPLE_COUNT - this.rippleCount) / this.MAX_RIPPLE_COUNT + ')';
                context.translate(axis.x + this.offsetY * axis.rate * (this.theta <= Math.PI ? -1 : 1), axis.y);
                context.scale(1, 0.3);
                context.beginPath();
                context.arc(0, 0, this.rippleCount / this.MAX_RIPPLE_COUNT * this.RIPPLE_RADIUS * axis.rate, 0, Math.PI * 2, false);
                context.stroke();
                context.restore();
                this.rippleCount++;
            }
            if(axis.y < this.thresholdY || (!this.endTheta || !this.endPhi)){
                if(this.y <= 0){
                    this.opacity = Math.min(this.opacity + 0.01, 1);
                }
                context.save();
                context.globalAlpha = this.opacity;
                context.fillStyle = this.shadowColor;
                context.strokeStyle = 'hsl(330, 30%,' + 40 * (0.3 + axis.rate) + '%)';
                context.translate(axis.x, Math.max(axis.y, this.thresholdY + this.thresholdY - axis.y));
                context.rotate(Math.PI - this.theta);
                context.scale(axis.rate * -Math.sin(this.phi), axis.rate);
                context.translate(0, this.offsetY);
                this.renderCherry(context, axis);
                context.restore();
            }
            context.save();
            context.fillStyle = this.entityColor;
            context.strokeStyle = 'hsl(330, 40%,' + 70 * (0.3 + axis.rate) + '%)';
            context.translate(axis.x, axis.y + Math.abs(this.SINK_OFFSET * Math.sin(this.psi) * axis.rate));
            context.rotate(this.theta);
            context.scale(axis.rate * Math.sin(this.phi), axis.rate);
            context.translate(0, this.offsetY);
            this.renderCherry(context, axis);
            context.restore();

            if(this.y <= -this.renderer.height / 4){
                if(!this.endTheta){
                    for(var theta = Math.PI / 2, end = Math.PI * 3 / 2; theta <= end; theta += Math.PI){
                        if(this.theta < theta && this.theta + Math.PI / 200 > theta){
                            this.theta = theta;
                            this.endTheta = true;
                            break;
                        }
                    }
                }
                if(!this.endPhi){
                    for(var phi = Math.PI / 8, end = Math.PI * 7 / 8; phi <= end; phi += Math.PI * 3 / 4){
                        if(this.phi < phi && this.phi + Math.PI / 200 > phi){
                            this.phi = Math.PI / 8;
                            this.endPhi = true;
                            break;
                        }
                    }
                }
            }
            if(!this.endTheta){
                if(axis.y == this.thresholdY){
                    this.theta += Math.PI / 200 * ((this.theta < Math.PI / 2 || (this.theta >= Math.PI && this.theta < Math.PI * 3 / 2)) ? 1 : -1);
                }else{
                    this.theta += Math.PI / 500;
                }
                this.theta %= Math.PI * 2;
            }
            if(this.endPhi){
                if(this.rippleCount == this.MAX_RIPPLE_COUNT){
                    this.psi += this.dpsi;
                    this.psi %= Math.PI * 2;
                }
            }else{
                this.phi += Math.PI / ((axis.y == this.thresholdY) ? 200 : 500);
                this.phi %= Math.PI;
            }
            if(this.y <= -this.renderer.height * this.SURFACE_RATE){
                this.x += 2;
                this.y = -this.renderer.height * this.SURFACE_RATE;
            }else{
                this.x += this.vx;
                this.y += this.vy;
            }
            return this.z > -this.FOCUS_POSITION && this.z < this.FAR_LIMIT && this.x < this.renderer.width * 1.5;
        }
    };
    $(function(){
        RENDERER.init();
    });
</script>






<div class="form">
  <div id="landing">登陆</div>
  <div id="registered">注册</div>
  <div class="fix"></div>
  <span id="error"></span>
  <form id="myform" action="/login" method="post">
  <div id="landing-content">
    <div id="photo"><img src="../../img/photo.jpg" /></div>
    <div class="inp"><input type="text" name="username" placeholder="用户名" /></div>
    <div class="inp"><input type="password" name="password" placeholder="密码" /></div>
    <div class="login" onclick="document.getElementById('myform').submit()">登陆</div>
    <div id="bottom"><span id="registeredtxt">立即注册</span><span id="forgotpassword">忘记密码</span></div>
  </div>
  </form>
  <div id="registered-content">
    <form id="myform1" action="/regist" method="post">
      <span style="padding-top: 5px"><font color="red">${requestScope.error}</font></span>
      <div class="inp"><input id="username" name="username" type="text" placeholder="请输入用户名" /></div>
    <div class="inp"><input id="password" name="password" type="password" placeholder="请输入密码" /></div>
    <div class="inp"><input type="password" placeholder="请再次输入密码" /></div>
    <div id="phone" class="inp"><input  id="phonenumber" name="phone" type="text" placeholder="手机号码" /></div>
    <div class="inp"><input style="display: inline-block;width:120px;" id="getcode" name="code" type="text" placeholder="验证码" /><input id="send" style="display: inline-block;width: 75px;" onclick="sendCode()" type="button" value="获取验证码"></div>
    <div class="login" onclick="document.getElementById('myform1').submit()">立即注册</div>
    <h1>${error1}</h1>
      <h2>${error}</h2>
    </form>
  </div>
<script>
    var x=("${requestScope.message}");
    console.log(x);
    if(x==""){
    }
    else{
        alert("账号或密码错误！")
    }
    var phoneNumber = document.getElementById("phonenumber").value;
    var phone=document.getElementById("phonenumber");
    var username=document.getElementById("username");
    var password=document.getElementById("password");
    var send=document.getElementById("send");
    phone.onblur=function(){
        if(phoneNumber==""){
            phone.placeholder="不能为空";
            phone.borderWidth=1+"px";
            phone.borderColor="red";
            phone.borderStyle="solid";
            }
        }
    username.onblur=function(){
        if(username.value==""){
            username.placeholder="不能为空";
        }
    }
    password.onblur=function(){
        if(password.value==""){
            password.placeholder="不能为空";
        }
    }

  function sendCode(){
        var phoneNumber = document.getElementById("phonenumber").value;
  $.ajax({
  type: 'post',
  url: 'code',
  data: {phone: phoneNumber},
  success: function(res){
  }
  });

      var x1=("${requestScope.error}");
      var x2=("${requestScope.error1}");
      if(x1==""){
          send.onclick=null;
          var i=60;
          var time;
          time=setInterval(timego,1000);
          function timego(){
              if(i>0){
                  i--;
                  send.value=i;
              }
              else{
                  clearInterval(time);
                  send.value="获取验证码"
                  send.onclick=sendCode;
              }
          }
      }
  }
  var x3=("${requestScope.error1}")
    console.log(x3);
  if(x3!=""){
        alert("验证码错误！")
  }
</script>
</div>
</body>
</html>
