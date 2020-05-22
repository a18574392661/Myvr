$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var typeId = $("#typeId").val(  );
	
	if( !typeId ){
		layer.msg("请选择到三级分类");
		return false;
	}
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/stock/stockGoods/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			goodsName : {
				required : true
			},
			stock : {
				required : true
			}
		},
		messages : {
			goodsName : {
				required : icon + "请输入商品名称"
			},
			stock : {
				required : icon + "请输入商品库存"
			}
		}
	})
}