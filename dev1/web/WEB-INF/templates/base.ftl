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
    <link rel="stylesheet" href="/static/css/style3.css">
    <script src="/static/js/script.js"></script>
</#macro>
<#macro nav_bar>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark probootstrap-navabr-dark">
        <div class="container">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#probootstrap-nav" aria-controls="probootstrap-nav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="probootstrap-nav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a href="/index" class="nav-link"><small>Home</small></a></li>
                    <li class="nav-item"><a href="/profile" class="nav-link"><small>My Profile</small></a></li>
                    <li class="nav-item"><a href="/posts" class="nav-link"><small>News</small></a></li>
                    <li class="nav-item"><a href="/sportsmen" class="nav-link"><small>Popular Athletes</small></a></li>
                    <li class="nav-item"><a href="/about" class="nav-link" ><small>About us</small></a></li>
                    <li class="nav-item"><a href="/results" class="nav-link"><small>Results</small></a></li>
                <#if user??>
                    <li class="nav-item probootstrap-cta probootstrap-seperator"><form method="post" action="/logout"><button type="submit" class="button8">Log out</button></li>
                <#else>
                    <li class="nav-item probootstrap-cta probootstrap-seperator"><a href="/registration" class="nav-link">Sign
                        up</a></li>
                    <li class="nav-item probootstrap-cta"><a href="/auth" class="nav-link">Log In</a></li>
                </#if>
                </ul>

            </div>
        </div>
    </nav>
</#macro>


<#macro main_content></#macro>

<#macro display_page>
<html>

<head>
    <@page_head/>
    <@import/>
</head>

<body>
    <@nav_bar/>
    <@main_content/>
</body>

</html>
</#macro>