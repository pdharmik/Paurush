jQuery(document).ready(function() {
    var selector = jQuery("#community-selector-chevron");
    var list = jQuery("#community-list");
    var down = false;
    var moused1 = false;
    var moused2 = false;

    function show() {
        if (!down) {
            list.slideDown(100);
            down = true;
        }
        return false;
    }

    function hide() {
        list.slideUp(100);
        down = false;
    }

    function hover1() {
        moused1 = true;
        selector.addClass("highlight");
    }

    function hover2() {
        moused2 = true;
    }

    function unhover1() {
        moused1 = false;
        if (!down) {
            selector.removeClass("highlight");
        }
        if (!moused2) {
            hide();
            selector.removeClass("highlight");
        }
    }

    function unhover2() {
        moused2 = false;
        if (!moused1) {
            hide();
            selector.removeClass("highlight");
        }
    }

    selector.click(show);

    selector.hoverIntent({
        sensitivity: 1,
        interval: 50,
        over: hover1,
        timeout: 1000,
        out: unhover1
    });
    list.hoverIntent({
        sensitivity: 1,
        interval: 50,
        over: hover2,
        timeout: 1000,
        out: unhover2
    });
});