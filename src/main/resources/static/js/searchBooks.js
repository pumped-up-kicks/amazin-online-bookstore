
$(document).ready(function () {
    console.log('Document is ready.');

    $('#search-bar').on('input', function (e) {
        console.log('input changed');
        let inputContent = $(this).val();
        // FIXME: input content with spaces are not being included in URL
        let url = '/api/book/search?query=' + encodeURIComponent(inputContent);
        console.log(inputContent);
        // fetch data

        // write content onto the DOM
        $.get(url, function (data, status) {
            console.log(data);
            console.log(status);
            //var classes = $(data).filter("container").attr("")
        });

        $("#search-results-block").load(url);
    });
})

