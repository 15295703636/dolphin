package org.cs.dp.oss.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface FileService {
	 Map<String, Object> upload(MultipartFile file);

	 String download(String path);

	 String delete(String path);
	 void downLoad(String group, String path, String fileName, HttpServletResponse response);
}
