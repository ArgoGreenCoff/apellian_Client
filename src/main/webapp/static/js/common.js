// Scroll Move
function scrollMove(t,h,m){
	"use strict";
	if(h==undefined) h=0;
	if(m!=undefined && jQuery(window).width()<993) h=m;
		var o = jQuery('html, body');
	if(navigator.userAgent.toLowerCase().match(/trident/i)){
		o = jQuery('html');
	}
	o.animate({
		scrollTop:jQuery(t).offset().top-h
	},500);
}



jQuery(function ($) {
	var $body = $('body'),
		$window = $(window),
		$wrap = $('#wrap');
	
	$('#ct .animated').addClass('ani-stop');
	function scrollSection(){
		var sT = $window.scrollTop();
		var wH = $window.height();
		$('#wrap').find('.px-motion').each(function(){
			var t = $(this);
			var tT = t.offset().top;
			var tH = t.innerHeight();
			var tD = 90;

			if(t.attr('data-delay')){
				tD = t.attr('data-delay');
			}
			if(tT-wH<sT-tD && tT+tH>sT && !$('body').hasClass('loading')){
				t.find('.animated').removeClass('ani-stop');
				if(t.find('video').length && !isNaN($('video').duration)){
					t.find('video')[0].play();
				}
			}else {
				t.find('.animated').addClass('ani-stop');
				if(t.find('video').length && !isNaN($('video').duration)){
					t.find('video')[0].pause();
					t.find('video')[0].currentTime  = 0;
				}
			}
		});
	}

	var lastScrollTop = 0;
	$window.scroll(function(){
		var sT = $(this).scrollTop(),
			lnbPosY;
		
		// up&down
		if(sT>lastScrollTop){
			$body.addClass('scroll-down');
			$body.removeClass('scroll-up');
		} else {
			$body.removeClass('scroll-down');
			$body.addClass('scroll-up');
		}
		lastScrollTop = sT;	
		
		if ($(this).scrollTop()>0) {
			$body.addClass('is-scroll');
		}else {
			$body.removeClass('is-scroll scroll-up');
		}
		if ($('.px-motion').length) {
			scrollSection();
		}
	}).trigger('scroll');
	
	if($('.sub-nav').length){
		var navPosY = $('.sub-nav').offset().top;
		
		$(window).scroll(function(){
			var sct = $(this).scrollTop();
			
			if(sct >= navPosY){
				$('#ct').addClass('sub-nav-fixed');
			}else{
				$('#ct').removeClass('sub-nav-fixed');
			}
		}).trigger('scroll');
	}
    
    // password
    $('input[type=password]+button').click(function(){
        var t = $(this),
            input = t.prev('input');
        if(input.attr('type')==='text'){
            $(this).addClass('icon-invisible').removeClass('icon-visible');
            input.attr('type','password');
        } else {
            $(this).addClass('icon-visible').removeClass('icon-invisible');
            input.attr('type','text');
        }
    });
});

function inputNumberAutoComma(obj) {

    // 콤마( , )의 경우도 문자로 인식되기때문에 콤마를 따로 제거한다.
    var deleteComma = obj.value.replace(/\,/g, "");

    // 콤마( , )를 제외하고 문자가 입력되었는지를 확인한다.
    if(isFinite(deleteComma) == false) {
        alert("문자는 입력하실 수 없습니다.");
        obj.value = "";
        return false;
    }

    // 기존에 들어가있던 콤마( , )를 제거한 이 후의 입력값에 다시 콤마( , )를 삽입한다.
    obj.value = inputNumberWithComma(inputNumberRemoveComma(obj.value));
}

// 천단위 이상의 숫자에 콤마( , )를 삽입하는 함수
function inputNumberWithComma(str) {

    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
}

// 콤마( , )가 들어간 값에 콤마를 제거하는 함수
function inputNumberRemoveComma(str) {

    str = String(str);
    return str.replace(/[^\d]+/g, "");
}