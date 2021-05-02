package core.utils;

import java.util.Random;

public class Randoms {

    public static String getRandomString() {
        Random random = new Random();
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 20;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
