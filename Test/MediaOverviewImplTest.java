import org.junit.gen5.api.Test;
import static org.junit.gen5.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import domain.*;
import java.util.Set;
import domain.MediaOverview.SearchType;

public class MediaOverviewImplTest {
    public static void main(String[] args){
        //testInitialize();
        //testLoadData();
        //testGetMediaList();
        //testGetCategories();
        //testAddFavorite();
        //testRemoveFavorite();
        //testSearchMedia();
        //testSearchCategory();
        //testGetFavoriteList();
        testIsFavorite();
    }
    @Test
    static void testInitialize() {
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();

        // Get the media list and categories
        List<Media> mediaList = mediaOverview.getMediaList();
        Set<String> categories = mediaOverview.getCategories();

        // Assert that the media list and categories are not empty
        assertFalse(mediaList.isEmpty());
        assertFalse(categories.isEmpty());
    }
    @Test
    static void testLoadData() {
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();

        // Create a list of test media data
        List<List<String>> testMediaData = Arrays.asList(
                Arrays.asList("Test Media 1", "2001", "Action, Drama", "5.0"),
                Arrays.asList("Test Media 2", "2002", "Comedy, Romance", "4.5"),
                Arrays.asList("Test Media 3", "2003", "Thriller, Horror", "4.0")
        );
        // Load the test data and get the resulting list of media
        mediaOverview.loadData(testMediaData, mediaOverview.getMediaList());
        List<Media> mediaList = mediaOverview.getMediaList();

        // Assert that the list has the correct number of media
        assertEquals(203, mediaList.size());

        // Assert that the first item in the list has the correct data
        Media firstMedia = mediaList.get(200);
        assertEquals("Test Media 1", firstMedia.getName());
        assertEquals("2001", firstMedia.getYear());
        assertEquals(5.0, firstMedia.getRating());
        List<String> cate = Arrays.asList("Action", "Drama");
        assertEquals(cate, firstMedia.getCategories());

        //The sixth media in the medialist is "Goen with the wind" with a ration of 8.2
        //released 1939 and has the categories Drama, History and Romance. Check if this is correct
        Media seventhMedia = mediaList.get(6);
        assertEquals("Gone With The Wind", seventhMedia.getName());
        assertEquals("1939", seventhMedia.getYear());
        assertEquals(8.2, seventhMedia.getRating());
        List<String> cateTwo = Arrays.asList("Drama","History","Romance");
        assertEquals(cateTwo, seventhMedia.getCategories());
    }

    @Test
    static void testGetMediaList() {
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();
        List<Media> mediaList = mediaOverview.getMediaList();

        // Assert that mediaList is not null
        assertNotNull(mediaList);
    }

    @Test
    static void testGetCategories() {
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();
        Set<String> categories = mediaOverview.getCategories();

        // Assert that categories is not null
        assertNotNull(categories);
    }

    @Test
    static void testAddFavorite() {
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();

        //Get current favorite Media
        List<Media> favoriteMedia = mediaOverview.getFavoriteMedia();
        System.out.println(favoriteMedia);
        //Add favorites
        List<String> testCategory = Arrays.asList("Action, Drama");
        Media media1 = new Movie("Test Media 1", testCategory, 5.0, "2001",null);
        Media media2 = new Movie("Test Media 2", testCategory, 8.0, "2005",null);
        Media media3 = new Movie("Test Media 3", testCategory, 1.0, "201",null);
        mediaOverview.addFavorite(media1);
        mediaOverview.addFavorite(media2);
        mediaOverview.addFavorite(media3);
        // Get the list of favorite media
        List<Media> updatedFavoriteMedia = mediaOverview.getFavoriteMedia();
        System.out.println(updatedFavoriteMedia);
    }

    @Test
    static void testRemoveFavorite() {
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();

        //Add favorites
        List<String> testCategory = Arrays.asList("Action, Drama");
        Media media1 = new Movie("Test Media 1", testCategory, 5.0, "2001",null);
        Media media2 = new Movie("Test Media 2", testCategory, 8.0, "2005",null);
        Media media3 = new Movie("Test Media 3", testCategory, 1.0, "201",null);
        mediaOverview.addFavorite(media1);
        mediaOverview.addFavorite(media2);
        mediaOverview.addFavorite(media3);
        // Get the list of favorite media
        List<Media> favoriteMedia = mediaOverview.getFavoriteMedia();
        System.out.println(favoriteMedia);
        // Remove third media (Test media 3) from favorite media
        Media mediaToRemove = favoriteMedia.get(2);
        mediaOverview.removeFavorite(mediaToRemove);

        // Get the updated list of favorite media
        List<Media> updatedFavoriteMedia = mediaOverview.getFavoriteMedia();
        assertEquals(favoriteMedia, updatedFavoriteMedia);
        System.out.println(updatedFavoriteMedia);
    }

    @Test
    static void testSearchMedia(){
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();
        List<String> testCategory = Arrays.asList("Comedy, Romance");
        List<List<String>> testMediaData = Arrays.asList(
                Arrays.asList("Test Media 1", "2001", "Action, Drama", "5.0"),
                Arrays.asList("Test Media 2", "2005", "Comedy, Romance", "8.0"),
                Arrays.asList("Test Media 3", "2003", "Thriller, Horror", "4.0")
        );
        mediaOverview.loadData(testMediaData, mediaOverview.getMediaList());
        List<Media> mediaList = mediaOverview.getMediaList();
        List<Media> testName = Arrays.asList(mediaList.get(201));
        List<Media> testReturnSearch = mediaOverview.searchMedia("Test Media 2");
        assertEquals(testName,testReturnSearch);


    }

    @Test
    static void testSearchCategory(){
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();
        List<String> testCategory = Arrays.asList("meddigsa");
        List<List<String>> testMediaData = Arrays.asList(
                Arrays.asList("Test Media 1", "2001", "Action, Drama", "5.0"),
                Arrays.asList("Test Media 2", "2005", "Comedy, Romance", "8.0"),
                Arrays.asList("Test Media 3", "2003", "Hejsa, meddigsa", "4.0")
        );
        Media media2 = new Movie("Test Media 2", testCategory, 8.0, "2005",null);
        mediaOverview.loadData(testMediaData, mediaOverview.getMediaList());
        List<Media> mediaList = mediaOverview.getMediaList();
        List<Media> testName = Arrays.asList(mediaList.get(202));
        List<Media> testReturnSearch = mediaOverview.searchCategories(testCategory, SearchType.MOVIE);
        assertEquals(testName,testReturnSearch);


    }
    @Test
    static void testGetFavoriteList() {
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();
        List<Media> mediaList = mediaOverview.getFavoriteMedia();

        // Assert that mediaList is not null
        assertNotNull(mediaList);
    }

    static void testIsFavorite(){
        MediaOverviewImpl mediaOverview = new MediaOverviewImpl();
        List<String> testCategory = Arrays.asList("meddigsa");
        List<List<String>> testMediaData = Arrays.asList(
                Arrays.asList("Test Media 1", "2001", "Action, Drama", "5.0"),
                Arrays.asList("Test Media 2", "2005", "Comedy, Romance", "8.0"),
                Arrays.asList("Test Media 3", "2003", "Hejsa, meddigsa", "4.0")
        );
        mediaOverview.loadData(testMediaData, mediaOverview.getMediaList());
        mediaOverview.addFavorite(mediaOverview.getMediaList().get(200));
        mediaOverview.addFavorite(mediaOverview.getMediaList().get(201));
        mediaOverview.addFavorite(mediaOverview.getMediaList().get(202));
        List<Media> mediaList = mediaOverview.getMediaList();
        Media media2 = mediaList.get(201);
        boolean isFavorite = mediaOverview.isFavorite(media2);
        assertEquals(true,isFavorite);

    }
}