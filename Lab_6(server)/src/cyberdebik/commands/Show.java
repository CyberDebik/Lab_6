package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;

/**
 * Класс команды show
 */
public class Show extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		if (vehicleList.vehicles.isEmpty()) {
			answer = "Коллекция пустая";
		} else {
			for (Vehicle vehicle : vehicleList.vehicles) {
				answer += vehicle + "\n";
			}
		}
	}
}