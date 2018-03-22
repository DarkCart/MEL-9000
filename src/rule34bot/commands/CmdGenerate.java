package rule34bot.commands;

import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import rule34bot.Bot;

public class CmdGenerate extends Command {
	
	StringBuilder sb = new StringBuilder();
	
	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		sb.setLength(0);
		if (event.getAuthor().getId().equals("141708505387237376")) {
			for (int i = 0; i < Integer.parseInt(event.getMessage().getContentDisplay().split(" ")[1]); i++) {
				sb.append(Bot.words.get(new Random().nextInt(Bot.words.size())) + " ");
			}
			sendMessage(sb.toString(), event.getChannel());
		}
	}

	@Override
	public String getPrefix() {
		return "-generate";
	}

}
