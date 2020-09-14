package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;
import cyberdebik.enums.FuelType;
import cyberdebik.enums.VehicleType;

/**
 * Класс команды add
 */
public class Add extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data)/* throws IOException*/ {
		String[] strings = data.split(" ");
		try {
			vehicleList.vehicles.add(new Vehicle(strings[0], strings[1],
					strings[2], strings[3], strings[4], VehicleType.valueOf(strings[5].toUpperCase()), FuelType.valueOf(strings[6].toUpperCase())));
			answer = "Элемент добавлен";
		} catch (Exception e) {
			answer = "Элемент не добавлен";
		}
	}
}
