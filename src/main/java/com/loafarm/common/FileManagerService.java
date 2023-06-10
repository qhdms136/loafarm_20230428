package com.loafarm.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // spring bean
public class FileManagerService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 실제 업로드 된 이미지가 저장될 경로(서버)
	// 상수 선언시 대문자로
	// D:\\장보은\\project\\loafarm\\workspace\\images/
<<<<<<< HEAD
	// public static final String FILE_UPLOAD_PATH = "C:\\jbe\\project\\loafarm\\workspace\\images/";
	 public static final String FILE_UPLOAD_PATH = "/home/ec2-user/images/";
	
=======

	public static final String FILE_UPLOAD_PATH = "C:\\jbe\\project\\loafarm\\workspace\\images/";

>>>>>>> master
	// input : MultipartFile(이미지 파일), loginId(이미지가 겹치지 않게 하기위해)
	// output : image path(String)
	public String saveFile(String loginId, MultipartFile file) {
		// 파일 디렉토리(폴더) ex) aaaa_2023050601/good.png
		String directoryName = loginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName; // ex) D:\\장보은\\project\\loafarm\\workspace\\images/aaaa_2023050601/good.png
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			return null;	// 폴더를 만드는데 실패 시 이미지 경로 null
		}
		// 파일 업로드 : byte 단위로 업로드
		try {
				byte[] bytes = file.getBytes();
				
				// 파일명 암호화
				// 파일명의 한글깨짐 방지를 위해 UTF-8로 변환
				String origName = new String(file.getOriginalFilename().getBytes("8859_1"), "UTF-8");
				String ext = origName.substring(origName.lastIndexOf("."));	// 확장자 추출
				String saveFileName = getUuid() + ext;	// 암호화된(Uuid) + 확장자명 >> 랜덤으로 암호화됨
				
				Path path = Paths.get(filePath + saveFileName);
				Files.write(path, bytes);	// 파일 업로드
			
				return "/images/" + directoryName + saveFileName;
		} catch (IOException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	// uuid 생성
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
		
	// 이미지 제거
	public void deleteFile(String imagePath) {
		// 겹치는 images 경로를 제거 - imagePath 안에있는 /images/ 구문 제거
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		// 이미지 삭제
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("[이미지 삭제] 이미지 삭제 실패. imagePath: {}", imagePath);
				return;
			}
		}
		
		// 디렉토리 폴더 삭제
		path = path.getParent();
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("[이미지 삭제] 디렉토리 삭제 실패. imagePath:{}", imagePath);
				// 메소드의 끝이기 때문에 return 안해도된다.
			}
		}
	}
}
