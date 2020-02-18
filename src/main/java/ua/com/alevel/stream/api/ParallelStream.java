package ua.com.alevel.stream.api;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStream {

    public void run() {
        System.out.println("ParallelStream.run");
        System.out.println("Normal...");
        IntStream range = IntStream.rangeClosed(1, 10);
        System.out.println("isParallel: " + range.isParallel());
        range.forEach(System.out::println);
        System.out.println("Parallel...");
        IntStream range2 = IntStream.rangeClosed(1, 10);
        IntStream range2Parallel = range2.parallel();
        System.out.println("isParallel: " + range2Parallel.isParallel());
        range2Parallel.forEach(System.out::println);
    }

    public void separateThread() {
        System.out.println("ParallelStream.separateThread");
        System.out.println("Normal...");
        IntStream range = IntStream.rangeClosed(1, 10);
        range.forEach(x -> System.out.println("Thread : " + Thread.currentThread().getName() + ", value: " + x));
        System.out.println("Parallel...");
        IntStream range2 = IntStream.rangeClosed(1, 10);
        range2.parallel().forEach(x -> System.out.println("Thread : " + Thread.currentThread().getName() + ", value: " + x));
    }

    public void peek() {
        System.out.println("ParallelStream.peek");
        long start = System.currentTimeMillis();
        long count = Stream.iterate(0, n -> n + 1)
                .limit(100000)
                .parallel()
                .filter(this::isPrime)
                .peek(x -> System.out.format("%s\t", x))
                .count();
        System.out.println("\nTotal: " + count + ", time: " + (System.currentTimeMillis() - start));
    }

    public void parallelStream() {
        System.out.println("ParallelStream.parallelStream");
        UserList userList = new UserList();
        userList.init();
        long start = System.currentTimeMillis();
        double age = userList.getUsers().parallelStream().mapToInt(User::getAge).average().getAsDouble();
        System.out.println("age = " + age + ", time in parallelStream: " + (System.currentTimeMillis() - start));
        age = userList.getUsers().stream().parallel().mapToInt(User::getAge).average().getAsDouble();
        System.out.println("age = " + age + ", time in parallel: " + (System.currentTimeMillis() - start));
        userList.getUsers().stream().mapToInt(User::getAge).average().getAsDouble();
        System.out.println("age = " + age + ", time: " + (System.currentTimeMillis() - start));
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        return !IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
    }

    @Data
    @ToString
    private class User {

        private int age;
        private String name;
    }

    @Data
    private class UserList {

        private List<User> users;

        public void init() {
            this.users = new ArrayList<>();
            Random random = new Random();
            IntStream range = IntStream.rangeClosed(1, 10000000);
            range.forEach(i -> {
                User user = new User();
                user.setAge(random.ints(20, 30).findAny().getAsInt());
                user.setName(RandomStringUtils.randomAlphabetic(10));
                this.users.add(user);
            });
        }
    }
}
