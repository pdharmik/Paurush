$('.dhx_page div').live("click",function(){rebrandPagination();});
	function rebrandPagination()
	{
		
		$('.dhx_page div').each(function(){
			if($(this).text()==">>")
			{
			$(this).html('');
			$(this).addClass('ui_icon_sprite next-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 0px 3px 5px');
			}
			else if($(this).text()==">|")
			{
			$(this).html('');
			$(this).addClass('ui_icon_sprite last-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 0px 3px 5px');
			}
			else if($(this).text()=="<<")
			{
			$(this).html('');
			$(this).addClass('ui_icon_sprite previous-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 5px 3px 0px');
			}
			else if($(this).text()=="|<")
			{
			$(this).html('');
			$(this).addClass('ui_icon_sprite first-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 5px 3px 0px');
			}
			else{
				//$(this).parent('.dhx_page').css('margin','3px 0px');
			}
		});
		
		}