<#include "base.ftl">
<#macro title>
    Profile
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style.css">
</#macro>
<#macro main_content>
        <section class="probootstrap-cover">
            <div class="container">
                <div class="row probootstrap-vh-100 align-items-center text-center">
                    <div class="col-sm">
                        <div class="probootstrap-text">
                            <h1 class="profile text-white mb-4"><small><p>Name: ${user.name}</p> <p>Login: ${user.login}</p> </small></h1>
                            <p><img src="/static/images/${user.login}.jpg" class = "newphoto" onerror="this.src='/static/images/myphoto.jpg'" width="150px" height="150px"> </p>
                            <p> <form method="GET" id="edit_post" action="/profile/${user.id}/posts">
                            <button type="submit" class="button8">See posts</button>
                        </form>  </p>
                        </form>
                            </p>
                            <div class="probootstrap-subheading mb-5">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
</#macro>

<@display_page/>