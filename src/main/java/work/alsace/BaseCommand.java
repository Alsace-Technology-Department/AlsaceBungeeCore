package work.alsace;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;

public class BaseCommand extends Command {

    private final AlsaceBungeeCore plugin;

    public BaseCommand(String name, AlsaceBungeeCore plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // 确保命令有参数
        if (args.length > 0) {
            switch (args[0]) {
                case "reload":
                    plugin.loadConfig();
                    sender.sendMessage(ChatColor.GRAY + "配置已重载。");
                case "info":
                    sender.sendMessage(ChatColor.GRAY + "插件名称: §fAlsaceCore\n§7插件版本: §f" + plugin.getDescription().getVersion() + "\n§7插件作者: §f" + Arrays.toString(plugin.getDescription().getAuthor().toCharArray()));
                default:
                    sender.sendMessage(ChatColor.RED + "未知命令");
            }
        }
    }
}
