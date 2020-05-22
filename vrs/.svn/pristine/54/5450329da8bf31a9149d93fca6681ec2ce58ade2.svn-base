
var prefix = "/vrs/userImgcls"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/saveMyUserName", // 服务器数据的加载地址
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
                              //userIdCreate:$('#userIdCreate').val(),
								userIdCreate:$("#uid").val(),
								proid:$("#cid").val(),
					            username:$('#searchName').val()
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
									title : '选择分配的子账号',
									align:'center',
									formatter : function(value, row, index) {

										//var ins="<input type='checkbox' />";

										/*if(row.isvip==0){
											//不是会员图片达到上限
											if (row.count>=$("#count").val()){
												m='<span class="glyphicon glyphicon-remove"></span>';
												return  m;
											}


										}*/

										if (row.sta!=0){
											var uid=row.userId;
											var cid=$("#cid").val();
											//已经有此图
											m='<span onclick="removeUserImg('+uid+","+cid+')" type="button" class="btn btn-warning btn-sm">取消该用户此图</span>';
											return  m;
										}

										var ins="<input type='checkbox' name='uids' value='"+row.userId+"'/>";
									  	return ins;
									}


								},
								/*								{
									field : 'id', 
									title : '' 
								},*/



							{
									field : 'username',
									align:'center',
									title : '用户名'
								},
								{
									title : '是否是会员',
									align:'center',
                                    formatter : function(value, row, index) {
                                        var m='<button type="button" class="btn btn-info btn-sm">否</button>';
                                        if(row.isvip==1){

                                            m='<button type="button" class="btn btn-warning btn-sm">是</button>';
                                        }
                                        return  m;

                                    }
								},
								{
								title : '已经上传图片数量',
									align:'center',
								formatter : function(value, row, index) {
									var m='';
									if (row.isvip==1){
										m='<font style="color:orange;">'+row.count+"/不限制"+'</font>'
									}
									else {
										if(row.count>=$("#count").val()){
											//m='<font style="color: red;">'+row.count+"/"+$("#count").val()+'&nbsp;非会员已经达到上限</font>'

											m='<font style="color: red;">'+row.count+"/"+$("#count").val()+'</font>'

										}
										else{
											m='<font style="color: darkgrey;">'+row.count+"/"+$("#count").val()+'</font>'
										}

									}

									return  m;

								 }
								},
							 {
								title : '是否已经拥有此图片',
								 align:'center',
								formatter : function(value, row, index) {
									var lab=row.sta;
									var spa="";

									if(lab==0){
											//没有
										spa='<button type="button" class="btn btn-info">没有此图</button>'
									}else{

										spa='<button type="button" class="btn btn-warning">已有此图</button>'
									}
									return  spa;
								}
							}

								]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function removeUserImg(uid,proid) {

	layer.confirm('你确定要取消该用户有此图吗?', {
		btn : [ '确定', '取消' ]
	}, function() {

			$.get("/vrs/userImgcls/removeUserImg",{"uid":uid,"cid":proid},function (data) {
			layer.alert(data.msg)
				if(data.code==0){
					reLoad();
				}

			})

	})
}

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}

function showProimg(pid) {
	var index=layer.open({
		type : 2,
		title : '详细图',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
	 	area : [ '800px', '520px' ],
		content : "/vrs/imgDetalies/to_detail?cid="+pid // iframe的url
	});
	layer.full(index);
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

function resetPwd(id) {
}
function batchUser() {
//	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	var uids=$("input[name='uids']:checked").length;
	if (uids == 0) {
		layer.msg("请选择你要分配的子账号");
		return;
	}

	var str='';

	$("input[name='uids']:checked").each(function(i){//把所有被选中的复选框的值存入数组
		str+=this.value;
		if(uids-1>i){
			str+=",";

		}

	});



	layer.confirm("确认要分配选中的'" + uids + "'个子账号吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {

		$.ajax({
			type : 'POST',
			data : {
				"ids" : str,
				"proid":$("#cid").val()
			},
			url : prefix + '/batchUser',
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