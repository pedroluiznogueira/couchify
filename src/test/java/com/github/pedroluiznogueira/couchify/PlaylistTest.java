package com.github.pedroluiznogueira.couchify;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.github.pedroluiznogueira.couchify.entity.Playlist;
import com.github.pedroluiznogueira.couchify.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaylistTest extends AbstractTests {

    @Autowired
    private PlaylistRepository playlistRepository;

    @BeforeEach
    public void setup() {
        assertThat(getCluster()).isNotNull();
        assertThat(getBucket()).isNotNull();
        playlistRepository = new PlaylistRepository(getCluster(), getBucket(), "couchify");
    }

    @Test
    public void findPlaylistByIdAsString_Success() {
        // arrange
        final String documentKey = "playlist::00011b74-12be-4e60-abbf-b1c8b9b40bfe";

        // act
        final String result = playlistRepository.findByIdAsString(documentKey);

        // assert
        assertThat(result).isNotNull();
    }

    @Test
    public void findPlaylistByIdAsString_Error_DocumentNotFound() {
        // arrange
        final String documentKey = RandomStringUtils.random(10);

        // act
        final Executable executable = () -> playlistRepository.findByIdAsString(documentKey);

        // assert
        assertThrows(DocumentNotFoundException.class, executable);
    }

    @Test
    public void findPlaylistByIdAsPlaylist_Success() {
        // arrange
        final String documentKey = "playlist::00011b74-12be-4e60-abbf-b1c8b9b40bfe";
        final String id = "00011b74-12be-4e60-abbf-b1c8b9b40bfe";

        // act
        final Playlist result = playlistRepository.findByIdAsPlaylist(documentKey);

        // assert
        assertThat(result)
                .extracting(Playlist::getId)
                .isEqualTo(id);
    }

    @Test
    public void findPlaylistByIdAsPlaylist_Error_DocumentNotFound() {
        // arrange
        final String documentKey = RandomStringUtils.random(10);

        // act
        final Executable executable = () -> playlistRepository.findByIdAsPlaylist(documentKey);

        // assert
        assertThrows(DocumentNotFoundException.class, executable);
    }

    @Test
    public void createPlaylist_Success() {
        // arrange
        final Playlist playlist = new Playlist();

        // act
        final Long cas = playlistRepository.createPlaylist(playlist);

        // assert
        assertThat(cas).isNotNull();
    }

}
