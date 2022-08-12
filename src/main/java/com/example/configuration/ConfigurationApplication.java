package com.example.configuration;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;

@SpringBootApplication
public class ConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationApplication.class, args);
	}

}
//channelExec.setCommand("./test.sh"); //내가 실행시킬 명령어를 입력
//
//		//콜백을 받을 준비.
//		StringBuilder outputBuffer = new StringBuilder();
//		InputStream in = channel.getInputStream();
//		((ChannelExec) channel).setErrStream(System.err);
//
//		channel.connect();  //실행