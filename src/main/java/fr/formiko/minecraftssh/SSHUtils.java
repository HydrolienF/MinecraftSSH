package fr.formiko.minecraftssh;

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
        if (booleanSupplier.getAsBoolean()) {
            commandSender
                    .sendMessage(Component.text("Success in " + (System.currentTimeMillis() - startTime) + "ms", NamedTextColor.GREEN));
        } else {
            commandSender.sendMessage(Component.text("Failure in " + (System.currentTimeMillis() - startTime) + "ms", NamedTextColor.RED));
        }
    }

    public static void runAsynchronouslyAndDisplayResult(Supplier<String> supplier, CommandSender commandSender) {
        new Thread(() -> {
            runAndDisplayResult(supplier, commandSender);
        }).start();
    }
    public static void runAndDisplayResult(Supplier<String> supplier, CommandSender commandSender) {
        runAndDisplayResult(() -> {
            String message = supplier.get();
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
}
