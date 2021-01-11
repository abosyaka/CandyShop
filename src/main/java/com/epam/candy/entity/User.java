package com.epam.candy.entity;

public class User extends Entity{
    private Long id;
    private String email;
    private String password;
    private String name;
    private Role role;

    public User(Long id, String email, String password, String name, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public User(String userEmail, String userPassword, String userName, Role role) {
        this.email = userEmail;
        this.password = userPassword;
        this.name = userName;
        this.role = role;
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return (this.password.hashCode() + this.email.hashCode()) + 21;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != getClass()) return false;

        User anotherUser = (User) obj;

        return this.email.equals(anotherUser.email) && this.password.equals(anotherUser.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
