package DiscordBot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Commands extends ListenerAdapter {
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        ArrayList<String> studylinks = new ArrayList<>();
        ArrayList<String> subjects = new ArrayList<>();

        Moderation.badWordDetection(args);


        if(args[0].equalsIgnoreCase(Main.prefix + "ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }

        if (args[0].equalsIgnoreCase(Main.prefix + "prefix")){
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Ayo, my prefix is `!`").queue();
        }
        if (args[0].equalsIgnoreCase(Main.prefix + "commands")){
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Commands:\n" +
                    " `prefix` - displays current prefix \n" +
                    " `commands` - displays all commands \n" +
                            " `config` - settings").queue();
        }
        if (args[0].equalsIgnoreCase(Main.prefix + "config")){
            SlashCommands.data.setName("configuration");
            SlashCommands.data.setDescription("configuration settings");
            if (args.length == 2) {
                if (args[1].equalsIgnoreCase("prefix")){
                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage("Do you want to change the prefix? \n To change a prefix use `config prefix set <prefix>`").queue();
                    event.getChannel().sendMessage(":warning: **DISCLAIMER**: This command doesn't work yet! :warning: ").queue();
                    event.getChannel().sendMessage("Probably because Chili is lazy.").queue();

                }
            }
            if (args.length == 4) {
                if (args[1].equalsIgnoreCase("prefix")){
                    if (args[3].equalsIgnoreCase("set")){
                        // Develop a setter
                    }
                }
            }else if (args.length == 1){
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Configuration commands: \n`config prefix` - prefix changing").queue();
            }

            if(args[1].equalsIgnoreCase(Main.prefix + "studylinks")){
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("").queue();
            }
        }

        if(args[0].equalsIgnoreCase(Main.prefix + "subjects"))  {
           if (args.length == 1) {
               if (subjects.size() > 0) {
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
                   for (int i = 0; i < subjects.size(); i++) {
                       event.getChannel().sendTyping().queue();
                       event.getChannel().sendMessage(subjects.get(i)).queue();
                   }
               }
           }
        }
    }
}
