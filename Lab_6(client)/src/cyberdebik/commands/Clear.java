package cyberdebik.commands;

import cyberdebik.Message;

/**
 * Класс команды clear
 */
public class Clear extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public Message execute(String data) {
		String[] strings = data.split(" ");
		if (!strings[0].isEmpty()) {
			System.out.println("Команда введена неверно");
		} else {
			message = new Message("CLEAR", " ");
		}
		return message;
	}
}
