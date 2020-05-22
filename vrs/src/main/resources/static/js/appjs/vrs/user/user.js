
var prefix = "/userallot/user"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					            username:$('#searchName').val(),
					           	rid:$('#rid').val(),
                                userIdCreate:$("#userIdCreate").val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
											/*					{
									field : 'userId', 
									title : '编号' 
								},*/
																{
									field : 'username',
                                                                    align : 'center',
									title : '用户名' 
								},
																{
									field : 'name',
                                                                    align : 'center',
									title : '昵称'
								},
								/*								{
									field : 'password', 
									title : '密码' 
								},*/
//																{
//									field : 'deptId', 
//									title : '' 
//								},
//																{
//									field : 'email', 
//									title : '邮箱' 
//								},
																{
									field : 'mobile',
		                            align : 'center',
									title : '手机号' 
								},
								{
									field : 'rname',
                                    align : 'center',
									title : '会员级别'
								},


									/*						{
									field : 'status', 
									title : '用户状态',
                                                                align : 'center',
									formatter : function(value, row, index) {
										if(value == 0){
											return "禁用";
										}else{
											return "正常";
										}
									}
								},*/
									/*							{
									field : 'userIdCreate', 
									title : '上级用户' 
								},*/
//																{
//									field : 'gmtCreate', 
//									title : '创建时间' 
//								},
//																{
//									field : 'gmtModified', 
//									title : '修改时间' 
//								},
//																{
//									field : 'sex', 
//									title : '性别' 
//								},
//																{
//									field : 'birth', 
//									title : '出身日期' 
//								},
//																{
//									field : 'picId', 
//									title : '' 
//								},
//																{
//									field : 'liveAddress', 
//									title : '现居住地' 
//								},
//																{
//									field : 'hobby', 
//									title : '爱好' 
//								},
//																{
//									field : 'province', 
//									title : '省份' 
//								},
//																{
//									field : 'city', 
//									title : '所在城市' 
//								},
//																{
//									field : 'district', 
//									title : '所在地区' 
//								},
//																{
//									field : 'rid', 
//									title : '角色id' 
//								},
																{
									field : 'isvip', 
									title : '是否是vip' ,
                                                                    align : 'center',
									formatter : function(value, row, index) {
										var sp="";
										var context="";
										if(value == 0){
											sp="<span class='btn btn-success btn-sm'>否</span>"
											return  sp;

										}else{

                                            sp="<span class='btn btn-warning btn-sm'>是</span>"

											context="<br/><span class='btn btn-success btn-sm'>"+row.context+"</span>";
										}

											return sp+context;
									}
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.userId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.userId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f ='';

										if ($("#uid").val()!=row.userId) {
											f = '<a class="btn btn-success btn-sm" href="#" title="查看他的图库"  mce_href="#" onclick="showUserimg(\''
												+ row.userId
												+ '\')"><i class="fa fa-key">查看他的图库</i></a> ';
										}

										var btns='';
										var m='';
										var u=$("#boos").val();

										if(u!=''&&u=='ok')
										{
												btns= '<a class="btn btn-info btn-sm" href="#"  mce_href="#" onclick="my_userApp(\''
												+ row.userId
												+ '\')">该用户添加的账号</a> ';
												m = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#"  mce_href="#" onclick="showUser('+row.userId+')")>查看他的下级</a> ';
										}
										//修改

										return e + d +btns+m+f;
									}
								} ]
					});
}
function reLoad() {

	$('#exampleTable').bootstrapTable('refresh');
}


function showUser(uid) {

	layer.open({
		type : 2,
		title : '查看下级',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/to_userTree?uid='+uid // iframe的url
	});


}

function ff() {
	layer.open({
		type : 2,
		title : '查看我的下级',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/to_userTree' // iframe的url
	});
}
function add() {
	//如果不是管理员 又不是会员 无法使用此功能
	$.get("/userallot/user/getUservip",function (data) {
		if(data.code==0){
			layer.open({
				type : 2,
				title : '增加账号',
				maxmin : true,
				shadeClose : false, // 点击遮罩关闭层
				area : [ '800px', '520px' ],
				content : prefix + '/add' // iframe的url
			});
		}
		else{
			layer.msg("非会员无法使用此功能");
		}

	})



}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'userId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function showUserimg(id) {

   var index=layer.open({
        type : 2,
        title : '查看该用户的图库',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/to_showUserimg?uid=' + id // iframe的url
    });
   layer.full(index);


}

function my_userApp(uid){
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add?uid='+uid // iframe的url
	});

}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['userId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}