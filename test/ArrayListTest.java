import main.java.MyCoolArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Random;

public class ArrayListTest {

    private MyCoolArrayList<Long> testList;
    private List<Long> randomValuesList;
    int iterations;
    Long value;

    @Before
    public void setUp() {
        testList = new MyCoolArrayList();
        iterations = 10;
        value = 10L;
        randomValuesList = fillingListRandomValues();
    }

    @After
    public void clean() {
        testList.clear();
    }

    @Test
    public void addElement() {
        testList.add(value);
        printList();
    }

    @Test
    public void addElements() {
        testList.addAll(randomValuesList);
        printList();
    }

    @Test
    public void getElements() {
        System.out.println(randomValuesList.get(0));
    }

    @Test
    public void removeElement() {
        testList = (MyCoolArrayList<Long>) fillingListRandomValues();
        testList.add(value);
        printList();
        testList.remove(value);
        System.out.println("The item has been removed: " + value);
        printList();

    }

    @Test
    public void getListSize() {
        testList = (MyCoolArrayList<Long>) fillingListRandomValues();
        System.out.println(testList.size());
    }

    @Test
    public void isEmptyTest() {
        System.out.println(testList.isEmpty());
    }

    @Test
    public void containsTest() {
        testList.add(value);
        System.out.println(testList.contains(value));
        System.out.println(testList.contains(20L));
    }

    @Test
    public void toArrayTest() {
        testList = (MyCoolArrayList<Long>) fillingListRandomValues();
        Object[] array = testList.toArray();
        printArray(array);
    }

    @Test
    public void setTest() {
        testList = (MyCoolArrayList<Long>) fillingListRandomValues();
        testList.set(0, 50L);
        printList();
    }

    @Test
    public void indexOfTest() {
        testList = (MyCoolArrayList<Long>) fillingListRandomValues();
        System.out.println(testList.indexOf(10L));
        System.out.println(testList.indexOf(testList.get(0)));
    }

    @Test
    public void lastIndexOfTest() {
        testList = (MyCoolArrayList<Long>) fillingListRandomValues();
        testList.set(0, 200L);
        testList.set(testList.size() - 1, 200L);
        System.out.println(testList.lastIndexOf(200L));
    }

    private List<Long> fillingListRandomValues() {
        MyCoolArrayList<Long> result = new MyCoolArrayList<>();
        while (iterations > 0) {
            result.add(new Random().nextLong());
            iterations--;
        }
        iterations = 10;
        return result;
    }

    private void printList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testList.size(); i++) {
            sb.append(testList.get(i)).append(" ");
        }
        System.out.println(sb.toString());
    }
    private void printArray(Object[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length - 1; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

}
