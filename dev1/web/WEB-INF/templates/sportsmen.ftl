<#include "base.ftl">

<#macro import>
    <link rel="stylesheet" href="/static/css/style2.css">
</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">

                <div class="col-sm">
                        <div id="sportsmen-list">
                        <#list sportsmen as sportsman>
                            <div id="sportsmen-item">
                            <h2>${sportsman.name}</h2>
                            <p>${sportsman.team.name}</p>
                            <p>${sportsman.bio}</p>
                                <img src="${sportsman.photo}" width="400" height="250">
                            </div>
                        </#list>
                        </div>
                    <div class="probootstrap-text">

                        <div class="probootstrap-subheading mb-5">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</#macro>

<@display_page/>