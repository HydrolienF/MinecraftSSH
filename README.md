# MinecraftSSH
Minecraft plugin to have ssh command ingame.

I created this plugin to be able to copy a folder on my Minecraft server without having ssh access to the server. The normal way to copy a directory with ftp or sftp access is to download it and send it back. It's a terrible way to do it, and take ages to do with big folders.

The aims of this plugin is to provide as much file edition command as possible. If some commands are missing feel free to open a request issue or to send a pull request (both is better).

All commands have tab completion with existing files and directories when it's uefull.

Support **Paper** forks including **Folia**.

## Use

Download last release .jar file and place it in your server directory `plugins/`.
If your Java version is under 21, update it to 21 or newer.

Write `/ssh ...` ingame

## Build

Build with `./gradlew assemble`. Jar will be in `build/libs/`.