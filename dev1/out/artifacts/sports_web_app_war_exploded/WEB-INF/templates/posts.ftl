<#include "base.ftl">
<#macro title>
    News
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style4.css">
</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">
                <div class="col-sm">
                    <#if user??>
                    <p>Make new post</p>
                    <p>
                    <form method="POST" id="create_post" action="/posts">
                        <textarea id="title" class="text" cols="60" rows="2" name="text" required></textarea>
                    <p>
                        <small>Title</small>
                    </p>
                    <textarea id="text" class="text" cols="40" rows="1" name="title" required></textarea>
                    <p></p>
                        <button type="submit" class="button8">Create post</button>
                    </form>
                    </p>
                    </#if>
                <#if posts?has_content>
                    <#list posts as post>
                    <div class="probootstrap-text">
                        <div><p><a href="/posts/${post.id}" class="nav-link">${post.title}</a></p>
                            <p> ${post.text}</p></div>
                        </div>
                    </#list>
                <#else>
                    <b>There is no posts yet.</b>
                </#if>
                </div>
            </div>
        </div>
        </div>
    </section>
</#macro>

<@display_page/>