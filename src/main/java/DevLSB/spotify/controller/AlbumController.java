package DevLSB.spotify.controller;

import DevLSB.spotify.client.AuthSpotifyClient;
import DevLSB.spotify.client.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> helloWorld() {
        var request = new LoginRequest(
                "client_credentials",
                "72e9e4795d5b42bd95e99599763bea57",
                "888ec5547bee463994fd396a6a067fd3"
        );
        return ResponseEntity.ok(authSpotifyClient.login(request).getAccessToken());
    }
}
