package DevLSB.spotify.controller;

import DevLSB.spotify.client.Album;
import DevLSB.spotify.client.AlbumSpotifyClient;
import DevLSB.spotify.client.AuthSpotifyClient;
import DevLSB.spotify.client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld(@RequestHeader("grant_type") String grantType,
                                                  @RequestHeader("client_id") String clientId,
                                                  @RequestHeader("client_secret") String clientSecret) {
        var request = new LoginRequest(
                grantType,
                clientId,
                clientSecret
        );
        var token  = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
