/**
 * 
 */

//函数的参数audioName和videoName都是从krpano的xml文件中直接传入
//音频播放
function PlayAudio(audioName) {
    //判断是否已经存在音乐标签，如果存在则移除
    var clearDiv = document.getElementById("backAudioDiv");
    if (clearDiv !== null) {
        clearDiv.parentNode.removeChild(clearDiv);
    }
    //判断是否存在视频标签，如果存在则暂停视频播放
    var pauseDiv = document.getElementById("backVideoDiv");
    if (pauseDiv !== null) {
        document.getElementById("video").pause();
    }
 
    //创建背景标签和删除按钮标签
    var backAudioDiv = document.createElement("div");
    backAudioDiv.setAttribute("id", "backAudioDiv");
    backAudioDiv.style.position = "absolute";
    backAudioDiv.style.zIndex = "2";
    document.body.appendChild(backAudioDiv);
    var closeImg = document.createElement("img");
    closeImg.setAttribute("id", "closeImg");
    closeImg.src = "/vtour/images/stop.jpg";
 
    //创建音乐标签并设置显示位置
    var audio = document.createElement("audio");
    audio.setAttribute("id", "audio");
    audio.controls = "controls";
    audio.autoplay = "true";
    audio.src = audioName;
    backAudioDiv.style.top = "20px";
    backAudioDiv.style.right = "20px";
    backAudioDiv.appendChild(audio);
 
    //关闭按钮的显示和事件
    closeImg.style.height = audio.offsetHeight+ "px";
    backAudioDiv.appendChild(closeImg);
    closeImg.addEventListener("click", function CloseMedia() {
        backAudioDiv.parentNode.removeChild(backAudioDiv);
    });
}
//视频播放
function PlayVideo(videoName) {
    var clearDiv = document.getElementById("backVideoDiv");
    if (clearDiv !== null) {
        clearDiv.parentNode.removeChild(clearDiv);
    }
    var pauseDiv = document.getElementById("backAudioDiv");
    if (pauseDiv !== null) {
        document.getElementById("audio").pause();
    }
 
    var backVideoDiv = document.createElement("div");
    backVideoDiv.setAttribute("id", "backVideoDiv");
    backVideoDiv.style.position = "absolute";
    backVideoDiv.style.zIndex = "2";
    document.body.appendChild(backVideoDiv);
    var closeImg = document.createElement("img");
    closeImg.setAttribute("id", "closeImg");
    closeImg.src = "picture/close.png";
 
    var video = document.createElement("video");
    video.setAttribute("id", "video");
    video.controls = "controls";
    video.autoplay = "true";
    video.src = videoName;
    backVideoDiv.appendChild(video);
    video.style.width = "100%";
 
    //设置视频居中 强制设置视频大小
    backVideoDiv.style.width = "40%";
    backVideoDiv.style.top = "30%";
    backVideoDiv.style.left = "30%";
 
    closeImg.style.position = "absolute";
    closeImg.style.top = "0px";
    closeImg.style.right = "0px";
    backVideoDiv.appendChild(closeImg);
    //关闭按钮的显示与隐藏
    closeImg.style.display = "none";
    backVideoDiv.addEventListener("mouseover", function showclose(){
        closeImg.style.display = "";
    });
    backVideoDiv.addEventListener("mouseout", function hideclose(){
        closeImg.style.display = "none";
    });
    closeImg.addEventListener("click", function CloseMedia() {
        video.pause();
        backVideoDiv.parentNode.removeChild(backVideoDiv);
    });

};