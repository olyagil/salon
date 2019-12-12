package by.bsac.beautysalon.entity;

import java.sql.Timestamp;

public class Talon extends Entity {

    private User client;
    private Service service;
    private Employee employee;
    private Timestamp receptionDate;
    private boolean status;

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Timestamp getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Timestamp receptionDate) {
        this.receptionDate = receptionDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

        Talon talon = (Talon) o;

        if (isStatus() != talon.isStatus()) {
            return false;
        }
        if (getClient() != null ? !getClient().equals(talon.getClient()) : talon.getClient() != null) {
            return false;
        }
        if (getService() != null ? !getService().equals(talon.getService())
                : talon.getService() != null) {
            return false;
        }
        if (getEmployee() != null ?
                !getEmployee().equals(talon.getEmployee()) : talon.getEmployee() != null) {
            return false;
        }
        return getReceptionDate() != null ?
                getReceptionDate().equals(talon.getReceptionDate()) : talon.getReceptionDate() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        result = 31 * result + (getService() != null ? getService().hashCode() : 0);
        result = 31 * result + (getEmployee() != null ? getEmployee().hashCode() : 0);
        result = 31 * result + (getReceptionDate() != null ? getReceptionDate().hashCode() : 0);
        result = 31 * result + (isStatus() ? 1 : 0);
        return result;
    }
}
