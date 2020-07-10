package com.pet.model.common.file;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pet.exception.FileSaveException;

@Component
public class FileManager {
	
	//파일명 생성하기!
	public static String createFilename(String path) {
		
		long time = System.currentTimeMillis();
		String ext = getExt(path); //확장자를 얻어옴!!
		String filename = time+"."+ext;
		return filename;
	}
	
	//확장자만 추출하기
	public static String getExt(String path) {
		
		int index = path.lastIndexOf(".");
		String ext = path.substring(index+1 , path.length());		
		return ext;
	}	
	
	//지정한 경로에 파일 저장하기!!
	public static String saveFile(MultipartFile myFile, String realPath) throws FileSaveException {
		boolean flag=false;
		String ori = myFile.getOriginalFilename();
		System.out.println("파일 명은 " +ori);			
		
		System.out.println(realPath);
		String filename=realPath+FileManager.createFilename(ori);
		File dest = new File(filename);
		
		try {
			myFile.transferTo(dest);//디스크에 저장!!
			flag=true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			flag=false;
		}
		if(flag == false) {
			throw new FileSaveException("파일 저장에 실패하였습니다");
		}
		return dest.getName();
		
	}
}
