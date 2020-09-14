package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;
import cyberdebik.enums.FuelType;
import cyberdebik.enums.VehicleType;

import java.util.Collections;

/**
 * Класс команды add_if_max
 */
public class Add_if_max extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		String[] strings = data.split(" ");
		try {
			Vehicle add_if_max = new Vehicle(strings[0], strings[1],
					strings[2], strings[3], strings[4], VehicleType.valueOf(strings[5].toUpperCase()), FuelType.valueOf(strings[6].toUpperCase()));
			if (vehicleList.vehicles.isEmpty()) {
				vehicleList.vehicles.add(add_if_max);
				answer = "Элемент добавлен";
			} else {
				if (add_if_max.compareTo(Collections.max(vehicleList.vehicles)) <= 0) {
					answer = "Элемент не максимальный";
				} else {
					vehicleList.vehicles.add(add_if_max);
					answer = "Элемент добавлен";
				}
			}
		} catch (Exception e) {
			answer = "Неверный ввод";
		}
	}
}
