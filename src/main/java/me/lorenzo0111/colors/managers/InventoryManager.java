package me.lorenzo0111.colors.managers;

import com.meteoritepvp.api.inventory.MeteoriteInventory;
import com.meteoritepvp.api.inventory.presets.BasicInventory;
import me.lorenzo0111.colors.ColorsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {
    private final ColorsPlugin plugin;
    private final MeteoriteInventory inventory;

    public InventoryManager(ColorsPlugin plugin) {
        this.plugin = plugin;
        this.inventory = new MeteoriteInventory(plugin, "Colors", 9, 4, false);
        this.rebuild();
    }

    public void rebuild() {
        BasicInventory page = new BasicInventory();

        for (int i = 0; i < plugin.getAddedColors().size(); i++) {
            page.setItem(i, new ItemStack(
                    Material.getMaterial(plugin.getAddedColors().get(i) + "_WOOL")
            ));
        }
        page.update();

        inventory.applyPage(page);
    }

    public void open(Player player) {
        inventory.show(player);
    }
}
