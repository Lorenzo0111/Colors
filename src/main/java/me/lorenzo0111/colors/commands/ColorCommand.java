package me.lorenzo0111.colors.commands;

import com.meteoritepvp.api.MeteoritePlugin;
import com.meteoritepvp.api.command.Command;
import com.meteoritepvp.api.command.CommandClass;
import com.meteoritepvp.api.command.DefaultCommand;
import com.meteoritepvp.api.inventory.MeteoriteInventory;
import com.meteoritepvp.api.inventory.presets.BasicInventory;
import com.meteoritepvp.api.utils.CC;
import me.lorenzo0111.colors.ColorsPlugin;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@DefaultCommand
@SuppressWarnings("unused")
public class ColorCommand implements CommandClass {

    @Command(description = "Add a color", args = {"add"}, params = {"@colors"})
    public void addCommand(CommandSender sender, String[] params) {
        if (params.length == 0) {
            sender.sendMessage(CC.format("&cUsage: /color add <color>"));
            return;
        }

        if (!ColorsPlugin.getColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color does not exist."));
            return;
        }

        if (ColorsPlugin.getInstance().getAddedColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color is already added."));
            return;
        }

        ColorsPlugin.getInstance().getAddedColors().add(params[0].toUpperCase());
    }

    @Command(description = "Remove a color", args = {"remove"}, params = {"@colors"})
    public void removeCommand(CommandSender sender, String[] params) {
        if (params.length == 0) {
            sender.sendMessage(CC.format("&cUsage: /color remove <color>"));
            return;
        }

        if (!ColorsPlugin.getColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color does not exist."));
            return;
        }

        if (!ColorsPlugin.getInstance().getAddedColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color is not added."));
            return;
        }

        ColorsPlugin.getInstance().getAddedColors().remove(params[0].toUpperCase());
    }

    @Command(description = "Open the colors gui")
    public void mainCommand(Player player, MeteoritePlugin plugin) {
        if (player == null) {
            return;
        }

        MeteoriteInventory inventory = new MeteoriteInventory(plugin, "Colors", 9, 4, false);
        BasicInventory page = new BasicInventory();
        for (int i = 0; i < ColorsPlugin.getInstance().getAddedColors().size(); i++) {
            page.setItem(i, new ItemStack(
                    Material.getMaterial(ColorsPlugin.getInstance().getAddedColors().get(i) + "_WOOL")
            ));
        }
        page.update();
        inventory.applyPage(page);

        inventory.show(player);
    }
}


