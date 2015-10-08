/**
 * 
 */
$(function(){
	initContainer();
	onKeyUp();
	$('#website').click(function(){
		var dataUrl="http://"+$('#dataUrl').val();
		$(".list").empty();
		if(dataUrl.trim()=="http://"){
			return false;
		}
		loadImageList(dataUrl);
	});
});
function onKeyUp(){
	$('#dataUrl').bind('keyup',function(event){
		if (event.keyCode == "13") {
           $('#website').click();
        }
	});
}
function loadImageList(dataUrl){
	var url=getRoot()+"/viewer/getUrlList";
	$.ajax({
		url:url,
		data:{
			"dataUrl":dataUrl
		},
		success:function(data){
			var len=data.length;
			for(var i=0;i<len;i++){
				var src=data[i];
				var html="<li><div><a href='"+src+"'><img src='"+src+"'></img></a></div></li>";
				$(".list").append(html);
			}
		}
	});
}
function initContainer(){
	var height=$(window).height();
	$('.container').height(height*0.8);
}