package service;

public class PrimeFinder implements Service{

    @Override
    public boolean isPrime(long candidate) {
        int root = (int) Math.sqrt(candidate);
        for (int i = 2; i <= root; i++) {
            if ((candidate % i) == 0) {
                return false;
            }
        }

        return true;
    }
}
