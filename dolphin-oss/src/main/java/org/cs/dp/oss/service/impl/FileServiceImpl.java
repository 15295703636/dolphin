package org.cs.dp.oss.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import org.cs.dp.oss.config.FastDFSClientWrapper;
import org.cs.dp.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FastDFSClientWrapper dfsClient;

	@Override
	public Map<String, Object> upload(MultipartFile file) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("returnCode", "1000");
		try {
			StorePath path = dfsClient.uploadFile(file);
			retMap.put("returnData", path);
		}catch (Exception e){
			retMap.put("returnCode", "1001");
			e.printStackTrace();
		}
		return retMap;
	}

	@Override
	public String download(String path) {
		return dfsClient.donloadFile(path);
	}

	@Override
	public String delete(String id) {
		dfsClient.deleteFile(id);
		return "ok";
	}

	@Override
	public void downLoad(String group, String path, String fileName, HttpServletResponse response) {
		try {
			dfsClient.downLoad(group, path, fileName, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
