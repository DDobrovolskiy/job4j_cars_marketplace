logging()

function logging() {
    $.ajax({
        type: 'GET',
        url: '/car/user',
        dataType: 'json'
    }).done(function (data) {
        console.log(data)
        let html = '<b  id="logging">' + data.name + '</b>'
        $('#logging').replaceWith(html)
    }
    ).fail(function (err) {
    console.log(err);
    });
}