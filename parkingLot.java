class ParkingLot {
	List<ParkingFloor> parkingFloors;
	List <Entrance> entrances;
	Lost<Exit> exits;
	Address address;
	String parkingName;

	public boolean isParkingSpaceAvailableForVehicle(Vehicle vehicle);
	public boolean updateParkingAttendent(ParkingAttendent parkingAttendent, int gateId);
}
class ParkingFloor {
	int levelId;
	boolean isFull;
	List<ParkingSpace> parkingSpace;
	ParkingDisplayBoard parkingDisplayBoard;
}

class Gate {
	int gateId;
	ParkingAttendent parkingAttendent;
}
class Entrance extends Gate {
	public ParkingTicket getParkingTicket(Vehicle vehicle);
}
class Exit extends Gate {
	public ParkingTicket payForParking(ParkingTicket parkingTicket);
}
class Address {
	String country;
	String state;
	String city;
	String street;
	String pinCode;
}
class ParkingSpace {
	int spaceId;
	boolean isFree;
	double costPerHour;
	Vehicle vehicle;
	ParkingSpaceType parkingSpaceType;
}
class ParkingDisplayBoard {
	Map<ParkingSpace, Integer> freeSpotsAvailableMap;
	public void updateFreeSpotsAvailable(ParkingSpaceType parkingSpaceType, int spaces);
}

class Account {
	String name;
	String email;
	String password;
	String empId;
	Address address;
}
class Admin extends Account {
	public boolean addParkingFloor(ParkingLot ParkingLot, ParkingFloor floor);
	public boolean addParkingSpace(ParkingFloor parkingFloor, ParkingSpace parkingSpace);
	public boolean addParkingDisplayBoard(ParkingFloor parkingFloor, ParkingDisplayBoard parkingDisplayBoard);
	.
	.
	.

}


class ParkingAttendent extends Account {
	Payment paymentService;
	public boolean processVehicleEntry(Vehicle vehicle);
	public PaymentInfo processPayment(parkingTicket parkingTicket, PaymentType paymentType);
}

class Vehicle {
	String licenseNumber;
	VehicleType vehicleType;
	ParkingTicket parkingTicket;
	PaymentInfo paymentInfo;
}

class ParkingTicket {
	int ticketId;
	int levelId;
	int spaceId;
	Date vehicleEntryTime;
	Date vehicleExitTime;
	ParkingSpaceType ParkingSpaceType;
	double totalCost;
	ParkingTicketStatus parkingTicketStatus;
	public void updateTotalCost();
	public void updateVehicleExitTime(Date vehicleExitTime);

}
public enum PaymentType {
	CASH, CREDIT_CARD, DEBIT_CARD, UPI;
}
public enum ParkingSpaceType {
	BIKE_PARKING, CAR_PARKING, TRUCK_PARKING;
}

class Payment {
	public PaymentInfo(ParkingTicket parkingTicket, PaymentType);
}
public class PaymentInfo {
	double amount;
	Date paymentDate;
	int transactionId;
	ParkingTicket parkingTicket;
	PaymentStatus paymentStatus;
}

public enum VehicleType {
	CAR, BIKE, TRUCK;
}
public enum ParkingTicketStatus {
	PAID, ACTIVE;
}
public enum PaymentStatus {
	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED;
}



