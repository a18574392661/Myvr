/****
 * Krpano util ，通用函数类
 * @author lixun
 * @create 2018年9月11日 17:37:44
 ***/
 var krpano = null;
 var Common = {
     //获得场景信息
     getScene : function( id,cb ){
         //这里使用假数据
        var map = {
            '1' : {
                type : 'tour.xml',//场景类型
                next : '2',//下一个场景ID
                prev : '3',//上一个场景ID
                imgs : [    //对应场景内的图片展示
                    '/images/scene1.jpg',
                    '/images/scene2.jpg',
                    '/images/scene3.jpg',
                    '/images/scene4.jpg',
                    '/images/scene5.jpg',
                    '/images/scene6.jpg'
                ]
            }//这里使用的是假数据，后续的就不贴了，占空间
        };
        if(cb){
            cb(map[id]);
        }
     },
     //加载场景
     loadPano : function(xml,imgs,next,prev){
        if(krpano){
            removepano('newId');
        }
        embedpano({
            swf : 'tour.swf',         // path to flash viewer (use null if no flash fallback will be requiered)
            id : "newId", 
            xml : xml, 
            target : 'pano', 
            consolelog : true,                    // trace krpano messages also to the browser console
            passQueryParameters : true,         // pass query parameters of the url to krpano
            onready : function(krpano_interface){
                krpano = krpano_interface;
                //继续操作
                console.log('delay...');
                Common.setHot(imgs,next,prev);
            }
        });

     },
     //设置热点图片路径
     setHot : function(imgs,next,prev){
         console.log('hot setting')
         if (krpano){
             setTimeout(function(){
                 imgs.forEach(function(imgPath,index){
                     krpano.set("hotspot[dyn_img"+(index+1)+"].url", imgPath);    
                 })
                 //设置场景跳转
                krpano.set('hotspot[spot1].onhover','showtext(去往第1展厅,hotspottextstyle)');
                krpano.set('hotspot[spot2].onhover','showtext(去往第2展厅,hotspottextstyle)');
                krpano.set('hotspot[spot1].onclick','js(showScene('+prev+'))');
                krpano.set('hotspot[spot2].onclick','js(showScene('+next+'))');
                //随机观察点
                krpano.call("lookto(" + (Math.random() * 360.0 - 180.0) + "," + (Math.random() * 180.0 - 90.0) + "," + (80.0 + Math.random() * 100.0) + ")");
             },200)
        }
     }
 };
 
 
//加载场景
 var imgs = [];
 function showScene(id){
     console.log('加载场景'+id);
     Common.getScene(id,function(obj){
         imgs = obj.imgs;
         Common.loadPano(obj.type,obj.imgs,obj.next,obj.prev);
     })
 }

 function initScene(){
 	//第一次加载需要先确定场景，然后初始化场景，然后加载图片
     //后续的需要获得场景信息，然后加载不同的pano,然后加载图片信息
     Common.getScene(1,function(obj){
         console.log(obj);
         Common.loadPano(obj.type,obj.imgs,obj.next,obj.prev);
     })
 }