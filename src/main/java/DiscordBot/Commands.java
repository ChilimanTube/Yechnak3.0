package DiscordBot;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Commands extends ListenerAdapter {
    ArrayList<String> subjects = new ArrayList<>();
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");

        //BAD WORD DETECTION
        // It's a method that checks if the message contains a bad word.
        if (Moderation.badWordDetection(args)) {
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Please, watch your language!").queue();
        }

        //PING
        // It's a command for latency check.
        if(args[0].equalsIgnoreCase(Main.prefix + "ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }

        //PREFIX DISPLAY
        // It's a command that displays the current prefix.
        if (args[0].equalsIgnoreCase(Main.prefix + "prefix")){
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Ayo, my prefix is `!`").queue();
        }

        // COMMAND LIST
        // It's a command that displays all commands.
        if (args[0].equalsIgnoreCase(Main.prefix + "commands")){
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("""
                    Commands:
                     `prefix` - displays current prefix\s
                     `commands` - displays all commands\s
                     `config` - settings\s
                     `ping` - command for testing latency\s
                     `timeout`- timeout a user\s
                     `remove timeout`- remove a timeout from a user\s
                     `poll` - command for creating a yes or no poll""").queue();
        }

        // CONFIGURATION SETTINGS
        // It's a command that allows you to change the prefix of the bot.
        if (args[0].equalsIgnoreCase(Main.prefix + "config")){
            //PREFIX
            if (args.length == 2) {
                if (args[1].equalsIgnoreCase("prefix")){
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage("Do you want to change the prefix? \n To change a prefix use `config prefix set <prefix>`").queue();
                    event.getChannel().sendMessage(":warning: **This changes the bots prefix globally** :warning:").queue();
                }
            }
            if (args.length == 4) {
                if (args[1].equalsIgnoreCase("prefix")){
                    if (args[2].equalsIgnoreCase("set")){
                        event.getChannel().sendTyping().queue();
                        Main.prefix = args[3];
                        event.getChannel().sendMessage("New global prefix was set.").queue();
                    }
                }
            }else if (args.length == 1){
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Configuration commands: \n`config prefix` - prefix changing").queue();
            }
        }

        //SUBJECTS - WIP
        // It's a command that allows you to add subjects to a list and then display them.
        if(args[0].equalsIgnoreCase(Main.prefix + "subjects"))  {
           if (args.length == 1) {
               if (subjects.size() >= 1) {
                   event.getChannel().sendTyping().queue();
                   event.getChannel().sendMessage("**Available commands:** \n" +
                           " list - displays all subjects").queue();
               } else {
                   event.getChannel().sendTyping().queue();
                   event.getChannel().sendMessage("No subjects were set. Type-in `" + Main.prefix + "subject add` to add one!").queue();
               }
           }
           if (args.length == 2 || args.length == 3) {
               if (args[1].equalsIgnoreCase("add")) {
                   if (args.length == 2) {
                       event.getChannel().sendTyping().queue();
                       event.getChannel().sendMessage("Type-in `" + Main.prefix + "subjects add <subject>` to add a subject!").queue();
                   } else {
                       event.getChannel().sendTyping().queue();
                       subjects.add(args[2]);
                       event.getChannel().sendMessage("Subject `" + args[2] + "` added.").queue();
                   }
               }
               if (args[1].equalsIgnoreCase("list")) {
                   event.getChannel().sendTyping().queue();
                   if(subjects.size() == 0){
                       event.getChannel().sendMessage("No subjects were set. Type-in `" + Main.prefix + "subject add` to add one!").queue();
                   }
                   else {
                       for (String subject : subjects) {
                           event.getChannel().sendMessage(subject).queue();
                       }
                   }
               }
           }
        }

        // POLL
        // It's a command that allows you to create a poll.
        if (args[0].equalsIgnoreCase(Main.prefix + "poll")) {
            String ping;
            String pollMessage;
            // Ping @everyone
            if(args[1].equalsIgnoreCase("pe")){
                ping = "@everyone ";
                //YesOrNo Poll
                if(args[2].equalsIgnoreCase("yn")){
                    StringBuilder buffer = new StringBuilder();
                    for(int i = 3; i < args.length; i++) {
                        buffer.append(args[i]).append(" ");
                    }
                    event.getChannel().sendTyping().queue();
                    pollMessage = buffer.toString();
                    event.getChannel().sendMessage(ping + pollMessage).queue(message -> {
                        message.addReaction("✅").queue();
                        message.addReaction("❌").queue();
                    });
                }
            }
            // Ping @here
            if(args[1].equalsIgnoreCase("ph")){
                ping = "@here ";
                //YesOrNo Poll
                if(args[2].equalsIgnoreCase("yn")){
                    StringBuilder buffer = new StringBuilder();
                    for(int i = 3; i < args.length; i++) {
                        buffer.append(args[i]).append(" ");
                    }
                    event.getChannel().sendTyping().queue();
                    pollMessage = buffer.toString();
                    event.getChannel().sendMessage(ping + pollMessage).queue(message -> {
                        message.addReaction("✅").queue();
                        message.addReaction("❌").queue();
                    });
                }
            }
            if(args[1].equalsIgnoreCase("yn")){
                ping = "";
                //YesOrNo Poll
                    StringBuilder buffer = new StringBuilder();
                    for(int i = 2; i < args.length; i++) {
                        buffer.append(args[i]).append(" ");
                    }
                    event.getChannel().sendTyping().queue();
                    pollMessage = buffer.toString();
                    event.getChannel().sendMessage(ping + pollMessage).queue(message -> {
                        message.addReaction("✅").queue();
                        message.addReaction("❌").queue();
                    });
            }
        }

        //TIMEOUT
        // A command that allows you to timeout a user for a day.
        if(args[0].equalsIgnoreCase(Main.prefix + "timeout")){
            if(args.length == 2){
                event.getChannel().sendTyping().queue();
                String id = args[1];
                id = id.replace("<@", "");
                id = id.replace(">", "");
                long userId = Long.parseLong(id);
                RestAction<Member> restaction = event.getGuild().retrieveMemberById(userId);
                restaction.queue(member -> member.timeoutFor(1, TimeUnit.DAYS));
                event.getChannel().sendMessage("Timeout for <@" + userId + "> has been set.").queue();
            } else {
                event.getChannel().sendMessage("Incorrect command. Use `" + Main.prefix + "timeout @<member>`").queue();
            }
        }

        //REMOVE TIMEOUT
        // Checking if the user is timeouted and if he is, it removes the timeout.
        if(args[0].equalsIgnoreCase(Main.prefix + "remove") && args[1].equalsIgnoreCase("timeout")){
            if(args.length == 3){
                Member member = event.getGuild().getMemberById(args[2].replace("<@", "").replace(">",""));
                if(member != null && member.isTimedOut()){
                    member.removeTimeout();
                    event.getChannel().sendMessage("Timeout for user @" + member + " was removed.").queue();
                }else{
                    event.getChannel().sendMessage("User @" + member + "  is not timeouted.").queue();
                }
            }
        }
    }
}
