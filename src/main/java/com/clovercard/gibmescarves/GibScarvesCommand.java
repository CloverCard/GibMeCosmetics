package com.clovercard.gibmescarves;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import com.pixelmonmod.pixelmon.api.enums.ServerCosmetics;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class GibScarvesCommand {
    public GibScarvesCommand(CommandDispatcher dispatcher) {
        dispatcher.register(
                Commands.literal("gibmescarves")
                        .then(
                                Commands.literal("give")
                                        .then(Commands.argument("player", StringArgumentType.string())
                                                .then(
                                                        Commands.literal("umbreon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.UMBREON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("espeon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.ESPEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("glaceon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.GLACEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("jolteon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.JOLTEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("vaporeon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.VAPOREON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("leafeon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.LEAFEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("flareon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.FLAREON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("sylveon")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.SYLVEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("drowned")
                                                                .executes(cmd -> giveScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.DROWNED_ROBE))
                                                )
                                        )
                        )
                        .then(
                                Commands.literal("remove")
                                        .then(Commands.argument("player", StringArgumentType.string())
                                                .then(
                                                        Commands.literal("umbreon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.UMBREON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("espeon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.ESPEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("glaceon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.GLACEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("jolteon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.JOLTEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("vaporeon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.VAPOREON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("leafeon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.LEAFEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("flareon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.FLAREON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("sylveon")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.SYLVEON_SCARF))
                                                )
                                                .then(
                                                        Commands.literal("drowned")
                                                                .executes(cmd -> removeScarf(cmd.getSource(), StringArgumentType.getString(cmd, "player"), ServerCosmetics.DROWNED_ROBE))
                                                )
                                        )
                        )
                        .then(
                                Commands.literal("clear")
                                        .then(Commands.argument("player", StringArgumentType.string())
                                                .executes(cmd -> clearScarves(cmd.getSource(), StringArgumentType.getString(cmd, "player")))
                                        )
                        )
        );
    }
    private int giveScarf(CommandSource src, String name, ServerCosmetics cosmetic) {
        ServerPlayerEntity sender = null;
        if(src.getEntity() instanceof ServerPlayerEntity) {
            sender = (ServerPlayerEntity) src.getEntity();
        }
        ServerPlayerEntity player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(name);
        if(player == null) {
            if(sender != null) sender.sendMessage(new StringTextComponent("Could not find a player with that name!"), Util.NIL_UUID);
            return 1;
        }
        StorageProxy.getParty(player.getUUID()).grantServerCosmetics(cosmetic);
        if(sender != null) sender.sendMessage(new StringTextComponent("You gave " + player.getName().getString() + " a cosmetic!"), Util.NIL_UUID);
        player.sendMessage(new StringTextComponent("You have received a new cosmetic!"), Util.NIL_UUID);
        return 0;
    }
    private int removeScarf(CommandSource src, String name, ServerCosmetics cosmetic) {
        ServerPlayerEntity sender = null;
        if(src.getEntity() instanceof ServerPlayerEntity) {
            sender = (ServerPlayerEntity) src.getEntity();
        }
        ServerPlayerEntity player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(name);
        if(player == null) {
            if(sender != null) sender.sendMessage(new StringTextComponent("Could not find a player with that name!"), Util.NIL_UUID);
            return 1;
        }
        StorageProxy.getParty(player.getUUID()).revokeServerCosmetics(cosmetic);
        if(sender != null) sender.sendMessage(new StringTextComponent("You revoked " + player.getName().getString() + " of a cosmetic!"), Util.NIL_UUID);
        player.sendMessage(new StringTextComponent("You have lost a cosmetic!"), Util.NIL_UUID);
        return 0;
    }
    private int clearScarves(CommandSource src, String name) {
        ServerPlayerEntity sender = null;
        if(src.getEntity() instanceof ServerPlayerEntity) {
            sender = (ServerPlayerEntity) src.getEntity();
        }
        ServerPlayerEntity player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(name);
        if(player == null) {
            if(sender != null) sender.sendMessage(new StringTextComponent("Could not find a player with that name!"), Util.NIL_UUID);
            return 1;
        }
        StorageProxy.getParty(player.getUUID()).getServerCosmetics().forEach(cos -> StorageProxy.getParty(player.getUUID()).revokeServerCosmetics(cos));
        if(sender != null) sender.sendMessage(new StringTextComponent("You revoked all of " + player.getName().getString() + "'s cosmetic!"), Util.NIL_UUID);
        player.sendMessage(new StringTextComponent("You have lost your cosmetics!"), Util.NIL_UUID);
        return 0;
    }
}
