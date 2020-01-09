package horse.code.spigotskulls;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.io.IOException;
import java.net.URL;

public class Util {
	private static final String MOJANG_PROFILE_URL = "https://api.mojang.com/users/profiles/minecraft/%s";
	private static Gson gson = new Gson();

	public static UUID getPlayerUUIDFromName(String name) throws IOException {
		String urlStr = String.format(MOJANG_PROFILE_URL, name);
		URL url = new URL(urlStr);
		String json = IOUtils.toString(url, StandardCharsets.UTF_8);
		if (json.isEmpty()) throw new RuntimeException("Invalid player name");
		PlayerUUID uuid = gson.fromJson(json, PlayerUUID.class);
		return uuid.getId();
	}

	private class PlayerUUID {
		private String name;
		private String id;

		public String getName() { return this.name; }
		public UUID getId() {
			StringBuilder sb = new StringBuilder(this.id);
			sb.insert(8,  '-');
			sb.insert(13, '-');
			sb.insert(18, '-');
			sb.insert(23, '-');
			return UUID.fromString(sb.toString());
		}
	}
}
