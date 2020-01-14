package com;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class User {
    String name, role;
    User(String a, String b) {
        name = a;
        role = b;
    }
    String getRole() { return role; }
    String getName() { return name; }
    public String toString() {
        return "User Name : " + name + ", Role :" + role;
    }

    public static void main(String args[])
    {
        List<User> users = new ArrayList<User>();
        users.add(new User("John", "admin"));
        users.add(new User("Peter", "member"));
        List admins = process(users, (User u) -> u.getRole().equals("admin"));
        System.out.println(admins);

        List<User> users1 =
                new ArrayList<User>();
        users1.add(new User("John", "admin"));
        users1.add(new User("Peter", "member"));
        List admins1 = users.stream()
                .filter((user1) -> user1.getRole().equals("admin"))
                .collect(Collectors.toList());

        System.out.println(admins);
    }

    public static List<User> process(List<User> users,
                                     Predicate<User> predicat)
    {
        List<User> result = new ArrayList<User>();
        for (User user: users)
            if (predicat.test(user))
                result.add(user);
        return result;
    }
}