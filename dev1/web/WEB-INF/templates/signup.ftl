<#include "base.ftl">

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
                     <p style="color:white"> WELCOME! </p>
                     <p><span class="fontawesome-lock"></span><input name="login" type="text" value="Login" onBlur="if(this.value === '') this.value = 'Login'" onFocus="if(this.value === 'Login') this.value = ''" required></p>
                     <p><span class="fontawesome-lock"></span><input name="password" type="password" value="Password" onBlur="if(this.value === '') this.value = 'Password'" onFocus="if(this.value === 'Password') this.value = ''" required></p>
                     <p><span class="fontawesome-user"></span>
                         <input name="name" type="text" value="Name" onBlur="if(this.value === '') this.value='Name'" onFocus="if(this.value === 'Name') this.value = ''" required></p>
                     <p><input type="submit" value="Sign Up"/></p>
                 </fieldset>
             </form>
         </div>
<div class="probootstrap-subheading mb-5">
</#macro>

<@display_page/>