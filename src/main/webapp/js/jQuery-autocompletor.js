
jQuery( document ).ready(function() {

jQuery("#countryName").keyup(function(){
console.log("onkey up");
    var data = [		{"label":"India", "actor":"India"},
		{"label":"UnitedStates", "actor":"UnitedStates"}];

$("#countryName").autocomplete({
    source : data,
   /* source: function(request, response) {

        $.ajax({
            dataType: "json",
            type : 'Get',
            url: 'yourURL',
            success: function(data) {
                $('input.suggest-user').removeClass('ui-autocomplete-loading');  // hide loading image

                response($.map(data, function(item) {
                    // your operation on data
                }));
            },
            error: function(data) {
                $('input.suggest-user').removeClass('ui-autocomplete-loading');
            }
        });
    },*/
    minLength: 2,
    open: function() {
        console.log("Open");

    },
    close: function() {

    },
    focus:function(event, ui) {

    },
    select: function(event, ui) {

    }
});

});

    });