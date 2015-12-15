
var str1=" values selected";

function MultiSelect(elementId,elementWidth,url)
{
	this.ele=elementId;
	this.eleW=elementWidth;
	this.loc=url;
	var that=this;
	var filtersTimer;
	
	this.createMultiSelect=function(){
	jQuery('#'+this.ele).append("<select id=\""+this.ele+"-options\" multiple=\"multiple\" class=\"options\" ></select><div id=\""+this.ele+"-multiSelectBody\" class=\"multiSelectBody\"><div id=\""+this.ele+"-contentZone\" class=\"contentZone\"><span id='"+this.ele+"-placeholderContent' class=\"placeholderContent\"></span>"+
	"<span class=\"dropimg down\" id=\""+this.ele+"-dropimg\"></span></div><div id=\""+this.ele+"-dropZone\" class=\"dropZone\"><ul></ul></div><p></p></div>");
	jQuery('#'+this.ele+"-multiSelectBody").css('width',this.eleW);
	$('#'+this.ele+'-dropZone').mouseout(function(){
		
		filtersTimer=setTimeout(function(){
			$('#'+that.ele+'-dropZone').hide();
			$('#'+that.ele+'-dropimg').removeClass('up');
			$('#'+that.ele+'-dropimg').removeClass('down');
			},200);		
	});
	$('#'+this.ele+'-dropZone').mouseover(function(){
	
	if(filtersTimer!=null) {
			 	clearTimeout(filtersTimer);
			 }
		$('#'+that.ele+'-dropZone').show();
	});
	};
	
	this.bindClick=function(){
	
	
	
	$('#'+this.ele+'-contentZone').live("click",function(){
	
	if($('#'+that.ele+'-dropZone').css('display')=='none')
			{
			//alert('if');	
				var arrayLoading=$('#'+that.ele+'-options').val();
				
				$('#'+that.ele+'-dropZone').css('display','block');
				$(this).removeClass('down');
				$(this).addClass('up');
				if(arrayLoading!=null)
				{
					
					$('#'+that.ele+'-dropZone ul li input').each(function(){
						
					var index=$.inArray($(this).val(),arrayLoading);
						if(index==-1)
							$(this).prop('checked',false);				
						else
							$(this).prop('checked',true);				
							
					});
				
					
				}
			}
			else{
			//alert('else')
				$('#'+that.ele+'-dropZone').css('display','none');
				$(this).removeClass('up');
				$(this).addClass('down');
			}
		}); 
	};
	
	this.createDropDownList=function(){
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
		var arr=$('#'+that.ele+'-options').val();
		
		
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
		$('#'+that.ele+'-options').find('option').each(function(i){
			if($.inArray($(this).val(),arr)!=-1){
				arrText.push($(this).text());	
			}
		});
		
		$('#'+that.ele+'-contentZone').attr('title',arrText.join(", \n"));
		if(arr.length<2){
			$('#'+that.ele+'-placeholderContent').html(arrText.join(", "));			
		}
		else{
			$('#'+that.ele+'-placeholderContent').html(arrText[0]+", "+arrText[1]+"...");
		}
		$('#'+that.ele+'-options').val(arr);
		if(arr.length==0){
			var txt="";
			$('#'+that.ele+'-options').find('option').each(function(i){
				if(i==0){
					txt=$(this).text();
					return false;
				}
			});
			$('#'+that.ele+'-contentZone').attr('title',txt);
			$('#'+that.ele+'-placeholderContent').html(txt);
		}
		
		
	});
	
	};
	
	this.createMultiSelect();
	this.bindClick();	
	this.createDropDownList();
	
}

