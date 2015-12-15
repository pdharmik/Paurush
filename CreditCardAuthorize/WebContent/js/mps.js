jQuery(document).ready(function () {
//// Create New Request Lightbox
	/*jQuery('.lightbox').each(function () {
		alert('mps js ready');
		var $link = jQuery(this);
		var $dialog = jQuery('<div></div>').load($link.attr('href')).dialog({
			autoOpen: false,
			title: $link.attr('title'),
			modal: true,
			resizable: false,
			position: 'center',
			draggable: false,
			width: 620,
			height: 400
		});

		$link.click(function () {
			$dialog.dialog('open');
			return false;
		});
	});*/
	
//// Solves input fields highlight issue in IE7
	if (jQuery.browser.msie === true) {
		jQuery('input, textarea, select').bind('focus', function () {
			jQuery(this).addClass('ieFocusHack');
		}).bind('blur', function () {
			jQuery(this).removeClass('ieFocusHack');
		});
	}

////CHL and Location popups
	jQuery('.chl').click(function () {
		jQuery('.leftOverlay').show(function () {
			jQuery(this).resizable();
		});
	});
	jQuery('.chlClose').click(function () {
		jQuery('.leftOverlay').hide(function () {});
	});

////Customize Coulmns
	jQuery('#headerMenuButton').click(function () {
		//alert('headerMenuButton click');
		jQuery('#grid_menu').show(function () {});
	});
	jQuery('.customizeClose').click(function () {
		jQuery('#grid_menu').hide(function () {});
	});
	//jQuery('#btnBrowseAttachment').button();  
	//jQuery('#btnBrowseAttachment2').button();  
	//jQuery('#btnUpdateAttachment').button();  

//// HELP Tip on mouseover of help icons
	showToolTip('helpIcon');
});
function showToolTip(clName){
	jQuery('.'+clName).qtip({
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
				target: 'leftMiddle',
				tooltip: 'rightTop'
			},
			adjust: {
				screen: true,
				mouse: true
			}
		}
	});
}

function showToolTipList(clName){
	jQuery('.'+clName).qtip({
		style: {
			name: 'green',
			tip: true,
			width: 120,
			padding: 3,
			//background: '#A2D959',
			color: '#000000',
      		textAlign: 'center',
			border: {
				//color: '#6699CC',
				width: 0,
				radius: 3
			}
		},
		position: {
			corner: {
				target: 'rightMiddle',
				tooltip: 'leftBottom'
			},
			adjust: {
				screen: true,
				mouse: true
			}
		}
	});
}

function showToolTipListbyId(clName){
	
	//alert('tooltip by id');
    jQuery('#'+clName).qtip({
            show: { solo: true },
            style: {
                    name: 'green',
                    tip: true,
                    width: 120,
                    padding: 3,
                    //background: '#A2D959',
                    color: '#000000',
                    textAlign: 'center',
                    border: {
                            //color: '#6699CC',
                            width: 0,
                            radius: 3
                    }
            },
            position: {
                    corner: {
                            target: 'rightMiddle',
                            tooltip: 'leftBottom'
                    },
                    adjust: {
                            screen: true,
                            mouse: true
                    }
            }
    });
}

//// Temporary script for form validation
function checkZipCode1() {
	var zipcode = document.getElementById('zipCode').value;
	var len = zipcode.length;
	for (var i = 0; i < len; i++) {
		//alert("Char at " + i + " is " + zipcode.charAt(i));
		if (zipcode.charAt(i) == 'a') {
			//alert("Zip code is not numeric at " + i);
			return false;
		}
	}
	return true;
}

function checkFields() {
	var zipcode = document.getElementById('zipCode').value;
	var pattern = /[a-z]/i;
	if (zipcode.match(pattern)) {
		//alert("Contains characters");
		return false;
	}
	if (document.getElementById('addrLine1').value == '') {
		//alert("Field is blank");
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
	//alert("Overlay Comes Here");
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

function textScroll(textId) {
	if(textId!=null)
	{
		var len = 100;
		if (textId.innerHTML.length>len) {
			jQuery(textId).addClass('textScroll');
		}
		else {
			jQuery(textId).removeClass('textScroll');
		}
	}
	else return;
}