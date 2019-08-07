package moe.maltools.comparator.data;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import moe.maltools.comparator.models.Anime;
import moe.maltools.comparator.models.AnimeList;
import moe.maltools.comparator.models.User;

/**
 * MalDao
 */
@Component
public class MalDao {

	private static final String BASEURL = "https://api.jikan.moe/v3";

	public User getUser(String userName) {
		User user = new User();

		RestTemplate restTemplate = new RestTemplate();
		user = restTemplate.getForObject(BASEURL + "/user/" + userName, User.class);

		return user;
	}

	public List<Anime> getUserAnimeList(String userName) {
		AnimeList animeList = new AnimeList();

		String url = BASEURL + "/user/" + userName + "/animelist";

		RestTemplate restTemplate = new RestTemplate();
		animeList = restTemplate.getForObject(url, AnimeList.class);

		System.out.println(animeList);

		return animeList.getAnime();
	}

}
