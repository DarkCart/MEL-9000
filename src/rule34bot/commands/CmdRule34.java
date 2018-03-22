package rule34bot.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdRule34 extends Command {

	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		if (event.getChannel().getName().contains("nsfw")) {
			String searchTerm = event.getMessage().getContentDisplay().split(" ")[1];
			int loops = 3;

			if (event.getMessage().getContentDisplay().split(" ").length == 3) {
				loops = Integer.parseInt(event.getMessage().getContentDisplay().split(" ")[2]);
				sendMessage("`Searching " + loops + " pages...`", event.getChannel());
			} else {
				sendMessage("`Searching...`", event.getChannel());
			}

			ArrayList<String> imageURLs = new ArrayList<String>();
			for (int i = 0; i < loops; i++) {
				getImages(searchTerm, i, imageURLs);
			}

			if (imageURLs.size() > 0) {
				String chosenImage = pickImage(searchTerm, imageURLs);
				sendMessage("`Tags: " + getTags(chosenImage) + "`", event.getChannel());
				sendMessage(chosenImage, event.getChannel());

			} else {
				sendMessage("`No images found.`", event.getChannel());
			}
		}
	}

	private static void getImages(String searchTerm, int i, ArrayList<String> imageURLs) {
		try {
			Document doc = Jsoup.connect("http://rule34.paheal.net/post/list/" + searchTerm + "/" + i).get();

			Elements links = doc.getElementsByTag("a");

			for (Element e : links) {
				if (e.text().equals("Image Only")) {
					imageURLs.add(e.attr("href"));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String pickImage(String searchTerm, ArrayList<String> imageURLs) {
		return imageURLs.get(new Random().nextInt(imageURLs.size()));
	}

	private static String getTags(String image) {
		return image.replaceAll("%20", " ").split(" - ")[1];
	}

	@Override
	public String getPrefix() {
		return "-r34";
	}

}
