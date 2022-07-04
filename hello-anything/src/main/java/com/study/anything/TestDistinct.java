package com.study.anything;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestDistinct {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("12","111"));
        userList.add(new User("12","1112"));
        userList.add(new User("123","123"));

        userList.stream().filter( o-> o.getName().startsWith("12")).forEach(System.out::println);

    }

}
class User {
    private String name;
    private String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}


