var page=1;
var prefix = "/ts/currlog";
var total=0;
var val='';
var ks1='';
var js1='';
var sj1='';
$(function() {
	$("#sj").hide();
	$("#jt").hide();
	load(0,1);
});


function load(rqid,page,ks,js,sj) {

	$("#fy").empty();
	//动态表头


	var dynamicHeader = [];
	$.get("/ts/currlog/list",{"page":page,"bs":$("#rq").val(),"ks":ks,"js":js,"sj":sj},function (data) {

		if(data==''||data==null||data.length<=0){

				//只查表头

       			$.get(prefix+"/getTitle",function (data1) {
					$("#exampleTable").empty();
       				//alert(data1.length);
					var hs="<thead style=\"height: 3rem;\"><tr class='active'>";
       				//alert("表头"+data.length);
					for (var i = 0; i <data1.length; i++) {
					//	alert(data1[i].name);
					hs+='<th class="text-center" >'+data1[i].name+'</th>';
					}
					hs+="</tr></thead>";
					$("#exampleTable").append(hs);

				});

			}


		else {
			for (var i = 0; i < (Object.keys(data[0])).length; i++) {
				//获取对应索引的value值，将获取的值设置到动态表头字段中。
				var property = (Object.keys(data[0]))[i];

				dynamicHeader.push({
					"title": property,
					"field": property,
					formatter: function (value, row, index) {
						/*新增添加课程*/
						//var btnw = '<button type="button" ondblclick="to_save(\'' + value.sdate + "'," + value.sjid + ')" class="btn btn-warning btn-sm">未安排课程</button>';

						var btnw='';
						var v = value.vs;
						var jid = value.jid;
						var days = value.days;
						var identification=value.identification;
						if (v != '' && v != null&&v!=undefined&&v!='undefined'){

							btnw = '<button type="button" class="btn btn-info">' + v + '&nbsp;' + days + '</button>';
							return btnw;
						}
						else{
							//对应时间对应的课程
							if (identification!=null&&identification!=''&&identification!=undefined&&identification!='undefined'){
								//可以点击修改 删除
								var contexts=''
								for (let j = 0; j <value.cList.length ; j++) {
									//remove(\'' + value.cList[j].daydate + "'," + jid + ')
									//\'' + value.cList[j].daydate + "'," + jid + '
										btns = '<span style="color:#ed5565;margin-right:5px;cursor: pointer;" class="glyphicon glyphicon-remove" onclick="remove('+value.cList[j].id+')"></span><button type="button" onclick="edit('+value.cList[j].id+')" class="btn btn-success">' + value.cList[j].cname + '</button>';
										contexts+=btns;
								}

								return  contexts+='<span class="glyphicon glyphicon-plus" onclick="to_save(\'' + value.sdate + "'," + value.sjid + ')"></span>';

							}
							else {
								var wbtnw = '<button type="button" ondblclick="to_save(\'' + value.sdate + "'," + value.sjid + ')" class="btn btn-warning btn-sm">未安排课程</button>';
								return wbtnw;
							}

						}




						//alert(identification)
						//
						/*if (v != '' && v != null) {

							if (days != undefined && days != 'undefined' && days != '') {
								btnw = '<button type="button" ondblclick="to_xzsave(\'' + value.ds + "'," + jid + ')" class="btn ' + value.colorsty + '">' + v + '&nbsp;' + days + '</button>';
							} else {
								btnw = '<span style="color:#ed5565;margin-right:5px;cursor: pointer;" class="glyphicon glyphicon-remove" onclick="remove(\'' + value.ds + "'," + jid + ')"></span><button type="button" onclick="edit(\'' + value.ds + "'," + jid + ')" class="btn ' + value.colorsty + '">' + v + '</button>';
							}


						}*/

						return btnw;
					},

					//显示是否显示隐藏
					switchable: true,
					//是否开启排序
					sortable: true,
					align: "center"
				});
			}
			//表格
			//拼接分页
			$.get("/ts/currlog/total",{"page":page,"bs":$("#rq").val(),"ks":ks,"js":js,"sj":sj},function (data) {
				var  pages=data.pages;//总页数


				if (pages>1){

					$("#fy").append('<li onclick="pre('+data.pageNum+','+pages+')">\n' +
						'\t\t\t<a " aria-label="Previous">\n' +
						'\t\t\t\t<span aria-hidden="true">&laquo;</span>\n' +
						'\t\t\t</a>');
					for (let i = 1; i <= pages; i++) {
						if (i==page){

							$("#fy").append('<li class="active" onclick="topage('+i+')"><a href="#">'+i+'</a></li>');
						}
						else {
							$("#fy").append('<li onclick="topage('+i+')"><a href="#">'+i+'</a></li>');
						}

					}
					$("#fy").append('<li onclick="next('+data.pageNum+','+pages+')">\n' +
						'\t\t\t<a href="#" aria-label="Next">\n' +
						'\t\t\t\t<span aria-hidden="true">&raquo;</span>\n' +
						'\t\t\t</a>\n' +
						'\t\t\t</li>');
				}


			})


			$('#exampleTable').bootstrapTable('destroy')
				.bootstrapTable(
					{
						data:data,
						method : 'get', // 服务器数据的请求方式 get or post
						//url : prefix + "/total", // 服务器数据的加载地址
						//	showRefresh : true,

						//	showColumns : true,
						//columns:columnsArray,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						//pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						//pageSize : 10, // 如果设置了分页，每页数据条数
						//pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								//cid:$('#cid').val(),
								bs:$("#rq").val()
								// username:$('#searchName').val()
							};
						},
						/*	responseHandler: function (res) {
                                return {"total": res.total,

                                };
                            },*/
						columns:dynamicHeader,// columnsArray,

					});
		}



	})



}

function pre(i,pages) {
if (i<=1){
	layer.msg("已经第一页了!");
	return;
}
--i;

	load(rq,i,ks1,js1,sj1);
}

function next(i,pages) {
	if (i>=pages){
		layer.msg("已经最后一页了!");
		return;
	}
++i;
	load(rq,i,ks1,js1,sj1);
}


function topage(page) {

	load(val,page,ks1,js1,sj1);
}
//点击查询
function reLoad() {
	//查询刷新到第一页
	var rq=$("#rq").val();
	var ks=$("#d1").val();
	var js=$("#d2").val();
	var sj=$("#jt1").val();
	val=rq;
	ks1=ks;
	js1=js;
	sj1=sj;

	load(rq,1,ks,js,sj);

	//$('#exampleTable').bootstrapTable('refresh');
}

function to_save(sj,jid) {

	//alert(sj+"//"+jid)
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add?sj='+sj+"&jid="+jid // iframe的url
	});
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
//rq,id
function edit(id) {
	if (rq!=null&&id!=null&&rq!=''&&id!=''&&id!='undefined') {

		var index=layer.open({
			type: 2,
			title: '编辑',
			maxmin: true,
			shadeClose: false, // 点击遮罩关闭层
			area: ['800px', '520px'],
			content: prefix + '/edit?id='+id //prefix + '/edit?id='+ id+"&rq="+rq // iframe的url
		});
		layer.full(index)
	}
}
//sj,jid
function remove(id) {


	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				//'jid' : jid,
				//'sj': sj
				'id':id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
				//	reLoad();
					load();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
	//resetPwd
	var index=layer.open({
		type : 2,
		title : '查看对于座位',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : "/ts/zw?cid="+id // iframe的url
	});

	layer.full(index);

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