package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.NumberOfWheelsComparator;
import cyberdebik.classes.Vehicle;

import java.util.ArrayList;

/**
 * Класс команды print_field_ascending_number_of_wheels
 */
public class Print_field_ascending_number_of_wheels extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		ArrayList<Vehicle> arrayList = new ArrayList<>(vehicleList.vehicles);
		arrayList.sort(new NumberOfWheelsComparator());
		for (Vehicle vehicle : arrayList) {
			answer = "У " + vehicle.getName() + " " + vehicle.getNumberOfWheels() + " колёс";
		}
	}
}
