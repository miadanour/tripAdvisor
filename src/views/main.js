var cat = "";
var dur = "";
var maxp = "";
var night = false;
var interests = false;
var rank = "";
var currentcity = "";
var typingTimer;
var doneTypingInterval = 2000;
var username = "";
var price = false;

$(document).ready(function() {
    $('.login-btn').click(function() {
        username = $("#username").val();
        switchpage(0, "cities");
    });

    $(".pageswitch").click(function() {
        var pageindex = $(".pageswitch").index(this);
        switchpage(pageindex, this.textContent.toLowerCase());
    });
    $('.season li').click(function() {
        var x = this.textContent;
        query1();
    });

    $('.category li').click(function() {
        var x = this.textContent;
        cat = x;
        query2();
    });

    $('.includescategory li').click(function() {
        var x = this.textContent;
        cat = x;
        query3();
    });

    $('.city li').click(function() {
        var x = this.textContent;
        currentcity = x;
        if (cat == "") {
            includes();
        } else {
            query10();
        }
    });

    $('.sortCat li').click(function() {
        var x = this.textContent;
        if (cat == "") {
            alert("you need to select a category first");
        } else {
            if (x == "Price") {
                query9();
            } else {
                query4();
            }
        }
    });

    $('.show li').click(function() {
        var x = this.textContent;
        cat = x;
        console.log(x);
        query15();
    });

    $('.rating li').click(function() {
        if (cat == "") {
            alert("you need to select a category first");
        } else {
            var x = this.textContent;
            rank = x;
            query5();
        }

    });

    $('.maxprice li').click(function() {
        maxp = this.textContent;
        if (cat == "") {
            alert("you need to select a category first");
        } else {
            query13();
        }

    });

    $("#night").click(function() {
        if (cat != "") {
            if (night) {
                query7();
            } else {
                query6();
            }
            night = this.checked;

        } else {
            alert("you need to select a category first");
            this.checked = false;
        }
        this.checked = night;
    });

    $("#interests").click(function() {

        if (cat != "") {
            if (interests) {
                query14();
            }
            interests = this.checked;
        } else {
            alert("you need to select a category first");
            this.checked = false;
        }
        this.checked = interests;
    });

    $("#Price").click(function() {

        if (cat != "") {
            if (interests) {
                query8();
            }
            price = this.checked;
        } else {
            alert("you need to select a category first");
        }
        this.checked = interests;
    });

    $('#duration').on('keyup', function() {
        clearTimeout(typingTimer);
        typingTimer = setTimeout(doneTyping1, doneTypingInterval);
    });

    //on keydown, clear the countdown 
    $('#duration').on('keydown', function() {
        clearTimeout(typingTimer);
    });

    $('#Keyword').on('keyup', function() {
        clearTimeout(typingTimer);
        typingTimer = setTimeout(doneTyping, doneTypingInterval);
    });

    //on keydown, clear the countdown 
    $('#Keyword').on('keydown', function() {
        clearTimeout(typingTimer);
    });


});

function doneTyping() {
    if (cat != "") {
        query12($("#Keyword").val());
    } else {
        alert("you need to select a category first");
    }
}

function doneTyping1() {
    dur = $("#duration").val();
    if (cat != "") {
        query6();
    } else {
        alert("you need to select a category first");
    }
}

function category(args) {
    console.log(args);
    var cards = "";
    var arg = args.results.bindings;
    for (var i = 0; i < arg.length; i++) {
        var cityname = arg[i].subject.value.split("#")[1];
        cards += '<div class="card flex-item">' +
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
        cards += '<div class="card flex-item">' +
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
        cards += '<div class="card flex-item">' +
            '<img src="' + ((arg[i].category != null) ? arg[i].category.value.split("#")[1] : cat) + '.jpg">' +
            '<a href="/activity?name=' + activityname + '">' + activityname + " </a>" +
            "<p>" + cat + "</p>" +
            "<p>" + ((arg[i].price != null) ? arg[i].price.value : "") + "</p>" +
            "<p>" + ((arg[i].rate != null) ? arg[i].rate.value : "") + "</p>" +
            "</div>";
    }
    if (cards == "") {
        cards = "No results found";
    }
    $(".pop-activities").html(cards);
}

function user(arg) {
    console.log(arg.user.value);
}

function start() {
    var filename = document.location.pathname.match(/[^\/]+$/)[0].split(".")[0];
    console.log(filename);


}

function switchpage(index, page) {
    $('.cloacked').hide();
    $($('.cloacked').get(index + 1)).show();
    console.log(index + " " + page);
    $.ajax({
        url: "http://localhost:8000/" + page,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}


function query1() {
    $.ajax({
        url: "http://localhost:8000/query1?season=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query2() {
    $.ajax({
        url: "http://localhost:8000/query2?category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query3() {
    $.ajax({
        url: "http://localhost:8000/query3?category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query4() {
    $.ajax({
        url: "http://localhost:8000/query4?category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query5() {
    $.ajax({
        url: "http://localhost:8000/query5?rank=" + rank + "&category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query6() {
    $.ajax({
        url: "http://localhost:8000/query6?category=" + cat + "&maxPrice=" + maxp +
            ((dur == "") ? "" : dur),
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query7() {
    $.ajax({
        url: "http://localhost:8000/query7?category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query8() {
    $.ajax({
        url: "http://localhost:8000/query8?category=" + cat +"&price="+price,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query9() {
    $.ajax({
        url: "http://localhost:8000/query9?category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query10() {
    $.ajax({
        url: "http://localhost:8000/includes?city=" + currentcity + "&category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query12(keyword) {
    $.ajax({
        url: "http://localhost:8000/query12?category=" + cat + "&match=" + keyword,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query13() {
    $.ajax({
        url: "http://localhost:8000/query13?category=" + cat + "&maxPrice=" + maxp,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query14() {
    $.ajax({
        url: "http://localhost:8000/query14?category=" + cat + "&user=" + username,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function query15() {
    $.ajax({
        url: "http://localhost:8000/query8?category=" + cat,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}

function includes() {
    $.ajax({
        url: "http://localhost:8000/includes?city=" + currentcity,
        crossDomain: true,
        dataType: 'jsonp',
        success: function(result) {}
    });
}
