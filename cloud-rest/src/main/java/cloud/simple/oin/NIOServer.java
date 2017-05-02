package cloud.simple.oin;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	private Selector selector;
	
	public void initServer(int port) throws IOException{
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress(port));
		this.selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	

	public void listen() throws IOException{
		System.out.println("服务端启动。。。。。。。。。");
		while(true){
			selector.select();
			@SuppressWarnings("rawtypes")
			Iterator it = this.selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key = (SelectionKey) it.next();  
				it.remove();
				
				if(key.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel)key.channel();
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("向客户端发了一个信息").getBytes()));
					channel.register(this.selector,SelectionKey.OP_READ);
				}else{}
			}
		}
	}
}
