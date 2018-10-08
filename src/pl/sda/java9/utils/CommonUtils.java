package pl.sda.java9.utils;

import pl.sda.java9.domains.User;

import java.util.HashMap;
import java.util.List;

public class CommonUtils {

    public static HashMap<Integer, User> createUserMap(List<User> userList) {

        HashMap<Integer, User> userMap = new HashMap<>();

        for (User user : userList) {

            userMap.put(user.getId(), user);
        }

        return userMap;
    }
}
