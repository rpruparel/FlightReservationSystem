package Reservation.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

/**
 *
 * @author Rohit Ruparel
 */
@
Entity@ Table(name = "Employees")
public class Employee implements Serializable {

    @
    Id@ Column(name = "employee_id")@ GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeID = 0;

    @
    Column(name = "name")
    private String name;

    @
    Column(name = "address")
    private String address;

    @
    Column(name = "salary")
    private int salary;

    @
    Column(name = "employee_type")
    private int employeeType;

    @
    Column(name = "user_account_id")
    private int userAccountID;

    @
    Transient
    private UserAccount userAccount;

    public Employee() {}

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }

    public int getEmployeeType() {
        return employeeType;
    }

    public int getUserAccountID() {
        return userAccountID;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setEmployeeType(int employeeType) {
        this.employeeType = employeeType;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccountID = userAccountID;
    }
}