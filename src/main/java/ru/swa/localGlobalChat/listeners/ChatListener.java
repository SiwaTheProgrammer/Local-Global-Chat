package ru.swa.localGlobalChat.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent event){
        Player player = event.getPlayer();

        String msg = PlainTextComponentSerializer.plainText().serialize(event.message());

        boolean isGlobal = msg.startsWith("!");
        if (isGlobal){
            msg = msg.substring(1);
        }

        event.setCancelled(true);

        if (isGlobal) {
            sendGlobal(player, msg);
        } else {
            sendLocal(player, msg);
        }
    }

    private void sendLocal(Player sender, String msg) {
        //§7
        boolean atLeastOne = false;
        for (Player p: Bukkit.getOnlinePlayers()) {
            if (!p.getWorld().equals(sender.getWorld()))
                continue;
            if (p.getLocation().distance(sender.getLocation()) > 100)
                continue;
            p.sendMessage("§7" + sender.getName() + ": " + msg);
            if (!sender.equals(p))
                atLeastOne = true;
        }
        if (!atLeastOne)
            sender.sendMessage("§4No one can hear you!");
    }
    private void sendGlobal(Player sender, String msg) {
        for (Player p: Bukkit.getOnlinePlayers()) {
            p.sendMessage( sender.getName() + ": " + msg);
        }
    }
}
