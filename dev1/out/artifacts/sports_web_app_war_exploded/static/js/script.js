const deletePost = () => {
    let req = new XMLHttpRequest();
    req.open('DELETE', window.location.pathname, true);
    req.send();
}

const deleteComment = (id) => {
    $.ajax({
        url: `${window.location.pathname}/comments`,
        type: 'DELETE',
        success: (data) => {
            $('comments-item-${id}').remove();
        }
    });
}

const createComment = (textId) => {
    let text = $(`#${textId}`).val();
    $.ajax({
        url: `/posts/${post.id}/comments`,
        type: 'POST',
        data: {
            text: text
        },
        success: (data) => {
            let list = $('#comments-list');
            list.append(
                '<div id="comment-item-${id}">' +
                '<p>text<em> data.comment.datetime</em>' +
                '<em> data.comment.author.name</em> </p>' +
                '</div>'
            );
        }
    });
};

