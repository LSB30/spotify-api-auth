package DevLSB.spotify.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "AlbumSpotifyClient",
        url = "https://api.spotify.com"
)
public interface AlbumSpotifyClient {


}
