package org.cs.dp.oss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dp.oss.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@Api("文件系统接口API")
public class FileController {

	@Resource
	private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping(value = "/upload")
    public Map<String,Object> upload(MultipartFile file) throws IOException {
        return fileService.upload(file);
    }
    
    /**
     * 下载文件
     * <a href="<%=request.getContextPath() %>/download.do?id=8000001">下载照片</a> 
     */
    @ApiOperation("文件下载")
    @GetMapping(value="/down")
    public String download(@RequestParam("path")String path,
                         HttpServletResponse response){
    	return fileService.download(path);
    }
    
    /**
     * 显示byte图片文件
     * <img src="<%=request.getContextPath() %>/getPhotoById.do?id=8000001"/>  
     */
    @ApiOperation("文件删除")
	@DeleteMapping(value="/delete")
    public void delete (@RequestParam("path")String path,
                          HttpServletResponse response, HttpServletRequest request){
		fileService.delete(path);
	}

    @ApiOperation("文件下载")
    @GetMapping("/download")
    public void downLoad(@RequestParam String group, @RequestParam String path, @RequestParam String fileName, HttpServletResponse response)  {
        fileService.downLoad(group, path, fileName, response);
    }

}
