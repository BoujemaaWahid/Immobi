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
    forRecherche: function(){
      let id = "#trouveBien2"
      if( $("#leftMenu").is(':visible') ){
        id = "#trouveBien"
          localStorage.setItem('active_link', id)
          let t = $(this).attr('id')
          $("#leftMenu").children().each(function(index){
            $(this).removeClass(['active','basicActiveLinkref'])
            $(this).attr('in','false')
            $(this).attr('in', 'true')
            $(this).addClass(['active','basicActiveLinkref'])
          })
        $(id).attr('in', 'true')
        $(id).addClass(['active','basicActiveLinkref'])
        return
      }
      localStorage.setItem('active_link_hm', id)
        $("#SideMenuRes").children().each(function(index){
          $(this).css({'background-color':'cornflowerblue'})
          $(this).attr('in','false')
        })
        $(id).attr('in', 'true')
        $(id).css({'background-color':'#292b2c'})
        $("#SideMenuRes").css({display:'none'})
        $("#SideMenuRes").attr('clicked', 'false')
    },
    updateActiveLink: function(){
      $(".hLink").click(function(){
        localStorage.setItem('active_link', '#'+$(this).attr('id'))
        let t = $(this).attr('id')
        $("#leftMenu").children().each(function(index){
          $(this).removeClass(['active','basicActiveLinkref'])
          $(this).attr('in','false')
        })
        if ( t == 'forRoot' || t == 'hmForRoot' ){ $('.hlLink').click() }
        $(this).attr('in', 'true')
        $(this).addClass(['active','basicActiveLinkref'])
      })

      $(".FC").click(function(){
        $(".forServicesLink").css({display:'none'})
        $(".forInformationsLink").css({display:'none'})
      })
      $(".forSearchPart").click(function(){
        $(".forServicesLink").css({display:'block'})
        $(".forInformationsLink").css({display:'block'})
      })

      $("#leftMenu").children().each(function(index){
        $(this).removeClass('active')
        $(this).attr('in','false')
      })

      if( localStorage.getItem('active_link') == undefined ){ localStorage.setItem('active_link', '#orRoot')}
      $(localStorage.getItem('active_link')).attr('in', 'true')

    },
    InitScrollInside: function(){

      $(".hero-wrap").mouseenter(function(e){
        $(".basicActiveLinkref").each(function(e){$(this).removeClass(['basicActiveLinkref', 'active'])})
        $("#forRoot").addClass(['basicActiveLinkref', 'active'])
      })
      $("#services").mouseenter(function(e){
        $(".basicActiveLinkref").each(function(e){$(this).removeClass(['basicActiveLinkref', 'active'])})
        $("#forServices").addClass(['basicActiveLinkref', 'active'])

      })
      $("#informations").mouseenter(function(e){
        $(".basicActiveLinkref").each(function(e){$(this).removeClass(['basicActiveLinkref', 'active'])})
        $("#forInformations").addClass(['basicActiveLinkref', 'active'])

      })
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
