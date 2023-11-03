package vavrdemo;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Queue;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@Slf4j
public class VavrCollections {

    public void vavrCollectionsDemo() {
        log.info("--> List -->");
        vavrList();

        log.info("--> Queue -->");
        vavrQueue();

        log.info("--> SortedSet -->");
        vavrSortedSet();

        log.info("--> Join strings -->");
        log.info("Joined string: {}", joinStrings("one", "two", "three"));
        log.info("Joined string: {}", List.of("one", "two", "three").mkString(", "));

        log.info("--> Maps -->");
        vavrMaps();

        log.info("--> Tuples -->");
        vavrTuples();

    }


    private void vavrList() {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = list1.tail().prepend(2);
        List<Integer> list3 = list1.prepend(2);

        log.info("{}, {}, {}", list1, list2, list3);
    }

    private void vavrQueue() {
        Queue<Integer> queue1 = Queue.of(1, 2, 3)
                .enqueue(4)
                .enqueue(5);

        Tuple2<Integer, Queue<Integer>> dequeue = queue1.dequeue();
        log.info("Dequeued: {}, {}", dequeue._1, dequeue._2);

        log.info("--> using \"Optional\" --> ");
        Option<Tuple2<Integer, Queue<Integer>>> dequeue2 = Queue.of(1).dequeueOption();
        log.info("Option (empty queue): {}", dequeue2.get()._2.getOrElse(-1));
//        log.info("Option (empty queue): {}", dequeue2.get()._2.getOrElseThrow(RuntimeException::new));
        log.info("Option (empty queue): {}", dequeue2.map(Tuple2::_2).getOrElseThrow(RuntimeException::new));

        Option<Integer> getDequeued = dequeue2.map(Tuple2::_1);
        log.info("Option (int): {}", getDequeued.getOrElse(-1));

    }

    private void vavrSortedSet() {
        SortedSet<Integer> sortedSet = TreeSet.of(2, 3, 1, 2, 4, 3, 7, 5);
        SortedSet<Integer> newSortedSet = sortedSet.add(6);
        log.info("Sorted set: {}", newSortedSet);

//        Comparator<Integer> comparator = (a, b) -> b.compareTo(a);
        SortedSet<Integer> reversedSet = TreeSet.of(Comparator.reverseOrder(), 2, 3, 1, 2, 4, 3, 7, 5);
        log.info("Reversed set: {}", reversedSet);
    }

    private String joinStrings(String... words) {
        return List.of(words)
                .intersperse(", ")
                .foldLeft(new StringBuilder(), StringBuilder::append)
                .toString();
    }

    private void vavrMaps() {
        // = HashMap((0, List(2, 4)), (1, List(1, 3)))
        Map<Integer, List<Integer>> tuple2s1 = List.of(1, 2, 3, 4).groupBy(i -> i % 2);
        log.info("map: {}", tuple2s1);
        Option<List<Integer>> lists = tuple2s1.get(0);
        log.info("First element of the list: {}", lists.getOrElse(List.of(-1)));

        // = List((a, 0), (b, 1), (c, 2))
        List<Tuple2<String, Integer>> tuple2s2 = List.of("a", "b", "c").zipWithIndex();
        log.info("list: {}", tuple2s2);
    }

    private void vavrTuples() {
        Tuple2<String, Integer> java8Tuple = Tuple.of("Java", 8);
        Tuple2<String, Integer> vavrTuple = java8Tuple.map(
                s -> s.substring(2) + "vr",
                i -> i / 8
        );
        log.info("{}", vavrTuple);

        Tuple2<String, Integer> that2 = java8Tuple.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );
        log.info("{}", that2);

        String that3 = java8Tuple.apply(
                (s, i) -> s.substring(2) + "vr " + i / 8
        );
        log.info("{}", that3);
    }
}
