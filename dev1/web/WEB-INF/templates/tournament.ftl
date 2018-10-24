<#-- @ftlvariable name="matches" type="java.util.Collection<entities.Match>" -->
<#include "base.ftl">
<#macro title>
    Tournament
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style5.css">
</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">

            <div class="row probootstrap-vh-100 align-items-center text-center">
                <p style="color:red">${tournament.name} </p>
                <div class="col-sm">

                    <p> </p>
                    <table>
                        <tr>
                            <th colspan="2">Name</th>
                            <th>Data</th>
                            <th>Teams</th>
                            <th> Results </th>
                        </tr>
                        <#list matches as match>
                        <tr>
                            <td></td>
                            <td><p>${match.name}</p></td>
                            <td>${match.datetime}</td>
                            <td>${match.team1.name} vs ${match.team2.name}</td>
                            <td>${match.result}</td>
                        </tr>
                        </#list>
                    </table>

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