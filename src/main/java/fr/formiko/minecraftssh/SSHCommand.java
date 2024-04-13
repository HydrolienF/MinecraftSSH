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
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.listFiles(directory).stream().sorted().reduce("", (a, b) -> a + b + "\n"),
                commandSender);
    }

    @Subcommand("cp")
    @CommandAlias("cp")
    @Description("Copy a file or a directory recursively")
    @CommandCompletion("@directoriesOrFiles @directories @empty")
    public void cp(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.copy(source, destination), commandSender);
    }

    @Subcommand("mv")
    @CommandAlias("mv")
    @Description("Move a file or a directory")
    @CommandCompletion("@directoriesOrFiles @directories @empty")
    public void mv(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.move(source, destination), commandSender);
    }

    @Subcommand("rm")
    @CommandAlias("rm")
    @Description("Remove a file or a directory recursively")
    @CommandCompletion("@directoriesOrFiles @empty")
    public void rm(CommandSender commandSender, String target) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.delete(target), commandSender);
    }

    @Subcommand("mkdir")
    @CommandAlias("mkdir")
    @Description("Create a directory")
    @CommandCompletion("@directories @empty")
    public void mkdir(CommandSender commandSender, String directory) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.createDirectory(directory), commandSender);
    }

    @Subcommand("cat")
    @CommandAlias("cat")
    @Description("Display the content of a file")
    @CommandCompletion("@files @empty")
    public void cat(CommandSender commandSender, String file) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.readFile(file), commandSender);
    }

    @Subcommand("wget")
    @CommandAlias("wget")
    @Description("Download a file")
    @CommandCompletion("@empty")
    public void wget(CommandSender commandSender, String url, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.download(url, destination), commandSender);
    }
}
