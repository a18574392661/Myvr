
var prefix = "/vrs/pro"
$(function() {
	load();
});

//画册
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
						pageSize : 5, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								typeid:3, //查询1定制设计的
								tableName:'vrs_img_cls4',
					            name:$('#searchName').val(),
								cid:$("#cid1").val(),
					           	uid:$("#uid1").val()
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
									checkbox : true
								},
										/*						{
									field : 'id', 
									title : '' 
								},*/
																{
										field : 'name',
																	align: "center",
										title : '产品名称'
									},
									{
										field : 'img',
										title : '产品主图片',
										align: "center",
										formatter : function(value, row, index) {

											var img="<img width='70px' src="+row.simg+"/>";
											return img;
										}
									},
							/*{
								field : 'img',
								title : '产品缩图片',
									formatter : function(value, row, index) {

										var img="<img width='70px' src="+row.simg+"/>";
										return img;
									}
							},*/

							/*  {
									field : 'pricce', 
									title : '产品价格'
								},
																{
									field : 'count', 
									title : '产品数量'
								},
*/


						/*	{
                                        field : 'createdate',
                                        title : '创建时间'
                                    },*/
                                {
                                    field : 'imgCls2DO.name',
									align: "center",
                                        title : '分类名称'
                                    },
                                    {
                                        //field : 'titleClsDO.name',
                                        title : '标签分类',
										align: "center",
										formatter: function (value, row, index) {
											/*var btn=' <button type="button" class="btn btn-warning btn-sm">付费</button>';

											if(row.payState==1){
												btn=' <button type="button" class="btn btn-info btn-sm">免费</button>';
											}*/
										//	$("#mc").html(row.titleClsDO.name)
											return row.titleClsDO.name;
										}

                                    },

							{

								title : '是否展示到页面',
								align: "center",
								formatter : function(value, row, index) {
									var btn=' <button type="button" class="btn btn-warning btn-sm">是</button>';

									if(row.status==0){
										btn=' <button type="button" class="btn btn-info btn-sm">否</button>';
									}
									return btn;

								}
							},


                                {
                                    align: "center",
                                    title: '是否需要开通会员',
                                    formatter: function (value, row, index) {
                                        var btn=' <button type="button" class="btn btn-warning btn-sm">付费</button>';

                                        if(row.payState==0){
                                            btn=' <button type="button" class="btn btn-info btn-sm">免费</button>';
                                        }

                                        return btn;
                                    }


                                },
							/*

                                                            /*{
                                                                field : 'cid',
                                                                title : '所属分类'
                                                            },*/

									/*							{
									field : 'status', 
									title : '0 删除 1 显示' 
								},*/
											/*					{
									field : 'img', 
									title : '海报图片详细情况' 
								},*/
										/*						{
									field : 'cid', 
									title : '所属分类'
								},*/

							{
								title : '展示简介',
								align: "center",
								field: "context"
							},

																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										var imgs='<a type="button" class="btn btn-info btn-sm" mce_href="#" onclick="imgs('+row.id+')">详细图信息</a>';
										var simgs='<a style="margin-left: 2rem" type="button" class="btn btn-info" mce_href="#" onclick="simgs('+row.id+')">设置推荐方按</a>';
										return e + d+imgs+simgs;

									}
								} ]
					});

	//多个分类的
	$('#exampleTable2')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/listProTie", // 服务器数据的加载地址
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
				pageSize : 5, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				//search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit: params.limit,
						offset:params.offset,
						typeid:1, //查询1定制设计的
						tableName:'vrs_img_cls',
						name:$('#searchName2').val(),
						catory2 :$('#catory2').val(),
						catory1 :$('#catory1').val(),
                        uid:$("#uid2").val()
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
						checkbox : true
					},


					/*						{
                field : 'id',
                title : ''
            },*/
					{
						field : 'name',
						align: "center",
						title : '产品名称'
					},
					{
						field : 'img',
						title : '产品主图片',
						align: "center",
						formatter : function(value, row, index) {

							var img="<img width='70px' src="+row.simg+"/>";
							return img;
						}
					},
				/*	{
						field : 'simg',
						title : '产品缩图',
						formatter : function(value, row, index) {
							var img="<img width='70px' src="+row.img+"/>";
							return img;
						}
					},*/

					{
						field : 'cname',
						align: "center",
						title : '产品所属分类'
					},

					{

						title : '分类所属标签',
						align: "center",
						formatter : function(value, row, index) {

							return $("#dz").html();
						}


					},


				/*	{
						field : 'pricce',
						title : '产品价格'
					},
					{
						field : 'count',
						title : '产品数量'
					},*/
					/*{
						field : 'createdate',
						title : '创建时间'
					},*/
					/*{
						field : 'imgCls2DO.name',
						title : '分类名称'
					},
					{
						field : 'titleClsDO.name',
						title : '分类所属标签'
					},

					{

						title : '产品所属标签',
						formatter : function(value, row, index) {
							var lists=row.ts;
							var  str="";
							if(lists!=null&&lists.length>0){
								for (let i = 0; i <lists.length ; i++) {
									str+=lists[i].name;
									if (lists.length-1>i){
										str+=",";

									}
								}



							}
							return str;
						}


					},
*/

					{

						title : '是否展示到页面',
						align: "center",
						formatter : function(value, row, index) {
							var btn=' <button type="button" class="btn btn-warning btn-sm">是</button>';

							if(row.status==0){
								btn=' <button type="button" class="btn btn-info btn-sm">否</button>';
							}
							return btn;
						}
					},
					{
						align: "center",
						title: '是否需要开通会员',
						formatter: function (value, row, index) {
							var btn=' <button type="button" class="btn btn-warning btn-sm">付费</button>';

							if(row.payState==0){
								btn=' <button type="button" class="btn btn-info btn-sm">免费</button>';
							}

							return btn;
						}


					},



					/*{
                        field : 'cid',
                        title : '所属分类'
                    },*/

					/*							{
                    field : 'status',
                    title : '0 删除 1 显示'
                },*/
					/*					{
            field : 'img',
            title : '海报图片详细情况'
        },*/
					/*						{
                field : 'cid',
                title : '所属分类'
            },*/

					{
						title : '展示简介',
						align: "center",
						field: "context"
					},

					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
								+ row.id
								+ '\')"><i class="fa fa-key"></i></a> ';
							var imgs='<a type="button" class="btn btn-info btn-sm" mce_href="#" onclick="imgs('+row.id+')">详细图信息</a>';
							var simgs='<a style="margin-left: 2rem" type="button" class="btn btn-info" mce_href="#" onclick="simgs('+row.id+')">设置推荐方按</a>';
							return e + d+imgs+simgs;

						}
					} ]
			});


}
function reLoad() {
	//画册系统
	$('#exampleTable').bootstrapTable('refresh');
	//换色系统
	$('#exampleTable2').bootstrapTable('refresh');


}
function add() {

	$.get("/userallot/user/getUservip",function (data) {

		if(data.code==0){
			var index=layer.open({
				type : 2,
				title : '传图',
				maxmin : true,
				shadeClose : false, // 点击遮罩关闭层
				area : [ '800px', '520px' ],
				content : prefix + '/add' // iframe的url
			});
			layer.full(index);
		}
		else{
			layer.alert("非会员无法上传图片!")
		}
	})


}
function edit(id) {
	var index=layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
	layer.full(index);
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

function imgs(id) {
	var index=layer.open({
		type: 2,
		title: '详细图查询',
		maxmin: true,
		shadeClose: false,
		area: ['1000px', '1000px'],
		content: '/vrs/imgclsImgs?cid='+id
	});
	layer.full(index)


//	location.href='/vrs/imgclsImgs?cid='+id;
}

/*缩yue图*/
function simgs(id) {

	var index=layer.open({
		type: 2,
		title: '缩图查询',
		maxmin: true,
		shadeClose: false,
		area: ['1000px', '1000px'],
		content: '/vrs/imgsSyt?cid='+id
	});
	layer.full(index)
	//alert(id)
	//打开一个页面


//	location.href='/vrs/imgsSyt?cid='+id;
}


function updateStatus(id){
	//批量修改 状态
	var sta=$("#status"+id).val();
	var pay=$("#pay"+id).val();
	var rows = "";
	if (id===1){
		rows=$('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	}
	else{
		rows=$('#exampleTable'+id).bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	}
	if (rows.length == 0) {
		layer.msg("请选择要修改的数据");
		return;
	}
	layer.confirm("确认要新修改选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
		// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids,
				"status":sta,
				"paystatus":pay
			},
			url : prefix + '/updateStatus',
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
			ids[i] = row['id'];
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