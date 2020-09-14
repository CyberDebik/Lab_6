package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;

/**
 * Класс команды group_counting_by_number_of_wheels
 */
public class Group_counting_by_number_of_wheels extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		int even = 0;
		int odd = 0;
		for (Vehicle vehicle : vehicleList.vehicles) {
			if (vehicle.getNumberOfWheels() % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		answer = "Транспорт с четным количеством колёс: " + even +
				"\nТранспорт с нечётным количеством колёс: " + odd;
	}
}

