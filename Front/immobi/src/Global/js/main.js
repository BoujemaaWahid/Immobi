function navBarHover(){
  $('.item')
  .mouseenter(function(){
    let isIn = ( $(this).attr('in') == 'true')?true:false
    if( !isIn ){
      $(this).addClass('active')
    }
  })
  .mouseleave(function(){
    let isIn = ( $(this).attr('in') == 'true')?true:false
    if( !isIn ){
      $(this).removeClass('active')
    }
  })
}


