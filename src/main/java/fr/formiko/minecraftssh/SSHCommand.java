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
    @Subcommand("lsr")
    @CommandAlias("lsr")
    @Description("List files in a directory recursively")
    @CommandCompletion("@directories @empty")
    public void lsr(CommandSender commandSender, String directory) {
        SSHUtils.runAsynchronouslyAndDisplayResult(
                () -> FLUFiles.listFilesRecursively(directory).stream().sorted().reduce("", (a, b) -> a + b + "\n"), commandSender);
    }

    @Subcommand("cp")
    @CommandAlias("cp")
    @Description("Copy a file or a directory recursively")
    @CommandCompletion("@directoriesOrFiles @directories2 @empty")
    public void cp(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.copy(source, destination), commandSender);
    }

    @Subcommand("mv")
    @CommandAlias("mv")
    @Description("Move a file or a directory")
    @CommandCompletion("@directoriesOrFiles @directories2 @empty")
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
    @CommandCompletion("@directoriesOrFiles @empty")
    public void cat(CommandSender commandSender, String file) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.readFile(file), commandSender);
    }

    @Subcommand("wget")
    @CommandAlias("wget")
    @Description("Download a file")
    @CommandCompletion("@empty @directories2 @empty")
    public void wget(CommandSender commandSender, String url, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.download(url, destination), commandSender);
    }

    @Subcommand("zip")
    @CommandAlias("zip")
    @Description("Zip a file or a directory")
    @CommandCompletion("@directoriesOrFiles @empty")
    public void zip(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.zip(source, destination), commandSender);
    }

    @Subcommand("unzip")
    @CommandAlias("unzip")
    @Description("Unzip a file")
    @CommandCompletion("@directoriesOrFiles @empty")
    public void unzip(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.unzip(source, destination), commandSender);
    }

    @Subcommand("size")
    @CommandAlias("size")
    @Description("Get the size of a file or a directory")
    @CommandCompletion("@directoriesOrFiles @boolean @empty")
    public void size(CommandSender commandSender, String target) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> SSHUtils.byteToHumainReadableLenght(FLUFiles.getSize(target)), commandSender);
    }

    @Subcommand("sizeInBytes")
    @CommandAlias("sizeInBytes")
    @Description("Get the size of a file or a directory")
    @CommandCompletion("@directoriesOrFiles @boolean @empty")
    public void sizeInBytes(CommandSender commandSender, String target) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.getSize(target) + " bytes", commandSender);
    }
}
