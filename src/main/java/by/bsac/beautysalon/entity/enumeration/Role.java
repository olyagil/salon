package by.bsac.beautysalon.entity.enumeration;

public enum Role {

    ADMINISTRATOR("Администратор"),
    EMPLOYEE("Специалист"),
    CLIENT("Клиент");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal();
    }

    public static Role getById(Integer id) {
        return Role.values()[id];
    }
}
