var category = "";
$(document).ready(function() {
    $('.season li').click(function() {
        var x = this.textContent;
        $.ajax({
            url: "http://localhost:8000/query1?season=" + x,
            crossDomain: true,
            dataType: 'jsonp',
            success: function(result) {
            }
        });
    });

    $('.category li').click(function() {
        var x = this.textContent;
        category = x;
        $.ajax({
            url: "http://localhost:8000/query2?category=" + x,
            crossDomain: true,
            dataType: 'jsonp',
            success: function(result) {
            }
        });
    });
});

function category(args) {
    console.log(args);
    var cards = "";
    var arg = args.results.bindings;
    for (var i = 0; i < arg.length; i++) {
        var cityname = arg[i].city.value.split("#")[1];
        cards += '<div class="card">'+
            '<img src="'+cityname+'.jpg">'+
            '<a href="/activity?name=">'+cityname+" </a>"+
            '<p>'+category+" </p>"+
        "</div>";
    }
    $(".pop-activities").html(cards);
}

function city(args) {
    console.log(args);
    var cards = "";
    var arg = args.results.bindings;
    for (var i = 0; i < arg.length; i++) {
        var cityname = arg[i].city.value.split("#")[1];
        cards += '<div class="card">'+
            '<img src="'+cityname+'.jpg">'+
            '<a href="/city?name='+cityname+'">'+cityname+" </a>"+
        "</div>";
    }
    $(".pop-activities").html(cards);
}


