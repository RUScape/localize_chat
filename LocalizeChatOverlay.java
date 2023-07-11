package net.runelite.client.plugins.localizechat;

import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.TextComponent;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LocalizeChatOverlay extends OverlayPanel {

    private final Client client;

    private LocalizeChatConfig config;

    private static final ImageComponent PLACEHOLDER_IMAGE = new ImageComponent(
            new BufferedImage(500, 100, BufferedImage.TYPE_4BYTE_ABGR));

    private String LastMessage = "Добро пожаловать в перевод";

    @Inject
    private LocalizeChatOverlay(Client client, LocalizeChatConfig config)
    {
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        panelComponent.setWrap(true);
        panelComponent.setPreferredSize(new Dimension(400, 30));
        panelComponent.setOrientation(ComponentOrientation.HORIZONTAL);
        this.client = client;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().add(PLACEHOLDER_IMAGE);
        graphics.setFont(FontManager.getRunescapeFont());
        final net.runelite.client.ui.overlay.components.TextComponent textComponent = new TextComponent();
        textComponent.setText(LastMessage);
        textComponent.setOutline(false);
        textComponent.setPosition(new Point(config.xPosition(), config.yPosition()));
        textComponent.render(graphics);

        return super.render(graphics);
    }

    public void sendMessage(ChatMessage chatMessage) {
        final String[] string_parts = chatMessage.getMessage().split("[|]");
        String message_author, message;
        if (string_parts.length > 1)
        {
            message_author = string_parts[0];
            message = string_parts[1];
        }
        else
        {
            message_author = "";
            message = string_parts[0];
        }

        LastMessage = message_author + " - " + message;
    }
}
