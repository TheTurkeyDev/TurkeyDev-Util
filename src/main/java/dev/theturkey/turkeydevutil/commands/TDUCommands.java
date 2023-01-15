package dev.theturkey.turkeydevutil.commands;

import com.mojang.brigadier.CommandDispatcher;
import dev.theturkey.turkeydevutil.listeners.DuckFriendSpawn;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

public class TDUCommands
{
	public TDUCommands(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		dispatcher.register(Commands.literal("tdu").requires(cs -> cs.hasPermission(Commands.LEVEL_GAMEMASTERS))
				.then(Commands.literal("spawnDuck")
						.then(Commands.argument("targets", EntityArgument.players())
								.executes(ctx ->
										this.spawnDuck(EntityArgument.getPlayers(ctx, "targets"))
								)
						)
				)
		);
	}

	public int spawnDuck(Collection<ServerPlayer> players)
	{
		for(ServerPlayer player : players)
			DuckFriendSpawn.spawnDuckForPlayer(player.getStringUUID());
		return 0;
	}
}
