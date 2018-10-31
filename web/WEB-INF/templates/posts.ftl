<#include "base.ftl">
<#macro title>
    News
</#macro>
<#macro import>
    <link rel="stylesheet" href="/static/css/style4.css">
    <script src="/static/js/jquery-3.3.1.min.js" defer></script>

    <script src="/static/js/script.js" defer></script>

</#macro>

<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">
                <div class="col-sm">
                    <#if user??>
                    <p>Make new post</p>
                    <p>
                    <form method="POST" id="create_post" action="/posts">
                        <textarea id="title" class="text" cols="60" rows="2" name="text" required></textarea>
                    <p>
                        <small>Title</small>
                    </p>
                        <textarea type="text" class="text" cols="40" rows="2" name="title" required maxlength="140"></textarea>
                    <p></p>

                    <select name="sport_id">
                    <#list sports as sport>
                        <option value="${sport.id}">${sport.name}</option>
                    </#list>
                    </select>
                        <button type="submit" class="button8">Create post</button>
                    </form>
                    </p>
                    <p>Search posts by sport:</p>
                    </#if>

                <#if posts?has_content>
                <div class="probootstrap-text" id="posts-list">
                    <div>
                    <select name="sport" id="sport">
                    <#list sports as sport>
                        <option value="${sport.name}">${sport.name}</option>
                    </#list>
                    </select>
                    <button onclick="showPosts()" class="btn-primary">Show posts</button>
                    </div>
                    <#list posts as post>
                        <div class="post-item">
                            <p><a href="/posts/${post.id}" class="nav-link">${post.title}</a></p>
                            <p class="post-text"> ${post.text}</p>
                        </div>
                    </#list>
                </div>
                <#else>
                    <b>There are no posts yet.</b>
                </#if>
                </div>
            </div>
        </div>
        </div>
    </section>
</#macro>

<@display_page/>