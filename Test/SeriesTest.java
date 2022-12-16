import org.junit.jupiter.api.Test;
import domain.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {
    @Test
    void testToString(){
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();

        List<List<String>> testMediaData = Arrays.asList(
                Arrays.asList("Test Media 1", "2001", "Action, Drama", "5.0", "1-13, 2-25, 3-12, 4-55, 5-12;"),
                Arrays.asList("Test Media 2", "2002", "Comedy, Romance", "4.5", "1-17, 2-25, 3-16;"),
                Arrays.asList("Test Media 3", "2003", "Thriller, Horror", "4.0", "1-9, 2-25, 3-19, 4-28;")
        );
        mediaOverview.loadData(testMediaData, mediaOverview.getMediaList());
        List<Media> mediaList = mediaOverview.getMediaList();
        String exp ="Test Media 1; 2001; Action, Drama; 5.0; 1-13, 2-25, 3-12, 4-55, 5-12;";
        assertEquals(exp,mediaList.get(200).toString());

    }
    @Test
    void testGetInfoMap(){
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();
        List<List<String>> testMediaData = Arrays.asList(
                Arrays.asList("Test Media 1", "2001", "Action, Drama", "5.0", "1-13, 2-25, 3-12, 4-55, 5-12;"),
                Arrays.asList("Test Media 2", "2002", "Comedy, Romance", "4.5", "1-17, 2-25, 3-16;"),
                Arrays.asList("Test Media 3", "2003", "Thriller, Horror", "4.0", "1-9, 2-25, 3-19, 4-28;")
        );
        mediaOverview.loadData(testMediaData, mediaOverview.getMediaList());
        List<Media> mediaList = mediaOverview.getMediaList();
        Map<String, List<Video>> mediaMap = mediaList.get(201).getInfoMap();
        assertNotNull(mediaMap);


    }
}