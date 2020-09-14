package cyberdebik;

import java.io.*;
import java.net.Socket;

public class Connection {
	private static final VehicleList vehicleList = new VehicleList();
	public static int PORT = 8000;
	public static String HOST = "localhost";

	public static void connect() throws Exception {
		while (true) {
			try (Socket client = new Socket(HOST, PORT)) {
				System.out.print("\r");
				work(client);
			} catch (IOException e) {
				System.err.print("Нет связи с сервером.\r");
			}
		}
	}

	public static void work(Socket socket) throws Exception {
		try {
			getAnswer(socket);
			while (true) {
				Message message;
				message = vehicleList.commandChoose();
				sendMessage(message, socket);
				if (message.getCommand().equals("EXIT")) {
					System.out.println("Завершение программы");
					System.exit(0);
				} else {
					getAnswer(socket);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Нет связи с сервером");
		}
	}

	public static void sendMessage(Message message, Socket socket) throws IOException {
		OutputStream sender = socket.getOutputStream();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(message);
		objectOutputStream.flush();
		sender.write(byteArrayOutputStream.toByteArray());
	}

	public static void getAnswer(Socket socket) throws Exception {
		InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String answer = "";
		char readChar = (char) reader.read();
		answer += readChar;
		while (reader.ready()) {
			readChar = (char) reader.read();
			answer += readChar;
		}
		System.out.println(answer);
	}
}
