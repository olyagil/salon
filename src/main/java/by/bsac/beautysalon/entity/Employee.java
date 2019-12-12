package by.bsac.beautysalon.entity;

import by.bsac.beautysalon.entity.enumeration.Specialty;

import java.sql.Date;

public class Employee extends User {

    private Specialty specialty;
    private Date employmentDate;
    private Integer cabinetNumber;
    private Double salary;

    public Employee() {
    }

    public Employee(Integer id) {
        super(id);
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Integer getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(Integer cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Employee employee = (Employee) o;

        if (getSpecialty() != employee.getSpecialty()) {
            return false;
        }
        if (getEmploymentDate() != null ? !getEmploymentDate().equals(employee.getEmploymentDate()) : employee.getEmploymentDate() != null) {
            return false;
        }
        if (getCabinetNumber() != null ? !getCabinetNumber().equals(employee.getCabinetNumber()) : employee.getCabinetNumber() != null) {
            return false;
        }
        return getSalary() != null ? getSalary().equals(employee.getSalary()) : employee.getSalary() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSpecialty() != null ? getSpecialty().hashCode() : 0);
        result = 31 * result + (getEmploymentDate() != null ? getEmploymentDate().hashCode() : 0);
        result = 31 * result + (getCabinetNumber() != null ? getCabinetNumber().hashCode() : 0);
        result = 31 * result + (getSalary() != null ? getSalary().hashCode() : 0);
        return result;
    }
}

