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
                 <button onclick='deleteComment();'>Delete comment</button>
                </div>`
                );
            }
        });
    }
    $('#comment').val('');
};

const search = () => {
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
            console.log(msg);
            $('#table-results').empty();
            msg.forEach((tournament) => $('#table-results').append(`<tr>
                            <td></td>
                            <td>${tournament[sport['name']]}</td>
                            <td><p><a href="/tournament/${tournament.id}">${tournament['name']}</p></a></td>
                            <td>${tournament['place']}</td>
                            <td>${tournament['date_from']} - ${tournament['date_to']}</td>
                            <td>${tournament['result']}</td>
                        </tr>`));
        }
    });

};
