$(function(){
    var myPlayer = videojs('my-player');
    var key = false;
    myPlayer.on('timeupdate',function(){
      var currentTime = this.currentTime();
      if(currentTime > 5 && !key){
          this.pause();
          $('#shade').show();
          $("#btn").click(playMoney)
      }
    })
    // 调用微信支付接口
    function playMoney(){
      // 支付成功后执行以下代码
       key = true;
       myPlayer.play();
       $('#shade').hide()
    }
})