package com.example.configuration;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication
public class ConfigurationApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConfigurationApplication.class, args);



		try{

			JSch jsch=new JSch();

			String user = "ec2-user";
			String host = "54.250.3.218";
			int port = 22;
			String privateKey = "/home/test_key.pem";

			jsch.addIdentity(privateKey);
			System.out.println("identity added ");

			Session session = jsch.getSession(user, host, port);
			System.out.println("session created.");


			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			session.connect();
			System.out.println("-------------------------------------");
			Channel channel = session.openChannel("exec");  //채널접속
			ChannelExec channelExec = (ChannelExec) channel; //명령 전송 채널사용
			channelExec.setPty(true);
			channelExec.setCommand("./test.sh"); //내가 실행시킬 명령어를 입력

			//콜백을 받을 준비.
			StringBuilder outputBuffer = new StringBuilder();
			InputStream in = channel.getInputStream();
			((ChannelExec) channel).setErrStream(System.err);

			channel.connect();  //실행
			System.out.println("-------------------------------------");

		}
		catch(Exception e){
			System.out.println(e);
		}

	}

}
