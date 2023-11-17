package com.dobson980.verbalnotifier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("verbalnotifier")
public interface VerbalNotifierConfig extends Config {

	enum Voices {
		SALLY,
		ELLA,
		SILAS,
		MICHAEL
	}

	@ConfigSection(
			name = "Voice Settings",
			description = "Settings related to voice notifications",
			position = 0
	)
	String voiceSettings = "voiceSettings";

	@ConfigItem(
			keyName = "voiceSelection",
			name = "Voice Selection",
			description = "Select the voice for notifications",
			section = voiceSettings,
			position = 1
	)
	default Voices voiceSelection() {
		return Voices.SALLY;
	}

	@ConfigSection(
			name = "Idle Notification Settings",
			description = "Settings for idle notifications",
			position = 2
	)
	String idleSettings = "idleSettings";

	@ConfigItem(
			keyName = "idleSelected",
			name = "Enable Idle Notification",
			description = "Enable verbal notifications for going idle.",
			section = idleSettings,
			position = 3
	)
	default boolean idleSelected() {
		return true;
	}

	@ConfigSection(
			name = "Hitpoints Notification Settings",
			description = "Settings for hitpoints notifications",
			position = 4
	)
	String hpSettings = "hpSettings";

	@ConfigItem(
			keyName = "lowHpSelected",
			name = "Enable Low HP Notification",
			description = "Enable verbal notifications for low hitpoints.",
			section = hpSettings,
			position = 5
	)
	default boolean lowHpSelected() {
		return false;
	}

	@ConfigItem(
			keyName = "lowHpThreshold",
			name = "Low HP Threshold",
			description = "Set the hitpoints threshold for low HP notification.",
			section = hpSettings,
			position = 6
	)
	@Range(
			min = 1,
			max = 99
	)
	default int lowHpThreshold() {
		return 20;
	}

	@ConfigItem(
			keyName = "criticalHpThreshold",
			name = "Critical HP Threshold",
			description = "Set the hitpoints threshold for critical HP notification.",
			section = hpSettings,
			position = 7
	)
	@Range(
			min = 1,
			max = 99
	)
	default int criticalHpThreshold() {
		return 10;
	}

	@ConfigItem(
			keyName = "repeatLowHpNotification",
			name = "Repeat Low HP Notification",
			description = "Enable repeating verbal notifications for low hitpoints.",
			section = hpSettings,
			position = 8
	)
	default boolean repeatLowHpNotification() {
		return false;
	}

	@ConfigItem(
			keyName = "repeatInterval",
			name = "Repeat Interval (Seconds)",
			description = "Time interval in seconds for repeating low HP notifications.",
			section = hpSettings,
			position = 9
	)
	@Range(
			min = 5,
			max = 60
	)
	default int repeatInterval() {
		return 15;
	}

	@ConfigSection(
			name = "Prayer Notification Settings",
			description = "Settings for prayer notifications",
			position = 10
	)
	String prayerSettings = "prayerSettings";

	@ConfigItem(
			keyName = "lowPrayerSelected",
			name = "Enable Low Prayer Notification",
			description = "Enable verbal notifications for low prayer points.",
			section = prayerSettings,
			position = 11
	)
	default boolean lowPrayerSelected() {
		return true;
	}

	@ConfigItem(
			keyName = "checkForCombat",
			name = "Check for combat",
			description = "Enable verbal notifications for low prayer points only when in combat.",
			section = prayerSettings,
			position = 12
	)
	default boolean checkForCombat() {
		return true;
	}

	@ConfigItem(
			keyName = "combatThreshold",
			name = "Combat Threshhold",
			description = "Time in seconds since last hitsplat, that will determine if you have left combat",
			section = prayerSettings,
			position = 13
	)
	@Range(
			min = 5,
			max = 15
	)
	default int combatThreshold() {
		return 6;
	}

	@ConfigItem(
			keyName = "lowPrayerThreshold",
			name = "Low Prayer Threshold",
			description = "Set the prayer points threshold for low prayer notification.",
			section = prayerSettings,
			position = 14
	)
	@Range(
			min = 1,
			max = 99
	)
	default int lowPrayerThreshold() {
		return 40;
	}

	@ConfigItem(
			keyName = "criticalPrayerThreshold",
			name = "Critical Prayer Threshold",
			description = "Set the prayer points threshold for critical prayer notification.",
			section = prayerSettings,
			position = 15
	)
	@Range(
			min = 1,
			max = 99
	)
	default int criticalPrayerThreshold() {
		return 15;
	}

	@ConfigItem(
			keyName = "repeatLowPrayerNotification",
			name = "Repeat Low Prayer Notification",
			description = "Enable repeating verbal notifications for low prayer points.",
			section = prayerSettings,
			position = 16
	)
	default boolean repeatLowPrayerNotification() {
		return false;
	}
	@ConfigItem(
			keyName = "repeatCriticalPrayerNotification",
			name = "Repeat Critical Prayer Notification",
			description = "Enable repeating verbal notifications for critical prayer points.",
			section = prayerSettings,
			position = 17
	)
	default boolean repeatCriticalPrayerNotification() {
		return true;
	}

	@ConfigItem(
			keyName = "repeatRunOutOfPrayerNotification",
			name = "Repeat Run Out Of Prayer Notification",
			description = "Enable repeating verbal notifications when you run out of prayer points.",
			section = prayerSettings,
			position = 18
	)
	default boolean repeatRunOutOfPrayerNotification() {
		return true;
	}

	@ConfigItem(
			keyName = "prayerRepeatInterval",
			name = "Prayer Repeat Interval (Seconds)",
			description = "Time interval in seconds for repeating prayer notifications.",
			section = prayerSettings,
			position = 19
	)
	@Range(
			min = 5,
			max = 60
	)
	default int prayerRepeatInterval() {
		return 15;
	}
}
