package work.alsace;

import net.md_5.bungee.api.plugin.Plugin;

public final class AlsaceBungeeCore extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getProxy().getPluginManager().registerListener(this, new PlayerListener(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
