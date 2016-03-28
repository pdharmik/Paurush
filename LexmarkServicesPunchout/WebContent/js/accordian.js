function initAccordian(parentId)
{
	var a_header=$('#'+parentId+' .accordion-header');
	var a_content=$('#'+parentId+' .accordion-content');
	a_header.toggleClass('inactive-header');
	a_header.first().toggleClass('active-header').toggleClass('inactive-header');
	a_content.first().slideDown().toggleClass('open-content');

	a_header.click(function () {
		if($(this).is('.inactive-header')) {
			$('#'+parentId+' .active-header').toggleClass('active-header').toggleClass('inactive-header').next().slideToggle().toggleClass('open-content');
			$(this).toggleClass('active-header').toggleClass('inactive-header');
			$(this).next().slideToggle().toggleClass('open-content');
		}
		else {
			$(this).toggleClass('active-header').toggleClass('inactive-header');
			$(this).next().slideToggle().toggleClass('open-content');
		}
	});
};