$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	
	if( !$("#musicUrl").val() ){
		layer.msg("背景音乐必须上传");
		return false;
	}
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/vr/productInfo/save",
		data : {
			name: $("#name").val(), 
			musicUrl: $("#musicUrl").val(), 
			logo: $("#logo").val(), 
			leftStyleIds : $("#leftStyleIds").val(), 
			colorStyleIds : $("#colorStyleIds").val()
		},
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
			name : {
				required : true
			},
			musicUrl : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请产品名称"
			},
			musicUrl: {
				required: icon + '请上传背景音乐'
			}
		}
	})
}