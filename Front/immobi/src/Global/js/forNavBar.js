function navBarActions(){
  return{
    navAnimation: function(){
        $("#SideMenuRes").css({display:'block'})
        let clicked = ($("#SideMenuRes").attr('clicked') == 'true')?true:false
        if( clicked ){
          $("#SideMenuRes").removeClass('animate__rollIn')
          $("#SideMenuRes").addClass('animate__rollOut')
          $("#SideMenuRes").attr('clicked','false')
          return
        }
        $("#SideMenuRes").removeClass('animate__rollOut')
        $("#SideMenuRes").addClass('animate__rollIn')
        $("#SideMenuRes").attr('clicked','true')
    },
    InitScrollInside: function(){
      $(".forServicesLink").click(function() {
        $([document.documentElement, document.body]).animate({
            scrollTop: $("#services").offset().top
        }, 500);
      });
      $(".forInformationsLink").click(function() {
        $([document.documentElement, document.body]).animate({
            scrollTop: $("#informations").offset().top
        }, 500);
      });
    }
  }
}
