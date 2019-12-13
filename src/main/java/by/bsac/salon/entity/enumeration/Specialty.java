package by.bsac.salon.entity.enumeration;

public enum Specialty {

    DERMATOLOGIST("Дерматолог"),
    COSMETOLOGIST("Косметолог");


    private final String name;

    Specialty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal();
    }

    public static Specialty getById(Integer id) {
        return Specialty.values()[id];
    }

}
