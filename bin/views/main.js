$(document).ready(function() {
    $('.season li').click(function() {
        var x = this.textContent;
       $.ajax({url: "http://localhost:8000/home" , success: function(result){
        $(".pop-activities").html(result);
        console.log(result);
    }});
    });
});
