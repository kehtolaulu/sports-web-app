<#-- @ftlvariable name="comments" type="" -->
<#include "base.ftl">

<#macro import>
    <link rel="stylesheet" href="/static/css/style4.css">
    <script src="/static/js/jquery-3.3.1.min.js" async></script>
    <script src="/static/js/script.js" async></script>
</#macro>
<#macro main_content>
    <section class="probootstrap-cover">
        <div class="container">
            <div class="row probootstrap-vh-100 align-items-center text-center">
                <div class="col-sm">
                    <div class="probootstrap-text">
                        <p> <a href="/posts" class="button8">Back to all posts</a>
                        <h1 class="profile text-white mb-4"><big><p>${post.title}</p> </big></h1>
                        <p class ="margin"><small>${post.text}</small></p>

                        <#if user??>
                            <#if post.author.id == user.id>
                            <form method="POST" id="edit_post" action="/posts/${post.id}">
                                <button type="submit" class="button8">Edit post</button>
                            </form>
                            <form method="POST" id="delete_post">
                                <button onclick='deletePost();' class="button8">Delete post</button>
                            </form>
                            </#if>
                        </#if>
                        <div class="probootstrap-subheading mb-5">
                            <p>
                            </p>
                            <p> ${post.datetime} </p>
                            <p> ${post.author.name} </p>
                        </div>

                        <div id="comments-list">
                            <#if comments?has_content>
                                <#list comments as comment>
                                    <div id="comment-item">
                                        <p>${comment.text}<em> ${comment.datetime}</em>   <em> ${comment.author.name}</em> </p>
                                    </div>


                                <#if user??>
                                <form method='POST' id="comment" action="/posts/${post.id}/comments">
                                        <textarea id="comment" class = "text" cols="60" rows="2"> </textarea>
                                        <p> <button type="submit" onclick="createComment('comment')"> Write a comment</button> </p>
                                </form>

                                <form method="POST" id="delete_post">
                                    <button onclick='deleteComment();' class="button8">Delete comment</button>
                                </form>
                                </#if>
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