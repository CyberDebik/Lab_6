package cyberdebik.commands;

import cyberdebik.VehicleList;

/**
 * Класс команды remove_by_id
 */
public class Remove_by_id extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		String[] strings = data.split(" ");
		long id = Long.parseLong(strings[0].trim());
		if (vehicleList.vehicles.removeIf(vehicle -> id == vehicle.getID())) {
			vehicleList.vehicles.removeIf(vehicle -> id == vehicle.getID());
			answer = "Элемент удалён";
		} else {
			answer = "Элемента с таким id нет";
		}
	}
}
