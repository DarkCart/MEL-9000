package rule34bot;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import rule34bot.commands.CmdCoin;
import rule34bot.commands.CmdDice;
import rule34bot.commands.CmdFav;
import rule34bot.commands.CmdGenerate;
import rule34bot.commands.CmdPing;
import rule34bot.commands.CmdRule34;
import rule34bot.commands.CmdRussian;
import rule34bot.commands.CmdSlots;
import rule34bot.commands.Command;

public class Bot {
	
	public static ArrayList<String> words = new ArrayList<String>();
	ArrayList<Command> commands = new ArrayList<Command>();

	public static void main(String[] args) {
		new Bot();
	}

	public Bot() {
		try {
			@SuppressWarnings("unused")
			JDA jda = new JDABuilder(AccountType.BOT)
					.setToken(new String(Files.readAllBytes(Paths.get("bot.dat"))))
					.addEventListener(new Listener()).buildBlocking();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		commands.add(new CmdCoin());
		commands.add(new CmdDice());
		commands.add(new CmdFav());
		commands.add(new CmdGenerate());
		commands.add(new CmdPing());
		commands.add(new CmdRule34());
		commands.add(new CmdRussian());
		commands.add(new CmdSlots());
	}

	class Listener extends ListenerAdapter {

		@Override
		public void onMessageReceived(MessageReceivedEvent event) {
			
			for (Command c: commands) {
				if (event.getMessage().getContentDisplay().startsWith(c.getPrefix())) {
					c.parse(event.getMessage().getContentDisplay().split(" "), event);
				}
			}
			
			String[] wordArray = event.getMessage().getContentDisplay().split(" ");
			for (String s1 : wordArray) {
				words.add(s1);
			}
		}
	}
}
