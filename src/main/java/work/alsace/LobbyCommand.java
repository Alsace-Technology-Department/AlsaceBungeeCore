package work.alsace;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command {

    private final AlsaceBungeeCore plugin;

    public LobbyCommand(String name, AlsaceBungeeCore plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("只有玩家可以使用这个命令。");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        ServerInfo lobbyServer = plugin.getProxy().getServerInfo("lobby");
        if (lobbyServer != null) {
            player.connect(lobbyServer);
        } else {
            player.sendMessage("找不到大厅服务器。");
        }
    }
}

