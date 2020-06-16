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
    hmUpdateActiveLink: function(){
      $("#SideMenuRes").children().each(function(index){
        $(this).css({'background-color':'cornflowerblue'})
        $(this).attr('in','false')
      })

      $('#hmForRoot').css({'background-color':'#292b2c'})
      $(localStorage.getItem('active_link_hm')).addClass('active')
      if( localStorage.getItem('active_link_hm') == undefined ){localStorage.setItem('active_link_hm', '#hmForRoot')}
      $(localStorage.getItem('active_link_hm')).attr('in', 'true')
      $(localStorage.getItem('active_link_hm')).css({'background-color':'#292b2c'})
      $(localStorage.getItem('active_link_hm')).addClass('active')
      $(".hmLink").click(function(){
        localStorage.setItem('active_link_hm', '#'+$(this).attr('id'))
        $("#SideMenuRes").children().each(function(index){
          $(this).css({'background-color':'cornflowerblue'})
          $(this).attr('in','false')
        })
        $(this).attr('in', 'true')
        $(this).css({'background-color':'#292b2c'})
        $("#SideMenuRes").css({display:'none'})
        $("#SideMenuRes").attr('clicked', 'false')
      })
    },
    updateActiveLink: function(){
      $('.hsLink').click(function(){
        $('.forServicesLink').removeClass('animate__fadeIn')
        $('.forInformationsLink').removeClass('animate__fadeIn')
        $('.forServicesLink').addClass('animate__fadeOut')
        $('.forInformationsLink').addClass('animate__fadeOut')
      })
      $('.hlLink').click(function(){
        if($("#forRoot").attr('in') == 'true' && $("#hmForRoot").attr('in') == 'true')return

        $('.forServicesLink').removeClass('animate__fadeOut')
        $('.forInformationsLink').removeClass('animate__fadeOut')
        $('.forServicesLink').addClass('animate__fadeIn')
        $('.forInformationsLink').addClass('animate__fadeIn')
      })
      $("#leftMenu").children().each(function(index){
        $(this).removeClass('active')
        $(this).attr('in','false')
      })
      if( localStorage.getItem('active_link') == undefined ){ localStorage.setItem('active_link', '#forRoot')}
      $(localStorage.getItem('active_link')).attr('in', 'true')
      $(localStorage.getItem('active_link')).addClass('active')
      $(".hLink").click(function(){
        localStorage.setItem('active_link', '#'+$(this).attr('id'))
        $("#leftMenu").children().each(function(index){
          $(this).removeClass('active')
          $(this).attr('in','false')
        })
        $(this).attr('in', 'true')
        $(this).addClass('active')
      })
    },
    InitScrollInside: function(){
      $(".forServicesLink").click(function() {
        try{
          $([document.documentElement, document.body]).animate({
              scrollTop: $("#services").offset().top
          }, 500);
        }catch(e){
        }
      });
      $(".forInformationsLink").click(function() {
        try{
          $([document.documentElement, document.body]).animate({
              scrollTop: $("#informations").offset().top
          }, 500);
        }catch(e){}
      });
      $(".forSearchPart").click(function() {
        try{
          $([document.documentElement, document.body]).animate({
              scrollTop: $("#SearchPart").offset().top
          }, 500);
        }catch(e){}
      });
    }
  }
}
