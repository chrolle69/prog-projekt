import java.util.*;
import domain.Series;
import domain.Video;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SeriesTest {

	@Before
	public void setUp() {
		System.out.println("**** START ****");
	}

	@After
	public void tearDown() {
		System.out.println("**** END ****");
	}

	@Test
	public void oneSeasonTest() {
		String season1 = "1";
		String episode1 = "2";
		
		ArrayList<String> episodeNamesExcpected = new ArrayList<>();
		ArrayList<String> episodeNamesResult = new ArrayList<>();
		ArrayList<Video> EpisodeList = new ArrayList<>();
		

		EpisodeList.add(new Video("Episode 1"));
		EpisodeList.add(new Video("Episode 2"));
		

		Series series = new Series(null, null, 0.5, null, null, season1 + "-" + episode1);
		Map<String, List<Video>> seasonToEpisode = series.getInfoMap();

		List<Video> episodes = seasonToEpisode.get(season1);

		for (Video episode : episodes) {
			episodeNamesResult.add(episode.getName());
		}

		for (int i = 1; i < Integer.valueOf(episode1) + 1; i++) {
			episodeNamesExcpected.add("Episode " + i);
		}


		assertEquals(episodeNamesResult, episodeNamesExcpected);


	}


	@Test
	public void TwoSeasonTest() {
		String season1 = "1";
		String episode1 = "3";

		String season2 = "2";
		String episode2 = "2";
		
		ArrayList<String> episodeNamesExcpected = new ArrayList<>();
		ArrayList<String> episodeNamesResult = new ArrayList<>();
		ArrayList<Video> EpisodeList = new ArrayList<>();


		EpisodeList.add(new Video("Episode 1"));
		EpisodeList.add(new Video("Episode 2"));

		Series series = new Series(null, null, 0.5, null, null, season1 + "-" + episode1 + ", " + season2 + "-" + episode2);
		Map<String, List<Video>> seasonToEpisode = series.getInfoMap();

		List<Video> episodes = seasonToEpisode.get(season1);
		for (Video episode : episodes) {
			episodeNamesResult.add(episode.getName());
		}
		for (int i = 1; i < Integer.valueOf(episode1) + 1; i++) {
			episodeNamesExcpected.add("Episode " + i);
		}

		episodes = seasonToEpisode.get(season2);
		for (Video episode : episodes) {
			episodeNamesResult.add(episode.getName());
		}
		for (int i = 1; i < Integer.valueOf(episode2) + 1; i++) {
			episodeNamesExcpected.add("Episode " + i);
		}


		assertEquals(episodeNamesResult, episodeNamesExcpected);


	}
}