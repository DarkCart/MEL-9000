package rule34bot.commands;

import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdRussian extends Command {
	
	Random rand = new Random();

	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		int i = rand.nextInt(5) + 1;
		if ((rand.nextInt(5) + 1) == i) {
			sendMessage(":dizzy_face: :boom: :gun: `BANG!`", event.getChannel());
		} else {
			sendMessage(":fearful: :gun: `CLICK!`", event.getChannel());
		}
	}

	@Override
	public String getPrefix() {
		return "-russian";
	}

}
