package DiscordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import javax.security.auth.login.LoginException;

public class Main {
    public static String prefix = "!";
    public static void main(String[] args)throws LoginException{

        JDA bot = JDABuilder.createDefault("OTcxMTEwNzQyMDcxODU3MTUy.GDon0y.-lmvjFFWQcdiDo7RGiKWn3mLLzUSWgfDg7Q0So")
                    .setActivity(Activity.playing("with Chili's brain"))
                    .build();

        bot.addEventListener(new Commands());
    }
}
