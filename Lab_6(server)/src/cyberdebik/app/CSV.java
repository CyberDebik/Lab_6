package cyberdebik.app;

import cyberdebik.VehicleList;
import cyberdebik.classes.Vehicle;
import cyberdebik.enums.FuelType;
import cyberdebik.enums.VehicleType;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * Класс для чтения коллекции из файла
 */
public class CSV {

	public static void read(VehicleList vehicleList) {
//		File file = new File(System.getenv("COLLECTION"));
		File file = new File("src/cyberdebik/Vehicles.csv");
		try {
			Scanner reader = new Scanner(file);
			String line;
			int index = 0;
			String name = null;
			String x = null;
			String y = null;
			ZonedDateTime date = ZonedDateTime.now();
			String enginePower = null;
			String numberOfWheels = null;
			VehicleType type = null;
			FuelType fuelType = null;
			while (reader.hasNextLine()) {
				line = reader.nextLine();
				Scanner scanner = new Scanner(line);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					String info_from_file = scanner.next();
					if (index == 0) {
						name = info_from_file;
					} else if (index == 1) {
						if (Check.checkFloat(info_from_file) && Check.checkMoreThan_minus_615(info_from_file)) {
							x = info_from_file;
						} else {
							System.out.println(name + " -> Координата X введена неверно");
						}
					} else if (index == 2) {
						if (Check.checkFloat(info_from_file)) {
							y = info_from_file;
						} else {
							System.out.println(name + " -> Координата Y введена неверно");
						}
					} else if (index == 3) {
						try {
							date = ZonedDateTime.parse(info_from_file);
						} catch (Exception e) {
							System.out.println(name + " -> Неправильный формат времени");
						}
					} else if (index == 4) {
						if (Check.checkInt(info_from_file) && Check.checkPositive(info_from_file)) {
							enginePower = info_from_file;
						} else {
							System.out.println(name + " -> Мощность двигателя введена неверно");
						}
					} else if (index == 5) {
						if (Check.checkLong(info_from_file) && Check.checkPositive(info_from_file)) {
							numberOfWheels = info_from_file;
						} else {
							System.out.println(name + " -> Количество колёс введено неверно");
						}
					} else if (index == 6) {
						if (info_from_file.equals("PLANE") || info_from_file.equals("DRONE") || info_from_file.equals("BICYCLE") || info_from_file.equals("MOTORCYCLE"))
							type = VehicleType.valueOf(info_from_file);
						else if (info_from_file.equals("null") || info_from_file.trim().equals(""))
							type = null;
						else
							System.out.println(name + " -> Тип транспорта введён неверно");
					} else if (index == 7) {
						try {
							fuelType = FuelType.valueOf(info_from_file.toUpperCase());
						} catch (Exception e) {
							System.out.println(name + " -> Тип топлива введён неверно");
						}
					}
					index++;
				}
				try {
					vehicleList.vehicles.add(new Vehicle(name, x, y, date, enginePower, numberOfWheels, type, fuelType));
				} catch (Exception e) {
					System.out.println("|> Данные введены неверно, элемент " + name + " не добавлен");
				}
				index = 0;
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Файл с коллекцией прочитать нельзя");
		}
	}
}