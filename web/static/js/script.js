const deletePost = () => {
    $.ajax({
        url: `${window.location.pathname}`,
        type: 'DELETE',
        success: (data) => {
            window.location.href = '/posts';
        }
    });
};

const deleteComment = (id) => {
    $.ajax({
        url: `/comments/${id}`,
        type: 'DELETE',
        success: (data) => {
            $(`#comment-item-${id}`).remove();
            let list = $('#comments-list');
            if (list.children().length === 0) {
                $('#comments-list').append(`<b id="no-comments">There are no comments yet.</b>`);
            }
        }
    });
};

const newComment = () => {
    let text = $('#comment').val();
    if (text === '') {
        alert('too empty comment!')
    } else {
        $.ajax({
            url: window.location.pathname + '/comments',
            type: 'POST',
            data: {
                text: text
            },
            success: (comment) => {
                console.log(comment);
                $('#no-comments').remove();
                let list = $('#comments-list');
                list.append(
                    `<div id="comment-item-${comment.id}">
                    <p>
                        ${comment.text}
                        <em>${comment.datetime}</em>
                        <em>${comment.author.name}</em>
                    </p>
                 <button onclick='deleteComment(${comment.id});'>
                    Delete comment
                 </button>
                </div>`
                );
            }
        });
    }
    $('#comment').val('');
};

const search = () => {
    // let comments = [];
    let name = $('#name').val();
    let city = $(`#city`).val();
    let year = $(`#year`).val();
    let sport = $(`#sport`).val();
    $.ajax({
        url: '/search.json',
        type: 'GET',
        data: {
            name: name,
            city: city,
            year: year,
            sport: sport
        },
        success: (msg) => {
            // comments.push(msg);
            // console.log(comments);
            $('#table-results').empty();
            msg.forEach((tournament) => $('#table-results').append(`<tr>
                            <td></td>
                            <td>${tournament.sport.name}</td>
                            <td><p><a href="/tournament/${tournament.id}" target="_blank" rel="noopener noreferrer">${tournament['name']}</p>
                            </a></td>
                            <td>${tournament['place']}</td>
                            <td>${tournament['date_from']} - ${tournament['date_to']}</td>
                            <td>${tournament['result']}</td>
                        </tr>`));
        }
    });

};

const showPosts = () => {
    let sport = $(`#sport`).val();
    $.ajax({
        url: '/posts.json',
        type: 'GET',
        data: {
            sport: sport
        },
        success: (msg) => {
            $(`.post-item`).remove();
            msg.forEach((post) => $('#posts-list').append(`
<div class="post-item"><p><a href="/posts/${post.id}" class="nav-link">${post.title}</a></p>
                            <p> ${post.text}</p>
                            </div>`));
        }

    });
};
const autoComplete = (inputId) => {
    let query = $(`#${inputId}`).val();
    if (query.length > 2) {
        $.ajax({
            url: '/search/autocomplete.json',
            type: 'GET',
            data: {
                query: query
            },
            success: (msg) => {
                console.log(msg);
                let list = $('#autocomplete-list');
                $('.autocomplete-item').remove();
                msg.forEach((tournament) => list.append(`
<div class="autocomplete-item">${tournament}</div>`));
            }
        });
    } else {
        $('.autocomplete-item').remove();
    }
};
