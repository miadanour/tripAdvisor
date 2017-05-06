$(document).ready(function() {
    $('.season li').click(function() {
        var x = this.textContent;
        $.ajax({
            url: "http://localhost:8000/home",
            crossDomain: true,
            dataType: 'jsonp',
            success: function(result) {
                $(".pop-activities").html(result);
                console.log(result);
            }
        });
    });
});
