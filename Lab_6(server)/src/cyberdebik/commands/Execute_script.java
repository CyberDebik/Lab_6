package cyberdebik.commands;

import cyberdebik.VehicleList;
import cyberdebik.app.Check;
import cyberdebik.classes.Vehicle;
import cyberdebik.enums.FuelType;
import cyberdebik.enums.VehicleType;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * Класс команды execute_script
 */
public class Execute_script extends Command {

	/**
	 * Метод выполнения команды
	 */
	@Override
	public void execute(VehicleList vehicleList, String data) {
		try {
			String[] strings = data.split(" ");
			String file_name = strings[0].trim();
			try {
				File file = new File("/home/s284260/Lab6/" + file_name);
				if (file.exists()) {
					Scanner reader = new Scanner(file);
					Scanner scanner;
					while (reader.hasNextLine()) {
						String line = reader.nextLine();
						scanner = new Scanner(line);
						while (scanner.hasNext()) {
							String command = scanner.nextLine();
							String[] stringsWithCommand = command.split(" ");
							switch (stringsWithCommand[0].toUpperCase()) {
								case "ADD": {
									try {
										if (Check.checkFloat(stringsWithCommand[2]) && Check.checkMoreThan_minus_615(stringsWithCommand[2]) && Check.checkFloat(stringsWithCommand[3]) && Check.checkInt(stringsWithCommand[4]) && Check.checkPositive(stringsWithCommand[4]) && Check.checkLong(stringsWithCommand[5]) && Check.checkPositive(stringsWithCommand[5])) {
											if (stringsWithCommand.length == 8) {
												vehicleList.vehicles.add(new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], VehicleType.valueOf(stringsWithCommand[6].toUpperCase()), FuelType.valueOf(stringsWithCommand[7].toUpperCase())));
												answer += "Элемент добавлен";
											} else if (stringsWithCommand.length == 7) {
												vehicleList.vehicles.add(new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], null, FuelType.valueOf(stringsWithCommand[6].toUpperCase())));
												answer += "Элемент добавлен";
											}
										} else {
											answer += "Данные введены неверно";
										}
									} catch (Exception e) {
										answer += "Данные введены неверно";
									}
									break;
								}
								case "ADD_IF_MAX": {
									try {
										if (Check.checkFloat(stringsWithCommand[2]) && Check.checkMoreThan_minus_615(stringsWithCommand[2]) && Check.checkFloat(stringsWithCommand[3]) && Check.checkInt(stringsWithCommand[4]) && Check.checkPositive(stringsWithCommand[4]) && Check.checkLong(stringsWithCommand[5]) && Check.checkPositive(stringsWithCommand[5])) {
											long maxVh = 0;
											for (Vehicle vehicle : vehicleList.vehicles) {
												if (maxVh < vehicle.getSize()) {
													maxVh = vehicle.getSize();
												}
											}
											if (stringsWithCommand.length == 8) {
												Vehicle add_if_max = new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], VehicleType.valueOf(stringsWithCommand[6].toUpperCase()), FuelType.valueOf(stringsWithCommand[7].toUpperCase()));
												vehicleList.vehicles.add(add_if_max);
												if (add_if_max.getSize() <= maxVh) {
													vehicleList.vehicles.remove(add_if_max);
													answer += "Элемент не максимальный";
												} else {
													answer += "Элемент добавлен";
												}
											} else if (stringsWithCommand.length == 7) {
												Vehicle add_if_max = new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], null, FuelType.valueOf(stringsWithCommand[6].toUpperCase()));
												vehicleList.vehicles.add(add_if_max);
												if (add_if_max.getSize() <= maxVh) {
													vehicleList.vehicles.remove(add_if_max);
													answer += "Элемент не максимальный";
												} else {
													answer += "Элемент добавлен";
												}
											}
										} else {
											answer += "Данные введены неверно";
										}
									} catch (Exception e) {
										answer += "Данные введены неверно";
									}
									break;
								}
								case "UPDATE": {
									try {
										if (Check.checkFloat(stringsWithCommand[3]) && Check.checkMoreThan_minus_615(stringsWithCommand[3]) && Check.checkFloat(stringsWithCommand[4]) && Check.checkInt(stringsWithCommand[5]) && Check.checkPositive(stringsWithCommand[5]) && Check.checkLong(stringsWithCommand[6]) && Check.checkPositive(stringsWithCommand[6])) {
											ZonedDateTime time = null;
											long id = Long.parseLong(stringsWithCommand[1]);
											for (Vehicle vehicle : vehicleList.vehicles) {
												if (id == vehicle.getID()) {
													time = vehicle.getCreationDate();
												}
											}
											if (stringsWithCommand.length == 9) {
												if (vehicleList.vehicles.removeIf(vehicle -> vehicle.getID() == id)) {
													vehicleList.vehicles.removeIf(vehicle -> vehicle.getID() == id);
													vehicleList.vehicles.add(new Vehicle(id, stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], time, stringsWithCommand[5], stringsWithCommand[6], VehicleType.valueOf(stringsWithCommand[7].toUpperCase()), FuelType.valueOf(stringsWithCommand[8].toUpperCase())));
													answer += "Элемент обновлён";
												} else {
													answer += "Элемента с таким id нет";
												}
											} else if (stringsWithCommand.length == 8) {
												if (vehicleList.vehicles.removeIf(vehicle -> vehicle.getID() == id)) {
													vehicleList.vehicles.removeIf(vehicle -> vehicle.getID() == id);
													vehicleList.vehicles.add(new Vehicle(id, stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], time, stringsWithCommand[5], stringsWithCommand[6], null, FuelType.valueOf(stringsWithCommand[7].toUpperCase())));
													answer += "Элемент обновлён";
												} else {
													answer += "Элемента с таким id нет";
												}
											}
										} else {
											answer += "Данные введены неверно";
										}
									} catch (Exception e) {
										answer += "Данные введены неверно";
									}
									break;
								}
								case "REMOVE_GREATER": {
									try {
										if (Check.checkFloat(stringsWithCommand[2]) && Check.checkMoreThan_minus_615(stringsWithCommand[2]) && Check.checkFloat(stringsWithCommand[3]) && Check.checkInt(stringsWithCommand[4]) && Check.checkPositive(stringsWithCommand[4]) && Check.checkLong(stringsWithCommand[5]) && Check.checkPositive(stringsWithCommand[5])) {
											if (stringsWithCommand.length == 8) {
												Vehicle remove_greater = new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], VehicleType.valueOf(stringsWithCommand[6].toUpperCase()), FuelType.valueOf(stringsWithCommand[7].toUpperCase()));
												long maxVh = remove_greater.getSize();
												if (!vehicleList.vehicles.isEmpty()) {
													vehicleList.vehicles.removeIf(vehicle -> vehicle.getSize() > maxVh);
													answer += "Элементы удалены";
												} else {
													answer += "Коллекция пуста";
												}
												vehicleList.vehicles.add(remove_greater);
											} else if (stringsWithCommand.length == 7) {
												Vehicle remove_greater = new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], null, FuelType.valueOf(stringsWithCommand[6].toUpperCase()));
												long maxVh = remove_greater.getSize();
												if (!vehicleList.vehicles.isEmpty()) {
													vehicleList.vehicles.removeIf(vehicle -> vehicle.getSize() > maxVh);
													answer += "Элементы удалены";
												} else {
													answer += "Коллекция пуста";
												}
												vehicleList.vehicles.add(remove_greater);
											} else {
												answer += "Элементов больше этого нет";
											}
										} else {
											answer += "Данные введены неверно";
										}
									} catch (Exception e) {
										answer += "Данные введены неверно";
									}
									break;
								}
								case "REMOVE_LOWER":
									try {
										if (Check.checkFloat(stringsWithCommand[2]) && Check.checkMoreThan_minus_615(stringsWithCommand[2]) && !Check.checkFloat(stringsWithCommand[3]) && Check.checkInt(stringsWithCommand[4]) && Check.checkPositive(stringsWithCommand[4]) && Check.checkLong(stringsWithCommand[5]) && Check.checkPositive(stringsWithCommand[5])) {
											if (stringsWithCommand.length == 8) {
												Vehicle remove_greater = new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], VehicleType.valueOf(stringsWithCommand[6].toUpperCase()), FuelType.valueOf(stringsWithCommand[7].toUpperCase()));
												long maxVh = remove_greater.getSize();
												if (!vehicleList.vehicles.isEmpty()) {
													vehicleList.vehicles.removeIf(vehicle -> vehicle.getSize() < maxVh);
													answer += "Элементы удалены";
												} else {
													answer += "Коллекция пуста";
												}
												vehicleList.vehicles.add(remove_greater);
											} else if (stringsWithCommand.length == 7) {
												Vehicle remove_greater = new Vehicle(stringsWithCommand[1], stringsWithCommand[2], stringsWithCommand[3], stringsWithCommand[4], stringsWithCommand[5], null, FuelType.valueOf(stringsWithCommand[6].toUpperCase()));
												long maxVh = remove_greater.getSize();
												if (!vehicleList.vehicles.isEmpty()) {
													vehicleList.vehicles.removeIf(vehicle -> vehicle.getSize() < maxVh);
													answer += "Элементы удалены";
												} else {
													answer += "Коллекция пуста";
												}
												vehicleList.vehicles.add(remove_greater);
											} else {
												answer += "Элементов меньше этого нет";
											}
										} else {
											answer += "Данные введены неверно";
										}
									} catch (Exception e) {
										answer += "Данные введены неверно";
									}
									break;
								default:
									answer += vehicleList.commandChoose(command) + "\n";
									break;
							}
						}
					}
					reader.close();
				} else {
					answer += "Такого файла с командами не существует";
				}
			} catch (Exception e) {
				answer += "Этот файл прочитать нельзя";
			}
		} catch (Error e) {
			answer += "Вы запустили бесконечный цикл, он будет прерван";
		}
	}
}