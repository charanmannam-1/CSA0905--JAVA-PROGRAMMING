# CSA09 - Assignment 1 | Campus Smart-Parking System

**Subject:** Programming in Java (CO1)  
**Assignment Title:** Design, Implement and Validate an Object-Oriented Model for a Campus Smart-Parking System

---

### Student Details
- **Name:** _______________________________
- **Register Number:** ____________________
- **Java Version:** 17+ (OpenJDK)
- **Submission:** GitHub only (as instructed)

---

## Folder Structure (Click any Task)

```
CampusSmartParking/
├── README.md
├── Task01/          → Problem Analysis & Objects
├── Task02/          → Data Representation
├── Task03/          → Control Logic + Complete the Code (ParkingFacility)
├── Task04/          → Classes, Objects & Constructors
├── Task05/          → static Members & Object Life Cycle
├── Task06/          → Inheritance Hierarchy
├── Task07/          → Method Overriding
├── Task08/          → Polymorphism + Complete the Code (FeeDemo)
├── Task09/          → Abstraction (Abstract class + Interface)
├── Task10/          → Packages & Encapsulation
├── Task11/          → Full Implementation, Testing & Defence
└── Task12/          → Day-4 Design Challenge (EBike, DeliveryVan, OneDayPass)
```

---

## How to Run the Main Codes

### Task 3 – ParkingFacility (Complete the Code)
```bash
cd Task03
javac ParkingFacility.java
java ParkingFacility
```
**Expected Output:**
```
[0, 0]
[0, 1]
[1, 0]
null
true false false
[0, 1]
```

### Task 8 – FeeDemo (Inheritance + Overriding + Polymorphism + Interface)
```bash
cd Task08
javac FeeDemo.java
java FeeDemo
```
**Expected Output:**
```
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```

### Task 11 – Complete Working System
```bash
cd Task11
javac -d . parking/model/*.java parking/service/*.java parking/app/*.java
java parking.app.SmartParkingDemo
```

### Task 12 – New Vehicle Types (EBike + DeliveryVan + OneDayPass)
New classes are already placed inside `Task11/parking/model/` and also kept in `Task12/` for reference.

---

## Design Highlights

- Jagged array (`String[][]`) for floors with different number of slots
- Abstract class `Vehicle` + interface `Chargeable`
- Method overriding + Dynamic method dispatch
- Static ticket counter
- Proper packages: `parking.model`, `parking.service`, `parking.app`
- New vehicle types added in Task 12 **without modifying** existing working classes

---

**Note:** All “Complete the Code” programs produce the exact expected output required by the assignment.
