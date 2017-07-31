package cn.wsxiot.server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.wsxiot.server.main.InitServer.MAXNUMTHREAD;
import static cn.wsxiot.server.main.InitServer.SERVERPORT;

public class ServerMain
{
    private ExecutorService executorService = Executors.newFixedThreadPool(MAXNUMTHREAD); // 线程池

    public void start(){
        InitServer.init();
        System.out.println("Server started");
        try {
            ServerSocket serverSocket = new ServerSocket(SERVERPORT);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new TerminalThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
