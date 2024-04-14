package fr.formiko.minecraftssh;

import fr.formiko.utils.FLUFiles;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import org.bukkit.command.CommandSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SSHUtils {
    private SSHUtils() {}
    public static void runAsynchronouslyAndDisplayResult(BooleanSupplier booleanSupplier, CommandSender commandSender) {
        new Thread(() -> {
            runAndDisplayResult(booleanSupplier, commandSender);
        }).start();
    }
    public static void runAndDisplayResult(BooleanSupplier booleanSupplier, CommandSender commandSender) {
        long startTime = System.currentTimeMillis();
        try {
            if (booleanSupplier.getAsBoolean()) {
                commandSender
                        .sendMessage(Component.text("Success in " + (System.currentTimeMillis() - startTime) + "ms", NamedTextColor.GREEN));
            } else {
                commandSender
                        .sendMessage(Component.text("Failure in " + (System.currentTimeMillis() - startTime) + "ms", NamedTextColor.RED));
            }
        } catch (Exception e) {
            commandSender.sendMessage(Component.text("Error in " + (System.currentTimeMillis() - startTime) + "ms", NamedTextColor.RED));
            e.printStackTrace();
        }
    }

    public static void runAsynchronouslyAndDisplayResult(Supplier<String> supplier, CommandSender commandSender) {
        new Thread(() -> {
            runAndDisplayResult(supplier, commandSender);
        }).start();
    }
    public static void runAndDisplayResult(Supplier<String> supplier, CommandSender commandSender) {
        runAndDisplayResult(() -> {
            String message;
            try {
                message = supplier.get();
            } catch (Exception e) {
                commandSender.sendMessage(Component.text("Error", NamedTextColor.RED));
                e.printStackTrace();
                return false;
            }
            if (message == null) {
                return false;
            } else {
                commandSender.sendMessage(Component.text(message));
                return true;
            }
        }, commandSender);
    }

    public static String byteToHumainReadableLenght(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), "KMGTPE".charAt(exp - 1));
    }

    public static List<String> getDirectoriesAndFiles(String path) {
        if (path == null || path.isEmpty() || !path.contains("/")) {
            path = ".";
        } else {
            path = path.substring(0, path.lastIndexOf("/"));
        }
        return FLUFiles.listFiles(path);
    }
}
