package me.lorenzo0111.colors;

import com.meteoritepvp.api.MeteoritePlugin;
import me.lorenzo0111.colors.commands.ColorCommand;
import me.lorenzo0111.colors.managers.InventoryManager;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ColorsPlugin extends MeteoritePlugin {
    private static final List<String> ALL_COLORS = Arrays.stream(Material.values())
            .filter(material -> material.name().endsWith("_WOOL"))
            .map(material -> material.name().replace("_WOOL", ""))
            .collect(Collectors.toList());
    private static ColorsPlugin instance;
    private final List<String> colors = new ArrayList<>();
    private InventoryManager manager;

    @Override
    protected void onInit() {
        instance = this;
        super.onInit();

        this.manager = new InventoryManager(this);
        registerCommandClass(ColorCommand.class);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        registerPlaceholderParameter("colors", (sender -> ALL_COLORS));
    }

    @Override
    protected void onRegisterMainCommand(String description) { }

    @Override
    protected void onRegisterCommands(String... aliases) {
        super.onRegisterCommands("colors");
    }

    public List<String> getAddedColors() {
        return this.colors;
    }

    public InventoryManager getManager() {
        return manager;
    }

    public static List<String> getColors() {
        return ALL_COLORS;
    }

    public static ColorsPlugin getInstance() {
        return instance;
    }
}
