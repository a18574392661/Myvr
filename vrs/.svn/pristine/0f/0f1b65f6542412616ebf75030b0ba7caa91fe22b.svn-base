
var prefix = "/vr/productInfo"
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
					            name:$('#searchName').val(),
					            status: $("#status").val()
					           // username:$('#searchName').val()
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
									field : 'name', 
									title : '产品名称' 
								},
								{
									field : 'musicUrl', 
									title : '产品背景音乐',
									formatter: function(v,r,i){
										if(v){
											return "<audio src='"+v+"' controls>您的浏览器不支持 Audio</audio>";
										}
										return "暂无背景音乐";
									}
								},
								{
									field : 'logo', 
									title : '产品Logo',
									formatter: function(v,r,i){
										if( v ){
											return "<img src='"+v+"' style='max-height: 60px'>";
										}
										return "-";
									}
								},{
									field : 'status', 
									title : '产品状态',
									formatter( v,r,i){
										if( v == 0 ){
											return '<a class="btn btn-danger btn-sm" href="javascript:;">冻结</a> ';
										} else {
											return '<a class="btn btn-success btn-sm" href="javascript:;">正常</a> ';
										}
									}
								},
								{
									field : 'createTime', 
									title : '产品添加时间' 
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="冻结"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="javascript:;" title="产品图库"  mce_href="#" onclick="lookDetail(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i>产品图库</a> ';
										
										var g = '<a class="btn btn-danger btn-sm" href="javascript:;" title="激活码"  mce_href="#" onclick="activeCode(\''
											+ row.id
											+ '\')"><i class="fa fa-key"></i>激活码</a> ';
										
										var h = '<a class="btn btn-success btn-sm" href="javascript:;" title="热点图"  mce_href="#" onclick="lookRdtDetail(\''
											+ row.id
											+ '\')"><i class="fa fa-edit"></i>热点图</a> ';
										
										return f + g + e + h + d;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

/**
 * 激活码管理
 * @param id
 * @returns
 */
function activeCode(id){
	layer.full(layer.open({
		type : 2,
		title : '查看产品详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '100%', '100%' ],
		content : prefix + '/activeCode/' + id // iframe的url
	}));
}

function lookDetail(id){
	layer.full(layer.open({
		type : 2,
		title : '查看产品详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '100%', '100%' ],
		content : prefix + '/lookDetails/' + id // iframe的url
	}));
}

function lookRdtDetail(id){
	var index = layer.open({
		type : 2,
		title : '编辑热点图',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/editRdt/' + id // iframe的url
	});
	layer.full(index)
}

function edit(id) {
	var index = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	layer.full(index)
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
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