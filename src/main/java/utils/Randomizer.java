package utils;

import java.util.Random;

/**
 * Created by borys on 03.06.2016.
 */
public class Randomizer {

    private Random random = new Random();

    public int getRandomInt (int x){
        final Random random = new Random();
        int val = random.nextInt(x+1);
        return val;

    }

    /**
     *Methods for generation random six-digit number
     */
    public int getRandom() {
        return 100000 * onlyPlus() + 10000 * onlyPlus() + 1000 * onlyPlus() + 100 * onlyPlus() + 10 * onlyPlus() + onlyPlus();
    }


    private int onlyPlus() {
        return random.nextInt(9) + 1;
    }

    /**
     *Method for generation random email
     */
    public String generateRandomEmail(String inputEmail) {
        String newEmail = "Test_" + getRandom() + inputEmail;

        return newEmail;
    }

}
