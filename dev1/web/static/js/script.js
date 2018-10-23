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
            $(`#comments-item-${id}`).remove();
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
                let list = $('#comments-list');
                list.append(
                    `<div id="comment-item-${comment.id}">
                    <p>
                        ${comment.text}
                        <em>${comment.datetime}</em>
                        <em>${comment.author.name}</em>
                    </p>
                 <button onclick='deleteComment();' class="button8">Delete comment</button>
                </div>`
                );
            }
        });
    }
    $('#comment').val('');
};

