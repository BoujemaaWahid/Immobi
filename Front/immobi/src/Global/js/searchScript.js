function basicInit(){
  $('#baseMenu').removeAttr('data-aos');

  $(".basicActiveLinkref").each(function(){$(this).removeClass('basicActiveLinkref')})
  $("#trouveBien").addClass('basicActiveLinkref')
  $('.ui.button').popup({popup : $('.custom.popup'),on:'click'})
  $(".ui.dropdown").dropdown()
  $(".btnClosepp").click(function(){$('body').click()})
  $(".hLink").click()
  $(window).resize(function(){
    if (window.matchMedia("(max-width: 600px)").matches) {
      $(".lieux_popup").css({width:'330px'});
    } else {
      $(".lieux_popup").css({width:'100%'});
    }
  })
}
