package Accounts.Server.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee) throws SQLException;
    void updateEmployee(Employee employee) throws SQLException;
    void deleteEmployee(int id) throws SQLException;
    Employee employeeLogin(int id) throws SQLException;
    List<Employee> getEmployees() throws SQLException;
    Employee employeeById(int id) throws SQLException;
}
