
$(document).ready(function () {
    console.log('Document is ready.');

    $('#search-bar').on('input', function (e) {
        console.log('input changed');
        let inputContent = $(this).val();
        let url = '/api/book/search?query=' + encodeURIComponent(inputContent);
        console.log(inputContent);

        $("#search-results-block").load(url);
    });
})

