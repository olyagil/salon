package by.bsac.beautysalon.entity;

import by.bsac.beautysalon.entity.enumeration.Gender;
import by.bsac.beautysalon.entity.enumeration.Role;

import java.sql.Date;

public class User extends Entity {

    private String login;
    private String password;
    private Role role;
    private String name;
    private String surname;
    private String patronymic;
    private Gender gender;
    private Integer phone;
    private Date birthDate;
    private String avatar;

    public User() {
    }

    public User(Integer id) {
        super(id);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

        User user = (User) o;

        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
            return false;
        }
        if (getPassword() != null ?
                !getPassword().equals(user.getPassword()) : user.getPassword() != null) {
            return false;
        }
        if (getRole() != user.getRole()) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) :
                user.getSurname() != null) {
            return false;
        }
        if (getPatronymic() != null ?
                !getPatronymic().equals(user.getPatronymic()) : user.getPatronymic() != null) {
            return false;
        }
        if (getGender() != user.getGender()) {
            return false;
        }
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) :
                user.getPhone() != null) {
            return false;
        }
        if (getBirthDate() != null ?
                !getBirthDate().equals(user.getBirthDate()) : user.getBirthDate() != null) {
            return false;
        }
        return getAvatar() != null ? getAvatar().equals(user.getAvatar()) :
                user.getAvatar() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        return result;
    }
}
