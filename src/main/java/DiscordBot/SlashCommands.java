package DiscordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class SlashCommands {

    SlashCommandInteraction slashCommandInteraction = new SlashCommandInteraction() {
        @NotNull
        @Override
        public MessageChannel getChannel() {
            return null;
        }

        @NotNull
        @Override
        public ModalCallbackAction replyModal(@NotNull Modal modal) {
            return null;
        }

        @NotNull
        @Override
        public ReplyCallbackAction deferReply() {
            return null;
        }

        @NotNull
        @Override
        public InteractionHook getHook() {
            return null;
        }

        @NotNull
        @Override
        public Command.Type getCommandType() {
            return null;
        }

        @NotNull
        @Override
        public String getName() {
            return null;
        }

        @Nullable
        @Override
        public String getSubcommandName() {
            return null;
        }

        @Nullable
        @Override
        public String getSubcommandGroup() {
            return null;
        }

        @Override
        public long getCommandIdLong() {
            return 0;
        }

        @Override
        public boolean isGuildCommand() {
            return false;
        }

        @NotNull
        @Override
        public List<OptionMapping> getOptions() {
            return null;
        }

        @Override
        public int getTypeRaw() {
            return 0;
        }

        @NotNull
        @Override
        public String getToken() {
            return null;
        }

        @Nullable
        @Override
        public Guild getGuild() {
            return null;
        }

        @NotNull
        @Override
        public User getUser() {
            return null;
        }

        @Nullable
        @Override
        public Member getMember() {
            return null;
        }

        @Override
        public boolean isAcknowledged() {
            return false;
        }

        @NotNull
        @Override
        public Locale getUserLocale() {
            return null;
        }

        @NotNull
        @Override
        public JDA getJDA() {
            return null;
        }

        @Override
        public long getIdLong() {
            return 0;
        }
    };
    static SlashCommandData data = new SlashCommandData() {
        @NotNull
        @Override
        public SlashCommandData setName(@NotNull String s) {
            return null;
        }

        @NotNull
        @Override
        public SlashCommandData setDefaultEnabled(boolean b) {
            return null;
        }

        @NotNull
        @Override
        public SlashCommandData setDescription(@NotNull String s) {
            return null;
        }

        @NotNull
        @Override
        public String getDescription() {
            return null;
        }

        @NotNull
        @Override
        public List<SubcommandData> getSubcommands() {
            return null;
        }

        @NotNull
        @Override
        public List<SubcommandGroupData> getSubcommandGroups() {
            return null;
        }

        @NotNull
        @Override
        public List<OptionData> getOptions() {
            return null;
        }

        @NotNull
        @Override
        public SlashCommandData addOptions(@NotNull OptionData... optionData) {
            return null;
        }

        @NotNull
        @Override
        public SlashCommandData addSubcommands(@NotNull SubcommandData... subcommandData) {
            return null;
        }

        @NotNull
        @Override
        public SlashCommandData addSubcommandGroups(@NotNull SubcommandGroupData... subcommandGroupData) {
            return null;
        }

        @NotNull
        @Override
        public String getName() {
            return null;
        }

        @Override
        public boolean isDefaultEnabled() {
            return false;
        }

        @NotNull
        @Override
        public Command.Type getType() {
            return null;
        }

        @NotNull
        @Override
        public DataObject toData() {
            return null;
        }
    };
}
