package demo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * working with files
 */
public class StreamsWithFiles {

    public static void main(String[] args) {

//        Files.lines(Paths.get("./src/main/resources/" + "textfile.txt")).forEach(System.out::println);

        URL res = StreamsWithFiles.class
                .getClassLoader()
                .getResource("textfile.txt");

        // print lines
        try (Stream<String> lines = Files.lines(Paths.get(res.toURI()))) {
            lines.forEach(System.out::println);
        } catch (IOException | URISyntaxException | NullPointerException e) {
            System.err.println("Exception! " + e.getClass() + " >> " + e.getMessage());
        } finally {
            System.out.println("------------------------------");
        }

        // print words without any duplicate:
        try (Stream<String> lines = Files.lines(Paths.get(res.toURI()))) {
            lines.map(line -> line.split(" "))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
        } catch (IOException | URISyntaxException e) {
            System.err.println("Exception! " + e.getClass() + " >> " + e.getMessage());
        } finally {
            System.out.println("------------------------------");
        }

        // print all files and directories present at the path
        try (Stream<String> lines = Files.lines(Paths.get("./"))) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Exception! " + e.getClass() + " >> " + e.getMessage());
        } finally {
            System.out.println("------------------------------");
        }

        // print all directories present at the path
        try (Stream<Path> list = Files.list(Paths.get("./"))) {
            list.filter(Files::isDirectory)
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Exception! " + e.getClass() + " >> " + e.getMessage());
        } finally {
            System.out.println("------------------------------");
        }
    }
}
