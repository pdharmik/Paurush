// jQuery Alert Dialogs Plugin
//
// Version 1.1
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 14 May 2009
//
// Visit http://abeautifulsite.net/notebook/87 for more information
//
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// History:
//
//		1.00 - Released (29 December 2008)
//
//		1.01 - Fixed bug where unbinding would destroy all resize events
//
// License:
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//
(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .01,                // transparency level of overlay
		overlayColor: '#FFF',               // base color of overlay
		draggable: false,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;' +  global_button_message.ok + '&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;' +  global_button_message.cancel + '&nbsp;', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		iOkButton: '&nbsp;OK&nbsp;',        // text for the customize OK button
		iCancelButton: '&nbsp;Cancel&nbsp;',// text for the customize Cancel button
		iPosition:'center',					// position for the customize button location
		
		// Public methods
		
		alert: function(message, title, callback) {
			if( title == null ) title = 'Alert';
			$.alerts._show(title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title, callback) {
			if( title == null ) title = 'Prompt';
			$.alerts._show(title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		
		iConfirm: function(message, title, buttonY,buttonN,position,callback) {
			if( title == null ) title = 'Confirm';
			$.alerts.iOkButton = buttonY;
			$.alerts.iCancelButton = buttonN;
			if(position!="" && position!=null)
				$.alerts.iPosition = position;
			$.alerts._show(title, message, null, 'iConfirm', function(result) {
				if( callback ) callback(result);
			});
		},
		// Private methods
		
		_show: function(title, msg, value, type, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<table><tr><td><div id="popup_message"></div></td></tr></table>' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			var pos = ($.browser.msie && parseInt($.browser.version.major) <= 6 ) ? 'absolute' : 'fixed'; 
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<div id="popup_panel"><a href="javascript:void(0);" class="button" id="popup_ok"><span>'+ $.alerts.okButton +'</span></a></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
						return false;
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel"><a href="javascript:void(0);" class="button simplelink" id="popup_ok"><span>'+ $.alerts.okButton +'</span></a>&nbsp;<a href="javascript:void(0);" class="button simplelink" id="popup_cancel" ><span>' + $.alerts.cancelButton + '</span></a></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
						return false;
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
						return false;
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
						return false;
					});
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
				case 'iConfirm':
					$("#popup_message").after('<div id="popup_panel" style="text-align:'+$.alerts.iPosition+';"><a href="javascript:void(0);" class="button simplelink" id="popup_ok"><span>'+ $.alerts.iOkButton +'</span></a>&nbsp;<a href="javascript:void(0);" class="button simplelink" id="popup_cancel" ><span>' + $.alerts.iCancelButton + '</span></a></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
						return false;
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
						return false;
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
						return false;
					});
				break;
			}
			
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
			if( $.browser.msie && parseInt($.browser.version.major) <= 6 ){
				$("#popup_overlay").css({
					width: $("#popup_container").outerWidth()+'px',
					height: $("#popup_container").outerHeight()+'px'
				});
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					if($.browser.msie && parseInt($.browser.version.major) <= 6){
						$("BODY").append('<iframe id="popup_overlay" src="javascript:\'<html></html>\';" scrolling="no" frameborder="0" />');
						$("#popup_overlay").css({
							position: 'absolute',
							zIndex: 99998,
							background: $.alerts.overlayColor,
							opacity: $.alerts.overlayOpacity
						});
						$("BODY").append('<div id="popup_overlay_one" />');
						$("#popup_overlay_one").css({
							position: 'absolute',
							zIndex: 9999,
							top: '0px',
							left: '0px',
							width: '100%',
							height: $(document).height(),
							background: $.alerts.overlayColor,
							opacity: $.alerts.overlayOpacity
						});
						$("select").each(function(){
							$(this).attr("disabled","disabled");
						});
					}else{
						$("BODY").append('<iframe id="popup_overlay" src="javascript:\'<html></html>\';" scrolling="no" frameborder="0" />');
						$("#popup_overlay").css({
							position: 'absolute',
							zIndex: 99998,
							top: '0px',
							left: '0px',
							width: '100%',
							height: $(document).height(),
							background: $.alerts.overlayColor,
							opacity: $.alerts.overlayOpacity
						});
					}
				break;
				case 'hide':
					$("#popup_overlay").remove();
					if($.browser.msie && parseInt($.browser.version.major) <= 6){
						$("#popup_overlay_one").remove();
						$("select").each(function(){
							$(this).removeAttr("disabled");
						});
					}
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.browser.msie && parseInt($.browser.version.major) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			if( $.browser.msie && parseInt($.browser.version.major) <= 6 ){
				$("#popup_overlay").css({
					top: top + 'px',
					left: left + 'px'
				});
			}else{
				$("#popup_overlay").height( $(document).height() );
			}
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
	
	// Shortuct functions
	jAlert = function(message, title, callback) {
		$.alerts.alert(message, title, callback);
	}
	
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
		
	jPrompt = function(message, value, title, callback) {
		$.alerts.prompt(message, value, title, callback);
	};
	
	jiConfirm = function(message, title, buttonY,buttonN,position,callback) {
		$.alerts.iConfirm(message, title, buttonY,buttonN,position,callback);
	};
})(jQuery);