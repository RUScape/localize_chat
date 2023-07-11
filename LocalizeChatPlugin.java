package net.runelite.client.plugins.localizechat;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "LocalizeChat"
)
public class LocalizeChatPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private LocalizeChatConfig config;

	@Inject
	private LocalizeChatOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.info("Example stopped!");
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		overlay.sendMessage(chatMessage);
		log.info(chatMessage.getMessage() + chatMessage.getSender());
	}

	@Provides
	LocalizeChatConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LocalizeChatConfig.class);
	}
}
