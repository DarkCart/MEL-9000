package rule34bot.commands;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdFav extends Command {

	HashMap<Long, String> favorites = new HashMap<Long, String>();
	PrintWriter writer;

	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		long authorId = event.getAuthor().getIdLong();
		if (args.length == 2) {
			if (args[1].equals("load")) {
				loadFromFile();
				sendMessage("`Attempted to load from file.`", event.getChannel());
			}
			if (args[1].equals("save")) {
				writeToFile();
				sendMessage("`Attempted to save to file.`", event.getChannel());
			}
			if (args[1].contains("http")) {
				favorites.put(authorId, args[1]);
				sendMessage("`Set favorite for " + event.getAuthor().getName() + "`", event.getChannel());
			}
		} else if (args.length == 1 && event.getChannel().getName().contains("nsfw")) {
			sendMessage("`Favorite for " + event.getAuthor().getName() + ":`" + favorites.get(authorId),
					event.getChannel());
		}
	}

	@Override
	public String getPrefix() {
		return "-fav";
	}

	private void loadFromFile() {
		favorites.clear();
		try {
			String[] lines = new String(Files.readAllBytes(Paths.get("favorites.txt"))).split("\n");
			for (String s : lines) {
				String[] s1 = s.split(",");
				favorites.put(Long.parseLong(s1[0]), s1[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeToFile() {
		try {
			writer = new PrintWriter("favorites.txt", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Long l : favorites.keySet()) {
			for (String s : favorites.values()) {
				writer.println(l + "," + s);
			}
		}

		writer.close();
	}
}
