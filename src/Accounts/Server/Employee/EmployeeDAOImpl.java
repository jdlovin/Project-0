package Accounts.Server.Employee;

import Accounts.Server.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final Statement statement = null;
    Connection connection = null;

    public EmployeeDAOImpl() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {

        String sql = "insert into employee (firstName, lastName, email, userName, passWord) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getEmail());
        preparedStatement.setString(4, employee.getUserName());
        preparedStatement.setString(5, employee.getPassWord());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Employee added");
        else
            System.out.println("Oh no! Something went wrong");
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "update employee set firstName = ?, lastName = ?, email = ?, passWord = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getEmail());
        preparedStatement.setString(4, employee.getPassWord());
        preparedStatement.setInt(5, employee.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Employee updated");
        else
            System.out.println("Oh no! Something went wrong");
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "delete from employee where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Employee deleted");
        else
            System.out.println("Oh no! Something went wrong");

    }

    @Override
    public Employee employeeLogin(String loginPassWord) throws SQLException {
        Employee employee = new Employee();
        String sql = "select * from employee where id = " + loginPassWord;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email = resultSet.getString(4);
            String userName = resultSet.getString(5);
            String passWord = resultSet.getString(6);
            employee = new Employee(id, firstName, lastName, email, userName, passWord);

        } else {
            System.out.println("Something happened");
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employee";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email = resultSet.getString(4);
            String userName = resultSet.getString(5);
            String passWord = resultSet.getString(6);
            Employee employee = new Employee(id, firstName, lastName, email, userName, passWord);
            employees.add(employee);
        }
        return employees;
    }

    /*
    Always returning false statement
    Fix when there is a chance
     */

    @Override
    public Employee employeeById(int empId) throws SQLException {
        Employee employee = new Employee();
        String sql = "select * from employee where id = " + empId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email = resultSet.getString(4);
            String userName = resultSet.getString(5);
            String passWord = resultSet.getString(6);
            employee = new Employee(id, firstName, lastName, email, userName, passWord);
        } else {
            System.out.println("None found");
        }
        return employee;
    }
}
