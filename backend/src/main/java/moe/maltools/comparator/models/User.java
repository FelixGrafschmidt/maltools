package moe.maltools.comparator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties
public class User {
	public User(String name) {
		this.name = name;
	}

	private String name;

	private String imageUrl;
}
