package fr.formiko.minecraftssh;

import fr.formiko.utils.FLUFiles;
import org.bukkit.command.CommandSender;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;

@CommandAlias("ssh")
@CommandPermission("minecraftssh")
public class SSHCommand extends BaseCommand {

    @Subcommand("ls")
    @CommandAlias("ls")
    @Description("List files in a directory")
    @CommandCompletion("@directories @empty")
    public void ls(CommandSender commandSender, String directory) {
        commandSender.sendMessage(
                "\n\n" + directory + ": \n" + FLUFiles.listFiles(directory).stream().sorted().reduce("", (a, b) -> a + b + "\n"));
    }

    @Subcommand("cp")
    @CommandAlias("cp")
    @Description("Copy a file or a directory recursively")
    @CommandCompletion("@directoriesOrFiles @directories @empty")
    public void cp(CommandSender commandSender, String source, String destination) {
        if (FLUFiles.copy(source, destination)) {
            commandSender.sendMessage("File copied");
        } else {
            commandSender.sendMessage("File failed to be copied");
        }
    }

    @Subcommand("mv")
    @CommandAlias("mv")
    @Description("Move a file or a directory")
    @CommandCompletion("@directoriesOrFiles @directories @empty")
    public void mv(CommandSender commandSender, String source, String destination) {
        if (FLUFiles.move(source, destination)) {
            commandSender.sendMessage("File moved");
        } else {
            commandSender.sendMessage("File failed to be moved");
        }
    }

    @Subcommand("rm")
    @CommandAlias("rm")
    @Description("Remove a file or a directory recursively")
    @CommandCompletion("@directoriesOrFiles @empty")
    public void rm(CommandSender commandSender, String target) {
        if (FLUFiles.delete(target)) {
            commandSender.sendMessage("File deleted");
        } else {
            commandSender.sendMessage("File failed to be deleted");
        }
    }

    @Subcommand("mkdir")
    @CommandAlias("mkdir")
    @Description("Create a directory")
    @CommandCompletion("@directories @empty")
    public void mkdir(CommandSender commandSender, String directory) {
        if (FLUFiles.createDirectory(directory)) {
            commandSender.sendMessage("Directory created");
        } else {
            commandSender.sendMessage("Directory failed to be created");
        }
    }

    @Subcommand("cat")
    @CommandAlias("cat")
    @Description("Display the content of a file")
    @CommandCompletion("@files @empty")
    public void cat(CommandSender commandSender, String file) { commandSender.sendMessage(FLUFiles.readFile(file)); }

}
