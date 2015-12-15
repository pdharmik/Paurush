$('.dhx_page div').live("click",function(){setTimeout(function(){
	rebrandPagination();},10);
});
	function rebrandPagination()
	{
		
		$('.dhx_page div').each(function(){
			if($(this).text()==">>")
			{
				//alert();
			$(this).html('');
			$(this).addClass('ui-icon next-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 0px 3px 5px');
			}
			else if($(this).text()==">|")
			{
			$(this).html('');
			$(this).addClass('ui-icon last-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 0px 3px 5px');
			}
			else if($(this).text()=="<<")
			{
			$(this).html('');
			$(this).addClass('ui-icon previous-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 5px 3px 0px');
			}
			else if($(this).text()=="|<")
			{
			$(this).html('');
			$(this).addClass('ui-icon first-page-icon');
			//$(this).parent('.dhx_page').css('margin','3px 5px 3px 0px');
			}
			/*else{
				$(this).parent('.dhx_page').css('margin','3px 0px');
			}*/
		});
		
		}