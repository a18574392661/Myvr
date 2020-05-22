package com.bootdo.common.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingEAN13Code {
	private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF; 
    
    /**
     * 条形码编码
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public void encode(String contents, int width, int height, String imgPath) {
        //保证最小为70*25的大小
        int codeWidth = Math.max(70, width);
        int codeHeight = Math.max(25, height);
        try {
            //使用EAN_13编码格式进行编码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, codeHeight, null);
            //生成png格式的图片保存到imgPath路径
            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));
            System.out.println("encode success! the img's path is "+imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static BitMatrix deleteWhite(BitMatrix matrix){  
	    int[] rec = matrix.getEnclosingRectangle();  
	    int resWidth = rec[2] + 1;  
	    int resHeight = rec[3] + 1;  
	  
	    BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
	    resMatrix.clear();  
	    for (int i = 0; i < resWidth; i++) {  
	        for (int j = 0; j < resHeight; j++) {  
	            if (matrix.get(i + rec[0], j + rec[1]))  
	                resMatrix.set(i, j);  
	        }  
	    }  
	    return resMatrix;  
	} 

    /**
     * 根据字符串生成条形码
     * 
     * @param code字符串
     * @return
     */
    
    
    public static void getShapeCode(String code, int w, int h, String imgPath) {
        // 编码条形码
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = null;
        try {
            // 使用code_128格式进行编码生成100*25的条形码
            matrix = new MultiFormatWriter().encode(code,
                    BarcodeFormat.CODE_128, w, h, hints);
          //生成png格式的图片保存到imgPath路径
            MatrixToImageWriter.writeToStream(matrix, "png",
                    new FileOutputStream(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 根据字符串生成条形码
     * 
     * @param code字符串
     * @return
     */
    public static BitMatrix getShapeCode(String code) {
        // 编码条形码
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = null;
        try {
            // 使用code_128格式进行编码生成100*25的条形码
            matrix = new MultiFormatWriter().encode(code,
                    BarcodeFormat.CODE_128, 300, 60, hints);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matrix;
    }
    
    /** 获取条形码 */
    public void getShapeCode(String printCode, HttpServletResponse response) {
        BitMatrix matrix = this.getShapeCode(printCode);
        // 返回png图片流
        // 获得Servlet输出流
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "png",
                    outStream);
            outStream.flush();
            // 关闭输出流
            outStream.close();
        } catch (IOException e) {
            String simplename = e.getClass().getSimpleName();
            if (!"ClientAbortException".equals(simplename)) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 解析条形码
     * @param imgPath
     * @return
     */
    public String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public static void encodeQrcode(String contents, int width, int height, String imgPath) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定显示格式为GBK
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, width, height, hints);
            //生成png格式的图片保存到imgPath路径位置
            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));
            System.out.println("QR Code encode sucsess! the img's path is "+imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 生成二维码
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    public static BitMatrix encodeQrcode(String contents) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定显示格式为GBK
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = null;
        try {
        	bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, 300, 300, hints);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitMatrix;
    }
    
    /**
     *  最终调用该方法生成二维码图片
     * @param url
     * @param imgPath 二维码生成的绝对路径
     * @param logoPath 二维码中间logo绝对地址
     * @throws Exception
     */
    public static void get2CodeImage(String url,String imgPath,String logoPath) throws Exception{
	    String format = "png";
	    Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(); 
	    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
	    BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300, hints);
	    File outputFile = new File(imgPath);  
	    writeToFile(bitMatrix, format, outputFile,logoPath);  
    }
    
    /**
     * 解析二维码
     * @param imgPath
     * @return
     */
    public static String decodeQrcode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            //读取图片
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
            //设置显示格式为GBK
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            //进行解码
            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();//返回结果信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static BufferedImage toBufferedImage(BitMatrix matrix){
	    int width = matrix.getWidth();
	    int height = matrix.getHeight();
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	    for(int x=0;x<width;x++){
		    for(int y=0;y<height;y++){
		    	image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
		    }
	    }
	    return image;
    }
    
    public static void writeToFile(BitMatrix matrix,String format,File file,String logoPath) throws IOException {
    	BufferedImage image = toBufferedImage(matrix);
    	Graphics2D gs = image.createGraphics();
    	                
    	//载入logo
    	Image img = ImageIO.read(new File(logoPath));
    	gs.drawImage(img, 125, 125, null);
    	gs.dispose();
    	img.flush();
    	if(!ImageIO.write(image, format, file)){
    		throw new IOException("Could not write an image of format " + format + " to " + file);  
    	}
	}
    
    public static void writeToFile(BitMatrix matrix,String format,ServletOutputStream outStream,String logoPath) throws IOException {
    	BufferedImage image = toBufferedImage(matrix);
    	Graphics2D gs = image.createGraphics();
    	//载入logo
    	Image img = ImageIO.read(new File(logoPath));
    	
    	int logoWidth = img.getWidth(null) > image.getWidth()*2 /10 ? (image.getWidth()*2 /10) : img.getWidth(null);
        int logoHeight = img.getHeight(null) > image.getHeight()*2 /10 ? (image.getHeight()*2 /10) : img.getHeight(null);
        //设置logo图片放置位置
        //中心
        int x = (image.getWidth() - logoWidth) / 2;
        int y = (image.getHeight() - logoHeight) / 2;                
    	
    	//gs.drawImage(img, 125, 125, 50, 50, null);//要二维码边上的白色部分
    	//gs.drawImage(img, 91, 91, 50, 50, null);//不要二维码边上的白色部分
        //开始合并绘制图片
        gs.drawImage(img, x, y, logoWidth, logoHeight, null);
        gs.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);
        //logo边框大小
        gs.setStroke(new BasicStroke(2));
        //logo边框颜色
        gs.setColor(Color.WHITE);
       // gs.drawArc(x, y, logoWidth, logoHeight, 0, 360);
        gs.drawRect(x, y, logoWidth, logoHeight);
        
    	gs.dispose();
    	img.flush();
    	if(!ImageIO.write(image, format, outStream)){
    		throw new IOException("Could not write an image of format " + format + " to ServletOutputStream.");  
    	}
	}
    
    public static void main(String[] args) {
    	
      /*  String decodeImgPath = "D:/profile/dsa.png";
        ZxingEAN13Code EAN13Code = new ZxingEAN13Code();
        System.out.println(EAN13Code.decode(decodeImgPath));*/
        
        
        String encodeImgPath = "D:/dsa.png";
        String contents = "18574392661";
        int width = 100, height = 40;
        ZxingEAN13Code.encodeQrcode(contents,width,height, encodeImgPath);
        
        /*String encodeImgPath = "D:/4.png";
        String contents = "18574392661";
        int width = 200, height = 200;
        ZxingEAN13Code.encodeQrcode(contents,width,height, encodeImgPath); */
    }
}