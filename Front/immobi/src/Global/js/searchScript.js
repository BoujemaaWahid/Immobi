function basicInit(){
  $(".basicActiveLinkref").each(function(){
    $(this).removeClass(['basicActiveLinkref', 'active'])
    $("#trouveBien").addClass(['basicActiveLinkref', 'active'])
  })
  $('#baseMenu').removeAttr('data-aos');
  $(".forServicesLink").css({display:'none'})
  $(".forInformationsLink").css({display:'none'})
  $('.ui.button').popup({popup : $('.custom.popup'),on:'click'})
  $(".ui.dropdown").dropdown({forceSelection: true})
  $(".btnClosepp").click(function(){$('body').click()})
  $("#search_filter_menu").click(function(){$(".ui.sidebar").sidebar('show');$('.ui.button').popup({popup : $('.custom.popup'),on:'click'})})
  $("#hideSideBar").click(function(){$(".ui.sidebar").sidebar('hide');$('.ui.button').popup({popup : $('.custom.popup'),on:'click'})})
 $(window).resize(function(){
   if (window.matchMedia("(max-width: 999px)").matches){
    $("#search_filter_menu").css({display:'flex'})
   }else if(window.matchMedia("(min-width: 1000px)").matches){
    $("#search_filter_menu").css({display:'none'})
   }
 })
}
