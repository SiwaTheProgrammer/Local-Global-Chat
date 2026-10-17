package ru.swa.localGlobalChat;

import org.bukkit.plugin.java.JavaPlugin;
import ru.swa.localGlobalChat.listeners.ChatListener;

public final class LocalGlobalChat extends JavaPlugin {

    @Override
    public void onEnable() {
    getServer().getPluginManager().registerEvents(new ChatListener(), this);
    getLogger().info("Local-Global Chat enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Local-Global Chat disabled!");
    }
}
