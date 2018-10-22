<#-- @ftlvariable name="comments" type="java.util.List" -->
<#include "base.ftl">

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
                    <div class="probootstrap-text">
                        <h1 class="profile text-white mb-4">${post.title}</h1>
                        <p class="margin">
                            <small>${post.text}</small>
                        </p>

                        <#if user??>
                            <#if post.author.id == user.id>
                            <form method="GET" id="edit_post" action="/posts/${post.id}/edit">
                                <button type="submit" class="button8">Edit post</button>
                            </form>
                            <form method="POST" id="delete_post">
                                <button onclick='deletePost();' class="button8">Delete post</button>
                            </form>
                            </#if>
                        </#if>
                        <div class="probootstrap-subheading mb-5">
                            <p> ${post.datetime} </p>
                            <p> <a href="/profile/${post.author.id}" class="nav-link">${post.author.name} </a></p>
                        </div>
                    <#--<p><a href="/posts" class="button8">Back to all posts</a>-->

                        <#if user??>
                                <div>
                                    <textarea title="new comment" id="comment" class="text" cols="60"
                                              rows="2"></textarea>
                                    <button onclick='newComment();'> Write a comment</button>
                                </div>

                        </#if>

                        <div id="comments-list">
                            <#if comments?has_content>
                                <#list comments as comment>
                                    <div id="comment-item">
                                        <p>${comment.text}<em> ${comment.datetime}</em> <em> ${comment.author.name}</em>
                                        </p>
                                    <#if user??>
                                        <#if user.id == comment.author.id>
                                        <button onclick='deleteComment();' class="button8">Delete comment</button>
                                        </#if>
                                    </#if>
                                    </div>
                                </#list>
                            <#else>
                                <b>There is no comments yet.</b>
                            </#if>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>
</#macro>
<@display_page/>