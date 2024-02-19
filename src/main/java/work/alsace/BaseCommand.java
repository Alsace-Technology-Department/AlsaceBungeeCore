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
            if (args[0].equals("reload")) {
                plugin.loadConfig();
                sender.sendMessage(ChatColor.GRAY + "配置已重载。");
            }
        }
    }
}
