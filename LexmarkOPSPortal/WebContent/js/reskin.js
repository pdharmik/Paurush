jQuery(document).ready(function(){
	reskinInputCheckbox();
	$("*").click(function(){reskinInputCheckbox();radioReset();checkboxReset();});
	$("#btnBrowse").click(function(){
		$(this).prev('.requestsUploader').trigger('click');
	});
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
	if($(x).hasClass('radio_Off')){
		$(x).prev('input').attr("checked",true);
		$(x).removeClass('radio_Off');
		$(x).addClass('radio_On');
		$(x).prev('input').trigger('click');
	}
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

function radioReset()
{
	$("input[type='radio']").each(function(){
		radioRebrandFunction(this);
	});
}
function checkboxReset()
{
	$("input[type='checkbox']").each(function(){
		checkboxRebrandFunction(this);
	});
}