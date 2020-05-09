package com.monk.myreader.controller;

import com.monk.myreader.dto.Result;
import com.monk.myreader.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @author :kuexun
 * @description : todo
 * @create : 2019/10/15 16:27
 */
@RestController
@Api("文件上传")
@RequestMapping("file")
public class FileController{

    @Autowired
    FileService fileService;
    private String[] types = {"", "head_link", "book", "banner","picture"};

    /**
     * 说明：文件上传
     *
     * @param file
     * @param type: 1:头像/2:书籍/3:首页轮播图
     * @return 路径
     */
    @PostMapping
    @ApiOperation(value = "文件上传", notes = "1:头像/2:书籍/3:首页轮播图/4:书籍封面")
    public Result uploadImg(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam Integer type
    ) {
        String picturesUrl = fileService.save(file,types[type]);
        if(picturesUrl != null) {
            return Result.SUCCESS(picturesUrl);
        }
        return Result.FAIL("失败");
    }

    @DeleteMapping("/file/{dir}/{name}")
    @ApiOperation("文件删除")
    public Result deleteImg(
            @PathVariable("dir") String dir,
            @PathVariable("name") String name
    ) {
        String path = File.separator+"file"+File.separator+dir+File.separator+name;
        if(fileService.delete(path)) {
            return Result.SUCCESS("成功");
        }
        return Result.FAIL("失败");
    }

    @GetMapping("/file/{dir}/{name}")
    @ApiOperation("文件下载")
    public Result downloadTxt(
            @PathVariable("dir") String dir,
            @PathVariable("name") String name,
            @RequestParam(value = "range",required = false) Long start,
            HttpServletResponse response
    ) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        String path = "file"+File.separator+dir+File.separator+name;
        File file = fileService.get(path);
        if(file != null){
            System.out.println(file.getName()+start);
            try(
                    InputStream in = new BufferedInputStream(new FileInputStream(file), 4096);
                    OutputStream os = new BufferedOutputStream(response.getOutputStream())
            ) {
                if(start != null) in.skip(start);
                response.setContentLength(in.available());
                byte[] bytes = new byte[4096];
                int i = 0;
                while ((i = in.read(bytes)) > 0) {
                    os.write(bytes, 0, i);
                }
                os.flush();
                return null;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return Result.FAIL("失败");
    }
}
