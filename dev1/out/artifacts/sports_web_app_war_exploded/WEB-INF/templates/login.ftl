<#include "base.ftl">
<#macro title>
    Login
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
</#macro>

<#macro main_content>
<section class="probootstrap-cover">
    <div class="container">
        <div class="row probootstrap-vh-100 align-items-center text-center">
            <div id="login">
                <form method="post">
                    <fieldset class="clearfix">
                        <p><span class="fontawesome-user"></span><input type="text" name="login" value="Login"
                                                                        onBlur="if(this.value == '') this.value = 'Login'"
                                                                        onFocus="if(this.value == 'Login') this.value = ''"
                                                                        required></p>
                        <!-- JS because of IE support; better: placeholder="Username" -->
                        <p><span class="fontawesome-lock"></span><input type="password" name="password" value="Password"
                                                                        onBlur="if(this.value == '') this.value = 'Password'"
                                                                        onFocus="if(this.value == 'Password') this.value = ''"
                                                                        required></p>
                        <!-- JS because of IE support; better: placeholder="Password" -->
                        <p><input type="submit" value="Log In"></p>
                        <p>  <input type="checkbox" name="remember_me" value="a2">Remember me</p>
                    </fieldset>
                </form>
                <p>No account? &nbsp;&nbsp;<a href="/registration">Sign up</a><span
                        class="fontawesome-arrow-right"></span></p>
            </div>
            <div class="probootstrap-subheading mb-5">

            </div>
        </div>
    </div>
    </div>
    </div>
</section>
</#macro>

<@display_page/>