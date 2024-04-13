package fr.formiko.minecraftssh;

import java.util.List;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import co.aikar.commands.PaperCommandManager;

public class MinecraftSSHPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        new Metrics(this, 21583);

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new SSHCommand());
        manager.getCommandCompletions().registerAsyncCompletion("directories", c -> List.of());
        manager.getCommandCompletions().registerAsyncCompletion("directoriesOrFiles", c -> List.of());
        manager.getCommandCompletions().registerAsyncCompletion("files", c -> List.of());
    }
}
