package cyberdebik.commands;

import cyberdebik.VehicleList;

/**
 * Класс команды clear
 */
public class Clear extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		if (!vehicleList.vehicles.isEmpty()) {
			vehicleList.vehicles.clear();
			answer = "Коллекция очищена";
		} else {
			answer = "Коллекция пуста";
		}
	}
}
