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
		url : "/vrs/imgCls2/save",
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
			name : {
				required : true
			},
			sort : {
				required : true,
				digits : true
			},
			category : {
				required : true,
			}
		
		},
		errorPlacement: function(error,element){
			//重写jqeuryvalidation样式
			if("category-error" == error[0].id){
				error.appendTo(element.parent().parent().parent());
				$("#category-error").css("margin-left","205px");
			}else{
				error.appendTo(element.parent());
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			},
			sort : {
				required : icon+"请输入排序编号"
			},
			category : {
				required : icon+"请选择分类类型"
			}
		}
	})
}