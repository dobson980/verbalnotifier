package com.dobson980.verbalnotifier;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Skill;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.events.ConfigChanged;

@Slf4j
@PluginDescriptor(
	name = "Verbal Notifier"
)
public class VerbalNotifierPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private VerbalNotifierConfig config;

	private final AudioPlayer audioPlayer = new AudioPlayer();
	private VerbalNotifierConfig.Voices voice;
	private boolean hasPlayedLowHpSound = false;
	private boolean hasPlayedCriticalHpSound = false;
	private long lastNotificationTime = 0;

	private long lastHitTime = 0;
	private long lastPrayerNotificationTime = 0;
	private boolean hasPlayedLowPrayerSound = false;
	private boolean hasPlayedCriticalPrayerSound = false;
	private boolean hasPlayedOutOfPrayerSound = false;

	@Override
	protected void startUp() throws Exception
	{
		log.info("VerbalNotifier started!");
		voice = config.voiceSelection();
		initializePrayerFlags(client.getBoostedSkillLevel(Skill.PRAYER));
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("VerbalNotifier stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			voice = config.voiceSelection();
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event) {

		ChatMessageType messageType = event.getType();
		//log.info("Message Type: {}", messageType);

		if (config.idleSelected() && messageType == ChatMessageType.CONSOLE) {
			String message = event.getMessage();
			if (message.contains("You are now idle!")) {
				audioPlayer.queueVoiceNotification(voice.name(), "idle.wav");
				//log.info("Player is idle. Message: {}", message);
				// Additional processing...
			}
		}

	}

	@Subscribe
	public void onGameTick(GameTick event) {
		int currentHp = client.getBoostedSkillLevel(Skill.HITPOINTS);
		checkHealth(currentHp);

		int currentPrayer = client.getBoostedSkillLevel(Skill.PRAYER);
		checkPrayer(currentPrayer);
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied event) {
		if (event.getActor() == client.getLocalPlayer()) {
			lastHitTime = System.currentTimeMillis();
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if ("verbalnotifier".equals(event.getGroup())) {
			if ("voiceSelection".equals(event.getKey())) {
				// Update the voice when the voice selection changes
				voice = config.voiceSelection();
			} else if ("checkForCombat".equals(event.getKey())) {
				initializePrayerFlags(client.getBoostedSkillLevel(Skill.PRAYER));
			}
		}
	}

	private void checkPrayer(int currentPrayer) {
		int lowPrayerThreshold = config.lowPrayerThreshold();
		int criticalPrayerThreshold = config.criticalPrayerThreshold();
		boolean repeatLowPrayerEnabled = config.repeatLowPrayerNotification();
		boolean repeatCriticalPrayerEnabled = config.repeatCriticalPrayerNotification();
		boolean repeatOutOfPrayerEnabled = config.repeatRunOutOfPrayerNotification();
		int repeatInterval = config.prayerRepeatInterval();
		boolean checkForCombat = config.checkForCombat();

		boolean isInCombat = isInCombatRecently();
		boolean shouldRepeat = (System.currentTimeMillis() - lastPrayerNotificationTime) >= repeatInterval * 1000L;

		if (!checkForCombat || isInCombat) {
			if (currentPrayer == 0 && (!hasPlayedOutOfPrayerSound || (repeatOutOfPrayerEnabled && shouldRepeat))) {
				audioPlayer.queueVoiceNotification(voice.name(), "outofprayer.wav");
				hasPlayedOutOfPrayerSound = true;
				lastPrayerNotificationTime = System.currentTimeMillis();
			} else if (currentPrayer <= criticalPrayerThreshold && (!hasPlayedCriticalPrayerSound || (repeatCriticalPrayerEnabled && shouldRepeat))) {
				audioPlayer.queueVoiceNotification(voice.name(), "criticalprayer.wav");
				hasPlayedCriticalPrayerSound = true;
				lastPrayerNotificationTime = System.currentTimeMillis();
			} else if (currentPrayer <= lowPrayerThreshold && (!hasPlayedLowPrayerSound || (repeatLowPrayerEnabled && shouldRepeat))) {
				audioPlayer.queueVoiceNotification(voice.name(), "lowprayer.wav");
				hasPlayedLowPrayerSound = true;
				lastPrayerNotificationTime = System.currentTimeMillis();
			}
		}

		if (currentPrayer > lowPrayerThreshold) {
			resetPrayerNotifications();
		}
	}

	private void resetPrayerNotifications() {
		hasPlayedLowPrayerSound = false;
		hasPlayedCriticalPrayerSound = false;
		hasPlayedOutOfPrayerSound = false;
	}

	private boolean isInCombatRecently() {
		return (System.currentTimeMillis() - lastHitTime) <= (config.combatThreshold() * 1000);
	}

	private void checkHealth(int currentHp) {
		int lowHpThreshold = config.lowHpThreshold();
		int criticalHpThreshold = config.criticalHpThreshold();
		boolean repeatEnabled = config.repeatLowHpNotification();
		int repeatInterval = config.repeatInterval();

		long currentTime = System.currentTimeMillis();
		boolean shouldRepeat = repeatEnabled && (currentTime - lastNotificationTime) >= repeatInterval * 1000L;

		if (currentHp <= criticalHpThreshold && (!hasPlayedCriticalHpSound || shouldRepeat)) {
			audioPlayer.queueVoiceNotification(voice.name(), "crithp.wav");
			hasPlayedCriticalHpSound = true;
			lastNotificationTime = currentTime;
		} else if (currentHp <= lowHpThreshold && (!hasPlayedLowHpSound || shouldRepeat)) {
			audioPlayer.queueVoiceNotification(voice.name(), "lowhp.wav");
			hasPlayedLowHpSound = true;
			lastNotificationTime = currentTime;
		} else if (currentHp > lowHpThreshold) {
			hasPlayedLowHpSound = false;
			hasPlayedCriticalHpSound = false;
		}
	}

	private void initializePrayerFlags(int currentPrayer) {
		int lowPrayerThreshold = config.lowPrayerThreshold();
		int criticalPrayerThreshold = config.criticalPrayerThreshold();

		hasPlayedLowPrayerSound = currentPrayer <= lowPrayerThreshold;
		hasPlayedCriticalPrayerSound = currentPrayer <= criticalPrayerThreshold;
		hasPlayedOutOfPrayerSound = currentPrayer == 0;
	}

	@Provides
	VerbalNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(VerbalNotifierConfig.class);
	}
}
