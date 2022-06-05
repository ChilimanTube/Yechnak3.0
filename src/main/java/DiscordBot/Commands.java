package DiscordBot;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands extends ListenerAdapter {
    ArrayList<String> studylinks = new ArrayList<>();
    ArrayList<String> subjects = new ArrayList<>();
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");


            if (Moderation.badWordDetection(args)) {
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage("Please, watch your language!").queue();
            }

        //PING
        if(args[0].equalsIgnoreCase(Main.prefix + "ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }

        //PREFIX DISPLAY
        if (args[0].equalsIgnoreCase(Main.prefix + "prefix")){
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Ayo, my prefix is `!`").queue();
        }

        // COMMAND LIST
        if (args[0].equalsIgnoreCase(Main.prefix + "commands")){
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Commands:\n" +
                    " `prefix` - displays current prefix \n" +
                    " `commands` - displays all commands \n" +
                    " `config` - settings \n" +
                            " ping - command for testing latency \n" +
                            " poll - command for creating a yes or no poll").queue();
        }
        // CONFIGURATION SETTINGS
        if (args[0].equalsIgnoreCase(Main.prefix + "config")){
            SlashCommands.data.setName("configuration");
            SlashCommands.data.setDescription("configuration settings");

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
                    if (args[3].equalsIgnoreCase("set")){
                        event.getChannel().sendTyping().queue();
                        Main.prefix = args[4];
                        event.getChannel().sendMessage("New global prefix was set.").queue();
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
                       for (int i = 0; i < subjects.size(); i++) {
                           event.getChannel().sendMessage(subjects.get(i)).queue();
                       }
                   }
               }
           }
        }

        // second try poll
        if (args[0].equalsIgnoreCase(Main.prefix + "poll")) {
            String ping = "";
            String pollMessage = "";
            // Ping @everyone
            if(args[1].equalsIgnoreCase("pe")){
                ping = "@everyone ";
                //YesOrNo Poll
                if(args[2].equalsIgnoreCase("yn")){
                    StringBuffer buffer = new StringBuffer();
                    for(int i = 3; i < args.length; i++) {
                        buffer.append(args[i] + " ");
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
                    StringBuffer buffer = new StringBuffer();
                    for(int i = 3; i < args.length; i++) {
                        buffer.append(args[i] + " ");
                    }
                    event.getChannel().sendTyping().queue();
                    pollMessage = buffer.toString();
                    event.getChannel().sendMessage(ping + pollMessage).queue(message -> {
                        message.addReaction("✅").queue();
                        message.addReaction("❌").queue();
                    });
                }
            }if(args[1].equalsIgnoreCase("yn")){
                ping = "";
                //YesOrNo Poll
                    StringBuffer buffer = new StringBuffer();
                    for(int i = 2; i < args.length; i++) {
                        buffer.append(args[i] + " ");
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
        if(args[0].equalsIgnoreCase(Main.prefix + "timeout")){
            if(args.length == 2){
                String regex = "\\d+";
                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                event.getChannel().sendTyping().queue();
                String id = args[1];
                Matcher matcher = pattern.matcher(args[1]);
                id = id.replace("<@", "");
                id = id.replace(">", "");
                long userId = Long.parseLong(id);
                RestAction<Member> restaction = event.getGuild().retrieveMemberById(userId);
                restaction.queue(member -> {
                    member.timeoutFor(1, TimeUnit.DAYS);
                });
                event.getChannel().sendMessage("Timeout for <@" + userId + "> has been set.").queue();
            } else {
                event.getChannel().sendMessage("Incorrect command. Use `" + Main.prefix + "timeout @<member>`").queue();
            }
        }
        //REMOVE TIMEOUT
        if(args[0].equalsIgnoreCase(Main.prefix + "remove") && args[1].equalsIgnoreCase("timeout")){
            if(args.length == 3){
                Member member = event.getGuild().getMemberById(args[1].replace("<@", "").replace(">",""));
                if(member.isTimedOut()){
                    member.removeTimeout();
                    event.getChannel().sendMessage("Timeout for user @" + member + " was removed.").queue();
                }else{
                    event.getChannel().sendMessage("User @" + member + "  is not timeouted.").queue();
                }
            }
        }
    }
}
