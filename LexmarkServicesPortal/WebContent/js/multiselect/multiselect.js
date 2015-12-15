/*$(document).ready(function(){
	$('.dropZone').live("mouseleave",function(){
		$(this).hide();
		$(this).siblings('.contentZone').children('.dropimg').removeClass('up');
		$(this).siblings('.contentZone').children('.dropimg').removeClass('down');
	});
	
	$('.dropimg').live("click",function(){
		if($(this).parent('.contentZone').siblings('.dropZone').css('display')=='none'){
			$(this).parent('.contentZone').siblings('.dropZone').css('display','block');
			$(this).removeClass('down');
			$(this).addClass('up');
		}
		else{
			$(this).parent('.contentZone').siblings('.dropZone').css('display','none');
			$(this).removeClass('up');
			$(this).addClass('down');
		}
	}); 
});

/*
var str1=" values selected";

function MultiSelect(elementId,elementWidth,url)
{
	this.ele=elementId;
	this.eleW=elementWidth;
	this.loc=url;
	this.data=[];
}

MultiSelect.prototype.createMultiSelect=function(){
	jQuery('#'+this.ele).append("<div id="+this.ele+"-multiSelectBody class=\"multiSelectBody\"><input type=\"hidden\" id=\""+this.ele+"-multiSelect\"/><div id="+this.ele+"-contentZone class=\"contentZone\"><span id="+this.ele+"-placeholderContent class=\"placeholderContent\"></span>"+
	"<span class=\"dropimg down\"></span></div><div id="+this.ele+"-dropZone class=\"dropZone\"><ul></ul></div><p></p></div>");
	jQuery('#'+this.ele+"-multiSelectBody").css('width',this.eleW);	
	
	
	
};

MultiSelect.prototype.createDropDownList=function(){
	var dataList=this.loc;
	for(i=0;i<dataList.length;i++){
		jQuery('#'+this.ele+"-dropZone ul").append("<li><input id="+this.ele+"-listValue"+i+
		" class=\"listValue\" type=\"checkbox\" value="+dataList[i].value+" />"+
		dataList[i].displayValue+"</li>");
	}
	var element=this.ele;
	var arrayList=this.data;
	$('#'+this.ele+' input').on('click',function(){
		var contentItem=$(this).val();
		var index=$.inArray(contentItem,arrayList);
		if(index==-1){
			arrayList.push(contentItem);
		}
		else{
			arrayList.splice(index, 1);
		}
		if(arrayList.length<4){
			$(this).parent().parent().parent().siblings('.contentZone').children('.placeholderContent').html(arrayList.join(","));
		}
		else{
			$(this).parent().parent().parent().siblings('.contentZone').children('.placeholderContent').html(arrayList.length+str1);
		}
		$('#'+element+'-multiSelect').val("["+arrayList+"]");
	});
	
	
	
};*/

/*
function MultiSelect(selectId,w)
{
	this.ele=selectId;
	this.eleW=w;
}

MultiSelect.prototype.createMultiSelect=function(){
	//jQuery('#'+this.ele).hide();
	jQuery('#'+this.ele).after("<div id="+this.ele+"-multiSelectBody class=\"multiSelectBody\"><div id="+this.ele+"-contentZone class=\"contentZone\"><span id="+this.ele+"-placeholderContent class=\"placeholderContent\"></span>"+
	"<span class=\"dropimg down\"></span></div><div id="+this.ele+"-dropZone class=\"dropZone\"><ul></ul></div><p></p></div>");
	jQuery('#'+this.ele+"-multiSelectBody").css('width',this.eleW);	
	
	
	
};

MultiSelect.prototype.createDropDownList=function(){
	
	
	var e=this.ele;
	$('#'+this.ele+' option').each(function(i){
		$('#'+e+"-dropZone ul").append("<li><input id="+e+"-listValue"+i+" class=\"listValue\" type=\"checkbox\" value="+$(this).val()+" />"+$(this).text()+"</li>");
	});
	
	var arr=[];
	$('#'+e+'-dropZone input:checkbox').on('click',function(){
		var v=$(this).val();
		
		if($(this).is(':checked')){
			arr.push(v);
		}else{
			arr=$.grep(arr,function(n,i){
				return (n!=v);
			});
		}
		
		
		$('#'+e).val(arr);
	});
	
	
	
};*/


$(document).ready(function(){
	$('.dropZone').live("mouseleave",function(){
		$(this).hide();
		$(this).siblings('.contentZone').children('.dropimg').removeClass('up');
		$(this).siblings('.contentZone').children('.dropimg').removeClass('down');
	});
	
	$('.dropimg').live("click",function(){
		if($(this).parent('.contentZone').siblings('.dropZone').css('display')=='none')
		{
			
			var arrayLoading=$(this).parent().parent().siblings('.options').val();
			$(this).parent().parent('.contentZone').siblings('.dropZone').css('display','block');
			$(this).removeClass('down');
			$(this).addClass('up');
			if(arrayLoading!=null)
			{
				$(this).parent('.contentZone').siblings('.dropZone').children('ul').children('li').each(function(){
                $(this).children('input').prop('checked',false);
            
                               });
			
				for(var i=0;i<arrayLoading.length;i++)
				{
					$("input:checkbox[value=\""+arrayLoading[i]+"\"]").prop('checked', true);
				}
			}
		}
		else{
			$(this).parent().parent('.contentZone').siblings('.dropZone').css('display','none');
			$(this).removeClass('up');
			$(this).addClass('down');
		}
	}); 
	
	$('.contentZone').live("click",function(){
		if($(this).siblings('.dropZone').css('display')=='none')
			{
				var arrayLoading=$(this).parent().siblings('.options').val();
				$(this).siblings('.dropZone').css('display','block');
				$(this).children('.dropimg').removeClass('down');
				$(this).children('.dropimg').addClass('up');
				if(arrayLoading!=null)
				{
					$(this).siblings('.dropZone').children('ul').children('li').each(function(){
	                $(this).children('input').prop('checked',false);
	            
	                               });
				
					for(var i=0;i<arrayLoading.length;i++)
					{
						$("input:checkbox[value=\""+arrayLoading[i]+"\"]").prop('checked', true);
					}
				}
			}
		else{
			$(this).siblings('.dropZone').css('display','none');
			$(this).children('.dropimg').removeClass('up');
			$(this).children('.dropimg').addClass('down');
		}
	});
});

var str1=" values selected";

function MultiSelect(elementId,elementWidth,url)
{
	this.ele=elementId;
	this.eleW=elementWidth;
	this.loc=url;
	this.createMultiSelect();
	this.createDropDownList();
}

MultiSelect.prototype.createMultiSelect=function(){
	jQuery('#'+this.ele).append("<select id=\""+this.ele+"-options\" multiple=\"multiple\" class=\"options\" ></select><div id=\""+this.ele+"-multiSelectBody\" class=\"multiSelectBody\"><div id=\""+this.ele+"-contentZone\" class=\"contentZone\"><span id='"+this.ele+"-placeholderContent' class=\"placeholderContent\"></span>"+
	"<span class=\"dropimg down\"></span></div><div id=\""+this.ele+"-dropZone\" class=\"dropZone\"><ul></ul></div><p></p></div>");
	jQuery('#'+this.ele+"-multiSelectBody").css('width',this.eleW);
	
};

MultiSelect.prototype.getValue=function(){
return JSON.stringify($('#'+this.ele+'-options').val());
//return ($('#'+this.ele+'-options').val());
};

MultiSelect.prototype.createDropDownList=function(){
	var dataList=this.loc;
	$('#'+this.ele+'-placeholderContent').html(dataList[0].displayValue);
	for(var i=0;i<dataList.length;i++){
			$('#'+this.ele+'-options').append("<option value=\""+dataList[i].value+"\">"+dataList[i].displayValue+"</option>");
				
	}
	for(var i=1;i<dataList.length;i++){
		jQuery('#'+this.ele+"-dropZone ul").append("<li><input id=\""+this.ele+"-listValue"+i+
				"\" class=\"listValue\" type=\"checkbox\" value=\""+dataList[i].value+"\" />"+
				dataList[i].displayValue+"</li>");
	}
	
	$('#'+this.ele+' input').on('click',function(){
		var arr=$(this).parent('li').parent('ul').parent('.dropZone').parent('.multiSelectBody').siblings('.options').val();
		
		
		if(arr==null)
		{
			arr=[];
		}
		
		var contentItem=$(this).val();
		var index=$.inArray(contentItem,arr);
		
		if(index==-1){
			arr.push(contentItem);
		}
		else{
			arr.splice(index, 1);
		}
		//This is for showing text in the span...
		var arrText=[];
		$(this).parent().parent().parent().parent().siblings('.options').find('option').each(function(i){
			if($.inArray($(this).val(),arr)!=-1){
				arrText.push($(this).text());	
			}
		});
		
		
		if(arr.length<3){
			$(this).parent().parent().parent().siblings('.contentZone').children('.placeholderContent').html(arrText.join(","));
		}
		else{
			$(this).parent().parent().parent().siblings('.contentZone').children('.placeholderContent').html(arrText.length+str1);
		}
		$(this).parent().parent().parent().parent().siblings('.options').val(arr);
		if(arr.length==0){
			var txt="";
			$(this).parent().parent().parent().parent().siblings('.options').find('option').each(function(i){
				if(i==0){
					txt=$(this).text();
					return false;
				}
			});
			
			$(this).parent().parent().parent().siblings('.contentZone').children('.placeholderContent').html(txt);
		}
		
		
	});
	
};

