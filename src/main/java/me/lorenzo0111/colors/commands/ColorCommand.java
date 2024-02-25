package me.lorenzo0111.colors.commands;

import com.meteoritepvp.api.MeteoritePlugin;
import com.meteoritepvp.api.command.Command;
import com.meteoritepvp.api.command.CommandClass;
import com.meteoritepvp.api.command.DefaultCommand;
import com.meteoritepvp.api.utils.CC;
import me.lorenzo0111.colors.ColorsPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@DefaultCommand
@SuppressWarnings("unused")
public class ColorCommand implements CommandClass {

    @Command(description = "Add a color", name = "add", params = {"@colors"})
    public void addCommand(CommandSender sender, String[] params, MeteoritePlugin plugin) {
        if (!ColorsPlugin.getColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color does not exist."));
            return;
        }

        if (ColorsPlugin.getInstance().getAddedColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color is already added."));
            return;
        }

        ColorsPlugin.getInstance().getAddedColors().add(params[0].toUpperCase());
        ((ColorsPlugin) plugin).getManager().rebuild();
    }

    @Command(description = "Remove a color", name = "remove", params = {"@colors"})
    public void removeCommand(CommandSender sender, String[] params, MeteoritePlugin plugin) {
        if (!ColorsPlugin.getColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color does not exist."));
            return;
        }

        if (!ColorsPlugin.getInstance().getAddedColors().contains(params[0].toUpperCase())) {
            sender.sendMessage(CC.format("&cThis color is not added."));
            return;
        }

        ColorsPlugin.getInstance().getAddedColors().remove(params[0].toUpperCase());
        ((ColorsPlugin) plugin).getManager().rebuild();
    }

    @Command(description = "Open the colors gui")
    public void mainCommand(Player player, MeteoritePlugin plugin) {
        ((ColorsPlugin) plugin).getManager().open(player);
    }
}


