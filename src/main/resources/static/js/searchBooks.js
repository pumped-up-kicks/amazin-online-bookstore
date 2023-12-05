
$(document).ready(function () {
    console.log('Document is ready.');

    $('#search-bar').on('input', function (e) {
        console.log('input changed');
        let inputContent = $(this).val();
        let url = '/api/book/search?query=' + inputContent;
        console.log(inputContent);
        // fetch data

        // write content onto the DOM
        $.get(url, function (data, status) {
            console.log(data);
            console.log(status);

        });
    });
})

