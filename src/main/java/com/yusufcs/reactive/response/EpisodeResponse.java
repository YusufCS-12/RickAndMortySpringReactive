package com.yusufcs.reactive.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)

public class EpisodeResponse {

	private String id;
	private String name;
	private String air_date;
	private String episode;
	private List<String> characters;
	private String url;

}
