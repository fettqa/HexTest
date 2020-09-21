package hex.test.course;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class CourseTest {

    @Test //1
    public void testCourseEqWithNotCourse() {
        final Course testCourse = new Course(1L, "Course101", new ArrayList<>());
        System.out.printf("Course name: %s",testCourse.getName());
        System.out.printf("Course sessions : %s",testCourse.getSessions());
        assertFalse("Equals with an extraneous String return true 0_0", testCourse.equals("test"));
    }

    @Test //2
    public void testCourseEqWithCourseWithDifferentUuid(){
        final Course testCourse1 = new Course(1111L, "101", new ArrayList<>());
        final Course testCourse2 = new Course(2222L, "101", new ArrayList<>());
        assertFalse("2 Courses with different uuid's returns true 0_0", testCourse1.equals(testCourse2));
    }

    @Test //3
    public void testCourseEqWithCourseWithSameUuid() {
        final Course testCourse1 = new Course(1L, "102", new ArrayList<>());
        final Course testCourse2 = new Course(1L, "122s", new ArrayList<>());
        assertTrue("Two courses with same uuid's but different names returns false 0_0"
                , testCourse1.equals(testCourse2));
    }

    @Test //4
    public void testSessionEqWithString() {
        final Course.Session session = new Course(1L, "102", new ArrayList<>()).new Session(new Date());
        assertFalse(session.equals("test"));
    }

    @Test //5
    public void testSessionEqWithDifferentDateAndSameCourse(){
        final Course.Session session1 = new Course(1L, "101", new ArrayList<>()).new Session(new Date());
        final Course.Session session2 = new Course(1L, "101", new ArrayList<>()).new Session(new Date(0));
        assertFalse(session1.equals(session2));
    }

    @Test //6
    public void testSessionEqWithSameDateButDifferentCourse() {
        final Course testCourse1 = new Course(1111L, "111", new ArrayList<>());
        final Course testCourse2 = new Course(2222L, "122", new ArrayList<>());
        final Date d = new Date();
        final Course.Session session1 = testCourse1.new Session(d);
        final Course.Session session2 = testCourse2.new Session(d);
        assertFalse(session1.equals(session2));
    }

    @Test //7
    public void testSessionEqWithSameCourseEndSameDate(){
        final Course testCourse1 = new Course(1L, "102", new ArrayList<>());
        final Course testCourse2 = new Course(1L, "101", new ArrayList<>());
        final Date d = new Date();
        final Course.Session session1 = testCourse1.new Session(d);
        final Course.Session session2 = testCourse2.new Session(d);
        assertTrue(session1.equals(session2));
    }
    @Test //8
    public void testSessionEqWithDifferentDateEndDifferentCourse() {
        final Course.Session session1 = new Course(1111L, "111", new ArrayList<>()).new Session(new Date());
        final Course.Session session2 = new Course(2222L, "111", new ArrayList<>()).new Session(new Date(0));
        assertFalse(session1.equals(session2));
    }
}
