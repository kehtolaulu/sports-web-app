<#-- @ftlvariable name="comments" type="java.util.List" -->
<#include "base.ftl">
<#macro title>
    Post
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
                    <div class="probootstrap-text">
                        <h1 class="profile text-white mb-4">${post.title}</h1>
                        <p class="margin">
                            <small>${post.text}</small>
                        </p>


                        <div class="probootstrap-subheading mb-5">
                            <p> ${post.datetime} </p>
                            <p> ${post.sport.name} </p>
                            <p> <a href="/profile/${post.author.id}" class="nav-link">${post.author.name} </a></p>
                        </div>

                        <div>
                        <#if user??>
                            <#if post.author.id == user.id>
                            <div class = " d-flex justify-content-between" >

                                <a href="/posts" class="nav-link" class="btn btn-primary"><button class="btn btn-primary">Back to all posts</button> </a>

                                <a href="/posts/${post.id}/edit" class="nav-link"><button class="btn btn-primary">Edit post</button> </a>

                                <button onclick='deletePost();' class="btn btn-primary">Delete post</button>
                            </div>
                            </#if>
                        </#if>
                        </div>

                        <div id="comments-list">
                            <#if comments?has_content>
                                <#list comments as comment>
                                    <div id="comment-item-${comment.id}">
                                        <p>${comment.text}<em> ${comment.datetime}</em> <em> ${comment.author.name}</em>
                                        </p>
                                    <#if user??>
                                        <#if user.id == comment.author.id>
                                        <button onclick='deleteComment(${comment.id});'>Delete comment</button>
                                        </#if>
                                    </#if>
                                    </div>
                                </#list>
                            <#else>
                                <b id="no-comments">There are no comments yet.</b>
                            </#if>
                        </div>

                        <#if user??>
                                <div>
                                    <textarea title="new comment" id="comment" class="text" cols="60"
                                              rows="2" maxlength="140"></textarea>
                                    <button onclick='newComment();'> Write a comment</button>
                                </div>

                        </#if>


                    </div>
                </div>
            </div>
        </div>
    </section>
</#macro>
<@display_page/>