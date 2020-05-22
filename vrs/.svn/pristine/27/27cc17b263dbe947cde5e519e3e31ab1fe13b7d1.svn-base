//var menuTree;

var menuIds;
$(function() {
	getMenuTreeData();


});
$.validator.setDefaults({

	submitHandler : function() {
		getAllSelectNodes();
	}
});

function getAllSelectNodes() {
	var ref = $('#menuTree').jstree(true); // 获得整个树

	menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组

	$("#menuTree").find(".jstree-undetermined").each(function(i, element) {
		menuIds.push($(element).closest('.jstree-node').attr("id"));
	});

	$('#menuTree').bind("activate_node.jstree", function (obj, e) {
		// 处理代码
		// 获取当前节点
		var currentNode = e.node;
	});


}

function getMenuTreeData() {
	var uid=$("#uid").val();
	$.ajax({
		type : "GET",
		url : "/userallot/user/userTree?uid="+uid,
		success : function(menuTree) {
			loadMenuTree(menuTree);
		}
	});
}
function loadMenuTree(menuTree) {



	$('#menuTree').jstree({
		'core' : {
			'data' : menuTree
		}
		/*"checkbox" : {
			"three_state" : false,
		},*/
	//	"plugins" : [ "wholerow", "checkbox" ]
	});
	//$('#menuTree').jstree("open_all");

}


