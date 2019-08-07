package moe.maltools.comparator.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Anime
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties
public class Anime {
	public Anime(String name) {
		this.name = name;
	}

	@JsonAlias(value = "title")
	private String name;

	@JsonAlias(value = "image_url")
	private String imageUrl;

	@JsonAlias(value = "mal_id")
	private long id;

	private String url;
}
