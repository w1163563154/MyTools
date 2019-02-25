package com.xiaoyu.mytools.Ui.Activity.ErWeiActivity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * Created by yfb on 2019/2/22.
 */

public class MyQRCode {

    /**
     * 获取建造者
     *
     * @param text 样式字符串文本
     * @return {@link MyQRCode.Builder}
     */
    public static MyQRCode.Builder builder(@NonNull CharSequence text) {
        return new MyQRCode.Builder(text);
    }

    public static class Builder {

        private int backgroundColor = 0xffffffff;

        private int codeColor = 0xff000000;

        private int codeSide = 800;

        public Bitmap mBitmap;

        private CharSequence content;

        public Builder backColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder codeColor(int codeColor) {
            this.codeColor = codeColor;
            return this;
        }

        public Builder codeSide(int codeSide) {
            this.codeSide = codeSide;
            return this;
        }



        public Builder(@NonNull CharSequence text) {
            this.content = text;
        }

        public Bitmap into(ImageView imageView) {
            Bitmap bitmap = MyQRCode.creatQRCode(content, codeSide, codeSide, backgroundColor, codeColor);
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
            return bitmap;
        }
    }

    //----------------------------------------------------------------------------------------------以下为生成二维码算法

    public static Bitmap creatQRCode(CharSequence content, int QR_WIDTH, int QR_HEIGHT, int backgroundColor, int codeColor) {
        Bitmap bitmap = null;
        try {
            // 判断URL合法性
            if (content == null || "".equals(content) || content.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content + "", BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = codeColor;
                    } else {
                        pixels[y * QR_WIDTH + x] = backgroundColor;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap creatQRCode(CharSequence content, int QR_WIDTH, int QR_HEIGHT) {
        return creatQRCode(content, QR_WIDTH, QR_HEIGHT, 0xffffffff, 0xff000000);
    }

    public static Bitmap creatQRCode(CharSequence content) {
        return creatQRCode(content, 800, 800);
    }

    //==============================================================================================二维码算法结束


    /**
     * @param content   需要转换的字符串
     * @param QR_WIDTH  二维码的宽度
     * @param QR_HEIGHT 二维码的高度
     * @param iv_code   图片空间
     */
    public static void createQRCode(String content, int QR_WIDTH, int QR_HEIGHT, ImageView iv_code) {
        iv_code.setImageBitmap(creatQRCode(content, QR_WIDTH, QR_HEIGHT));
    }

    /**
     * QR_WIDTH  二维码的宽度
     * QR_HEIGHT 二维码的高度
     *
     * @param content 需要转换的字符串
     * @param iv_code 图片空间
     */
    public static void createQRCode(String content, ImageView iv_code) {
        iv_code.setImageBitmap(creatQRCode(content));
    }

    /**
     * 生成有图片的二维码
     * w  二维码的宽度
     * h 二维码的高度
     *
     * @param content 需要转换的字符串
     * @param logo 图片
     */
    public static Bitmap createQRImage(String content, int w, int h,Bitmap logo)throws WriterException {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        /*偏移量*/
        int offsetX = w / 2;
        int offsetY = h / 2;

        /*生成logo*/
        Bitmap logoBitmap=null;

        if (logo!=null){
            Matrix matrix = new Matrix();
            float scaleFactor = Math.min(w * 1.0f / 5 / logo.getWidth(), h * 1.0f / 5 /logo.getHeight());
            matrix.postScale(scaleFactor,scaleFactor);
            logoBitmap= Bitmap.createBitmap(logo, 0, 0, logo.getWidth(),   logo.getHeight(), matrix, true);
        }


        /*如果log不为null,重新计算偏移量*/
        int logoW = 0;
        int logoH = 0;
        if (logoBitmap != null) {
            logoW = logoBitmap.getWidth();
            logoH = logoBitmap.getHeight();
            offsetX = (w - logoW) / 2;
            offsetY = (h - logoH) / 2;
        }

        /*指定为UTF-8*/
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //容错级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置空白边距的宽度
        hints.put(EncodeHintType.MARGIN, 0);
        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, w, h, hints);

        // 二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if(x >= offsetX && x < offsetX + logoW && y>= offsetY && y < offsetY + logoH){
                    int pixel = logoBitmap.getPixel(x-offsetX,y-offsetY);
                    if(pixel == 0){
                        if(matrix.get(x, y)){
                            pixel = 0xff000000;
                        }else{
                            pixel = 0xffffffff;
                        }
                    }
                    pixels[y * w + x] = pixel;
                }else{
                    if (matrix.get(x, y)) {
                        pixels[y * w + x] = 0xff000000;
                    } else {
                        pixels[y * w + x] = 0xffffffff;
                    }
                }
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }
}
