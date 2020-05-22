
var prefix = "/stock/report"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/inList", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : false, // 设置为true会在底部显示分页条
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
							var fpid = $('#firstPid').val();
							var spid = $('#secondPid').val();
							var tpid = $('#thirdPid').val();
							
							var path = "";
							
							if( tpid ){
								path += "," + tpid;
							}
							
							if( spid ){
								path += "," + spid;
							}
							
							if( fpid ){
								path += "," + fpid;
							}
							
							path += ",";
							
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								//limit: params.limit,
								//offset:params.offset,
								startTime: $('#startTime').val(),
								endTime: $('#endTime').val(),
								typePath: path,
					            goodsName:$('#searchName').val()
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
																{
									field : 'goodsName', 
									title : '商品名称' 
								},{
									field : 'typePath', 
									title : '商品所属分类' 
								},{
									field : 'num', 
									title : '入库数量' 
								},{
									field : 'price', 
									title : '入库单价(元)' 
								},{
									field : 'createTime', 
									title : '入库时间' 
								},{
									field : 'remarks', 
									title : '入库备注' 
								},{
									title : '合计金额(元)',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										return "<span style='color:red'>¥"+(row.num * row.price).toFixed(2) + "</span>";
									}
								} ],
							onLoadSuccess: function(data){    
					            var rows = data.rows;
					            var totalAmount = 0;
					            var len = rows.length;
					            for( var i = 0; i < len ; i++ ){
					            	var row = rows[i];
					            	totalAmount += +(row.num * row.price).toFixed(2);
					            }
					            //console.log( totalAmount )
					            $("#totalAmount").text(totalAmount + "元");
					            $("#totalAmount2").text(totalAmount + "元");
					        }
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}