
var str1=" values selected";

function MultiSelect(multiSelectObj)
{
	this.ele=multiSelectObj.elementId;
	this.eleW=multiSelectObj.elementWidth;
	this.list=multiSelectObj.dataList;
	var that=this;
	
	var filtersTimer;
	
	this.createMultiSelect=function(){
			jQuery('#'+this.ele).append("<select id=\""+this.ele+"-options\" multiple=\"multiple\" class=\"options\" ></select><div id=\""+this.ele+"-multiSelectBody\" class=\"multiSelectBody\"><div id=\""+this.ele+"-contentZone\" class=\"contentZone\"><span id='"+this.ele+"-placeholderContent' class=\"placeholderContent\"></span>"+
					"<span class=\"dropimg down\" id=\""+this.ele+"-dropimg\"></span></div><div id=\""+this.ele+"-dropZone\" class=\"dropZone\"><ul></ul></div><p></p></div>");
					jQuery('#'+this.ele+"-contentZone").css('width',this.eleW);
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
						if($('#'+that.ele+'-multiSelectBody').attr('disabled')=='disabled'){							
							$('#'+that.ele+'-dropZone').css('display','none');
							$(this).removeClass('up');
							$(this).addClass('down');
						}
						if($('#'+that.ele+'-dropZone').css('display')=='none'){
								var arrayLoading=$('#'+that.ele+'-options').val();
								$('#'+that.ele+'-dropZone').css('display','block');
								$(this).removeClass('down');
								$(this).addClass('up');
								if(arrayLoading!=null){
									$('#'+that.ele+'-dropZone ul li input').each(function(){
										var index=$.inArray($(this).val(),arrayLoading);
										if(index==-1)
											$(this).prop('checked',false);				
										else
											$(this).prop('checked',true);				
									});}
						}else{
								$('#'+that.ele+'-dropZone').css('display','none');
								$(this).removeClass('up');
								$(this).addClass('down');
						}
					}); 
				};
	
	this.createDropDownList=function(){
				$('#'+this.ele+'-placeholderContent').html(this.list[0].displayValue);
				for(var i=0;i<this.list.length;i++){
					if(this.list[i].value != "Select All" && this.list[i].value != "NoUtilization")
					{	//console.log(this.list[i].value);
						$('#'+this.ele+'-options').append("<option value=\""+this.list[i].value+"\">"+this.list[i].displayValue+"</option>");
										
					}
					if(this.list[i].value === "NoUtilization")
					{
						$('#'+this.ele+'-options').append("<option value=\""+this.list[i].value+"\">"+this.list[i].displayValue+"</option>");

					}
				}
				for(var i=1;i<this.list.length;i++){
					if(this.list[i].value != "NoUtilization")
					{					
					jQuery('#'+this.ele+"-dropZone ul").append("<li><input id=\""+this.ele+"-listValue"+i+
							"\" class=\"listValue\" type=\"checkbox\" value=\""+this.list[i].value+"\" />"+
							this.list[i].displayValue+"</li>");
					}
					if(this.list[i].value === "NoUtilization")
					{
						jQuery('#'+this.ele+"-dropZone ul").append("<li class=\"noDisplay\"><input id=\""+this.ele+"-listValue"+i+
								"\" class=\"listValue\" type=\"checkbox\" value=\""+this.list[i].value+"\" />"+
								this.list[i].displayValue+"</li>");
					}
				}
				$('#'+this.ele+' input').on('click',function(){
					var arr=$('#'+that.ele+'-options').val();
					var contentItem=$(this).val();
					if(arr==null || contentItem == "Select All"){
						arr=[];
					}
					if(contentItem == "Select All"){
						if( $(this).prop('checked') == true )
						{
							$('#'+that.ele+'-options').children('option').each(function(){
							var content = $(this).val();
							if(content != "")
								
								arr.push(content);
																
							});	
							arr.push("NoUtilization");
							
							
							$(this).parent('li').parent('ul').children('li').each(function(){
								$(this).children('input[type=\'checkbox\']').prop('checked', true);
							});
						}
						else{
							arr = [];
							$(this).parent('li').parent('ul').children('li').each(function(){
								$(this).children('input[type=\'checkbox\']').prop('checked', false);
							});

						}
					}
					else{
						if($('#'+that.ele+'-listValue1').val() == "Select All")
							$('#'+that.ele+'-listValue1').prop('checked', false);
						var index=$.inArray(contentItem,arr);
						if(index==-1){
							arr.push(contentItem);
						}else{
							arr.splice(index, 1);
						}
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
					}else{
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
			//Need to fire an event if a value is selected or deselected LBS 1.5
			that.clickCheckBoxFunction();
			});
	
	};
	
	this.getValue=function(){
		return $('#'+that.ele+'-options').val();
	};
	this.setClickFunction=function(func){
		this.clickCheckBoxFunction=(typeof func == 'function'? func:function(){});
	};
	this.clear=function(){
		var txt="";
		$('#'+that.ele+'-options').val([]);
		
		
		for(var i=0;i<this.list.length;i++){
			$('#'+this.ele+'-listValue'+i).prop('checked', false);
		}
		
		$('#'+that.ele+'-options').find('option').each(function(i){
			if(i==0){
				txt=$(this).text();
				return false;
			}
			
		});
		$('#'+that.ele+'-contentZone').attr('title',txt);
		$('#'+that.ele+'-placeholderContent').html(txt);		
	};
	this.createMultiSelect();
	this.bindClick();	
	this.createDropDownList();
	
}

