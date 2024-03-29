function imageUpload(id,name,srcimg,hid){
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#'+id, //绑定元素
            url: '/school/upload?name='+name, //上传接口
            size: 60000,
          accept: 'file',


        before: function(obj){
                //this.data={client: $("#client").val(), version: $("#versionNo").val()}//携带额外的数据
            },
            done: function (r) {
            	if( r.code == 0 ){
            		$("#"+hid).val(r.fileName);
            	
					$("#"+srcimg).attr("src", r.fileName);
					$("#tp").attr("width",200);
            	} else {
            		layer.msg(r.msg);
            	}
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
	})
}


function imageUpload2(id,name,srcimg,hid){
    layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#'+id, //绑定元素
            url: '/vr/colorType/uploadQjt?name='+name, //上传接口
            size: 60000,
            accept: 'file',
            before: function(obj){
                //this.data={client: $("#client").val(), version: $("#versionNo").val()}//携带额外的数据
            },
            done: function (r) {
                if( r.code == 0 ){
                    $("#"+hid).val(r.fileName);
                    $("#"+srcimg).attr("src", r.fileName)
                  //  alert(r.simg);
                    //缩小的图片
                    $("#simg1").val(r.simg);
                } else {
                    layer.msg(r.msg);
                }
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    })

}


function imageUpload3(id,name,srcimg,hid){
    layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#'+id, //绑定元素
            url: '/school/upload2?name='+name, //上传接口
            size: 60000,
            accept: 'file',


            before: function(obj){
                //this.data={client: $("#client").val(), version: $("#versionNo").val()}//携带额外的数据
            },
            done: function (r) {
                if( r.code == 0 ){
                    $("#"+hid).val(r.fileName);

                    $("#"+srcimg).attr("src", r.fileName);
                } else {
                    layer.msg(r.msg);
                }
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    })
}


/*
 * 通用富文本编辑器
 */
function initEditorSaveAndEdit(){
	 var E = window.wangEditor
     var editor = new E('#wangEditorDescription')//初始化
     editor.customConfig.uploadImgServer =  "/school/upload?name=text"; //上传URL
     editor.customConfig.uploadFileName = 'file';
     editor.customConfig.uploadImgHooks = {
         customInsert: function (insertImg, result, editor) {
                     // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                     // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
                   
                     // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
                     
                     var url =result.fileName;
                     insertImg(url);
              
                     // result 必须是一个 JSON 格式字符串！！！否则报错
                 }
             }


     
	  //标签值 要回显示样式
	 editor.customConfig.onchange = function (html) {
	        // 监控变化，同步更新到 textarea
	       
	        //同步到 隐藏域
	        $("#detailed").val(html);
	        
	    }
	 
	 
	 editor.create();
	 editor.txt.html($("#detailed").val())
	 
	// $("#detailed").val(editor.txt.html());
	
	 
	
}


/*
 * 通用富文本编辑器
 */
function initEditorSaveAndEdit(name){
    var E = window.wangEditor
    var editor = new E('#wangEditorDescription')//初始化
    editor.customConfig.uploadImgServer =  "/school/upload?name="+name; //上传URL
    editor.customConfig.uploadFileName = 'file';
    editor.customConfig.uploadImgHooks = {
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：

            var url =result.fileName;
            insertImg(url);

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    }



    //标签值 要回显示样式
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea

        //同步到 隐藏域
        $("#detailed").val(html);

    }


    editor.create();
    editor.txt.html($("#detailed").val())

    // $("#detailed").val(editor.txt.html());



}


function  to_proDetail(proid) {

 //根据图片id 查到 typeid 判断要跳的页面
   $.get("/pro/h5/queryProType",{"proid":proid},function (data) {
       if(data.success){
            //根据typeid去不同的页面
          var typeid=data.data;

           if(typeid==1){

                location.href="/pro/h5/proDetailed?id="+proid;

           }else if(typeid==2){

               location.href="/pro/h5/proHuance?id="+proid;

           }else  if(typeid==3){
               //门窗

               location.href="/pro/h5/proMengchuang?id="+proid;

           }else if(typeid==4){
               //画册
               location.href="/pro/h5/proHuace?id="+proid;
           }
       }else{
           alert(data.message);

       }

   })
}


function  member_edit(hrefs) {
    if(hrefs!=''&&hrefs!='#'&&hrefs!=null){


        //location.href="/vrs/houses/tourl?url="+hrefs;
        //window.open("http://www.baidu.com","_blank");
        location.href=hrefs;
    }
}


function register() {
    var password=$("#password").val();
    var name=$("#name").val();
    var rid=$("#rid").val();
    var pwd2=$("#password2").val();
    var mobile=$("#mobile").val();
    if (mobile===''||mobile==null){
        alert("手机号码不能为空!");
        return;
    }
  /*  if(rid===''||rid==null){
        alert("请选择级别");
        return ;
    }*/
    if (password===''){
        alert("密码不能为空!")
        return;
    }
    //"name":name,
    if(pwd2!=password){
        alert("两次密码不一样!!");
        return;
    }
    $.post("/vrs/user/addH5user",{"mobile":mobile,"password":password,"rid":rid},function (data) {



        if(data.code==0){
            location.href="/vrs/user/login"
        }
        else{
            alert(data.msg);
        }



    })


}

$.get("/vrs/user/getRoles",function (data) {
    for (let j = 0; j <data.length ; j++) {

        $("#rsh5").append('<option value="'+data[j].roleId+'">'+data[j].roleName+'</option>');
    }

})

//去判断是否开通会员
$.get("/vrs/user/isUsernotVip",function (data) {
   if (data==true){
       $("#hy").html("开通会员");
   }else {
       $("#hy").html("续费会员");
   }

})



function lbDetail(pid) {

    location.href="/pro/h5/queryLbByid?id="+pid;
}


function to_vip(){
    location.href="/pro/h5/queryVip"
}


