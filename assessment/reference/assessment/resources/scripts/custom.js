//Start Include side bar into all pages by using side bar main div id #sidebar
$(function(){
    $("#sidebar").load("html/sidebar.html"); 
});

//Start add_question.html
/* off-canvas sidebar toggle */
$(document).on('click','[data-toggle=offcanvas]',function () {
    $('.row-offcanvas').toggleClass('active');
    $('.collapse').toggleClass('in').toggleClass('hidden-xs').toggleClass('visible-xs');
});

$(document).on('click', '#addanother', function () {
    var alphabet = nextString($("#maindivforaddmore").children().last().children().first().text());
    $("#maindivforaddmore").append("<div class='option'><span>" + alphabet + "</span><input type='text'><div class='choice'><input name='option' type='radio' style='margin-right: 29px;'><a href='javascript:void(0);' class='removenewdiv'>-</a></div></div>");
});

$(document).on('click', '.removenewdiv', function () {
    $(this).parent().parent().remove();
});

function nextString(str) {
    if (!str)
        return 'A'

    let tail = ''
    let
            i = str.length - 1
    let
            char = str[i]

    while (char === 'Z' && i > 0) {
        i--;
        char = str[i]
        tail = 'A' + tail
    }
    if (char === 'Z')
        return 'AA' + tail

    return str.slice(0, i) + String.fromCharCode(char.charCodeAt(0) + 1) + tail
}

$(function () {
    $(".addimage").on('click', function (e) {
        alert('dsdsd');
        e.preventDefault();
        $("#addimage").trigger('click');
    });
    $(".addaudio").on('click', function (e) {
        e.preventDefault();
        $("#addaudio").trigger('click');
    });
    $(".addvideo").on('click', function (e) {
        e.preventDefault();
        $("#addvideo").trigger('click');
    });
});
$('#addimage').change(function () {
    var file = $('#addimage')[0].files[0].name;
    $('.queimage').text('Image: ' + file);
});
$('#addaudio').change(function () {
    var file = $('#addaudio')[0].files[0].name;
    $('.queaudio').text('Audio: ' + file);
});
$('#addvideo').change(function () {
    var file = $('#addvideo')[0].files[0].name;
    $('.quevideo').text('Video: ' + file);
});
//End add_question.html

//Start Question_list.html
$('[data-toggle=offcanvas]').click(function () {
    $('.row-offcanvas').toggleClass('active');
    $('.collapse').toggleClass('in').toggleClass('hidden-xs').toggleClass('visible-xs');
});
//End Question_list.html

//Start index.html
function myFunction() {
    var x = document.getElementById("userpassword");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
//End index.html


$(document).on('click','.add-new-questions',function () {
    $("#main").load("add_question_inc.html"); 
});

$(document).on('click','.question-list',function () {
    $("#main").load("question_list_inc.html"); 
});


$(document).on('click','.add_test',function () {
    $("#main").load("add_test.html"); 
});

$(document).on('click','.add_test_step2',function () {
    $("#main").load("add_test_step2.html"); 
});

$(document).on('click','.backstep1',function () {
    $("#main").load("add_test.html"); 
});

$(document).on('click','.add_test_step3',function () {
    $("#main").load("add_test_step3.html"); 
});

$(document).on('click','.backstep2',function () {
    $("#main").load("add_test_step2.html"); 
});


$(document).on('click','.add_test_step4',function () {
    $("#main").load("add_test_step4.html"); 
});

$(document).on('click','.backstep3',function () {
    $("#main").load("add_test_step3.html"); 
});


// Add section
var counter = 1;
$(document).on('click', '.addquestion', function () {  
  // counter++;
    //$(".quesectiondiv").append("<div style='border-bottom: 1px solid #ddd;'><h4>Section " + counter + "</h4></div>");
    //sendMsg('Information', 'Add multiple sections coming in next release');
    window.location="addNewSection";
});


$(document).on('click', '.quesectiondiv .quesection', function () {
    $(".step2content .sectionlabel").hide();
    $(".step2content .questiontable").show();
});

// open question form popup
$(document).on('click', '#openqueform', function () {
    $(".addquestionform").show();
});
$(document).on('click', '.close_queform', function () {
    $(".addquestionform").hide();
});


// Userform step3
$(document).on('click', '#openuserform', function () {
    $(".step3userinfo").show();
});



$(function () {
 var acc = document.getElementsByClassName("accordion");
    var m;

    for (m = 0; m < acc.length; m++) {
        acc[m].addEventListener("click", function () {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.display === "block") {
                panel.style.display = "none";
            } else {
                panel.style.display = "block";
            }
        });
    }

});

function sendMsg(messageType, message){
		 var notification = 'Information';
			 $(function(){
				 new PNotify({
				 title: notification,
				 text: message,
				 type: messageType,
				 styling: 'bootstrap3',
				 hide: true
			     });
			 }); 	
		}

