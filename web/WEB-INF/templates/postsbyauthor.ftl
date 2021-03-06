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
                <div class="probootstrap-text">
                    <#if posts?has_content>
                    <#list posts as post>
                    <div class="probootstrap-text">
                        <div><p><a href="/posts/${post.id}" class="nav-link">${post.title}</a></p>
                            <p> ${post.text}</p></div>
                    </div>
                    </#list>
                    <#else>
                    <b>There are no posts yet.</b>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
</#macro>

<@display_page/>