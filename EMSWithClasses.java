import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EMSWithClasses {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Welcome to Employee Management System (EMS)");
		Map<Integer, Employee> empDB = new HashMap<>();
		empDB = load(args[0]);
		empDB = menu(empDB);
		save(args[0], empDB);

	}
//methods

	// menu
	private static Map<Integer, Employee> menu(Map<Integer, Employee> empDB) {
		Scanner keyboard = new Scanner(System.in);
		String userInput;
		int userChoice = 0;
		boolean completedFunction;

		while (userChoice != 7) {
			userChoice = 0;
			System.out.println("Main Menu:\n" + "1.	Add an Employee\n" + "2.	Find an Employee (By Employee ID)\n"
					+ "3.	Find an Employee (By Name)\n" + "4.	Delete an Employee\n" + "5.	Display Statistics\n"
					+ "6.	Display All Employees\n" + "7.	Exit");

			completedFunction = false;
			while (completedFunction == false) {
				System.out.println("Enter your selection (1..7):");
				userInput = keyboard.nextLine();
				userChoice = Integer.parseInt(userInput);
				
				switch (userChoice) {
				case 1:
					empDB = addEmployee(keyboard, empDB);
					completedFunction = true;
					break;
				case 2:
					findEmployeeByEID(keyboard, empDB);
					completedFunction = true;
					break;
				case 3:
					findEmployeeByName(keyboard, empDB);
					completedFunction = true;
					break;
				case 4:
					empDB = deleteEmployee(keyboard, empDB);
					completedFunction = true;
					break;
				case 5:
					displayStatistics(keyboard, empDB);
					completedFunction = true;
					break;
				case 6:
					displayEmployees(empDB);
					completedFunction = true;
					break;
				case 7:
					System.out.println("Thank you for using Employee Management System (EMS)");
					completedFunction = true;
					break;
				default:
					System.out.println("Invalid selection.");
					break;

				}
			}
		}
		return empDB;
	}

	// case 1 add employee
	private static Map<Integer, Employee> addEmployee(Scanner keyboard, Map<Integer, Employee> empDB)
			throws NumberFormatException {
		// loop to get empID. if ID already exists in bigger map, make them put in a new one
		// put employee in map using ID int, create hash map for employee and set key
		// EID and value to ID
		// set key Name and set value with user input
		// set key department "
		// set key title "
		// set key salary and get salary with user input, do not allow it to be negative

		boolean validKey = false;
		Integer employeeID = null;
		int ID = 0;
		String userInput = "0";
		
		while (validKey == false) {
			System.out.println("Enter an employee ID or QUIT to stop:");
			userInput = keyboard.nextLine();
			if ("QUIT".equalsIgnoreCase(userInput))
				return empDB;
			ID = Integer.parseInt(userInput);
			employeeID = ID;
			if (employeeID <= 0)
				System.out.println("Invalid ID. Employee ID should be a positive integer number.");
			else if (empDB.containsKey(employeeID))
				System.out.printf("Employee ID: %s already exists in the database.\n", userInput);
			else
				validKey = true;
		}

		Employee employee = new Employee();

		employee.setEID(employeeID);
		System.out.println("Enter employee name:");
		userInput = keyboard.nextLine();
		employee.setName(userInput);
		System.out.println("Enter employee department:");
		userInput = keyboard.nextLine();
		employee.setDepartment(userInput);
		System.out.println("Enter employee title:");
		userInput = keyboard.nextLine();
		employee.setTitle(userInput);

		double salary = 0;
		boolean validSalary = false;
		
		while (validSalary == false) {
			System.out.println("Enter employee salary:");
			userInput = keyboard.nextLine();
			salary = Double.parseDouble(userInput);
			if (salary <= 0)
				System.out.println("Invalid salary. Salary should be a positive number.");
			else
				validSalary = true;
		}
		
		Double salaryObj = null;
		salaryObj = salary;
		employee.setSalary(salaryObj);

		empDB.put(employeeID, employee);
		return empDB;
	}

// case 2 find by employee ID
	private static void findEmployeeByEID(Scanner keyboard, Map<Integer, Employee> empDB) {
		System.out.println("Enter an employee ID or QUIT to stop:");
		String userInput = keyboard.nextLine();
		if (!"QUIT".equalsIgnoreCase(userInput)) {
			empDB.get(Integer.parseInt(userInput)).printAll();
		}
	}

	// case 3 find by name
	private static void findEmployeeByName(Scanner keyboard, Map<Integer, Employee> empDB) throws ClassCastException {
		System.out.println("Enter an employee name or QUIT to stop:");
		String userInput = keyboard.nextLine();
		if (!"QUIT".equalsIgnoreCase(userInput)) {
			ArrayList<Employee> matches = new ArrayList<Employee>();
			for (Employee employee : empDB.values()) {
				if ((employee.getName()).equalsIgnoreCase(userInput))
					matches.add(employee);
			}
			System.out.printf("Found %d employee%s with that name.\n", matches.size(), matches.size() == 1 ? "" : "s");
			if (matches.size() != 0) {
				for (Employee employee : matches) {
					employee.printAll();
				}
			}

		}
		return;
	}

// case 4 delete employee from database
	private static Map<Integer, Employee> deleteEmployee(Scanner keyboard, Map<Integer, Employee> empDB) {
		System.out.println("Enter an employee ID or QUIT to stop:");
		String userInput = keyboard.nextLine();
		String employeeID;
		if (!"QUIT".equalsIgnoreCase(userInput)) {
			System.out.printf("Employee ID: %s will be deleted from the database. Are you sure (y/n)?\n", userInput);
			employeeID = userInput;
			userInput = keyboard.nextLine();
			if ("Y".equalsIgnoreCase(userInput)) {
				empDB.remove(Integer.parseInt(employeeID));
				System.out.printf("Employee ID: %s was deleted from the database.\n", employeeID);
			} else {
				System.out.println("Employee deletion was cancelled.");
			}
		}
		return empDB;
	}

	// case 5 display statistics. separate employees by department and get the mean median and mode of their salaries 
	private static void displayStatistics(Scanner keyboard, Map<Integer, Employee> empDB) {
		if (empDB.size() == 0) {
			System.out.println("There are no departments in the database.");
			System.out.println("Employee database is empty.");
		} else {
			System.out.println("Department Statistics:");
			Map<String, ArrayList<Employee>> statMAP = new HashMap<String, ArrayList<Employee>>();
			for (Employee employee : empDB.values()) {
				if (!statMAP.containsKey(employee.getDepartment())) {
					ArrayList<Employee> departmentEmployeeArray = new ArrayList<Employee>();
					departmentEmployeeArray.add(employee);
					statMAP.put(employee.getDepartment(), departmentEmployeeArray);
				} else {
					statMAP.get(employee.getDepartment()).add(employee);
				}
			}

			double companyMinimum = 0;
			double companyMaximum = 0;
			double companyTotal = 0;
			
			for (String department : statMAP.keySet()) {
				double departmentMinimum = 0;
				double departmentMaximum = 0;
				double departmentTotal = 0;
				double employeeSalary = 0;
				
				for (Employee employee : statMAP.get(department)) {
					employeeSalary = employee.getSalary();
					if (employeeSalary > departmentMaximum)
						departmentMaximum = employeeSalary;
					if (departmentMinimum == 0)
						departmentMinimum = employeeSalary;
					else if (employeeSalary < departmentMinimum)
						departmentMinimum = employeeSalary;
					if (employeeSalary > companyMaximum)
						companyMaximum = employeeSalary;
					if (employeeSalary < companyMinimum)
						companyMinimum = employeeSalary;
					departmentTotal = departmentTotal + employeeSalary;
				}
				
				System.out.printf("\tDepartment: %s - %d employee%s\n", department, statMAP.get(department).size(),
						statMAP.get(department).size() == 1 ? "" : "s");
				System.out.printf("\t\tMaximum Salary: $%10.2f\n", departmentMaximum);
				System.out.printf("\t\tMinimum Salary: $%10.2f\n", departmentMinimum);
				System.out.printf("\t\tAverage Salary: $%10.2f\n", departmentTotal / statMAP.get(department).size());
				companyTotal = companyTotal + departmentTotal;
			}
			
			System.out.printf("There %s %d department%s in the database.\n", statMAP.size() == 1 ? "is" : "are",
					statMAP.size(), statMAP.size() == 1 ? "" : "s");
			System.out.printf("There %s %d employee%s in the database.\n", empDB.size() == 1 ? "is" : "are",
					empDB.size(), empDB.size() == 1 ? "" : "s");
		}
	}

	// case 6 display all employees
	private static void displayEmployees(Map<Integer, Employee> empDB) {
		if (empDB.size() == 0) {
			System.out.println("Employee database is empty.");
		} else {
			for (Employee employee : empDB.values()) {
				employee.printAll();

			}
			System.out.printf("There %s %d employee%s in the database.\n", empDB.size() == 1 ? "is" : "are",
					empDB.size(), empDB.size() == 1 ? "" : "s");
		}
	}

	// save to file location specified in command argument
	private static void save(String filename, Map<Integer, Employee> empDB) throws FileNotFoundException {
		PrintWriter outputFile = new PrintWriter(filename);
		for (Employee employee : empDB.values()) {
			String employeeLine = employee.saveValues();
			outputFile.println(employeeLine);
		}
		outputFile.close();
	}

//load file specified in command argument
	private static Map<Integer, Employee> load(String filename) throws FileNotFoundException {
		File loadedDB = new File(filename);
		Map<Integer, Employee> empDB = new HashMap<>();
		if (!loadedDB.exists()) {
			System.out.printf("Database input file %s not found.\nCreating an empty database.\n", filename);
		} else {
			Scanner inputFile = new Scanner(loadedDB);
			while (inputFile.hasNext()) {
				String line = inputFile.nextLine();
				String[] wordArray = line.split(",");
				Employee employee = new Employee();
				int eID = Integer.parseInt(wordArray[0]);
				Integer ID = eID;
				employee.setEID(ID);
				employee.setName(wordArray[1]);
				employee.setDepartment(wordArray[2]);
				employee.setTitle(wordArray[3]);
				double salary = Double.parseDouble(wordArray[4]);
				Double salaryObj = salary;
				employee.setSalary(salaryObj);
				empDB.put(ID, employee);
			}
			inputFile.close();
		}
		return empDB;
	}

//call when developing
	private static void notImplemented() {
		System.out.println("This feature is not implemented yet.");
	}

}
