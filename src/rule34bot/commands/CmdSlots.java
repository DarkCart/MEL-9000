package rule34bot.commands;

import java.util.Random;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdSlots extends Command{
	
	Random rand = new Random();
	String[] slotChoices = { ":tangerine:", ":apple:", ":pear:", ":cherries:", ":strawberry:" };

	@Override
	public void parse(String[] args, MessageReceivedEvent event) {
		String choiceOne = slotChoices[rand.nextInt(slotChoices.length)];
		String choiceTwo = slotChoices[rand.nextInt(slotChoices.length)];
		String choiceThree = slotChoices[rand.nextInt(slotChoices.length)];
		sendMessage("`Rolling.. (" + event.getAuthor().getName() + ")`\n" + choiceOne + " " + choiceTwo + " "
				+ choiceThree, event.getChannel());
		if (choiceOne.equals(choiceTwo) && choiceTwo.equals(choiceThree)) {
			sendMessage("`JACKPOT!`", event.getChannel());
		}
	}

	@Override
	public String getPrefix() {
		return "-slots";
	}

}
