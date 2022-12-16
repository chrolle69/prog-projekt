import domain.Media;
import domain.MediaOverviewImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    @Test
    void testToString(){
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();

        List<List<String>> testMediaData = Arrays.asList(
                Arrays.asList("Test Media 1", "2001", "Action, Drama", "5.0"),
                Arrays.asList("Test Media 2", "2002", "Comedy, Romance", "4.5"),
                Arrays.asList("Test Media 3", "2003", "Thriller, Horror", "4.0")
        );
        mediaOverview.loadData(testMediaData, mediaOverview.getMediaList());
        List<Media> mediaList = mediaOverview.getMediaList();
        String exp ="Test Media 1; 2001; Action, Drama; 5.0";
        assertEquals(exp,mediaList.get(200).toString());

    }

}