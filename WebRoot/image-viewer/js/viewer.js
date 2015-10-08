/**
 * 
 */
$(function(){
	$('#website').click(function(){
		var dataUrl="http://"+$('#dataUrl').val();
		$("#imageList").empty();
		loadImageList(dataUrl);
	});
});
function loadImageList(dataUrl){
	var url="http://localhost/viewer/getUrlList";
	$.ajax({
		url:url,
		data:{
			"dataUrl":dataUrl
		},
		success:function(data){
			var len=data.length;
			for(var i=0;i<len;i++){
				var src=data[i];
				var html="<image src='"+src+"'></image>";
				$("#imageList").append(html);
			}
		}
	});
}