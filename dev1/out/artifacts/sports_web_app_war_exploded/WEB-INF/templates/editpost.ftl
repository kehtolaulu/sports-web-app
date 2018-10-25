<#include "base.ftl">
<#macro title>
    Edit post
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style2.css">
</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">
                <div class="col-sm">
                    <div class="probootstrap-text">
                        <p style="color:red"><b>EDIT YOUR POST:</b><br>
                            <form method="POST" id="edit_post" action="/posts/${post.id}/edit">
                            <textarea id="title" name="newTitle" class="text" cols="60" rows="2"> </textarea>
                            <p><small>Title</small></p>
                            <textarea wrap="off" cols="60" rows="10" id="text" name="newText"></textarea>
                            <button type="submit" class="button8">Save changes</button>
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