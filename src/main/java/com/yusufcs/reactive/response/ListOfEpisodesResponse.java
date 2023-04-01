package com.yusufcs.reactive.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ListOfEpisodesResponse {

	List<EpisodeResponse> results;
}
