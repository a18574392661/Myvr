$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

 	var simg=$("#img").val();

	if(simg==null||simg===''){
		layer.msg("请上传全景图!");
		return;
	}

	var lens=$("input[name='tids']:checked").length;
	if(lens<=0){
		layer.alert("请选择所属标签!");
		return;
	}

	//alert($("#cid").val()+"//"+$("#cid2").val());
	//判断是顶级分类是否1选择了每有
	if ($("#cid").val()==''&&$("#cid2").val()==''){
		//是要多选
		var len=$("input[name='childs']:checked").length;
		if(len<=0){
			layer.alert("请选中一个分类!");
			return;
		}
	}



	$.ajax({
		cache : true,
		type : "POST",
		url : "/vrs/pro/save",
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