package cyberdebik.commands;

import cyberdebik.Message;

/**
 * Абстрактный класс команд
 */
public abstract class Command implements ICommand {
	public String answer;
	public Message message = new Message("0", " ");
}
