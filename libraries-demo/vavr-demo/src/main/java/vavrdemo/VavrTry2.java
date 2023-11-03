package vavrdemo;

import io.vavr.control.Try;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VavrTry2 {

    public static void main(String[] args) {
        List<String> codes = List.of("AUS", "IAH", "ERR", "DFW", "SAT");

        codes.stream()
                .map(Airports::optionalGetAirportName)
                .map(o -> o.orElse("Communication error"))
                .forEach(System.out::println);

        codes.stream()
                .map(code -> Try.of(() -> Airports.tryGetAirportName(code))
                        .getOrElseGet(VavrTry2::handleException))
                .forEach(System.out::println);

        codes.forEach(code -> Try.run(() -> Airports.voidPrintAirportName(code))
                .onFailure(ex -> System.out.println(handleException(ex))));
    }

    public static String handleException(Throwable t) {
        return t.getMessage();
    }
}

class Airports {

    private static final String API_URL = "https://soa.smext.faa.gov/asws/api/airport/status/";

    public static String tryGetAirportName(String code) throws IOException {
        String url = API_URL + code;
        Scanner scanner = new Scanner(new URL(url).openStream());
        String response = scanner.nextLine();
        if (!response.contains("Name")) {
            throw new RuntimeException("Invalid airport code " + code);
        }
        scanner.close();
        return response.split("\"")[3];
    }

    public static Optional<String> optionalGetAirportName(String code) {
        String url = API_URL + code;

        try (Scanner scanner = new Scanner(new URL(url).openStream())) {
            String response = scanner.nextLine();
            if (!response.contains("Name")) {
                return Optional.of("Invalid airport code " + code);
            }
            return Optional.of(response.split("\"")[3]);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static void voidPrintAirportName(String code) throws IOException {
        String url = API_URL + code;
        Scanner scanner = new Scanner(new URL(url).openStream());
        String response = scanner.nextLine();
        if (!response.contains("Name")) {
            throw new RuntimeException("Invalid airport code " + code);
        }
        System.out.println(response.split("\"")[3]);
        scanner.close();
    }
}
