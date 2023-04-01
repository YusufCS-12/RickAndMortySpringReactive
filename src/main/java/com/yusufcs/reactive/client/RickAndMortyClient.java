package com.yusufcs.reactive.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import com.yusufcs.reactive.response.CharacterResponse;
import com.yusufcs.reactive.response.EpisodeResponse;
import com.yusufcs.reactive.response.ListOfEpisodesResponse;
import com.yusufcs.reactive.response.LocationResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {

	private final WebClient webClient;
	
	
	
	public RickAndMortyClient(WebClient.Builder builder) {
		
		webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
	}


	//Mono →Tekil bir obje streami

	public Mono<CharacterResponse> findACharacterById(String id) {
        log.info("[{}] kimliğine sahip bir karakter aranıyor", id);
        return webClient
                .get()
                .uri("/character/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("girilen parametreleri kontrol edin")))
                .bodyToMono(CharacterResponse.class);
    }

    public Mono<LocationResponse> findALocationById(String id) {
        log.info("[{}] kimliğine sahip bir karakter aranıyor", id);
        return webClient
                .get()
                .uri("/location/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("girilen parametreleri kontrol edin")))
                .bodyToMono(LocationResponse.class);
    }


    public Mono<EpisodeResponse> findAEpisodeById(String id) {
        log.info("[{}] kimliğine sahip bir karakter aranıyor", id);
        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("girilen parametreleri kontrol edin")))
                .bodyToMono(EpisodeResponse.class);
    }

    //Flux →Multiple bir obje streami üzerinden veri almak üzerine tasarlanmaktadır.

    public Flux<ListOfEpisodesResponse> ListAllEpisodes() {
        log.info("Kayıtlı tüm bölümleri listeleme");
        return webClient
                .get()
                .uri("/episode")
                .accept(MediaType.APPLICATION_JSON)
                //Retrieve →Direkt olarak response bodye odaklanır ,kullanımı daha kolaydır
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("girilen parametreleri kontrol edin")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }

}