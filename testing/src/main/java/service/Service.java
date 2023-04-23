package service;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Service {

    public boolean isPrime(long candidate) {
        int root = (int) Math.sqrt(candidate);
        for (int i = 2; i <= root; i++) {
            if ((candidate % i) == 0) {
                return false;
            }
        }

        return true;
    }

    public int min(int[] array) {
        return Arrays
                .stream(array)
                .min()
                .orElseThrow();
    }

    public int reverse(int number) {
        int reversedNumber = 0;
        int numberToReverse = Math.abs(number);

        while (numberToReverse > 0) {
            int mod = numberToReverse % 10;
            reversedNumber = reversedNumber * 10 + mod;
            numberToReverse /= 10;
        }

        return number < 0 ? reversedNumber * -1 : reversedNumber;
    }

    public boolean isPalindrome(String text) {
        String temp = text.replaceAll("\\s+", "").toLowerCase();
        return IntStream.range(0, temp.length() / 2)
                .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - i - 1));
    }

    public boolean areAmicableNumbers(int a, int b) {
        return sumOfDivisors(a) == b && sumOfDivisors(b) == a;
    }

    private static int sumOfDivisors(int n) {
        int sum = 0;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                if (i == (n / i))
                    sum = sum + i;
                else
                    sum += (i + n / i);
            }
        }
        return sum + 1;
    }

    public boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        var a1 = s1.toCharArray();
        var a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }
}
