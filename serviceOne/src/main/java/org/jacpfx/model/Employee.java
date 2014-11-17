package org.jacpfx.model;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by amo on 09.10.14.
 */
public class Employee implements Serializable{

    private BigInteger id;

    private String employeeId;

    private String jobDescription;

    private Date jobTime;

    private String jobType; //ActivityType

    private String firstName;

    private String lastName;

    public Employee(String employeeId, String jobDescription, Date jobTime, String jobType, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.jobDescription = jobDescription;
        this.jobTime = jobTime;
        this.jobType = jobType;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getJobTime() {
        return jobTime;
    }

    public void setJobTime(Date jobTime) {
        this.jobTime = jobTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobTime=" + jobTime +
                ", jobType='" + jobType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
