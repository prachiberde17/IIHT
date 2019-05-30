/*Header*/

$(window).scroll(function () {
	if ($(document).scrollTop() > 0) {
		$('#header').addClass('sticky');
	} else {
		$('#header').removeClass('sticky');
	}
});

/*Scroll-set*/

$(function () {
	$('.scroll-set').click(function () {
		if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
			var target = $(this.hash);
			target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
			if (target.length) {
				$('html, body').animate({
					scrollTop: target.offset().top - 50
				}, 1000);
				return false;
			}
		}
	});
});


/*WOW js*/

var wow = new WOW({
	boxClass: 'wow', // animated element css class (default is wow)
	animateClass: 'animated', // animation css class (default is animated)
	offset: 110, // distance to the element when triggering the animation (default is 0)
	mobile: true, // trigger animations on mobile devices (default is true)
	live: true, // act on asynchronously loaded content (default is true)
	callback: function (box) {
		// the callback is fired every time an animation is started
		// the argument that is passed in is the DOM node being animated
	},
	scrollContainer: null // optional scroll container selector, otherwise use window
});
wow.init();


// page-codes

// tabs

$(document).ready(function () {

	$('.tab-link').click(function () {
		var tab_id = $(this).attr('data-tab');

		$('.tab-link').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#" + tab_id).addClass('current');
		$('.section-flex').addClass('animated fadeInDown');
		$('.section-content').addClass('animated fadeInUp');
		$(".home").hide();
	})

})

// 2

$(".body-close").click(function () {
	$(".tab-content").removeClass("current");
	$('.section-flex').removeClass('fadeInDown');
	$('.section-content').removeClass('fadeInUp');
	$(".home").fadeIn();
	$('.section-title').addClass('animated fadeInDown');
	$('.about-card').addClass('animated fadeInUp');
});


// mobile-float

$(document).ready(function () {
	$('.floatingButton').on('click',
		function (e) {
			e.preventDefault();
			$(this).toggleClass('open');
			$('.floatingMenu').stop().slideToggle();
			$('.floating-backdrop').toggleClass('show');
			$('#nav-icon2').toggleClass('open');
		}
	);

	$('.floatingMenu li a').on('click', function (e) {
		$('.floatingMenu').hide();
		$('.floatingButton').removeClass('open');
		$('.floating-backdrop').removeClass('show');
		$('#nav-icon2').removeClass('open');

	});
});


// accordian animation

$(".expand1").click(function () {
	$(".li-content1").toggle().addClass('animated fadeIn');
	$(".li-icon1").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".automated .custom-list li:first-child .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand2").click(function () {
	$(".li-content2").toggle().addClass('animated fadeIn');
	$(".li-icon2").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".automated .custom-list li:nth-child(2) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand3").click(function () {
	$(".li-content3").toggle().addClass('animated fadeIn');
	$(".li-icon3").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".automated .custom-list li:nth-child(3) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand4").click(function () {
	$(".li-content4").toggle().addClass('animated fadeIn');
	$(".li-icon4").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".automated .custom-list li:nth-child(4) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand5").click(function () {
	$(".li-content5").toggle().addClass('animated fadeIn');
	$(".li-icon5").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".skill .custom-list li:first-child .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand6").click(function () {
	$(".li-content6").toggle().addClass('animated fadeIn');
	$(".li-icon6").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".skill .custom-list li:nth-child(2) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand7").click(function () {
	$(".li-content7").toggle().addClass('animated fadeIn');
	$(".li-icon7").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".skill .custom-list li:nth-child(3) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand8").click(function () {
	$(".li-content8").toggle().addClass('animated fadeIn');
	$(".li-icon8").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".skill .custom-list li:nth-child(4) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand9").click(function () {
	$(".li-content9").toggle().addClass('animated fadeIn');
	$(".li-icon9").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".talent .custom-list li:first-child  .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand10").click(function () {
	$(".li-content10").toggle().addClass('animated fadeIn');
	$(".li-icon10").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".talent .custom-list li:nth-child(2) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});

$(".expand11").click(function () {
	$(".li-content11").toggle().addClass('animated fadeIn');
	$(".li-icon11").toggle().addClass('animated fadeIn');
	$('.collapse-content').addClass('animated fadeIn');
	$(".talent .custom-list li:nth-child(3) .collapse-flex").toggleClass('justify-content-flex-end');
	$(this).toggleClass('rotate-close');
});


$(function () {
	$('[data-toggle="tooltip"]').tooltip()
  })