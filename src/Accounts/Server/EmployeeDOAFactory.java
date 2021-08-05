package Accounts.Server;

public class EmployeeDOAFactory {

    private static EmployeeDAO dao;

    private EmployeeDOAFactory(){
    }

    public static EmployeeDAO getEmployeeDao(){
        if(dao == null)
            dao = new EmployeeDAOImpl();
        return dao;
    }

}
