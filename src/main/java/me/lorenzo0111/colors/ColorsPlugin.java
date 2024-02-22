package me.lorenzo0111.colors;

import com.meteoritepvp.api.MeteoritePlugin;
import me.lorenzo0111.colors.commands.ColorCommand;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ColorsPlugin extends MeteoritePlugin {
    private static final List<String> COLORS = Arrays.stream(Material.values())
            .filter(material -> material.name().endsWith("_WOOL"))
            .map(material -> material.name().replace("_WOOL", ""))
            .collect(Collectors.toList());
    private static ColorsPlugin instance;
    private final List<String> colors = new ArrayList<>();

    @Override
    protected void onInit() {
        instance = this;
        super.onInit();

        registerPlaceholderParameter("colors", (sender -> COLORS));
        registerCommandClass(ColorCommand.class);

    }

    public List<String> getAddedColors() {
        return this.colors;
    }

    public static List<String> getColors() {
        return COLORS;
    }

    public static ColorsPlugin getInstance() {
        return instance;
    }
}
