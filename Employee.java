import java.util.HashMap;
import java.util.Map;

public class Employee {
	
	private Map<String, Object> employee = new HashMap<>();
//set
	public void setEID(Integer employeeID) {	
		employee.put("EID", employeeID);		
	}
	
	public void setName(String employeeName) {
		employee.put("Name", employeeName);
	}
	
	public void setDepartment(String employeeDepartment ) {
		employee.put("Department", employeeDepartment);
	}
	
	public void setTitle(String employeeTitle) {
		employee.put("Title", employeeTitle);
	}
	
	public void setSalary(Double employeeSalary) {
		employee.put("Salary", employeeSalary);
	}
//print	
	public void printAll() {
		System.out.println("Employee ID: " + employee.get("EID"));
		System.out.printf("\tName: %s\n", employee.get("Name"));
		System.out.printf("\tDepartment: %s\n", employee.get("Department"));
		System.out.printf("\tTitle: %s\n", employee.get("Title"));
		System.out.printf("\tSalary: %,.2f\n", employee.get("Salary"));
	}
//get	
	public Integer getEID() {
		return (Integer) employee.get("EID");
	}
	
	public String getName() {
		return (String) employee.get("Name");
	}
	
	public String getDepartment() {
		return (String) employee.get("Department");
	}
	
	public String getTitle() {
		return (String) employee.get("Title");
	}
	
	public Double getSalary() {
		return (Double) employee.get("Salary");
	}
//save
	public String saveValues() {
		String values;
		values = (employee.get("EID") + "," + employee.get("Name") + "," + employee.get("Department") + "," + employee.get("Title") + "," + employee.get("Salary"));
		return values;
	}
}
