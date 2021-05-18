package com.example.demo.util;

import java.math.BigInteger;
import java.util.Random;

public class IdGenerator {

//    This is a temp solution
    public static BigInteger getNextId() {

        String env = System.getenv("PROFILE");
        if(env!= null && env.equals("test")) {
            return BigInteger.ONE;
        }

        BigInteger maxLimit = new BigInteger("5000000000000");
        BigInteger minLimit = new BigInteger("25000000000");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);

        return res;
    }
}
