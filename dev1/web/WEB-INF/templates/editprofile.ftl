<#include "base.ftl">

<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">
                <div class="col-sm">
                    <div class="probootstrap-text">
                        <form method="post" action="/profile/${user.id}/edit">
                        <p style="color:white"><b>Edit name:</b><br>
                            <input type="text" size="30" name="editname" value=${user.name}>
                        </p>
                        <p style="color:white"><b>Edit login:</b><br>
                            <input type="text" size="30" name="editlogin" value=${user.login}>
                        </p>
                        <p style="color:white"><b>Edit password:</b><br>
                            <input type="password" size="30" name="editpassword">
                        </p>
                        <p> <button> Load new picture</button> </p>
                        <p>
                        <button type="submit">SAVE ALL</a> </button></p>
                    </form>
                        <div class="probootstrap-subheading mb-5">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</#macro>

<@display_page/>