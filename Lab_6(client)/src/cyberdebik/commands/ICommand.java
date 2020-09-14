package cyberdebik.commands;

import cyberdebik.Message;

/**
 * Интерфейс команд
 */
public interface ICommand {
	Message execute(String data) throws Exception;
}
