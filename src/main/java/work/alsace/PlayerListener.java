package work.alsace;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.event.TabCompleteResponseEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PlayerListener implements Listener {

    private final Set<String> enabledServers;

    private final Set<String> hasConnected = new HashSet<>();
    private final SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");

    private TextComponent getClickableName(String name) {
        TextComponent result = new TextComponent(name);
        result.setColor(ChatColor.YELLOW);
        result.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new Text(name + "\n§7时间：§f" + this.date.format(new Date(System.currentTimeMillis())))));
        return result;
    }

    private TextComponent getComponent(TextComponent text, String msg) {
        text.addExtra(new TextComponent(msg));
        return text;
    }

    public PlayerListener(AlsaceBungeeCore plugin) {
        this.enabledServers = plugin.enabledServers;
    }

    @EventHandler
    public void onServerTeleport(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();
        ServerInfo from = event.getFrom();
        if (from == null) {
            String serverName = player.getServer().getInfo().getName();
            if (enabledServers.contains(serverName)) {
                this.sendMessage(this.getComponent(this.getClickableName(player.getName()), "加入了" + serverName));
                this.hasConnected.add(player.getName());
            }
        } else {
            String toServer = player.getServer().getInfo().getName();
            if (enabledServers.contains(toServer)) {
                this.sendMessage(this.getComponent(this.getClickableName(player.getName()), "已跨服至" + toServer));
            }
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (this.hasConnected.remove(player.getName())) {
            this.sendMessage(this.getComponent(this.getClickableName(player.getName()), "离开了游戏"));
        }

    }

    @EventHandler
    public void onTabComplete(TabCompleteResponseEvent event) {
        event.getSuggestions().removeIf((s) -> s.startsWith("~BTLP"));
    }

    private void sendMessage(TextComponent component) {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (enabledServers.contains(player.getServer().getInfo().getName())) {
                player.sendMessage(component);
            }
        }
    }

}
