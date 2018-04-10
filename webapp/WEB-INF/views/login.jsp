<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html class="login-alone">
<head>
<title>Links-UCenter</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${ctxStatic }/css/screen.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic }/css/login.css">
</head>
  <body>
      <div class="logina-logo" style="height: 55px">
        <a href="">
            <img src="${ctxStatic }/toplogo.png" height="60" alt="">
        </a>
    </div>
    <div class="logina-main main clearfix">
        <div class="tab-con">
            <form id="form-login" method="post" action="${ctx }/loginpost">
            	<input type="hidden" value="${returnUrl }" name="returnUrl" id="returnUrl"/>
            	<input type="hidden" name="ctoken" value="${ctoken}">
                <div id='login-error' class="error-tip">
                	${errorMsg }
                </div>
                <table border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <th>账户</th>
                            <td width="245">
                                <input type="text" name="loginName" placeholder="电子邮箱/手机号" autocomplete="off" value=""></td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <th>密码</th>
                            <td width="245">
                                <input type="password" name="password" placeholder="请输入密码" autocomplete="off">
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr id="tr-vcode">
                            <th>验证码</th>
                            <td width="245">
                                <div class="valid">
                                    <input type="text" id="captcha" name="captcha">
                                    <img onclick="this.src=('${ctx }/captcha/image.html?ctoken=${ctoken}&t='+(new Date()).getTime())" class="vcode" src="${ctx }/captcha/image.html?ctoken=${ctoken}&t=" width="85" height="35" alt="">
                                </div>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr class="find">
                            <th></th>
                            <td>
                                <div>
                                    <label class="checkbox" for="chk11">
                                    <input style="height: auto;" id="chk11" type="checkbox" name="remember_me" >记住我</label>
                                    <a href="#">忘记密码？</a>
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td width="245"><input class="confirm" type="submit" value="登  录"></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <input type="hidden" name="refer" value="site/">
            </form>
        </div>
        <div class="reg">
            <p>还没有账号？<br>赶快免费注册一个吧！</p>
            <a class="reg-btn" href="#">立即免费注册</a>
        </div>
    </div>
    <div id="footer">
        <div class="copyright">Copyright © 2015 kisso. All Rights Reserved.</div>
    </div>
</body>
</html>
