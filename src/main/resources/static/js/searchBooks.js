
$(document).ready(function () {
    console.log('Document is ready.');

    $('#search-bar').on('input', function (e) {
        console.log('input changed');
        let inputContent = $('#search-bar').val();
        let url = '/api/book?title=' + inputContent;
        console.log(inputContent);
        // fetch data

        // write content onto the DOM
        $.get(url, function (data, status) {
            console.log(data);
            console.log(status);
        });
    });
})

