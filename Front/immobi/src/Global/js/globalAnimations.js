function pulseAnimation(){
  $(".to_pulse_anim")
  .mouseenter(function(){$(this).addClass('animate__pulse')})
  .mouseleave(function(){$(this).removeClass('animate__pulse')})
}
function initAos(){
  AOS.init()
}
function HomeAnime(){
  placeholderWRITER($)
    return {
        init: function(){

            $('#basic_search_input').placeholderTypewriter({
              text: ["Lieu: Paris 75001","Lieu: Nanterre 92000", "Prix: 4450$","Maison S+2", "Location", "Achat", "250m²", "Description: Maison avec un petit jardin"]
            });
            $("#basic_acceuil_modal").click(function(){
              $('.modal')
              .modal('setting', 'transition', 'fly left').modal('hide')
            })
        },
        showConnectMsgInfo: function(){
          let data = ($("#connect_msg_info").attr('data') == 'true')?true:false
          if( !data ) return
          $("#connect_msg_info").css({display:''})
          $("#connect_msg_info").addClass('animate__backInLeft')
        },
        shake:function(element){
          $('#paramet').transition('shake')
        },
        hideConnectMsgInfo: function(){
          $("#connect_msg_info").addClass('animate__backOutLeft')
          $("#connect_msg_info").attr('data','false')
        }
    }
}
