package cyberdebik.classes;

import cyberdebik.enums.FuelType;
import cyberdebik.enums.VehicleType;

import java.time.ZonedDateTime;

/**
 * Класс транспорта
 */
public class Vehicle implements Comparable<Vehicle> {

	/**
	 * ID объекта
	 */
	private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

	/**
	 * Имя объекта
	 */
	private String name; //Поле не может быть null, Строка не может быть пустой

	/**
	 * Координаты объекта
	 */
	private Coordinates coordinates; //Поле не может быть null

	/**
	 * Дата создания объекта
	 */
	private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

	/**
	 * Мощность объекта
	 */
	private Integer enginePower; //Поле не может быть null, Значение поля должно быть больше 0

	/**
	 * Кол-во колёс объекта
	 */
	private long numberOfWheels; //Значение поля должно быть больше 0

	/**
	 * Тип объекта
	 */
	private VehicleType type; //Поле может быть null

	/**
	 * Тип топлива объекта
	 */
	private FuelType fuelType; //Поле не может быть null

	public Vehicle() {
	}

	public Vehicle(String name, String x, String y, String enginePower, String numberOfWheels, VehicleType type, FuelType fuelType) {
		id = (long) hashCode();
		this.name = name;
		this.coordinates = new Coordinates(x, y);
		creationDate = ZonedDateTime.now();
		this.enginePower = Integer.parseInt(enginePower);
		this.numberOfWheels = Long.parseLong(numberOfWheels);
		this.type = type;
		this.fuelType = fuelType;
	}

	public Vehicle(String name, String x, String y, ZonedDateTime creationDate, String enginePower, String numberOfWheels, VehicleType type, FuelType fuelType) {
		id = (long) hashCode();
		this.name = name;
		this.coordinates = new Coordinates(x, y);
		this.creationDate = creationDate;
		this.enginePower = Integer.parseInt(enginePower);
		this.numberOfWheels = Long.parseLong(numberOfWheels);
		this.type = type;
		this.fuelType = fuelType;
	}

	public Vehicle(Long id, String name, String x, String y, ZonedDateTime creationDate, String enginePower, String numberOfWheels, VehicleType type, FuelType fuelType) {
		this.id = id;
		this.name = name;
		this.coordinates = new Coordinates(x, y);
		this.creationDate = creationDate;
		this.enginePower = Integer.parseInt(enginePower);
		this.numberOfWheels = Long.parseLong(numberOfWheels);
		this.type = type;
		this.fuelType = fuelType;
	}

	public Vehicle(Long id, String name, String x, String y, String enginePower, String numberOfWheels, VehicleType type, FuelType fuelType) {
		this.id = id;
		this.name = name;
		this.coordinates = new Coordinates(x, y);
		this.enginePower = Integer.parseInt(enginePower);
		this.numberOfWheels = Long.parseLong(numberOfWheels);
		this.type = type;
		this.fuelType = fuelType;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#name}
	 *
	 * @return Возвращает название транспорта
	 */
	public String getName() {
		return name;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#id}
	 *
	 * @return Возвращает ID объекта
	 */
	public long getID() {
		return id;
	}

	/**
	 * Метод для получения размера объекта ({@link Vehicle#enginePower} складывается с {@link Vehicle#numberOfWheels})
	 *
	 * @return Возвращает размер объекта
	 */
	public long getSize() {
		return enginePower + numberOfWheels;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#enginePower}
	 *
	 * @return Возвращает мощность объекта
	 */
	public int getEnginePower() {
		return enginePower;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#coordinates}
	 *
	 * @return Возвращает координаты объекта
	 */
	public float getCoordinates() {
		return coordinates.getX() + coordinates.getY();
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#creationDate}
	 *
	 * @return Возвращает время создания объекта
	 */
	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#numberOfWheels}
	 *
	 * @return Возвращает кол-во колес объекта
	 */
	public Long getNumberOfWheels() {
		return numberOfWheels;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#type}
	 *
	 * @return Возвращает тип объекта
	 */
	public VehicleType getType() {
		return type;
	}

	/**
	 * Метод для получения значения поля {@link Vehicle#fuelType}
	 *
	 * @return Возвращает тип топлива объекта
	 */
	public FuelType getFuelType() {
		return fuelType;
	}

	/**
	 * Метод для получения информации об объекте
	 *
	 * @return Возвращает информацию об объекте
	 */
	@Override
	public String toString() {
		return "\n" + name + "\n|id: " + id + "\n" +
				"|Coordinates: (" + coordinates.getX() + "; " + coordinates.getY() + ")\n" +
				"|Creation date: " + getCreationDate() + "\n" +
				"|Engine power: " + getEnginePower() + "\n" +
				"|Number of wheels: " + getNumberOfWheels() + "\n" +
				"|Vehicle type: " + getType() + "\n" +
				"|Fuel type: " + getFuelType();
	}

	/**
	 * Метод для получения информации об объекте для записи в файл
	 *
	 * @return Возвращает информацию об объекте
	 */
	public String info_for_file() {
		return name + "," + coordinates.getX() + "," + coordinates.getY() + "," + creationDate + "," + enginePower + "," + numberOfWheels + "," + type + "," + fuelType + "\n";
	}

	@Override
	public int compareTo(Vehicle anotherVehicle) {
		if (getSize() > anotherVehicle.getSize()) {
			return 1;
		} else if (getSize() == anotherVehicle.getSize()) {
			return 0;
		} else {
			return -1;
		}
	}
}
