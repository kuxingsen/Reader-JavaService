package com.monk.myreader.service.impl;

import com.monk.myreader.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2019/10/15 16:30
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService{
    @Value("${file.uploadRootDir}")
    private String uploadRootDir;

    @Override
    public String saveFiles(MultipartFile[] files, String type) {
        StringBuilder picturesUrl = new StringBuilder();
        for(int i = 0; i < files.length; i++) {
            picturesUrl.append(save(files[i], type)).append("|");
        }
        if(picturesUrl.length() > 0){
            picturesUrl.deleteCharAt(picturesUrl.length()-1);
        }
        return picturesUrl.toString();
    }

    @Override
    public String save(MultipartFile file, String type) {
        String path = uploadRootDir + "file";
        File dir = new File(path, type);
        if(!dir.getParentFile().exists()) {
            boolean mk = dir.getParentFile().mkdirs();
            log.error("create: file " + mk);
        }
        if(!dir.exists()) {
            boolean mk = dir.mkdirs();
            log.error("create:" + mk + "---" + type);
        }
        String fileName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString().replace("-", "") + "_"+fileName;
        log.info("上传的文件名：{}  并改成 {}", fileName, newName);
        File tempFile = new File(dir, newName);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return File.separator+"file"+File.separator+type+File.separator+newName;
    }

    @Override
    public boolean delete(String fileUrl) {
        String path = uploadRootDir;
        File dir = new File(path, fileUrl);
        if(dir.exists()) {
            boolean res = dir.delete();
            if(!res) {
                log.error("Failed to delete file");
            }
            return res;
        } else {
            log.error("Failed to find file {}", dir.getAbsolutePath());
        }
        return false;
    }

    @Override
    public File get(String path) {
        File file = new File(uploadRootDir, path);
        System.out.println(uploadRootDir+path);
        if(file.exists()) {
            return file;
        }
        return null;
    }

}
