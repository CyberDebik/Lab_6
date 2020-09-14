package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;
import cyberdebik.enums.FuelType;
import cyberdebik.enums.VehicleType;

import java.time.ZonedDateTime;

/**
 * Класс команды update
 */
public class Update extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		String[] strings = data.split(" ");
		ZonedDateTime time = null;
		long id = Long.parseLong(strings[0].trim());
		for (Vehicle vehicle : vehicleList.vehicles) {
			if (id == vehicle.getID()) {
				time = vehicle.getCreationDate();
			}
		}
		if (vehicleList.vehicles.removeIf(vehicle -> vehicle.getID() == id)) {
			vehicleList.vehicles.removeIf(vehicle -> vehicle.getID() == id);
			vehicleList.vehicles.add(new Vehicle(strings[0], strings[1],
					strings[2], time, strings[3], strings[4], VehicleType.valueOf(strings[5].toUpperCase()), FuelType.valueOf(strings[6].toUpperCase())));
			answer = "Элемент обновлён";
		} else {
			answer = "Элемента с таким id нет";
		}
	}
}
