/**
 * 
 */

function toggleSlide(selector, direction)
	{
		$(".deviceStatusSubMenu").each(function(){
			var iD = '#'+$(this).attr('id');
			var parentId = '#'+$(this).parent().attr('id');
			if($(this).css('display') == 'block' &&  iD != selector)
			{
				$(this).hide();
				toggleClass(parentId, 'openSub', false);
			}
		});
		$( selector ).toggle( "slide",{direction : (direction != null ? direction : '')} );
	}
	
	function toggleClass(selector, cssClass, flag){
		if (flag != null){
			if(flag && !$(selector).hasClass(cssClass)){
				$(selector).addClass(cssClass);
			}
			if(!flag){	
				$(selector).removeClass(cssClass);
			}
			return;
		}
		else if($(selector).hasClass(cssClass)){
			$(selector).removeClass(cssClass);
		}
		else{
			$(selector).addClass(cssClass);
		}
	}