import java.util.*;
import domain.Series;
import domain.Episode;

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
		ArrayList<Episode> EpisodeList = new ArrayList<>();
		HashMap<Integer, ArrayList<Episode>> hypo = new HashMap<>();

		EpisodeList.add(new Episode("Episode 1"));
		EpisodeList.add(new Episode("Episode 2"));
		hypo.put(1, EpisodeList);

		Series series = new Series(null, null, 0.5, null, null, season1 + "-" + episode1);
		Map<Integer, List<Episode>> seasonToEpisode = series.getSeasonToEpisode();

		List<Episode> episodes = seasonToEpisode.get(Integer.valueOf(season1));

		for (Episode episode : episodes) {
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
		ArrayList<Episode> EpisodeList = new ArrayList<>();
		HashMap<Integer, ArrayList<Episode>> hypo = new HashMap<>();

		EpisodeList.add(new Episode("Episode 1"));
		EpisodeList.add(new Episode("Episode 2"));
		hypo.put(1, EpisodeList);

		Series series = new Series(null, null, 0.5, null, null, season1 + "-" + episode1 + ", " + season2 + "-" + episode2);
		Map<Integer, List<Episode>> seasonToEpisode = series.getSeasonToEpisode();

		List<Episode> episodes = seasonToEpisode.get(Integer.valueOf(season1));
		for (Episode episode : episodes) {
			episodeNamesResult.add(episode.getName());
		}
		for (int i = 1; i < Integer.valueOf(episode1) + 1; i++) {
			episodeNamesExcpected.add("Episode " + i);
		}

		episodes = seasonToEpisode.get(Integer.valueOf(season2));
		for (Episode episode : episodes) {
			episodeNamesResult.add(episode.getName());
		}
		for (int i = 1; i < Integer.valueOf(episode2) + 1; i++) {
			episodeNamesExcpected.add("Episode " + i);
		}


		assertEquals(episodeNamesResult, episodeNamesExcpected);


	}
}