package com.example.configuration;

import org.apache.catalina.connector.Response;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

@RestController
public class ZkController {

    private ZkService zs;
    private ZooKeeper zk;

    @Autowired
    public ZkController(ZkService zs, ZooKeeper zk){
        this.zs = zs;
        this.zk = zk;
    }

    @PostMapping("/create")
    public String createNode(@RequestBody cNode cNode){

        byte[] data = cNode.getData().getBytes();

        try{
            zs.create(cNode.getPath(), data, zk); // node 생성
            return "success";
        }
        catch(Exception e){
            System.out.println(e);
            return "fail";
        }
    }

    @GetMapping("/get")
    public String getData(@RequestParam String path){

        try{
            Stat stat = zs.znode_exists(path,zk);

            if (stat != null) {
                byte[] dataByte = zk.getData(path, false, null);
                String pathData = new String(dataByte, "UTF-8");
                System.out.println("node : " + path + " , data : " + pathData);
//                FileWriter file = new FileWriter("/json/test.json");
//                file.write(pathData);
//                file.flush();
//                file.close();

//                JSONParser parser = new JSONParser();
//                BufferedReader br = new BufferedReader(new FileReader("/json/test.json"));
//                System.out.println("@@@@");
//                while(true) {
//                    String line = br.readLine();
//                    if (line==null) break;  // 더 이상 읽을 라인이 없을 경우 while 문을 빠져나간다.
//                    System.out.println(line);
//                }
//
//                br.close();

            }
            else{
                System.out.println("Node does not exists");
            }
            return "success";

        }
        catch(Exception e){
            System.out.println(e);
            return "fail";
        }
    }

}

