package hex.test.set;

import java.util.Arrays;
import java.util.Set;

public class CustomHashSetTest {

    public static void main(final String... args) {
        final Set<String> s = new HexSet<>();

        // test add & size
        s.add("1");
        s.add("1");
        assertEquals("The size of the set does not return 1 after "
                + "adding the same element twice.", 1, s.size());
        assertTrue("The contains() method does not return true for "
                + "an item that has been added twice.", s.contains("1"));

        // test remove, size, isEmpty
        s.remove("1");
        assertEquals("The size() method does not return 0 when all "
                + "elements have been removed from the set.", 0, s.size());
        assertTrue("The isEmpty() method does not return true if the set size is 0", s.isEmpty());

        // test contains, size
        s.add("2");
        s.add("3");
        assertEquals("The size() method returns an invalid value after "
                + "adding two elements to this collection.", 2, s.size());
        assertTrue("The contains() method cannot find the element \"2\"!", s.contains("2"));
        assertTrue("The contains() method cannot find the element \"2\"!", s.contains("3"));

        final Set<String> s2 = new HexSet<>();
        s2.add("1");
        s2.add("4");
        s2.add("5");
        s2.add("6");

        // test addAll
        s.addAll(s2);
        assertEquals("The addAll() method did not add all the elements from the incoming "
                        + "collection to the current collection! Collection size does not match.",
                6,
                s.size());
        assertTrue("The addAll() method did not add all the elements from the incoming "
                        + "collection to the current collection! No item \"1\" was found using the "
                        + "contains() method.",
                s.contains("1"));

        // test containsAll
        assertTrue("The containsAll() method does not find all elements of the incoming "
                        + "collection in the current collection. But they are here!",
                s.containsAll(s2));
        final Set<String> s3 = new HexSet<>();
        s3.add("3");
        s3.add("4");
        s3.add("5");
        s3.add("30");
        s3.add("40");
        assertFalse("The containsAll() method found non-existent elements (\"30\" & \"40\") in "
                + "the collection!", s.containsAll(null));

        // test removeAll & containsAll & contains
        s.removeAll(s3);
        assertEquals("The removeAll() method was to remove all elements in the current "
                        + "collection that match the elements in the incoming collection! The "
                        + "size of the current collection is incorrect.",
                3,
                s.size());

        final Set<String> s4 = new HexSet<>();
        s4.add("3");
        s4.add("4");
        s4.add("5");
        assertFalse("The containsAll() method found non-existent elements "
                        + "(\"3\", \"4\", \"5\") in the collection, after removeAll() method!",
                s.containsAll(s4));
        assertTrue("After the removeAll() method worked, an element (\"1\") that "
                        + "should not have disappeared disappeared! It does not find the "
                        + "contains() method.",
                s.contains("1"));
        assertTrue("After the removeAll() method worked, an element (\"2\") that should "
                        + "not have disappeared disappeared! It does not find the contains() method.",
                s.contains("2"));

        // test retainAll
        s3.add("1");
        s3.add("2");
        System.out.println("Test retainAll: \n");
        System.out.println("Collection s3: " + Arrays.deepToString(s3.toArray()));
        System.out.println("Collection s4: " + Arrays.deepToString(s4.toArray()));
        System.out.println("Colling s3.retainAll(s4);");
        s3.retainAll(s4);
        System.out.println("Collection s3 after retainAll: " + Arrays.deepToString(s3.toArray()));
        assertEquals("The retainAll() method removed the wrong number of items from "
                        + "the current collection. The size of the current collection is "
                        + "incorrect.",
                3,
                s.size());

        // test clear

        s.clear();
        assertTrue("Why, after clear (), the collection is not empty?", s.isEmpty());

    }

    private static <T> void assertEquals(final String msg, final T expectedVal, final T actualVal) {
        if (!expectedVal.equals(actualVal)) {
            throw new RuntimeException(msg + String.format("\nExpected: %s, Actual: %s",
                    expectedVal,
                    actualVal));
        }
    }

    private static void assertTrue(final String msg, final Boolean value) {
        assertEquals(msg, true, value);
    }

    private static void assertFalse(final String msg, final Boolean value) {
        assertEquals(msg, false, value);
    }
}