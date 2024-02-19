package work.alsace;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class AlsaceBungeeCore extends Plugin {

    public Set<String> enabledServers = new HashSet<>();
    public Map<String, Map<String, Object>> aliasServers = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        getProxy().getPluginManager().registerCommand(this, new LobbyCommand("lobby", this));
        getProxy().getPluginManager().registerCommand(this, new BaseCommand("alsacebungeecore", this));
        this.getProxy().getPluginManager().registerListener(this, new PlayerListener(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        File configFile = new File(this.getDataFolder(), "config.yml");

        // 确保插件数据文件夹存在
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        try {
            // 检查配置文件是否存在，如果不存在，则创建默认配置
            if (!configFile.exists()) {
                try {
                    configFile.createNewFile();
                    Configuration defaultConfig = new Configuration();
                    // 设置默认配置
                    defaultConfig.set("enabledServers", new ArrayList<>(Arrays.asList("lobby", "server1")));
                    Map<String, Object> aliasExample = new HashMap<>();
                    aliasExample.put("name", "ExampleServer");
                    aliasExample.put("servers", new ArrayList<>(Arrays.asList("server1", "server2")));
                    defaultConfig.set("aliasservers.ExampleAlias", aliasExample);

                    // 保存默认配置
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(defaultConfig, configFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 加载配置
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);

            // 从配置文件读取启用提示消息的服务器列表
            enabledServers.addAll(configuration.getStringList("enabledServers"));

            // 从配置文件读取服务器别名的配置
            if (configuration.contains("aliasservers")) {
                Configuration aliasConfig = configuration.getSection("aliasservers");
                for (String alias : aliasConfig.getKeys()) {
                    Map<String, Object> aliasInfo = new HashMap<>();
                    aliasInfo.put("name", aliasConfig.getString(alias + ".name"));
                    aliasInfo.put("servers", aliasConfig.getStringList(alias + ".servers"));
                    aliasServers.put(alias, aliasInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
