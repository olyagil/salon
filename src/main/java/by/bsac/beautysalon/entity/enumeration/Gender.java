package by.bsac.beautysalon.entity.enumeration;

public enum Gender {

    FEMALE("female"),
    MALE("male");


    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal();
    }

    public static Gender getById(Integer id) {
        return Gender.values()[id];
    }
}
