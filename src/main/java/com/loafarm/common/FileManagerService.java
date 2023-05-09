package com.loafarm.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // spring bean
public class FileManagerService {
	
	// 실제 업로드 된 이미지가 저장될 경로(서버)
	// 상수 선언시 대문자로
	public static final String FILE_UPLOAD_PATH = "D:\\장보은\\project\\loafarm\\workspace\\images/";
	
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
}
