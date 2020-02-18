package ua.com.alevel.stream.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {

    public void collectionStream() {
        System.out.println("CreateStream.collectionStream");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3");
        Stream<String> streamFromCollection = collection.stream();
        System.out.println("streamFromCollection = " + streamFromCollection.count());
    }

    public void streamOf() {
        System.out.println("CreateStream.streamOf");
        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
        System.out.println("streamFromValues = " + streamFromValues.count());
    }

    public void arraysStream() {
        System.out.println("CreateStream.arraysStream");
        String[] array = { "a1", "a2", "a3" };
        Stream<String> streamFromArrays = Arrays.stream(array);
        System.out.println("streamFromArrays = " + streamFromArrays.count());
    }

    public void filesLinesStream() {
        System.out.println("CreateStream.filesLinesStream");
        File file = new File("test.txt");
        file.deleteOnExit();
        try(PrintWriter out = new PrintWriter(file)) {
            out.println("a1");
            out.println("a2");
            out.println("a3");
            out.close();
            Stream<String> streamFromFiles;
            try {
                streamFromFiles = Files.lines(Paths.get(file.getAbsolutePath()));
                System.out.println("streamFromFiles = " + streamFromFiles.collect(Collectors.toList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void charStream() {
        System.out.println("CreateStream.charStream");
        IntStream streamFromString = "123".chars();
//        System.out.println("streamFromString = " + streamFromString.sum());
//        System.out.println("streamFromString = " + streamFromString.findAny());
//        System.out.println("streamFromString = " + streamFromString.count());
//        System.out.println("streamFromString = " + streamFromString.sorted().findFirst().getAsInt());
        System.out.println("streamFromString = " + streamFromString.findFirst().getAsInt());
    }

    public void streamBuilder() {
        System.out.println("CreateStream.streamBuilder");
        Stream<Object> stream = Stream
                .builder()
                .add("a4")
                .add("a1")
                .add("a4")
                .add("a2")
                .add("a5")
                .add("a3")
                .build();
        stream
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }

    public void iterateStream() {
        System.out.println("CreateStream.iterateStream");
//        Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 1);
//        streamFromIterate.forEach(System.out::println);
//        streamFromIterate.limit(10).forEach(System.out::println);
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    public void generateStream() {
        System.out.println("CreateStream.generateStream");
        Stream<Integer> streamFromGenerate = Stream.generate(() -> 1);
        streamFromGenerate.forEach(System.out::println);
    }

    public void parallel() {
        System.out.println("CreateStream.parallel");
        System.out.println("Normal...");
        IntStream range = IntStream.rangeClosed(1, 10);
        range.forEach(System.out::println);
        System.out.println("Parallel...");
        IntStream range2 = IntStream.rangeClosed(1, 10);
        range2.parallel().forEach(System.out::println);
    }

    public void parallelStream() {
        System.out.println("CreateStream.parallelStream");
        System.out.println("Normal...");
        List<String> alpha = getAlfaData();
        alpha.forEach(System.out::print);
        System.out.println();
        System.out.println("Parallel...");
        List<String> alpha2 = getAlfaData();
        alpha2.parallelStream().forEach(System.out::print);
    }

    private List<String> getAlfaData() {
        List<String> alpha = new ArrayList<>();
        int n = 97;
        while (n <= 122) {
            char c = (char) n;
            alpha.add(String.valueOf(c));
            n++;
        }
        return alpha;
    }
}
