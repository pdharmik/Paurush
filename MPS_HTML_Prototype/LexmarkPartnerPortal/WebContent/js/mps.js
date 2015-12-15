$(document).ready(function () {
//// Create New Request Lightbox
	$('.lightbox').each(function () {
		var $link = $(this);
		var $dialog = $('<div></div>')
		.load($link.attr('href'))
		.dialog({
			autoOpen: false,
			title: $link.attr('title'),
			modal: true,
			resizable: false,
			closeOnEscape: true,
			position: 'center',
			draggable: false,
			width: 700,
			height: 450
		});
		$link.click(function () {
			$dialog.dialog('open');
			return false;
		});
	});
//// CONTACT & ADDRESS Lightbox
		$('.lightboxLarge').each(function () {
		var $link = $(this);
		var $dialog = $('<div></div>')
		.load($link.attr('href'))
		.dialog({
			autoOpen: false,
			title: $link.attr('title'),
			modal: true,
			resizable: false,
			closeOnEscape: true,
			position: 'center',
			draggable: false,
			width: 1015,
			height:600
		});
		$link.click(function () {
			$dialog.dialog('open');
			showAddress();
			return false;
		});
	});
//// CHL Lightbox - in page, NOT on LHS
		$('.chlModOpen').each(function () {
		var $link = $(this);
		var $dialog = $('<div></div>')
		.load($link.attr('href'))
		.dialog({
			autoOpen: false,
			title: $link.attr('title'),
			modal: true,
			resizable: false,
			closeOnEscape: true,
			position: 'center',
			draggable: false,
			width: 300,
			height:635
		});
		$link.click(function () {
			$dialog.dialog('open');
			showHiererchy();
			return false;
		});
	});
//// Solves input fields highlight issue in IE7
	if (jQuery.browser.msie === true) {
		jQuery('input, textarea, select').bind('focus', function () {
			$(this).addClass('ieFocusHack');
		}).bind('blur', function () {
			$(this).removeClass('ieFocusHack');
		});
	}
////CHL and Location popups
	$('.chl').click(function () {
		$('.leftOverlay').show(function () {
			//$(this).resizable();
		});
	});
	$('.chlClose').click(function () {
		$('.leftOverlay').hide(function () {});
	});

////Customize Coulmns
	$('#headerMenuButton').click(function () {
		$('#grid_menu').show(function () {});
	});
	$('.customizeClose').click(function () {
		$('#grid_menu').hide(function () {});
	}); 
//// Toggle Containers
$(function() {
    $(".expand").toggler();
});
//// Toggle Table
$(function() {
	$('tr.parent')
		.css("cursor","pointer")
		.attr("title","Click to expand/collapse")
		.click(function(){
			$(this).siblings('.child-'+this.id).toggle();
		});
	$('tr[@class^=child-]').hide().children('td');
});
////Disable Div Contents - Temporary script for demo only
	$('#orderRequest').click(function () {
		$("#viewFilter :input").prop("disabled", true);
		$("#viewFilter").addClass("disabledForm");
	});
//// HELP Tip on mouseover of help icons
	$(".helpIcon").qtip({
		style: {
			name: 'green',
			tip: true,
			width: 180,
			//background: '#A2D959',
			color: '#000000',
      		textAlign: 'left',
			border: {
				//color: '#6699CC',
				width: 1,
				radius: 5
			}
		},
		position: {
			corner: {
				target: 'rightMiddle',
				tooltip: 'leftTop'
			},
			adjust: {
				screen: true,
				mouse: true
			}
		}
	});
});
//// Temporary script for form validation
function checkZipCode1() {
	var zipcode = document.getElementById('zipCode').value;
	var len = zipcode.length;
	for (var i = 0; i < len; i++) {
		alert("Char at " + i + " is " + zipcode.charAt(i));
		if (zipcode.charAt(i) == 'a') {
			alert("Zip code is not numeric at " + i);
			return false;
		}
	}
	return true;
}

function checkFields() {
	var zipcode = document.getElementById('zipCode').value;
	var pattern = /[a-z]/i;
	if (zipcode.match(pattern)) {
		alert("Contains characters");
		return false;
	}
	if (document.getElementById('addrLine1').value == '') {
		alert("Field is blank");
		return false;
	}
	return true;
}
//// Temporary script for adding & removing blocks (Secondary Contact, CHL etc)
function hideAddiContacts() {
	document.getElementById('buttonContact').style.display = "block";
	document.getElementById('addiContact').style.display = "none";
}
function showAddiContacts() {
	document.getElementById('buttonContact').style.display = "none";
	document.getElementById('addiContact').style.display = "block";
}
function hideAddress() {
	document.getElementById('installedAddr').style.display = "block";
	document.getElementById('installedAddrData').style.display = "none";
}
function hideContact() {
	document.getElementById('contact').style.display = "block";
	document.getElementById('contactData').style.display = "none";
}
function hideContact2() {
	document.getElementById('contactData').style.display = "none";
	document.getElementById('contactData2').style.display = "none";
}
function showAddress() {
	document.getElementById('installedAddr').style.display = "none";
	document.getElementById('installedAddrData').style.display = "block";
}
function showContact() {
	document.getElementById('contact').style.display = "none";
	document.getElementById('contactData').style.display = "block";
}
function showContact2() {
	document.getElementById('contactData').style.display = "none";
	document.getElementById('contactData2').style.display = "block";
}
function showHiererchy() {
	document.getElementById('defaultHiererchy').style.display = "none";
	document.getElementById('changeHiererchy').style.display = "block";
}
function hideHiererchy() {
	document.getElementById('defaultHiererchy').style.display = "block";
	document.getElementById('changeHiererchy').style.display = "none";
}
function show1() {
	document.getElementById('show').style.display = "block";
}
function hide1() {
	document.getElementById('show').style.display = "none";
}
// Select Country Screen
function disable_button() {
document.getElementById("btn_continue").disabled = true;
document.getElementById("btn_continue").setAttribute("class", "button disabled");

}
function enable_button() {
var e = document.getElementById("selectCountry");
var selected_value = e.options[e.selectedIndex].value;
if(selected_value=="-Select-")
{
document.getElementById("btn_continue").disabled = true;
document.getElementById("btn_continue").setAttribute("class", "button disabled");
}
else
{
document.getElementById("btn_continue").disabled = false;
document.getElementById("btn_continue").setAttribute("class", "button");
}
}
