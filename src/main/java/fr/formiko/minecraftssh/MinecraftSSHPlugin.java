package fr.formiko.minecraftssh;

import fr.formiko.utils.FLUFiles;
import java.util.List;
import java.util.function.Predicate;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import co.aikar.commands.BukkitCommandCompletionContext;
import co.aikar.commands.PaperCommandManager;

public class MinecraftSSHPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new Metrics(this, 21583);

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new SSHCommand());
        manager.getCommandCompletions().registerAsyncCompletion("directories", c -> getDirectoriesAndFiles(c, s -> s.endsWith("/"), 1));
        manager.getCommandCompletions().registerAsyncCompletion("directoriesOrFiles", c -> getDirectoriesAndFiles(c, s -> true, 1));
        manager.getCommandCompletions().registerAsyncCompletion("files", c -> getDirectoriesAndFiles(c, s -> !s.endsWith("/"), 1));
        manager.getCommandCompletions().registerAsyncCompletion("directories2", c -> getDirectoriesAndFiles(c, s -> s.endsWith("/"), 2));
        manager.getCommandCompletions().registerAsyncCompletion("directoriesOrFiles2", c -> getDirectoriesAndFiles(c, s -> true, 2));
        manager.getCommandCompletions().registerAsyncCompletion("files2", c -> getDirectoriesAndFiles(c, s -> !s.endsWith("/"), 2));
    }

    /**
     * Get directories and files from the context
     * 
     * @param c           the context
     * @param extraFilter extra filter to apply to the files
     * @param i           the index of the argument to get context value from. Not ideal but it works.
     * @return
     */
    private List<String> getDirectoriesAndFiles(BukkitCommandCompletionContext c, Predicate<? super String> extraFilter, int i) {
        String path = c.getContextValue(String.class, i);
        System.out.println("contextValue: " + path);
        if (path.contains(" ")) {
            path = path.substring(0, path.lastIndexOf(" ") + 1);
        }
        final String initialPath = path;
        if (path == null || path.isEmpty() || !path.contains("/")) {
            path = ".";
        } else {
            path = path.substring(0, path.lastIndexOf("/") + 1);
        }
        final String finalPath = path.equals(".") ? "" : path;
        System.out.println("path: " + path);
        List<String> files = FLUFiles.listFiles(path);
        // return a list of absolute paths by adding the path to the file name
        return files.stream().map(s -> finalPath + s).filter(s -> s.startsWith(initialPath)).filter(extraFilter).limit(10).toList();
    }
}
