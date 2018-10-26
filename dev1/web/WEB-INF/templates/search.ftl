<#-- @ftlvariable name="comments" type="java.util.List" -->
<#include "base.ftl">
<#macro title>
    Search
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style2.css">
    <script src="/static/js/jquery-3.3.1.min.js" defer></script>
    <script src="/static/js/script.js" defer></script>
</#macro>
<#macro main_content>
<section class="probootstrap-cover">
    <div class="container">
        <div class="row probootstrap-vh-100 align-items-center text-center">

            <div class="col-sm">

                <div class="probootstrap-text">

                    <div>

                        <input type="text" name="name" placeholder="name" size="15" class="input" id="name"/>
                        <select name="sport" id="sport">
                        <#list sports as sport>
                            <option value="${sport.name}">${sport.name}</option>
                        </#list>
                        </select>
                        <input type="text" size="15" class="input" name="city" placeholder="city" id="city">
                        <input type="text" size="15" class="input" name="year" placeholder="year" id="year">
                        <button class="button8" onclick="search();"> SEARCH </button>

                    </div>
                    <br>
                    <table id="table-results">
                    </table>
                    <div class="probootstrap-subheading mb-5">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</#macro>
<@display_page/>