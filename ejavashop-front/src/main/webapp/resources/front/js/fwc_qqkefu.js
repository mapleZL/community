$(function() {
			
			var $ad=$('.qq-online,.mian-img');
			var $closeAd=$('.closeAd');
			var $mian=$('.mian-img');

			/*$(window).scroll(function(event) {
				
				var sT=$(this).scrollTop();
				
				$ad.stop().animate({
					top:sT+50
					
				}, 500);

			});*/

			$closeAd.click(function(event) {
				$(this).parent().hide(500);

				$mian.stop().animate({
					'right': 0,
					'opacity':1


			}, 500)
				
			});

			$mian.click(function(event) {
				$ad.stop().show(500);
				$mian.stop().animate({
					'right': 110,
					'opacity':0

			}, 500)
				
			});
		});