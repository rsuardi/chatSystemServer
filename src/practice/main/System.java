package practice.main;

import practice.repository.Server;

import java.io.IOException;

public class System {

    public static void main(String[] args) {

        boolean isRunning = true;
        try {
            Server server = new Server("servidor", 1234,"localhost");
            while(isRunning){
                server.startServer();
                //server.startServer2();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
