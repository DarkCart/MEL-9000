package rule34bot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {

	public abstract void parse(String[] args, MessageReceivedEvent event);
	public abstract String getPrefix();

	public static void sendMessage(String message, MessageChannel channel) {
		channel.sendMessage(message).queue();
	}
}
