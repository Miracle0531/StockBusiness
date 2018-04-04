package websocket;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@SuppressWarnings("all")
public class WebSocketServer {
    private final int port;  
  
    public WebSocketServer(int port) {  
        this.port = port;  
    }  
  
    public void run() {
        // 设置 Socket channel factory  
        ServerBootstrap bootstrap = new ServerBootstrap(  
                new NioServerSocketChannelFactory(  
                        Executors.newCachedThreadPool(),  
                        Executors.newCachedThreadPool()));  
  
        // 设置 Socket pipeline factory  
        bootstrap.setPipelineFactory(new WebSocketServerPipelineFactory());  
  
        // 启动服务，开始监听  
        bootstrap.bind(new InetSocketAddress(port));  
  
        // 打印提示信息  
        System.out.println("Web socket server started at port " + port + '.');  
        System.out.println("Open your browser and navigate to http://localhost:" + port + '/');
    }  
  
    public static void main(String[] args) {  
        int port;  
        if (args.length > 0) {  
            port = Integer.parseInt(args[0]);  
        } else {  
            port = 8080;  
        }  
        new WebSocketServer(port).run();  
    }  
}  
