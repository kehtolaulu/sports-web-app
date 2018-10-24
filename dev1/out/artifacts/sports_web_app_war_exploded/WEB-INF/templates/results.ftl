<#include "base.ftl">

<#macro import>
    <link rel="stylesheet" href="/static/css/style5.css">
</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">
                <p style="color:red"> TOURNAMENTS </p>
                <div class="col-sm">
                    <table>
                        <tr>
                            <th colspan="2">Kind of Sport</th>
                            <th>Name</th>
                            <th>Place</th>
                            <th>Date</th>
                            <th> Results</th>
                        </tr>
                        <#list tournaments as tournament>
                        <tr>
                            <td></td>
                            <td>${tournament.sport.name}</td>
                            <td><p><a href="/tournament/${tournament.id}">${tournament.name}</p></a></td>
                            <td>${tournament.place}</td>
                            <td>${tournament.date_from}-${tournament.date_to}</td>
                            <#--<td>${tournament.result}</td>-->
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