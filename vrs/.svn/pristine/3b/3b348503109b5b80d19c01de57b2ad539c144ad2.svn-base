package com.bootdo.common.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.R;


/**
 * 验证码获取
 * @author huangqingwen
 *
 */
@Controller
@RequestMapping("/verify")
public class VerifyCodeController{
	
	@RequestMapping("/getVerifyCode")
    public Map<String, Object> getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
       
        int width=102;
        int height=34;
        
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        //创建内存图像并获得图形上下文
        BufferedImage image=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        
        /*
         * 产生随机验证码
         * 定义验证码的字符表
         */
        String chars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands=new char[4];
        int length = 4;
        for(int i=0;i<length;i++){
            //int rand=(int) (Math.random() *36);
            int rand = new Random().nextInt(36);
            rands[i]=chars.charAt(rand);
        }
        
        /*
         * 产生图像
         * 画背景
         */
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        
        /*
         * 随机产生120个干扰点
         */
        int len = 120;
        for(int i=0;i< len ;i++){
            int x=(int)(Math.random()*width);
            int y=(int)(Math.random()*height);
            //int red=(int)(Math.random()*255);
            int red = new Random().nextInt(255);
            //int green=(int)(Math.random()*255);
            int green = new Random().nextInt(255);
            //int blue=(int)(Math.random()*255);
            int blue = new Random().nextInt(255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x, y, 1, 0);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC|Font.BOLD,18));
        
        //在不同高度输出验证码的不同字符
        g.drawString(""+rands[0], 6, 25);
        g.drawString(""+rands[1], 28, 24);
        g.drawString(""+rands[2], 52, 23);
        g.drawString(""+rands[3], 72, 24);
        g.dispose();
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode", new String(rands));
        //将图像传到客户端
        try {
			ServletOutputStream sos=response.getOutputStream();
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ImageIO.write(image, "JPEG", baos);
			byte[] buffer=baos.toByteArray();
			response.setContentLength(buffer.length);
			sos.write(buffer);
			baos.close();
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }
	@ResponseBody
	@RequestMapping("/checkVerifyCode")
    public R checkVerifyCode(HttpServletRequest request) {
		String verify = request.getParameter("verify")+"";
		String verifyCode = request.getSession().getAttribute("verifyCode")+"";
		if(verify.toUpperCase().equals(verifyCode)) {
			return R.ok();
		}else {
			return R.error("验证码不正确");
		}
	}
}
