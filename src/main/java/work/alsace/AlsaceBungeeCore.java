package work.alsace;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class AlsaceBungeeCore extends Plugin {

    public Set<String> enabledServers = new HashSet<>();
    public Map<String, Map<String, Object>> aliasServers = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        this.getProxy().getPluginManager().registerListener(this, new PlayerListener(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfig() {
        try {
            // 获取配置文件提供器
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(new File(this.getDataFolder(), "config.yml"));

            // 从配置文件读取启用提示消息的服务器列表
            enabledServers.addAll(configuration.getStringList("enabledServers"));

            // 从配置文件读取服务器别名的配置
            if (configuration.contains("aliasservers")) {
                Configuration aliasConfig = configuration.getSection("aliasservers");
                for (String alias : aliasConfig.getKeys()) {
                    Map<String, Object> servers = new HashMap<>();
                    Configuration serversConfig = aliasConfig.getSection(alias);
                    for (String server : serversConfig.getKeys()) {
                        servers.put(server, serversConfig.getString(server));
                    }
                    aliasServers.put(alias, servers);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
