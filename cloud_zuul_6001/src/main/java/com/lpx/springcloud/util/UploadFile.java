package com.lpx.springcloud.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

	/**
     * 上传图片
     *  原名称
     * @param request 请求
     * @param path_deposit 存放位置(路径)
     * @param file 文件
     * @param isRandomName 是否随机名称
     * @return 完整文件路径
     */
    public static  String uploadImage(String path,MultipartFile file,boolean isRandomName) {
        //上传
        try {
            if(file!=null){
                String origName=file.getOriginalFilename();// 文件原名称
                // 判断文件类型
                String type =origName.indexOf(".")!=-1?origName.substring(origName.lastIndexOf(".")+1, origName.length()):null;
                if (type!=null) {
                        //组合名称
                        String fileSrc=""; 
                        //是否随机名称
                        if(isRandomName){
                            origName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+UUID.randomUUID().toString()+origName.substring(origName.lastIndexOf("."));
                        }
                        //判断是否存在目录
                        File Fpath=new File(path);
                        if(!Fpath.exists()){
                        	Fpath.mkdirs();//创建目录
                        }
                        
                        //上传
                        File targetFile=new File(path,origName);
                        file.transferTo(targetFile);
	                    //完整路径
	                    fileSrc="zhsl/"+origName;
                        return fileSrc;
                }
            }
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
