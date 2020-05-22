$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/stock/stockIn/save",
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
			goodsId : {
				required : true
			},
			num : {
				required : true
			},
			price : {
				required : true
			}
		},
		messages : {
			goodsId : {
				required : icon + "请选择入库的商品"
			},
			num : {
				required : icon + "请选择入库数量"
			},
			price : {
				required : icon + "请选择入库商品单价"
			}
		}
	})
}