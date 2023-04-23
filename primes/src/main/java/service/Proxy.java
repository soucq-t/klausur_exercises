package service;

import java.util.HashMap;

public class Proxy implements Service{
    private PrimeFinder realprime;
    private HashMap<Long, Boolean> map;
    @Override
    public boolean isPrime(long candidate) {
        boolean isPrime=false;
        if (map.containsKey(candidate)){
            return map.get(candidate);
        }
        return realprime.isPrime(candidate);
    }
}
