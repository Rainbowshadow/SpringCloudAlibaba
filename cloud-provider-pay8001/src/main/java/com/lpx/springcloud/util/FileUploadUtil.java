package com.lpx.springcloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.lpx.springcloud.model.CommonResult;


@Component
public class FileUploadUtil {
	
	@Value("${upload.path}")
	String path;//路径
	
	
	//Base64 解密
	public CommonResult<String> basic64ImageUpload(String imageString) {
		
		if(imageString==null || imageString ==""){
			return new CommonResult<String>(200,"请选择上传的图片");
		}
		//创建basic64 解密对象  
		Decoder decoder = Base64.getDecoder();
		//解密  并得到字节数组
		byte[] img = decoder.decode(imageString);
		String imgName = UUID.randomUUID().toString()+".jpg";
		//判断文件夹是否存在不存在则创建
		File file=new File(path+imgName);
		if(!file.getParentFile().exists()){
			new File(file.getParent()).mkdirs();
		}
		//创建文件输出流
		FileOutputStream fileOut=null ;
		try {
			fileOut = new FileOutputStream(file);//
			//输出
			fileOut.write(img);
			//关闭输出流
			fileOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new CommonResult<String>(200,"上传成功，路径为："+"/gateway/"+imgName);
	}
	
	
	//Base64加密
	public String basic64ImageEnload(String pathImg) {
		//创建加密对象
		Encoder encode = Base64.getEncoder();
		//得到图片
		//File file=new File("E:\\Image\\2.jpg");
		File file=new File(pathImg);
		byte[] buffer=new byte[(int) file.length()];
		try {
			FileInputStream fileInput=new FileInputStream(file);
			
			fileInput.read(buffer);
			fileInput.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//加密成字符串
		String imgString = encode.encodeToString(buffer);
		return imgString;
	}
	
	
	//MultipartFile   图片上传
	public CommonResult<String> multipartFile(MultipartFile mm) {
		
		if(mm==null){
			return new CommonResult<String>(200,"请选择上传的图片");
		}
		//得到图片名字截取后缀并生成新的名字
		String oldName = mm.getOriginalFilename();
		String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."));
		//创建输出流
		File file = new File(path+newName);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
			
		try {
			//上传
			mm.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new CommonResult<String>(200,"上传成功，路径为："+"/gateway/"+newName,"/gateway/"+newName);
	}
	
	

}
