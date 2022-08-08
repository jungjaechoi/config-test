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

		String private_dns = "172.16.155.51";

		try{
			JSch jsch = new JSch();
			System.out.println("-------------------------------------");
			Session session = jsch.getSession("jj", private_dns, 22);
			session.setPassword("8989");
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("-------------------------------------");
			session.connect();  //연결
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

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					outputBuffer.append(new String(tmp, 0, i));
					if (i < 0) break;
				}
				if (channel.isClosed()) {
					System.out.println("결과");
					System.out.println(outputBuffer.toString());
					channel.disconnect();
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}

	}

}
