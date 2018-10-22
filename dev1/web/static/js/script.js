const deletePost = () => {
    let req = new XMLHttpRequest();
    req.open('DELETE', window.location.pathname, true);
    req.send();
};

const deleteComment = (id) => {
    $.ajax({
        url: `${window.location.pathname}/comments`,
        type: 'DELETE',
        success: (data) => {
            $(`comments-item-${id}`).remove();
        }
    });
};

const newComment = () => {
    let text = $('#comment').val();
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
};

