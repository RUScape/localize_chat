package net.runelite.client.plugins.localizechat;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Units;

@ConfigGroup("example")
public interface LocalizeChatConfig extends Config
{
	@ConfigItem(
		keyName = "x_pos",
		name = "X position",
		description = "Amount of ticks before hiding status bars after no longer in combat. 0 = always show status bars."
	)
	@Units(Units.PIXELS)
	default int xPosition()
	{
		return 10;
	}

	@ConfigItem(
			keyName = "y_pos",
			name = "Y position",
			description = "Amount of ticks before hiding status bars after no longer in combat. 0 = always show status bars."
	)
	@Units(Units.PIXELS)
	default int yPosition()
	{
		return 20;
	}
}
