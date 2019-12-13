package by.bsac.salon.entity;

public class Service extends Entity {
    private String name;
    private String description;
    private double price;
    private double duration;

    public Service() {
    }

    public Service(String name, String description, double price,
                   double duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public Service(Integer id, String name, String description, double price,
                   double duration) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
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

        Service service = (Service) o;

        if (Double.compare(service.getPrice(), getPrice()) != 0) {
            return false;
        }
        if (Double.compare(service.getDuration(), getDuration()) != 0) {
            return false;
        }
        if (getName() != null ? !getName().equals(service.getName()) :
                service.getName() != null) {
            return false;
        }
        return getDescription() != null ? getDescription().equals(service.getDescription()) : service.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getDuration());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
