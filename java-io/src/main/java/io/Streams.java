package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var streams = new Streams();
        var objects = new ArrayList<SomeRecord>();
        objects.add(new SomeRecord("John", 20));
        objects.add(new SomeRecord("Amy", 30));
        File dataFile = Files.createFile(Paths.get("C:\\WORK\\in-out\\ocp-io\\objects.dat")).toFile();

        streams.saveToFile(objects, dataFile);
        List<SomeRecord> someRecords = streams.readFromFile(dataFile);
        System.out.println(someRecords);

    }

    public void copyTextFile(File src, File dest) throws IOException {
        try (
             var reader = new BufferedReader(new FileReader(src));
             var writer = new BufferedWriter(new FileWriter(dest))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    void copyUsingPrintWriter(File src, File dest) throws IOException {
        try (
             var reader = new BufferedReader(new FileReader(src));
             var writer = new PrintWriter(new FileWriter(dest))) {
            String line;
            while ((line = reader.readLine()) != null)
                writer.println(line);
        }
    }

    public void copyBinaryFile(File src, File dest) throws IOException {
        try (
             var reader = new BufferedInputStream(new FileInputStream(src));
             var writer = new BufferedOutputStream(new FileOutputStream(dest))) {

            byte[] read = reader.readAllBytes();
            writer.write(read);
        }
    }

    // example - read a file using NIO2
    private void nio2ReadLazily(Path path) throws IOException {
        try (Stream<String> s = Files.lines(path)) {
            s.filter(f -> f.startsWith("WARN:"))
                    .map(f -> f.substring(5))
                    .forEach(System.out::println);
        }
    }

    void saveToFile(List<SomeRecord> objects, File dataFile) throws IOException {
        try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
            for (SomeRecord record : objects)
                out.writeObject(record);
        }
    }

    List<SomeRecord> readFromFile(File dataFile) throws IOException, ClassNotFoundException {
        var objects = new ArrayList<SomeRecord>();
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof SomeRecord g)
                    objects.add(g);
            }
        } catch (EOFException ignored) {}
        return objects;
    }


}

record SomeRecord(String name, int age) implements Serializable {}