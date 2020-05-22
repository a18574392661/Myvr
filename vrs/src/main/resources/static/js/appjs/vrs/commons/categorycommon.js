//返回状态
function categoryStatus(value,show,hide){
	if(value == 1){
		return show;
	}else{
		return hide;
	}
}
//返回分类
function getCategorys(requestUrl,requestparam){
	
	var category = "";
	$.ajax({          
      	url:requestUrl, 
      	async: false,
        dataType:"json",  
        type:"get", 
        data:{"id":requestparam},
        success:function(data){
        	category = data.category;
        },      
        error:function(){
        	console.log("console log errro");
        }
	});
	return category;
}

//分页查询
function dispatch(obj,page,requetPaht){
	
	
	var url = $(obj).attr("url");
	location.href=url = '?+page=1&requetPaht='+requetPaht;

//	var form = $('<form></form>'); 
//	form.attr('action', url);
//    form.attr('method', 'get');  
//    var page = $('<input type="text" name="page" value='+page+'>');  
//    var requetPaht = $('<input type="text" name="requetPaht" value='+requetPaht+'>'); 
//    
//	form.append(page);
//	form.append(requetPaht);
//	form.appendTo('body')
//    form.submit(); 
}
