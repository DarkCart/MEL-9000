package rule34bot.commands;

import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdDice extends Command {
	
	Random rand = new Random();

	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		int i = rand.nextInt(5) + 1;
		sendMessage("`You roll a " + i + "`", event.getChannel());
	}

	@Override
	public String getPrefix() {
		return "-dice";
	}

}
