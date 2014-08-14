
$(document).ready(function() {
    $("[name='submitBtn']").click(function() {
        $('#result').html("");
        $.get("Control", {type: $("#selecttype").val()}, function(ajaxresult) {
            $.each(ajaxresult, function(i, val) {
                $('#result').append('<li>' + val + '</li>');
            });
        });
    });
});
