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
</#macro>
<#macro nav_bar>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark probootstrap-navabr-dark">
    <div class="container">
        <p>
        <form action="" method="post" class="search">
            <input type="search" name="" placeholder="search" class="input"/>
            <input type="submit" name="" value="" class="submit"/>
        </form>
        </p>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#probootstrap-nav"
                aria-controls="probootstrap-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="probootstrap-nav">
            <ul class="navbar-nav mx-auto">
                <li class="nav-item"><a href="/index" class="nav-link">
                    <small>Home</small>
                </a></li>

                <#if user??>
                <li class="nav-item"><a href="cards.html" class="nav-link">
                    <small>My Profile</small>
                </a></li>
                </#if>

                <li class="nav-item"><a href="news.html" class="nav-link">
                    <small>News</small>
                </a></li>
                <li class="nav-item"><a href="popularathletes.html" class="nav-link">
                    <small>Popular Athletes</small>
                </a></li>
                <#--<li class="nav-item"><a href="contact.html" class="nav-link">-->
                    <#--<small>Contact</small>-->
                <#--</a></li>-->
                <li class="nav-item"><a href="/about" class="nav-link">
                    <small>About us</small>
                </a></li>
                <li class="nav-item"><a href="results.html" class="nav-link">
                    <small>Game Scheduels</small>
                </a></li>

                <#if user??>
                <li class="nav-item probootstrap-cta probootstrap-seperator"><a href="/logout" class="nav-link">Log out</a></li>
                <#else>
                    <li class="nav-item probootstrap-cta probootstrap-seperator"><a href="signup.html" class="nav-link">Sign
                        up</a></li>
                    <li class="nav-item probootstrap-cta"><a href="/login" class="nav-link">Log In</a></li>
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