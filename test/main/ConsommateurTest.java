package main;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ConsommateurTest{
    private static Consommateur testCons;

    @BeforeEach
    public void setup() {
        testCons = new Consommateur("123", "test", "test", "@", "1234567890", "test", "test",  42, "test,test1");
    }

    @Test

    void addActivityConsommateurShouldAddActivity() {
        ArrayList<String> oldactivities = (ArrayList<String>) testCons.getActivities().clone() ;
        testCons.addActivity("activity");

        assertNotEquals(oldactivities.size(), testCons.getActivities().size());
        assertNotEquals(oldactivities.get(oldactivities.size()-1),
                testCons.getActivities().get(testCons.getActivities().size()-1));
    }
    @Test
    void deleteActivitywithinvalidargumentThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testCons.deleteActivity(-1);
                });
    }
    @Test
    void deleteActivityConsommateurShouldDeleteActivity() {
        ArrayList<String> oldactivities = (ArrayList<String>) testCons.getActivities().clone() ;
        testCons.deleteActivity(testCons.getActivities().size()-1);

        assertEquals(oldactivities.size()-1, testCons.getActivities().size());
        assertNotEquals(oldactivities.get(oldactivities.size()-1),
                testCons.getActivities().get(testCons.getActivities().size()-1));
    }
    @Test
    void deleteActivityAddActivityConsommateurSanitary() {
        ArrayList<String> oldactivities = (ArrayList<String>) testCons.getActivities() ;
        testCons.addActivity("activity");
        testCons.deleteActivity(testCons.getActivities().size()-1);

        assertEquals(oldactivities.size(), testCons.getActivities().size());
        assertEquals(oldactivities.get(oldactivities.size()-1),
                testCons.getActivities().get(oldactivities.size()-1));
    }
}