package com.monk.myreader.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2019/10/15 16:30
 */
public interface FileService{
    String saveFiles(MultipartFile[] files, String type);

    String save(MultipartFile file, String type);

    boolean delete(String fileUrl);

    File get(String path);
}
