package moe.maltools.comparator.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import moe.maltools.comparator.data.MalDao;
import moe.maltools.comparator.models.Anime;

@RestController
public class AnimeController {

	@Autowired
	private MalDao malDao;

	@GetMapping(path = "/get-anime-for-username", params = "userName")
	@CrossOrigin(origins = "http://localhost:8081")
	public Collection<Anime> getAnimeForUserName(@Param(value = "userName") String userName) {

		List<Anime> animeList = new ArrayList<Anime>();
		animeList = malDao.getUserAnimeList(userName);

		return animeList;
	}
}
