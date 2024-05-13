import java.util.*;
import java.util.function.Function;
import java.util.stream.*;
public class StreamsAPI_set3 {
    public static void main(String[] args) {
        printLogs("1.Given a list of integers, find out all the "+
                "even numbers that exist in the list using Stream functions?");
        List<Integer> list1 = Arrays.asList(10, 15, 8, 49, 25, 98, 32);
        list1.stream().filter(x->x%2==0).forEach(System.out::println);
        printLogs("Given a list of integers, find out all the numbers starting with 1 using Stream functions?");
        List<Integer> list2 = Arrays.asList(10,15,8,49,25,98,32);
        list2.stream().map(x->Integer.toString(x)).filter(str->str.charAt(0)=='1').forEach(System.out::println);
        //Method 2
        /*list2.stream()
                .map(s -> s + "") // Convert integer to String
                .filter(s -> s.startsWith("1"))
                .forEach(System.out::println);*/

        printLogs("How to find duplicate elements in a given integers list in java using Stream functions?");
        List<Integer> list3 = Arrays.asList(10,15,8,49,25,98,98,32,15);
        list3.stream()
                .collect(Collectors.groupingBy(x->x,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry->entry.getValue()>=2)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
        //Method2
        /*Set<Integer> set = new HashSet<>();
        list3.stream()
                .filter(x->!set.add(x))
                .forEach(System.out::println);*/
        printLogs("4. Given the list of integers, find the first element of the list using Stream functions?");
        List<Integer> list4 = Arrays.asList(10,15,8,49,25,98,98,32,15);
        list4.stream()
                .findFirst().ifPresent(System.out::println);
        printLogs("5. Given the list of integers, find the total number of elements present in the list using Stream functions?");
        List<Integer> list5 = Arrays.asList(10,15,8,49,25,98,98,32,15);
        long count =  list5.stream()
                .count();
        System.out.println(count);

        printLogs("6. Given a list of integers, find the maximum value element present in it using Stream functions?");
        List<Integer> list6 = Arrays.asList(10,15,8,49,25,98,98,32,15);
        int max = list6.stream().max(Integer::compare).get();
        System.out.println(max);

        printLogs("7.Given a String, find the second non-repeated character in it using Stream functions?");
        String str7 = "Java articles are Awesome";
        Character result = str7.toLowerCase().chars()
                .mapToObj(ch->(char)ch)
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry->entry.getValue() == 1L)
                .map(Map.Entry::getKey)
                .skip(1)
                .findFirst().get();
        System.out.println(result);

        printLogs("8. Given a String, find the first repeated character in it using Stream functions?");
        String str8 = "Java Articles are Awesome";
        Character repChar = str8.chars()
                .mapToObj(ch -> Character.toLowerCase((char) ch))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue()>1)
                .map(Map.Entry::getKey)
                .findFirst().get();//Function.identity() = x->x
        System.out.println(repChar);

        printLogs("9. Given a list of integers, sort all the values present in it using Stream functions?");
        List<Integer> list9 = Arrays.asList(10,15,8,49,25,98,98,32,15);
        list9.stream().sorted().forEach(x -> System.out.print(x+","));
        System.out.println();
        String res9 = list9.stream().sorted().map(x->x+"").collect(Collectors.joining(",","[","]"));
        System.out.println(res9);

        printLogs("10. Given a list of integers, sort all the values present in it in descending order using Stream functions?");
        List<Integer> list10 = Arrays.asList(10,15,8,49,25,98,98,32,15);
        list10.stream().sorted(Collections.reverseOrder()).forEach(x -> System.out.print(x+","));
        System.out.println();
        String res10 = list9.stream().sorted((x,y)->-Integer.compare(x,y)).map(Object::toString).collect(Collectors.joining(",","[","]"));
        System.out.println(res10);

        printLogs("11. Given an integer array nums, return true if any value appears at least twice in the array," +
                " and return false if every element is distinct.");
        int[] nums1 = {1,2,3,1};
        int[] nums2 = {1,2,3,4};
        List<Integer> list = Arrays.stream(nums1).boxed().collect(Collectors.toList()); //boxed=mapToObj(x->x)
        HashSet<Integer> set = new HashSet<>(list);
        if(list.size()!=set.size())
            System.out.println("true");
        else
            System.out.println("false");

        printLogs("12. How will you get the current date and time using Java 8 Date and Time API?");
        System.out.println("Current Local Date: " + java.time.LocalDate.now());
        //Used LocalDate API to get the date
        System.out.println("Current Local Time: " + java.time.LocalTime.now());
        //Used LocalTime API to get the time
        System.out.println("Current Local Date and Time: " + java.time.LocalDateTime.now());
        //Used LocalDateTime API to get both date and time

        printLogs("13. Write a Java 8 program to concatenate two Streams?");
        List<String> list13_1 = Arrays.asList("Java", "8");
        List<String> list13_2 = Arrays.asList("explained", "through", "programs");
        Stream<String> concatStream = Stream.concat(list13_1.stream(),list13_2.stream());
        concatStream.forEach(str -> System.out.print(str + " "));

        printLogs("14. Java 8 program to perform cube on list elements and filter numbers greater than 50.");
        List<Integer> integerList14 = Arrays.asList(4,5,6,7,1,2,3);
        integerList14.stream().map(x->x*x*x).filter(x->x>50).forEach(x->System.out.print(x+","));

        printLogs("15. Write a Java 8 program to sort an array and then convert the sorted array into Stream?");
        int arr[] = { 99, 55, 203, 99, 4, 91 };
        Arrays.parallelSort(arr);
        Arrays.stream(arr).forEach(n->System.out.print(n+" "));

        printLogs("16. How to use map to convert object into Uppercase in Java 8?");
        List<String> names = Arrays.asList("aa", "bb", "cc", "dd");
        List<String> nameLst = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(nameLst);

        printLogs("17. How to convert a List of objects into a Map by considering duplicated keys and store them in sorted order?");
        List<Notes> noteLst = new ArrayList<>();
        noteLst.add(new Notes(1, "note1", 11));
        noteLst.add(new Notes(2, "note2", 22));
        noteLst.add(new Notes(3, "note3", 33));
        noteLst.add(new Notes(4, "note4", 44));
        noteLst.add(new Notes(5, "note5", 55));
        noteLst.add(new Notes(6, "note4", 66));

        Map<String, Integer> notesRecords =
                noteLst.stream()
                        .sorted(Comparator.comparingInt(Notes::getTagId).reversed())
                        .collect(Collectors.toMap(Notes::getTagName,Notes::getTagId,(oldValue,NewValue) -> oldValue,LinkedHashMap::new));

        System.out.println("Notes : " + notesRecords);

        printLogs("18. How to count each element/word from the String ArrayList in Java8?");
        names = Arrays.asList("AA", "BB", "AA", "CC");
        Map<String,Long> namesCount = names.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(namesCount);
        //count chars
        Map<Character,Long> charCount = names.stream()
                .flatMap(str->str.chars().mapToObj(x->(char)x))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(charCount);


        printLogs("19. How to find only duplicate elements with its count from the String ArrayList in Java8?");
        List<String> names19 = Arrays.asList("AA", "BB", "AA", "CC");
        Map<String,Long> names19Count = names19.stream()
                .filter(x -> Collections.frequency(names19,x)>1)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        System.out.println(names19Count);

        printLogs("20. How to check if list is empty in Java 8 using Optional, " +
                "if not null iterate through the list and print the object?");
        Optional.ofNullable(noteLst)
                .orElseGet(Collections::emptyList) // creates empty immutable list: [] in case noteLst is null
                .stream().filter(Objects::nonNull) //loop throgh each object and consider non null objects
                .map(Notes::getTagName) // method reference, consider only tag name
                .forEach(System.out::println); // it will print tag names

        printLogs("21. Write a Program to find the Maximum element in an array?");

        int[] arr21 = {12,19,20,88,00,9};
        System.out.println(Arrays.stream(arr21).max().getAsInt());

        printLogs("22. Write a program to print the count of each character in a String?");

        String str22 = "string data to count each character";
        Map<String, Long> map = Arrays.stream(str22.split(""))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(str -> str,LinkedHashMap::new, Collectors.counting()));
        System.out.println(map);
    }

    static void printLogs(String log){
        System.out.println("\n============================================================");
        System.out.println(log);
        System.out.println("============================================================");
    }
    public static class Notes{
        int id;
        String tagName;
        Integer tagId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTagId() {
            return tagId;
        }

        public void setTagId(Integer number) {
            this.tagId = number;
        }

        public Notes(int id, String tagName, Integer tagId) {
            this.id = id;
            this.tagName = tagName;
            this.tagId = tagId;
        }
    }

}
