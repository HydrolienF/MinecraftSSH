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
                commandSender, "ls " + directory);
    }
    @Subcommand("ls")
    @CommandAlias("ls")
    @Description("List files in a directory")
    @CommandCompletion("@directories @empty")
    public void ls(CommandSender commandSender) { ls(commandSender, "."); }

    @Subcommand("lsr")
    @CommandAlias("lsr")
    @Description("List files in a directory recursively")
    @CommandCompletion("@directories @empty")
    public void lsr(CommandSender commandSender, String directory) {
        SSHUtils.runAsynchronouslyAndDisplayResult(
                () -> FLUFiles.listFilesRecursively(directory).stream().sorted().reduce("", (a, b) -> a + b + "\n"), commandSender,
                "lsr " + directory);
    }
    @Subcommand("lsr")
    @CommandAlias("lsr")
    @Description("List files in a directory recursively")
    @CommandCompletion("@directories @empty")
    public void lrs(CommandSender commandSender) { lsr(commandSender, "."); }

    @Subcommand("cp")
    @CommandAlias("cp")
    @Description("Copy a file or a directory recursively")
    @CommandCompletion("@directoriesOrFiles @directories2 @empty")
    public void cp(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.copy(source, destination), commandSender,
                "cp " + source + " " + destination);
    }

    @Subcommand("mv")
    @CommandAlias("mv")
    @Description("Move a file or a directory")
    @CommandCompletion("@directoriesOrFiles @directories2 @empty")
    public void mv(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.move(source, destination), commandSender,
                "mv " + source + " " + destination);
    }

    @Subcommand("rm")
    @CommandAlias("rm")
    @Description("Remove a file or a directory recursively")
    @CommandCompletion("@directoriesOrFiles @empty")
    public void rm(CommandSender commandSender, String target) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.delete(target), commandSender, "rm " + target);
    }

    @Subcommand("mkdir")
    @CommandAlias("mkdir")
    @Description("Create a directory")
    @CommandCompletion("@directories @empty")
    public void mkdir(CommandSender commandSender, String directory) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.createDirectory(directory), commandSender, "mkdir " + directory);
    }

    @Subcommand("cat")
    @CommandAlias("cat")
    @Description("Display the content of a file")
    @CommandCompletion("@directoriesOrFiles @empty")
    public void cat(CommandSender commandSender, String file) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.readFile(file), commandSender, "cat " + file);
    }

    @Subcommand("wget")
    @CommandAlias("wget")
    @Description("Download a file")
    @CommandCompletion("@empty @directories2 @empty")
    public void wget(CommandSender commandSender, String url, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.download(url, destination), commandSender,
                "wget " + url + " " + destination);
    }

    @Subcommand("zip")
    @CommandAlias("zip")
    @Description("Zip a file or a directory")
    @CommandCompletion("@directoriesOrFiles @directoriesOrFiles @empty")
    public void zip(CommandSender commandSender, String s1, String s2) {
        String source;
        String destination;
        if(s1.endsWith(".zip")) {
            source = s2;
            destination = s1;
        } else {
            source = s1;
            destination = s2;
        }
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.zip(source, destination), commandSender,
                "zip " + source + " " + destination);
    }
    @Subcommand("zip")
    @CommandAlias("zip")
    @Description("Zip a file or a directory")
    @CommandCompletion("@directoriesOrFiles @directoriesOrFiles @empty")
    public void zip(CommandSender commandSender, String s1) {
        zip(commandSender, s1, s1 + ".zip");
    }

    @Subcommand("unzip")
    @CommandAlias("unzip")
    @Description("Unzip a file")
    @CommandCompletion("@directoriesOrFiles @empty")
    public void unzip(CommandSender commandSender, String source, String destination) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.unzip(source, destination), commandSender,
                "unzip " + source + " " + destination);
    }

    @Subcommand("size")
    @CommandAlias("size")
    @Description("Get the size of a file or a directory")
    @CommandCompletion("@directoriesOrFiles @boolean @empty")
    public void size(CommandSender commandSender, String target) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> SSHUtils.byteToHumainReadableLenght(FLUFiles.getSize(target)), commandSender,
                "size " + target);
    }

    @Subcommand("sizeInBytes")
    @CommandAlias("sizeInBytes")
    @Description("Get the size of a file or a directory")
    @CommandCompletion("@directoriesOrFiles @boolean @empty")
    public void sizeInBytes(CommandSender commandSender, String target) {
        SSHUtils.runAsynchronouslyAndDisplayResult(() -> FLUFiles.getSize(target) + " bytes", commandSender, "sizeInBytes " + target);
    }
}
