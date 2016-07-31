$(document).ready(function(){
    $("input").focus(function(){
        $(this).css("background-color", "#D1F3C0");
    });
    $("input").blur(function(){
        $(this).css("background-color", "#ffffff");
    });
});

$(document).ready(function() {
    $('.backgroundBlur > div').blurr({offsetX: -100, offsetY: -100, height:800});
});
        
            
            

