/* ---------------------------------------------
Toggler v.1.0
http://www.adipalaz.com/experiments/jquery/expand.html
Requires: jQuery v1.3+
Copyright (c) 2009 Adriana Palazova
Dual licensed under the MIT (http://www.adipalaz.com/docs/mit-license.txt) and GPL (http://www.adipalaz.com/docs/gpl-license.txt) licenses
------------------------------------------------ */
jQuery.fn.toggler = function(options) {
    var o = jQuery.extend({}, jQuery.fn.toggler.defaults, options);
    var $this = jQuery(this);
    $this.wrapInner('<a style="display:block" href="#" title="Expand/Collapse" />');
    if (o.initShow) {jQuery(o.initShow).addClass('shown');}
    $this.next(o.cllpsEl + ':not(.shown)').hide();
    return this.each(function() {
      var container;
      (o.container) ? container = o.container : container = 'html';
      if ($this.next('div.shown').length) { $this.closest(container).find('.shown').show().prev().find('a').addClass('open'); }
      jQuery(this).click(function() {
        jQuery(this).find('a').toggleClass('open').end().next(o.cllpsEl)[o.method](o.speed);
        return false;
    });
});};
jQuery.fn.toggler.defaults = {
     cllpsEl : 'div.collapse',
     method : 'slideToggle',
     speed : 'fast',
     container : '', //the common container of all groups with collapsible content (optional)
     initShow : '.shown' //the initially expanded sections (optional)
};
/* ---------------------------------------------
Feel free to remove any of the following functions if you don't need it.
------------------------------------------------ */
//credit: http://jquery.malsup.com/fadetest.html
jQuery.fn.fadeToggle = function(speed, callback) {
    return this.animate({opacity: 'toggle'}, speed, function() { 
    if (jQuery.browser.msie) { this.style.removeAttribute('filter'); }
    if (jQuery.isFunction(callback)) { callback(); }
  }); 
};
jQuery.fn.slideFadeToggle = function(speed, easing, callback) {
    return this.animate({opacity: 'toggle', height: 'toggle'}, speed, easing, function() { 
    if (jQuery.browser.msie) { this.style.removeAttribute('filter'); }
    if (jQuery.isFunction(callback)) { callback(); }
  }); 
};
jQuery.fn.slideFadeDown = function(speed, callback) { 
  return this.animate({opacity: 'show', height: 'show'}, speed, function() { 
    if (jQuery.browser.msie) { this.style.removeAttribute('filter'); }
    if (jQuery.isFunction(callback)) { callback(); }
  }); 
}; 
jQuery.fn.slideFadeUp = function(speed, callback) { 
  return this.animate({opacity: 'hide', height: 'hide'}, speed, function() { 
    if (jQuery.browser.msie) { this.style.removeAttribute('filter'); }
    if (jQuery.isFunction(callback)) { callback(); }
  }); 
}; 
/* --- end of the optional code --- */
