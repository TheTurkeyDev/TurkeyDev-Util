package dev.theturkey.turkeydevutil.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUnstableIngot extends TDUBaseItem
{
	public static final long TICK_DURATION = 200;

	public ItemUnstableIngot()
	{
		super((new Properties()), "unstable_ingot");
	}

	public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity ent, int p_41407_, boolean p_41408_)
	{
		if(level.isClientSide)
			return;
		CompoundTag tag = stack.getTag();
		if(tag != null && tag.contains("creation_stamp"))
		{
			long creationStamp = tag.getLong("creation_stamp");
			if(level.getGameTime() > creationStamp + TICK_DURATION)
			{
				level.explode(null, ent.getX(), ent.getY(), ent.getZ(), 10f, Explosion.BlockInteraction.BREAK);
				stack.setCount(0);
			}
		}
	}

	public void onCraftedBy(@NotNull ItemStack stack, @NotNull Level level, @NotNull Player player)
	{
		stack.addTagElement("creation_stamp", LongTag.valueOf(level.getGameTime()));
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag)
	{
		CompoundTag tag = stack.getTag();
		if(tag != null && tag.contains("creation_stamp") && level != null)
		{
			long creationStamp = tag.getLong("creation_stamp");
			long ticks = TICK_DURATION - (level.getGameTime() - creationStamp);
			if(ticks <= 0)
			{
				list.add(new TextComponent("Boom?"));
			}
			else
			{
				long seconds = ticks / 20;
				long millis = (ticks % 20) * 50;
				list.add(new TextComponent(seconds + "." + millis));
			}
		}
	}
}
