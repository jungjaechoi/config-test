package com.example.configuration;

import com.example.configuration.Service.ZkService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Text;

import java.io.InputStream;

@Configuration
public class SpringConfig {
    private ZkService zs;

    @Autowired
    public SpringConfig(ZkService zs){
        this.zs = zs;
    }

    @Bean
    public ZooKeeper GetZooKeeperConnection(){
        try{
            ZooKeeper zk = zs.connect("172.20.2.201:2181");
            return zk;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
