package com.example.configuration.Controller;

import com.example.configuration.Model.jsonToCompare;
import com.example.configuration.Service.TextChanges;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;

@RestController
public class ConfigController {

    private TextChanges tc;


    @Autowired
    public ConfigController(TextChanges tc){
        this.tc = tc;
    }

    @PostMapping("/diff")
    public String getDiffernece(@RequestBody jsonToCompare jsonToCompare){
        return tc.getChanges(jsonToCompare.getOldValue(),jsonToCompare.getNewValue());

    }


    @PostMapping("/update")
    public String updateConfig(@RequestBody String newValue){

        try{
            System.out.println(newValue);
            FileWriter file = new FileWriter("/conf/global.json");
            file.write(newValue);
            file.flush();
            file.close();
            return "success";
        }
        catch(Exception e){
            System.out.println("Error:"+e);
            return "fail";
        }


//        try{
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(configString);
//            JSONObject jsonObj = (JSONObject) obj;
//
//            JSch jsch=new JSch();
//
//            String user = "ec2-user";
//            String host = "54.178.100.107";
//            int port = 22;
//            String privateKey = "/home/test_key.pem";
//
//            jsch.addIdentity(privateKey);
//            System.out.println("identity added ");
//
//            Session session = jsch.getSession(user, host, port);
//            System.out.println("session created.");
//
//
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//
//            session.connect();
//
//            Channel channel = session.openChannel("exec");  //채널접속
//            ChannelExec ce = (ChannelExec) channel; //명령 전송 채널사용
//            ce.setPty(true);
//            System.out.println("-------------------------------------");
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
    }

}
