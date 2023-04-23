package service;

import java.io.*;

public class Proxy implements DecodeInterface{
    private final DecodeInterface wrapped=new Decoder();

    @Override
    public Object decode(String json) {
        try(BufferedReader bfr =new BufferedReader(new FileReader(new File("resources/forbidden.txt"))))  {
            String line;
            while ((line= bfr.readLine())!= null){
                if (json.contains(line)){
                    throw new RuntimeException();
                }
            }

            return wrapped.decode(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
