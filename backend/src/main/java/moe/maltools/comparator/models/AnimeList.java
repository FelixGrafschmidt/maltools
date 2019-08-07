package moe.maltools.comparator.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AnimeList
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties
public class AnimeList {

	private boolean request_cached;
	private float request_cache_expiry;

	private ArrayList<Anime> anime = new ArrayList<Anime>();

	private String request_hash;

}
