<#include "base.ftl">

<#macro import>
    <link rel="stylesheet" href="/static/css/style2.css">
</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">

                <div class="col-sm">
                    <a href="#openModal">Maria Sharapova</a>

                    <div id="openModal" class="modalDialog">
                        <div>
                            <a href="#close" title="Закрыть" class="close">X</a>
                        <#list sportsmen as sportsman>
                            <h2>${sportsman.name}</h2>
                            <p>${sportsman.team}</p>
                            <p>${sportsman.bio}</p>
                        </#list>
                        </div>
                    </div>

                    <img src="images/maria.jpg" width="400" height="250"> </img>
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