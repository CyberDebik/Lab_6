package cyberdebik;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Postman {

	public static Logger log = Logger.getGlobal();
	static ByteBuffer buffer = ByteBuffer.allocate(1024);

	public static String readClient(SelectionKey selectionKey) throws IOException, ClassNotFoundException {
		SocketChannel channel = (SocketChannel) selectionKey.channel();
		channel.read(buffer);
		buffer.flip();
		buffer.clear();
		byte[] bytes = buffer.array();
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(b);
		Message message = (Message) objectInputStream.readObject();
		log.info("Сервер получил сообщение = " + message.getCommand() + ";" + message.getData() + " от " + channel.getLocalAddress() + ".\n");
		return message.getCommand() + " " + message.getData();
	}

	public static void sendToClient(SelectionKey selectionKey, String answer) throws IOException {
		SocketChannel channel = (SocketChannel) selectionKey.channel();
		log.info("Отправляю сообщение на " + channel.getLocalAddress() + "\n");
		channel.write(ByteBuffer.wrap(answer.getBytes()));
	}
}
