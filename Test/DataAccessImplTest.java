import domain.MediaOverviewImpl;
import org.junit.jupiter.api.Test;
import data.DataAccessImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessImplTest {

    @Test
    void testLoad(){

    DataAccessImpl dataTest = new DataAccessImpl();
    List<List<String>> expData = new ArrayList<>();
    List<String> contList = new ArrayList<>();
    expData.add(contList);
    expData.get(0).add("The Wizard Of Oz");
    expData.get(0).add("1939");
    expData.get(0).add("Adventure, Family, Fantasy");
    expData.get(0).add("8,0");

    List<List<String>> dataRead = dataTest.load("Data/film.txt");
    List<String> expDataReal = expData.get(0);
    List<String> realData = dataRead.get(7);
    assertEquals(expDataReal,realData);


    }
}