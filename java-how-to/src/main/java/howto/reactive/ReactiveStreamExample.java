package howto.reactive;

import io.reactivex.rxjava3.core.Flowable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class ReactiveStreamExample {

    private static final List<String> codes = List.of("IAH", "AUS", "DFW1", "SAT");

    public static void main(String[] args) {

        Flowable.fromIterable(codes)
                .map(Airports::getAirportName)
                .map(String::toUpperCase)
                .subscribe(
                        System.out::println,
                        err -> System.out.println("-->> ERROR: <<--" + err),
                        () -> System.out.println("--> COMPLETE. <--"));
    }
}

class Airports {

    private static final String API_URL = "https://soa.smext.faa.gov/asws/api/airport/status/";

    public static String getAirportName(String code) throws IOException {
        String url = API_URL + code;

        try (Scanner scanner = new Scanner(new URL(url).openStream())) {
            String response = scanner.nextLine();
            if (!response.contains("Name")) {
                throw new RuntimeException("Invalid airport code " + code);
            }
            return response.split("\"")[3];
        }
    }
}