<%@ page language="java" import="java.util.*,com.assist.util.SignatureUrl" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String signature = request.getParameter("signature");
String openid = request.getParameter("openid");
String timestamp=request.getParameter("timestamp");
if(SignatureUrl.getSignature(openid,timestamp).equals(signature)){
	if(new Date().getTime()>Long.parseLong(timestamp)+10000){
		session.setAttribute("openid", openid);
	}else{
		System.out.print("链接失效");
	}
}else {
	System.out.print("链接失效");
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>一键绑定</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript">
     function check(){
      	//判断用户名是否为空
      	var v_obj_uid=document.getElementById("uid");
        var v_obj_pwd=document.getElementById("pwd");
        var v_obj_code=document.getElementById("piccode");
        var v_uid=v_obj_uid.value;
        var v_pwd=v_obj_pwd.value;
        var v_code=v_obj_code.value;
        if (v_uid==""){
           alert("用户名不能为空");
           v_obj_uid.focus();
           return false;
        }
       
         if (v_pwd==""){
           alert("密码不能为空");
           v_obj_pwd.focus();
           return false;
        }
         
         if (v_code==""){
             alert("验证码不能为空");
             v_obj_code.focus();
             return false;
          }
        return true;
     }
     function closeWin(){
      if (confirm("你确实想关闭吗？")){
	      window.opener=null;
	      window.close();
      }        
     }
     function reloadCode(){
    	 document.getElementById("checkcode").src="image.do?t="+new Date().getTime();
     }
    
    </script>

  </head>
  
  <body>
       <form action="login.do" method="post" onsubmit="return check()">
            用户名：<input type="text" name="uid" id="uid"/><br/>
            密码：<input type="text" name="pwd" id="pwd"/><br/>    
           验证码：<input type="text" name="piccode" id="piccode"/>
           <img id="checkcode" alt="验证码" src="image.do">
           <a href="javascript:reloadCode();">看不清楚</a><br/>                 
          	<input type="submit" value="一键绑定"/>
          	<input type="button" value="关闭" onclick="closeWin()"/>
       </form>
       
  </body>
</html>