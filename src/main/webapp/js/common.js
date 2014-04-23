function processAndExecuteScript(targetDivId, scriptDivId, formIds, url, isAsync) {

    console.log("processAndExecuteScript"+formIds.toString().split(","));
    console.log("processAndExecuteScript"+jQuery('#' + formIds).serialize());
    var data= jQuery('#' + formIds).serialize();
    /*for (var formId in formIds.toString().split(","))    {
        console.log("--------------formid"+formId);
        data = data + jQuery('#' + formId).serialize();
    }*/
    console.log("---data"+data);
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function(data) {
            jQuery("#" + targetDivId).html(data);
            //parse script and execute
            parseScript(scriptDivId);
        },
       // dataType: "html",
        async: isAsync
    });
}

//http://www.webdeveloper.com/forum/showthread.php?138830-Run-JavaScript-after-AJAX-load
function parseScript(scriptId) {
    var source = document.getElementById(scriptId);
    var scripts = new Array();

    // Strip out tags
    while (source.indexOf("<script") > -1 || source.indexOf("</script") > -1) {
        var s = source.indexOf("<script");
        var s_e = source.indexOf(">", s);
        var e = source.indexOf("</script", s);
        var e_e = source.indexOf(">", e);

        // Add to scripts array
        scripts.push(source.substring(s_e + 1, e));
        // Strip from source
        source = source.substring(0, s) + source.substring(e_e + 1);
    }

    // Loop through every script collected and eval it
    for (var i = 0; i < scripts.length; i++) {
        try {
            eval(scripts[i]);
        }
        catch(ex) {
            // do what you want here when a script fails
        }
    }

    // Return the cleaned source
    return source;
}

function submitForm(formObj, actionName) {
    console.log(":submitform:"+formObj+" "+actionName)
    //var formObj = document.getElementById(formId);
    formObj.action = actionName;
    formObj.submit();
}