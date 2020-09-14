package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;
import cyberdebik.enums.FuelType;
import cyberdebik.enums.VehicleType;


/**
 * Класс команды remove_greater
 */
public class Remove_lower extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		String[] strings = data.split(" ");
		Vehicle remove_lower = new Vehicle(strings[0], strings[1],
				strings[2], strings[3], strings[4], VehicleType.valueOf(strings[5].toUpperCase()), FuelType.valueOf(strings[6].toUpperCase()));
		long maxVh = remove_lower.getSize();
		long collection_size = vehicleList.vehicles.size();
		vehicleList.vehicles.removeIf(vehicle -> vehicle.getSize() < maxVh);
		if (vehicleList.vehicles.size() < collection_size) {
			System.out.println("Элементы удалены");
			vehicleList.vehicles.add(remove_lower);
		} else {
			System.out.println("Элементов меньше заданного нет");
		}
	}
}
