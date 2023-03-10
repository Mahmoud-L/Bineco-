package main;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ResidentTest {
    private static Resident testResident;

    @BeforeEach
    public void setup() {
        testResident = new Resident("123", "test", "test", "@", "1234567890", "test");
        testResident.addBac(new Bac("A","bacA","Recyclage"));
        testResident.addBac(new Bac("B","bacB","Compostage"));
        testResident.addBac(new Bac("C","bacC","Ordures"));
    }

   @Test
    void addBacResidentAddsBac() {
       ArrayList<Bac> oldBacs = (ArrayList<Bac>) testResident.getBacs().clone();
       Bac testbac = new Bac("###", "###", "###");
       testResident.addBac(testbac);

       assertNotEquals(oldBacs.size(), testResident.getBacs().size());
       assertNotEquals(oldBacs.get(oldBacs.size() - 1),
               testResident.getBacs().get(testResident.getBacs().size() - 1));
   }

    @Test
    void deleteBacWithNegativeIndexThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testResident.deleteBac(-1);
                });
    }

    @Test
    void deleteBacWithTooBigIndexThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    int index = testResident.getBacs().size();
                    testResident.deleteBac(index);
                });
    }

    @Test
    void deleteBacDeletesExistingBac() {
        ArrayList<Bac> oldBacs = (ArrayList<Bac>) testResident.getBacs().clone();
        testResident.deleteBac(0);

        assertNotEquals(oldBacs.size(), testResident.getBacs().size());
        assertNotEquals(oldBacs.get(0), testResident.getBacs().get(0));
    }

    @Test
    void editMetricParamsWithInvalidParamThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testResident.editMetricParam("notAValidParam", 0.0);
                });
    }

    @Test
    void editMetricParamsWithInvalidValueThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testResident.editMetricParam("Recyclage", 10.0);
                    testResident.editMetricParam("Compostage", 10.0);
                    testResident.editMetricParam("Ordures", 10.0);
                    testResident.editMetricParam("CIM", 1000.0);
                });
    }

    @Test
    void editMetricParamsWithNegativeValueThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    testResident.editMetricParam("Recyclage", -1.0);
                    testResident.editMetricParam("Compostage", -1.0);
                    testResident.editMetricParam("Ordures", -1.0);
                    testResident.editMetricParam("CIM", -1.0);
                });
    }

    @Test
    void editMetricParamsWithValidParamShouldEditParam() {
        var oldParams = (HashMap<String, Double>) testResident.getMetricParams().clone();
        testResident.editMetricParam("Recyclage", 1.0);
        assertNotEquals(oldParams.get("Recyclage"), testResident.getMetricParams().get("Recyclage"));

        testResident.editMetricParam("Compostage", 1.0);
        assertNotEquals(oldParams.get("Compostage"), testResident.getMetricParams().get("Compostage"));

    }
}
