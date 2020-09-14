package cyberdebik;

import cyberdebik.app.CSV;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static cyberdebik.Postman.*;

public class Main_Server {

	public static final int PORT = 8000;
	private static final VehicleList vehicleList = new VehicleList();
	public static boolean running = true;
	private static String result = "";

	public static void main(String[] args) throws Exception {
		CSV.read(vehicleList);
		log.info("Сервер начал работать.\nПорт = " + PORT + "\nОжидаю подключения клиентов");
		connect();
	}

	public static void close() {
		log.info("Завершение программы");
		System.exit(1);
	}

	public static void connect() throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		Selector selector = Selector.open();
		serverSocketChannel.bind(new InetSocketAddress(PORT));
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, serverSocketChannel.validOps());
		while (running) {
			selector.select(1000);
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				try {
					if (selectionKey.isAcceptable()) {
						SocketChannel client = serverSocketChannel.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
						log.info(client.getLocalAddress() + " подключен.\n");
						client.write(ByteBuffer.wrap(("Вы подключены к адресу " + serverSocketChannel.getLocalAddress()).getBytes()));
					} else if (selectionKey.isReadable()) {
						result = readClient(selectionKey);
						selectionKey.interestOps(SelectionKey.OP_WRITE);
					} else if (selectionKey.isWritable()) {
						String answer = vehicleList.commandChoose(result);
						sendToClient(selectionKey, answer);
						selectionKey.interestOps(SelectionKey.OP_READ);
					}
				} catch (IOException e) {
					log.info("Клиент отключился");
					selectionKey.cancel();
				}
				iterator.remove();
			}
		}
		selector.close();
		serverSocketChannel.close();
	}
}
