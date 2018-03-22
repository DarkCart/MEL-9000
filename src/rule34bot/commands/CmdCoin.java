package rule34bot.commands;

import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdCoin extends Command{
	
	Random rand = new Random();

	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		int coinSeed = rand.nextInt(99);
		if (coinSeed > 50) {
			sendMessage("`Tails.`", event.getChannel());
		} else {
			sendMessage("`Heads.`", event.getChannel());
		}
	}

	@Override
	public String getPrefix() {
		return "-coin";
	}

}
