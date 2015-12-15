jQuery(document).ready(function() {
    function show() {
        var menu = jQuery(this);
        menu.children(".actions").slideDown();
    }
           
    function hide() { 
        var menu = $(this);
        menu.children(".actions").slideUp();
    }
             
    $(".hover-menu").hoverIntent({
        sensitivity: 1, // number = sensitivity threshold (must be 1 or higher)
        interval: 50,   // number = milliseconds for onMouseOver polling interval
        over: show,     // function = onMouseOver callback (required)
        timeout: 300,   // number = milliseconds delay before onMouseOut
        out: hide       // function = onMouseOut callback (required)
    });
                 
});
