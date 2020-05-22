$(window).ready(function() {
	getData();
});

function getData() {
	$.ajax({
		type : "get",
		url : "/vr/leftStyle/queryAllTopAndLeftStyles",
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(res) {
			
			if (res.code == 0) {
				console.log( res.data )
				$("#topLeftStylesContainer").html(template("addGoodsAttrPriceTemplate", { topLeftStyle: res.data }));
			} else {
				layer.alert(res.msg)
			}
		}
	});
}


function selectAll(obj){
	
	if( $(obj).parents('.top').hasClass("checked") ){
		$(obj).parents('.top').removeClass("checked");
		$(obj).next('ul').find('.left').removeClass("active");
	} else {
		$(obj).parents('.top').addClass("checked");
		$(obj).next('ul').find('.left').addClass("active");
	}
	
	/*if( $(obj).next('ul').find('.active').length == 0 ){
		$(obj).parents('.top').addClass("checked");
	} else {
		$(obj).parents('.top').removeClass("checked");
	}
	
	$(obj).next('ul').find('.left').each(function(){
		if( $(this).hasClass('active') ){
			$(this).removeClass('active')
		} else {
			$(this).addClass('active')
		}
	});*/
}

function reLoad() {
	window.location.reload();
}

function addTopStyle(){
	layer.open({
		type : 2,
		title : '增加大风格',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/vr/topStyle/add' // iframe的url
	});
}

function addLeftStyle(topStyleId){
	//layer.msg(topStyleId + "添加更多");
	layer.open({
		type : 2,
		title : '增加小风格',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/vr/leftStyle/add2/' + topStyleId
	});
}

//增加房间类型
function addRoomType(){
	var leftStyleDoms = $(".left.active");
	if( leftStyleDoms.length > 1 ){
		layer.msg("一次只能为一个小风格添加房间类型");
		return false;
	} else if( leftStyleDoms.length == 0 ){
		layer.msg("请选择小风格");
		return false;
	}
	
	var topStyleId = leftStyleDoms.attr("data-topid");
	var leftStyleId = leftStyleDoms.attr("data-leftid");
	console.log(topStyleId, leftStyleId);
	layer.open({
		type : 2,
		title : '增加房间类型',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/vr/romeType/add2/' + topStyleId + "/" + leftStyleId
	});
	
}

function buildProduct(){
	var leftStyleIds = [];
	$(".left.active").each(function(){
		leftStyleIds.push($(this).attr("data-leftid"));
	});
	
	if( leftStyleIds.length == 0 ){
		layer.msg("请选择小风格");
		return false;
	}
	
	var leftIds = leftStyleIds.join(",");
	var index = layer.open({
		type : 2,
		title : '生成产品套餐',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/vr/productInfo/add/' + leftIds
	});
	layer.full(index)
}








