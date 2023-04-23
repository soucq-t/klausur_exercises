package app;

import service.Decoder;
import service.Proxy;

import java.io.IOException;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var json = """
                   {
                       "title": "Complex Analysis",
                       "author": "Tristan Needham"
                   }
                """;
        var proxy = new Proxy();
        var decoded = proxy.decode(json);
        System.out.println(decoded);
        System.out.println("okk");
    }
}
