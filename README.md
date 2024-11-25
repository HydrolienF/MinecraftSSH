[download]: https://img.shields.io/github/downloads/HydrolienF/MinecraftSSH/total
[downloadLink]: https://hangar.papermc.io/Hydrolien/MinecraftSSH
[discord-shield]: https://img.shields.io/discord/728592434577014825?label=discord
[discord-invite]: https://discord.gg/RPNbtRSFqG

[ ![download][] ][downloadLink]
[ ![discord-shield][] ][discord-invite]

# MinecraftSSH
Minecraft plugin to have basic ssh commands ingame.

I created this plugin to be able to copy a folder on my Minecraft server without having ssh access to the server. The normal way to copy a directory with ftp or sftp access is to download it and send it back. It's a terrible way to do it, and take ages to do with big folders.

The aims of this plugin is to provide as much file edition command as possible. If some commands are missing feel free to open a request issue or to send a pull request (both is better).

All commands have tab completion with existing files and directories when it's uefull.

Support **Paper** forks including **Folia** for version 1.18 to last. (See version compatibility in releases)
Older version than 1.18 won't be supported.
Since version 1.1.1, this plugin require Java 21. If you are on an old version before 1.20.5, you might need to update to Java 21 or use the latest version.

## Use

Download last release .jar file and place it in your server directory `plugins/`.

Write `/ssh ...` ingame to see all available commands. Each command have a shortcut.

For example if you want to make a save of your world do `/cp world worldSave` or `/zip world world.zip`

## Non-Goals

There will be no auto backup system as there is already great plugins for that as [Backuper](https://github.com/DVDishka/Backuper).

## Statistics
[![bStats Graph Data](https://bstats.org/signatures/bukkit/MinecraftSSH.svg)](https://bstats.org/plugin/bukkit/MinecraftSSH/21583)

## Build

Build with `./gradlew assemble`. Plugin file will be in `build/libs/`.
