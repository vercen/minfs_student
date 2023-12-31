package com.ksyun.campus.dataserver.controller;

import com.ksyun.campus.dataserver.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class DataController {
    @Autowired
    DataService dataService;
    /**
     * 1、读取request content内容并保存在本地磁盘下的文件内
     * 2、同步调用其他ds服务的write，完成另外2副本的写入
     * 3、返回写成功的结果及三副本的位置
     * @param fileSystem
     * @param path
     * @param offset
     * @param length
     * @return
     */
    @RequestMapping("write")
    public ResponseEntity writeFile(@RequestHeader String fileSystem,
                                    @RequestParam String path,
                                    @RequestParam int offset,
                                    @RequestParam int length){
        // 1. 读取request content内容并保存在本地磁盘下的文件内
        // TODO: Implement file writing logic

        // 2. 同步调用其他ds服务的write，完成另外2副本的写入
//        dataService.write(fileSystem, path, offset, length);

        // 3. 返回写成功的结果及三副本的位置
        // TODO: Construct and return appropriate response

//        return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 在指定本地磁盘路径下，读取指定大小的内容后返回
     * @param fileSystem
     * @param path
     * @param offset
     * @param length
     * @return
     */
    @RequestMapping("read")
    public ResponseEntity readFile(@RequestHeader String fileSystem, @RequestParam String path, @RequestParam int offset, @RequestParam int length){

        // 在指定本地磁盘路径下，读取指定大小的内容后返回
        byte[] content = dataService.read(path, offset, length);


        return new ResponseEntity(HttpStatus.OK);
    }
    /**
     * 关闭退出进程
     */
    @RequestMapping("shutdown")
    public void shutdownServer(){
        System.exit(-1);
    }
}
