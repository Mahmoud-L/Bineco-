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
        testCons = new Consommateur("123", "test", "test", "@", "1234567890", "test", "test", "test" 42, "test");
    }

    @Test
    void addActivitywithinvalidargumentThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testCons.addActivity(123);
                });
    }
    @Test

    void addActivityConsommateurAddsActivity() {
        ArrayList<string> oldactivities = (ArrayList<string>) testCons.getActivities() ;
        testCons.addActivity("activity");

        assertNotEquals(oldactivities.size(), testCons.getActivities().size());
        assertNotEquals(oldactivities.get(oldactivities.size()-1),
                testCons.getActivities().get(testCons.getActivities().size()-1));
    }
    @Test
    void deleteActivitywithinvalidargumentThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testcon.deleteActivity(-1);
                });
    }
    @Test
    void deleteActivityConsommateurDeletesActivity() {
        ArrayList<string> oldactivities = (ArrayList<string>) testcon.getActivities() ;
        testCons.deleteActivity(0);

        assertNotEquals(oldactivities.size(), testCons.getActivities().size());
        assertNotEquals(oldactivities.get(oldactivities.size()-1),
                testCons.getActivities().get(testCons0.getActivites().size()-1));
    }
    void deleteActivityAddActivityConsommateurSanitary() {
        ArrayList<string> oldactivities = (ArrayList<string>) testcon.getActivities() ;
        testCons.addActivity("activity");
        testCons.deleteActivity(oldactivities.size());

        assertEquals(oldactivities.size(), testCons.getActivities().size());
        assertNotEquals(oldactivities.get(oldactivities.size()-1),
                testCons.getActivities().get(oldactivities.size()-1));
    }
}