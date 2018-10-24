<#include "navbar.ftl">
<#macro page_head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/icomoon.css">
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/script.js"></script>
</#macro>
<#macro title></#macro>


<#macro main_content></#macro>

<#macro display_page>
<html>

<head>
    <title><@title/></title>
    <@page_head/>
    <@import/>
</head>

<body>
    <@nav_bar/>
    <@main_content/>
</body>

</html>
</#macro>