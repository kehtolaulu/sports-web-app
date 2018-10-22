<#include "base.ftl">

<#macro import>
    <link rel="stylesheet" href="/static/css/style2.css">
</#macro>

<#macro main_content>
<section class="probootstrap-cover">
    <div class="container">
        <div class="row probootstrap-vh-100 align-items-center text-center">
            <div class="col-sm">
                <div class="probootstrap-text">
                    <#if posts?has_content>
                    <#list posts as post>
                    <div class="probootstrap-text">
                        <div><p><a href="/posts/${post.id}" class="nav-link">${post.title}</a></p>
                            <p> ${post.text}</p></div>
                    </div>
                    </#list>
                    <#else>
                    <b>There is no comments yet.</b>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
</#macro>

<@display_page/>