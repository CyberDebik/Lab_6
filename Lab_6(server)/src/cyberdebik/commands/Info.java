package cyberdebik.commands;

import cyberdebik.VehicleList;

/**
 * Класс команды info
 */
public class Info extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		answer = "Тип коллекции - HashSet\n" + vehicleList.vehicles.size() + " элементов в коллекции" + "\nДата инициализации: " + vehicleList.initializationDate;
	}
}
