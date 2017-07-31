package cn.wsxiot;

import cn.wsxiot.server.main.ServerMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IotwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotwebApplication.class, args);
		//start server for connecting gateway and android, webserver
		new ServerMain().start();
	}
}
