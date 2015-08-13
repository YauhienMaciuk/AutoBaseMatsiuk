package com.epam.autobasematsiuk.entity;

/**
 * The class User.
 */
public class User extends AbstractEntity {

    private static final long serialVersionUID = -3385642020539223565L;
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean status;

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param id        is the id
     * @param login     is the login
     * @param password  is the password
     * @param firstName is the first name
     * @param lastName  is the last name
     * @param role      is the role
     */
    public User(int id, String login, String password, String firstName, String lastName, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    /**
     * Gets the id of user.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for user.
     *
     * @param id is the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the login of user.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login for user.
     *
     * @param login is the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password of user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password for user.
     *
     * @param password is the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets first name of user.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name for user.
     *
     * @param firstName is first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name of user.
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name for user.
     *
     * @param lastName is last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the role of user.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role for user.
     *
     * @param role is the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the status of user.
     *
     * @return false if user is free
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets status for user.
     *
     * @param status is the status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * The method equals from java.lang.Object
     *
     * @param o is the object
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (status != user.status) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (role != user.role) return false;

        return true;
    }

    /**
     * The method hashCode from java.lang.Object
     *
     * @return hash code of the object User
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    /**
     * The method toString from java.lang.Object
     *
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("User [").append("id = ").append(getId()).append(", Login = ").append(getLogin()).
                append(", Password = ").append(getPassword()).append(", fistName = ").
                append(getFirstName()).append(", lastName = ").append(getLastName()).
                append(", role = ").append(getRole()).append("]").toString();
    }
}
