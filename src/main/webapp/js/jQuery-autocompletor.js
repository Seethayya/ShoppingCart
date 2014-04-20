
jQuery( document ).ready(function() {

jQuery("#countryName").keyup(function(){
    var countryName = jQuery(this).val();
    var data = ["India", "UnitedStates", "Indonasia", "Indiana"];

$("#countryName").autocomplete({
  //  source : data,
    source: function(request, response) {
        $.ajax({
            dataType: "json",
            type : 'Get',
            url: 'getCountry',
            data:'country='+countryName,
            success: function(data) {
                data = data.countryList;
                console.log("Data:"+data) ;
               // return data;
                $('input.suggest-user').removeClass('ui-autocomplete-loading');  // hide loading image
                response($.map(data, function(item) {
                     return {
                        label: item,
                        value: item
                    };
                }));
            },
            error: function(data) {
                $('input.suggest-user').removeClass('ui-autocomplete-loading');
            }
        });
    },
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