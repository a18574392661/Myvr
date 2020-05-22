$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	if ($("#ds").val()==2){
		var pid=$("#zpid").val();
		if (pid===''||pid==null||pid.length<=0){
			layer.msg("请选择二级分类!");
			return ;
		}

		var conts=$("#sanCount");
	var names=$(conts).find("input[name='names']")
			var len =names.length;
		if (len<=0){
			layer.msg("请添加三级分类!");
			return;
		}
	}

	$.ajax({
		cache : true,
		type : "POST",
		url : "/vrs/imgCls/save",
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
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}