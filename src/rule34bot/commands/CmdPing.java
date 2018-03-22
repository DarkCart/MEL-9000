package rule34bot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdPing extends Command {

	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		sendMessage("pong", event.getChannel());
	}

	@Override
	public String getPrefix() {
		return "-ping";
	}

}
