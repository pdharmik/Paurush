jQuery(document).ready(function(){
	reskinInputCheckbox();
	$("*").click(function(){reskinInputCheckbox();});
});

function reskinInputCheckbox(){
	$("input[type='checkbox']").each(function(){
		if(!$(this).next('span').hasClass('checkbox_Span'))
		{
		$(this).hide();
		$(this).after('<span style="width:16px;" class="checkbox_Span" onclick="checkboxSpanRebrandFunction(this)"></span>');
		checkboxRebrandFunction(this);
		}
	});
	$("input[type='checkbox']").click(function(){
		checkboxRebrandFunction(this);
		return false;
	});
	setTimeout(function(){
		$("input[type='radio']").each(function(){
			if(!$(this).next('span').hasClass('radio_Span')){
				$(this).hide();
				$(this).after('<span class="radio_Span" onclick="radioSpanRebrandFunction(this)"></span>');
				radioRebrandFunction(this);
				}
			});
	});
	$("input[type='radio']").click(function(){
		var radioName=$(this).attr("name");
		$('input[name="'+radioName+'"]').each(function(){radioRebrandFunction(this);});
	});
}
function radioRebrandFunction(x)
{
	if($(x).prop('checked')==true){
		
		$(x).next('span').addClass('radio_On');
		$(x).next('span').removeClass('radio_Off');
	}
	else{
		$(x).next('span').removeClass('radio_On');
		$(x).next('span').addClass('radio_Off');
	}
}
function checkboxRebrandFunction(x)
{
	if($(x).prop('checked')==true){
		$(x).next('span').addClass('check_On');
		$(x).next('span').removeClass('check_Off');
	}
	else{
		$(x).next('span').removeClass('check_On');
		$(x).next('span').addClass('check_Off');
	}
}
function checkboxRebrandFunction(x)
{
	if($(x).prop('checked')==true){
		$(x).next('span').addClass('check_On');
		$(x).next('span').removeClass('check_Off');
	}
	else{
		$(x).next('span').removeClass('check_On');
		$(x).next('span').addClass('check_Off');
	}
}
function radioSpanRebrandFunction(x)
{
	if($(x).prev('input').is(":disabled"))
	{
		return;
	}
	//var name1=$(x).prev('input').attr("name");
	
	$('span .radio_Span').removeClass('radio_On');
	if($(x).hasClass('radio_On')){
		$(x).prev('input').attr("checked",false);
		$(x).addClass('radio_Off');
		$(x).removeClass('radio_On');
		$(x).prev('input').trigger('click');
	}
	else{
		$(x).prev('input').attr("checked",true);
		$(x).removeClass('radio_Off');
		$(x).addClass('radio_On');
		$(x).prev('input').trigger('click');
	}
	//$('input[name="'+name1+'"]').each(function(){radioRebrandFunction(this);});
}
function checkboxSpanRebrandFunction(x)
{
	if($(x).prev('input').is(":disabled"))
	{
		return;
	}
	if($(x).hasClass('check_On')){
		$(x).prev('input').attr("checked",false);
		$(x).addClass('check_Off');
		$(x).removeClass('check_On');
		$(x).prev('input').trigger('click');
	}
	else{
		$(x).prev('input').attr("checked",true);
		$(x).removeClass('check_Off');
		$(x).addClass('check_On');
		$(x).prev('input').trigger('click');
	}
}