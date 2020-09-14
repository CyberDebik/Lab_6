package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;

/**
 * Класс команды filter_greater_than_type
 */
public class Filter_greater_than_type extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		String[] strings = data.split(" ");
		String type = strings[0].trim();
		String number = strings[1].trim();
		float more_than = Float.parseFloat(number);
		switch (type) {
			case ("coordinates"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (more_than < vehicle.getCoordinates()) {
						System.out.println(vehicle);
					}
				}
				break;
			case ("enginePower"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (more_than < vehicle.getEnginePower()) {
						System.out.println(vehicle);
					}
				}
				break;
			case ("numberOfWheels"):
				for (Vehicle vehicle : vehicleList.vehicles) {
					if (more_than < vehicle.getNumberOfWheels()) {
						System.out.println(vehicle);
					}
				}
				break;
			default:
				System.out.println("Такого поля нет");
		}
	}
}