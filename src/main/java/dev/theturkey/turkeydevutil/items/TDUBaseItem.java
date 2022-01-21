package dev.theturkey.turkeydevutil.items;

import com.google.common.collect.Lists;
import dev.theturkey.turkeydevutil.TDUCore;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class TDUBaseItem extends Item
{
	private final List<String> lore = Lists.newArrayList();

	public TDUBaseItem(Properties builder, String name)
	{
		super(builder.tab(TDUCore.MOD_TAB));
		this.setRegistryName(TDUCore.MOD_ID, name);
	}

	public void addLore(String info)
	{
		lore.add(info);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag)
	{
		for(String s : lore)
			list.add(new TextComponent(s));
	}
}