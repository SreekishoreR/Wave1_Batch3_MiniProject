package com.studyopedia.com;
import java.util.*;


// ================= VEHICLE =================
class Vehicle {
    private String number;
    private String rfid;
    private long entryTime;

    public Vehicle(String number, String rfid) {
        this.number = number;
        this.rfid = rfid;
    }

    public String getNumber() {
        return number;
    }

    public String getRfid() {
        return rfid;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }
}

// ================= BASE SLOT =================
abstract class ParkingSlot {
    private int slotId;
    private boolean occupied;
    private Vehicle vehicle;

    public ParkingSlot(int id) {
        this.slotId = id;
    }

    public int getSlotId() {
        return slotId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void parkVehicle(Vehicle v) {
        this.vehicle = v;
        this.occupied = true;
        v.setEntryTime(System.currentTimeMillis());
        System.out.println("Vehicle " + v.getNumber() + " parked at Slot " + slotId);
    }

    public Vehicle removeVehicle() {
        Vehicle temp = vehicle;
        vehicle = null;
        occupied = false;
        return temp;
    }

    public abstract double calculateFee(long duration);
}

// ================= SLOT TYPES =================
class CarSlot extends ParkingSlot {
    public CarSlot(int id) { super(id); }
    public double calculateFee(long duration) {
        double hours = Math.ceil(duration / (1000.0 * 60 * 60));
        return hours * 50;
    }
}

class BikeSlot extends ParkingSlot {
    public BikeSlot(int id) { super(id); }
    public double calculateFee(long duration) {
        double hours = Math.ceil(duration / (1000.0 * 60 * 60));
        return hours * 20;
    }
}

// ================= FLOOR =================
class ParkingFloor {
    private int floorNumber;
    private List<ParkingSlot> slots;

    public ParkingFloor(int floorNumber, int carSlots, int bikeSlots) {
        this.floorNumber = floorNumber;
        slots = new ArrayList<>();
        int id = floorNumber * 100;

        for (int i = 0; i < carSlots; i++)
            slots.add(new CarSlot(++id));

        for (int i = 0; i < bikeSlots; i++)
            slots.add(new BikeSlot(++id));
    }

    public ParkingSlot allocateSlot(String type) {
        for (ParkingSlot s : slots) {
            if (!s.isOccupied()) {
                if (type.equalsIgnoreCase("car") && s instanceof CarSlot)
                    return s;
                if (type.equalsIgnoreCase("bike") && s instanceof BikeSlot)
                    return s;
            }
        }
        return null;
    }

    public void showStatus() {
        System.out.println("\nFloor " + floorNumber + " Status:");
        for (ParkingSlot s : slots) {
            System.out.println("Slot " + s.getSlotId() + " -> " + (s.isOccupied() ? "Occupied" : "Available"));
        }
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }
}

// ================= RESERVATION SYSTEM =================
class ReservationSystem {
    private Map<String, String> reservations = new HashMap<>();

    public void reserve(String vehicleNo, String type) {
        reservations.put(vehicleNo, type);
        System.out.println("Booking Confirmed for " + vehicleNo + " (" + type + ")");
    }

    public String getReservationType(String number) {
        return reservations.getOrDefault(number, null);
    }

    public void cancel(String number) {
        reservations.remove(number);
    }
}

// ================= PARKING LOT =================
class ParkingLot {
    private List<ParkingFloor> floors = new ArrayList<>();
    private ReservationSystem reservation = new ReservationSystem();

    public ParkingLot() {
        floors.add(new ParkingFloor(1, 3, 3));
        floors.add(new ParkingFloor(2, 3, 3));
    }

    // ===== Mobile Booking =====
    public void bookSlot(String vehicleNo, String type) {
        reservation.reserve(vehicleNo, type);
    }

    // ===== RFID Entry =====
    public void rfidEntry(Vehicle v) {
        String reservedType = reservation.getReservationType(v.getNumber());
        String type = reservedType != null ? reservedType :
                (v.getNumber().startsWith("B") ? "bike" : "car");

        for (ParkingFloor f : floors) {
            ParkingSlot s = f.allocateSlot(type);
            if (s != null) {
                s.parkVehicle(v);
                reservation.cancel(v.getNumber());
                return;
            }
        }
        System.out.println("No Available Slots!");
    }

    // ===== Vehicle Exit =====
    public void vehicleExit(String rfid) {
        for (ParkingFloor f : floors) {
            for (ParkingSlot s : f.getSlots()) {
                if (s.isOccupied() && s.getVehicle().getRfid().equals(rfid)) {
                    Vehicle v = s.removeVehicle();
                    long duration = System.currentTimeMillis() - v.getEntryTime();
                    double bill = s.calculateFee(duration);

                    System.out.println("\nVehicle Exiting: " + v.getNumber());
                    System.out.println("Duration: " + (duration/1000) + " sec");
                    System.out.println("Bill ₹" + bill);
                    System.out.println("Payment Successful!");
                    return;
                }
            }
        }
        System.out.println("RFID not found!");
    }

    public void showAvailability() {
        for (ParkingFloor f : floors)
            f.showStatus();
    }
}

// ================= MAIN =================
public class SmartParkingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingLot lot = new ParkingLot();

        while (true) {
            System.out.println("\n===== ADVANCED SMART PARKING =====");
            System.out.println("1. Mobile Booking");
            System.out.println("2. RFID Entry");
            System.out.println("3. Vehicle Exit");
            System.out.println("4. Show Availability");
            System.out.println("5. Exit System");
            System.out.print("Enter Choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter Vehicle Number: ");
                    String vNo = sc.nextLine();
                    System.out.print("Enter Type (car/bike): ");
                    String type = sc.nextLine();
                    lot.bookSlot(vNo, type);
                    break;

                case 2:
                    System.out.print("Enter Vehicle Number: ");
                    String vn = sc.nextLine();
                    System.out.print("Enter RFID Tag: ");
                    String tag = sc.nextLine();
                    lot.rfidEntry(new Vehicle(vn, tag));
                    break;

                case 3:
                    System.out.print("Enter RFID Tag: ");
                    String exitTag = sc.nextLine();
                    lot.vehicleExit(exitTag);
                    break;

                case 4:
                    lot.showAvailability();
                    break;

                case 5:
                    System.out.println("System Closed.");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
