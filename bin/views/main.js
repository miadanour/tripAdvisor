var cat = "";
var dur = "";
var maxp = "";
$(document).ready(function() {
    $('.season li').click(function() {
        var x = this.textContent;
        $.ajax({
            url: "http://localhost:8000/query1?season=" + x,
            crossDomain: true,
            dataType: 'jsonp',
            success: function(result) {}
        });
    });

    $('.category li').click(function() {
        var x = this.textContent;
        cat = x;
        $.ajax({
            url: "http://localhost:8000/query2?category=" + x,
            crossDomain: true,
            dataType: 'jsonp',
            success: function(result) {}
        });
    });

    $('.includescategory li').click(function() {
        var x = this.textContent;
        cat = x;
        $.ajax({
            url: "http://localhost:8000/query3?category=" + x,
            crossDomain: true,
            dataType: 'jsonp',
            success: function(result) {}
        });
    });


    $('.sortCat li').click(function() {
        var x = this.textContent;
        cat = x;
        $.ajax({
            url: "http://localhost:8000/query4?category=" + x,
            crossDomain: true,
            dataType: 'jsonp',
            success: function(result) {}
        });
    });

    $('.rating li').click(function() {
        if (cat == "") {
            alert("you need to select a category first");
        } else {
            var x = this.textContent;
            $.ajax({
                url: "http://localhost:8000/query5?rank=" + x + "&category=" + cat,
                crossDomain: true,
                dataType: 'jsonp',
                success: function(result) {}
            });
        }

    });

    $('.maxprice li').click(function() {
        if (cat == "") {
            alert("you need to select a category first");
        } else {
            var x = this.textContent;
            $.ajax({
                url: "http://localhost:8000/query6?maxprice=" + x + "&category=" + cat + 
               ((dur=="")?"":dur)+((maxp=="")?"":maxp),
                crossDomain: true,
                dataType: 'jsonp',
                success: function(result) {}
            });
        }

    });
});

function category(args) {
    console.log(args);
    var cards = "";
    var arg = args.results.bindings;
    for (var i = 0; i < arg.length; i++) {
        var cityname = arg[i].subject.value.split("#")[1];
        cards += '<div class="card">' +
            '<img src="' + cityname + '.jpg">' +
            '<a href="/activity?name=">' + cityname + " </a>" +
            '<p>' + cat + " </p>" +
            "</div>";
    }
    if (cards == "") {
        cards = "No results found";
    }
    $(".pop-activities").html(cards);
}

function city(args) {
    console.log(args);
    var cards = "";
    var arg = args.results.bindings;
    for (var i = 0; i < arg.length; i++) {
        var cityname = arg[i].city.value.split("#")[1];
        cards += '<div class="card">' +
            '<img src="' + cityname + '.jpg">' +
            '<a href="/city?name=' + cityname + '">' + cityname + " </a>" +
            "</div>";
    }
    if (cards == "") {
        cards = "No results found";
    }
    $(".pop-activities").html(cards);
}


function activity(args) {
    console.log(args);
    var cards = "";
    var arg = args.results.bindings;
    for (var i = 0; i < arg.length; i++) {
        var activityname = arg[i].activity.value.split("#")[1];
        cards += '<div class="card">' +
            '<img src="' + cat + '.jpg">' +
            '<a href="/activity?name=' + activityname + '">' + activityname + " </a>" +
            "<p>" + cat + "</p>" +
            "<p>" + ((arg[i].rate != null) ? arg[i].rate.value : "") + "</p>" +
            "</div>";
    }
    if (cards == "") {
        cards = "No results found";
    }
    $(".pop-activities").html(cards);
}
