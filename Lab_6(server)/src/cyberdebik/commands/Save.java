package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Класс команды save
 */
public class Save extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
//		File file = new File(System.getenv("COLLECTION"));
		File file = new File("src/cyberdebik/Vehicles.csv");
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			for (Vehicle vehicle : vehicleList.vehicles) {
				bufferedWriter.write(vehicle.info_for_file());
			}
			bufferedWriter.close();
			answer = "Коллекция сохранена в файл " + file.getAbsolutePath();
		} catch (Exception e) {
			answer = "Нельзя записать коллекцию в этот файл";
		}
	}
}
