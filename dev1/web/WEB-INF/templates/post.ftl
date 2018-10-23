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


                        <div class="probootstrap-subheading mb-5">
                            <p> ${post.datetime} </p>
                            <p> <a href="/profile/${post.author.id}" class="nav-link">${post.author.name} </a></p>
                        </div>
                    <#--<p><a href="/posts" class="button8">Back to all posts</a>-->
                        <div>
                        <#if user??>
                            <#if post.author.id == user.id>
                              <form method="GET" id="edit_post" action="/posts">
                                  <button type="submit" class="button8">Back to all posts</button>
                              </form>
                                <#--<p> </p>-->
                                <form method="GET" id="edit_post" action="/posts/${post.id}/edit">
                                    <button type="submit" class="button8">Edit post</button>
                                </form>
                                <#--<p> </p>-->
                                <button onclick='deletePost();' class="button8">Delete post</button>
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
                                <b>There is no comments yet.</b>
                            </#if>
                        </div>

                        <#if user??>
                                <div>
                                    <textarea title="new comment" id="comment" class="text" cols="60"
                                              rows="2"></textarea>
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


    <#--<section class="probootstrap-cover">-->
        <#--<div class="container">-->
            <#--<div class="row probootstrap-vh-100 align-items-center text-center">-->
                <#--<div class="col-sm">-->
                    <#--<div class="probootstrap-text">-->
                        <#--<h1 class="profile text-white mb-4"><big><p>Maria Sharapova won new game...</p> </big></h1>-->

                        <#--<p class ="margin"><small> After winning the semifinals of the WTA Finals over Anastasia Myskina in 2004, Myskina said that Maria’s father constantly suggested to-->
                            <#--her daughter during the match, shouting insults at Anastasia. Then Myskina said that if Sharapova joined the national team next season,-->
                            <#--then she herself would no longer be in the national team, and she considered the behavior of her father incorrect. Assistant captain-->
                            <#--of the Russian national team at the Federation Cup Shamil Tarpishcheva said: "The behavior of her father (at the Final tournament) was simply-->
                            <#--outrageous."</small></p>-->
                        <#--<p> Comment1 : This is the best topic!   <em> 23.06.17 </em>   <em> User100 </em> </p>-->

                        <#--<textarea class = "text" cols="60" rows="2"> </textarea>-->
                        <#--<p> <button> Write a comment  </button> </p>-->
                        <#--<p> <a href="news.html" class="button8">Back to all posts</a> <a href="editpost.html" class="button8">Edit</a> <a href="news.html" class="button8">Delete</a> </p>-->
                        <#--<div class="probootstrap-subheading mb-5">-->
                            <#--<p>-->
                            <#--</p>-->

                            <#--<p> 24.03.2018 </p>-->
                            <#--<p> User100 </p>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</section>-->