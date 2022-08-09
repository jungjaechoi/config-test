package com.example.configuration;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private ZkService zs;

    @Autowired
    public SpringConfig(ZkService zs){
        this.zs = zs;
    }

    @Bean
    public ZooKeeper createConnectoin(){
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
