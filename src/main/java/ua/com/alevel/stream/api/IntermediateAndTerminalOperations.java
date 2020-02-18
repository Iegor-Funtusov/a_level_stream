package ua.com.alevel.stream.api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * -- No storage. A stream is not a data structure that stores elements; instead, it conveys elements from a source such as a data structure, an array, a generator function, or an I/O channel, through a pipeline of computational operations.
 * -- Functional in nature. An operation on a stream produces a result, but does not modify its source. For example, filtering a Stream obtained from a collection produces a new Stream without the filtered elements, rather than removing elements from the source collection.
 * -- Laziness-seeking. Many stream operations, such as filtering, mapping, or duplicate removal, can be implemented lazily, exposing opportunities for optimization. For example, "find the first String with three consecutive vowels" need not examine all the input strings. Stream operations are divided into intermediate (Stream-producing) operations and terminal (value- or side-effect-producing) operations. Intermediate operations are always lazy.
 * -- Possibly unbounded. While collections have a finite size, streams need not. Short-circuiting operations such as limit(n) or findFirst() can allow computations on infinite streams to complete in finite time.
 * -- Consumable. The elements of a stream are only visited once during the life of a stream. Like an Iterator, a new stream must be generated to revisit the same elements of the source.
 *
 * link -- https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
 * **/
public class IntermediateAndTerminalOperations {

    /*
    *  у stream'a может быть сколько угодно вызовов конвейерных вызовов и в конце один терминальный,
    * при этом все конвейерные методы выполняются лениво
    * и пока не будет вызван терминальный метод никаких действий на самом деле не происходит
    */

    public void run() {
        List<String> strings1 = Arrays.asList("a", "b", "f", "c", "g", "d", "e", "e", "h");
        List<String> strings = Arrays.asList("1", "10", "7", "9", "4", "5", "2", "9", "6", "10","3", "8");
        List<Integer> integers = Arrays.asList(1, 10, 7, 4, 5, 2, 9, 6, 3, 3, 8);

        //filter
        System.out.println("filter = " + strings
                .stream()
                .filter("a"::equals)
                .count());
        System.out.println("filter = " + integers
                .stream()
                .filter(integer -> integer < 5)
                .distinct()
                .sorted()
                .collect(Collectors.toList()));

        //skip
        System.out.println("skip = " + integers
                .stream()
                .skip(5)
                .collect(Collectors.toList()));

        //map
        System.out.println("map = " + integers
                .stream()
                .map(integer -> integer + 1)
//                .map(integer -> integer++)
//                .map(integer -> ++integer)
                .collect(Collectors.toList()));

        //limit
        System.out.println("limit = " + strings.stream().limit(2).collect(Collectors.toList()));

        //mapToInt
        System.out.println("mapToInt = " + strings.stream().mapToInt(Integer::parseInt).sum());

        //findFirst
        System.out.println("findFirst = " + strings.stream().mapToInt(Integer::parseInt).findFirst());

        //findAny
        System.out.println("findAny = " + strings.stream().mapToInt(Integer::parseInt).findAny());

        //count
        System.out.println("count = " + strings.stream().mapToInt(Integer::parseInt).distinct().count());

        //anyMatch
        System.out.println("anyMatch = " + strings.stream().anyMatch("1"::equals));

        //noneMatch
        System.out.println("noneMatch = " + strings.stream().noneMatch("1"::equals));

        //allMatch
        System.out.println("allMatch = " + strings.stream().allMatch("1"::equals));

        //min
        System.out.println("min = " + integers
                .stream()
                .sorted()
                .distinct()
                .limit(4)
                .min(Integer::compareTo)
                .get());

        //max
        System.out.println("max = " + integers
                .stream()
                .sorted()
                .distinct()
                .limit(4)
                .max(Integer::compareTo)
                .get());

        //toArray
        System.out.println("toArray = " + Arrays.toString(
                strings1
                        .stream()
                        .distinct()
                        .sorted()
                        .map(String::toUpperCase)
                        .toArray(String[]::new)));

        //reduce
        System.out.println("reduce = " + integers.stream().reduce((a, b) -> a * b).get());

        int[] randomIntsArray = IntStream
                .generate(() -> new Random().nextInt(100))
//                .limit(1_000_000)
                .limit(1_000)
                .toArray();
//        List<Integer> list = Arrays.stream(randomIntsArray).boxed().collect(Collectors.toList());
//
//        System.out.println("summingInt = " + list.stream().collect(Collectors.summingInt(((p) -> p % 2 == 1? p: 0))));
//        System.out.println("summingInt = " + (Integer) list.stream().mapToInt(((p) -> p % 2 == 1 ? p : 0)).sum());
//
//        System.out.println("averagingInt = " + list.stream().collect(Collectors.summingInt(((p) -> p - 1))));
//        System.out.println("averagingInt = " + (Integer) list.stream().mapToInt(((p) -> p - 1)).sum());
//
//        System.out.println("partitioningBy = " + list.stream().collect(Collectors.partitioningBy(((p) ->  p % 2 == 0))));
//
//        System.out.println("summarizingInt = " + list.stream().collect(Collectors.summarizingInt(((p) -> p * 2))));


        List<String> stringList = Arrays.asList("a1", "b2", "c3", "a1", "a1", "b2", "c3", "a1", "a4", "b3", "c1", "a3");
        System.out.println("stringList = " + stringList
                .stream()
                .collect(Collectors.joining(": ", "<b> ", " </b>")));
        System.out.println("stringList = " + stringList
                .stream()
                .collect(Collectors.groupingBy((p) -> p.substring(0, 1))));
        System.out.println("stringList = " + stringList
                .stream()
                .collect(Collectors.groupingBy((p) -> p.substring(0, 1),
                        Collectors.mapping((p) -> p.substring(1, 2), Collectors.joining(":")))));
    }
}
