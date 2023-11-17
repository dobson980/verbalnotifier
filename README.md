# Verbal Notifier

![icon](https://i.imgur.com/kpJJ6TE.png)  

The Verbal Notifier Plugin is an enhancement for the RuneLite client, providing audio queues for various game events in Old School RuneScape. This plugin is designed to assist players by offering verbal cues for idle states, low health, and prayer levels.

## Features

**Idle Notifications:** Receive an audio alert when your character goes idle.  
**Hitpoint Notifications:** Get notified when your hitpoints fall below pre-set thresholds.  
**Prayer Notifications:** Alerts for low and critical prayer points, with an option to only notify in combat.  
**Voice Selection:** Choose from multiple voice options for your notifications.  


## AI Generated Voices

Customize your gameplay with a variety of AI-generated voices, each designed to offer a unique auditory experience. Tailor your RuneScape journey with a voice that suits your style!

## How It Works

The plugin monitors game states such as hitpoints, prayer points, and idle status. When certain conditions are met (like hitpoints dropping below a set threshold), it triggers a pre-recorded audio notification. Users can customize thresholds and choose when these notifications should play.

Begin by navigating to the Verbal Notification settings panel:   
  
![VNSettings](https://imgur.com/cYQvT9W.png)

### Idle Notifications

Receive an audio alert when your character goes idle: 

![enableIdle](https://i.imgur.com/m5fkTTw.png)

This verbal notification is activated by a chat message from RuneLite:  
  
![idleNotifications](https://i.imgur.com/DvIomhY.png)  

Make sure RuneLite's Idle Notifications are enabled. For an optimal experience, we suggest turning off the sound from RuneLite's native notifications:  
  
![runeliteSound](https://i.imgur.com/cvQ2RAI.png)

## Hitpoint Notifications

Receive an audio alert when your characters hitpoints become undesirable:

![hpNotificaiton](https://i.imgur.com/6hewDuU.png)

This verbal notification is activated when your HP hits specific thresholds:

![hpThresholds](https://i.imgur.com/BoqM2QJ.png)

- **Low HP Alert:** Sounds when your Hitpoints drop to the level set in the 'Low HP Threshold' field.
- **Critical HP Alert:** Sounds when your Hitpoints reach the level specified in the 'Critical HP Threshold' field.
- **Repeating Notifications:** If 'Repeat Low HP Notification' is enabled, the notifier will replay at the interval (in seconds) specified in the 'Repeat Interval (seconds)' field.

## Prayer Notifications

Receive an audio alert when your characters remaining prayer points become undesirable:

![pNotification](https://i.imgur.com/sY6dcJl.png)

This verbal notification is activated when your Prayer hits specific thresholds:

![pThresholds](https://i.imgur.com/XRhlOax.png)

- **Combat Check:** An alert is activated only if your character has recently been in combat, as determined by recent hits taken.
- **Combat Threshold:** Sets the duration (in seconds) to consider for recent combat activity after receiving a hit.
- **Low Prayer Alert:** Triggers when your Prayer points fall to the level set in the 'Low Prayer Threshold' field.
- **Critical Prayer Alert:** Sounds when your Prayer points reach the level specified in the 'Critical Prayer Threshold' field.
- **Repeat Low Prayer Notifications:** When enabled, the low prayer alert will repeat at the interval defined in the 'Prayer Repeat Interval' field.
- **Repeat Critical Prayer Notifications:** When enabled, the critical prayer alert will repeat at the interval defined in the 'Prayer Repeat Interval' field.
- **Repeat Out Of Prayer Notifications:** If checked, an alert for running out of prayer points will rrepeat at the interval defined in the 'Prayer Repeat Interval' field.
- **Prayer Repeat Interval:** The frequency (in seconds) at which the prayer-related notifications will repeat if their respective repeat options are enabled.

## Future Enhancements

**Superior Slayer Creature Announcement:** Stay alert with audio notifications for the appearance of Superior Slayer Creatures. This feature aims to ensure you never miss out on these valuable encounters during your Slayer tasks.  
  
**Farming Run Reminders:** Get timely reminders for your farming runs. This addition is designed to help you maximize your efficiency in one of RuneScape's most rewarding skills.  

**More Voices Coming Soon:** We're working on adding a wider range of AI-generated voices for an even more customized experience!

## Contributing

Contributions, issues, and feature requests are welcome. Feel free to check issues page if you want to contribute.

## Author

Tom Dobson - Initial work - [Dobson980](https://github.com/dobson980)

## License

This project is licensed under the BSD 2-Clause "Simplified" License.

## Acknowledgments

Special thanks to the [RuneLite](https://github.com/runelite/runelite/tree/master) community for their support and contributions.
Inspired by the vibrant [Old School RuneScape](https://oldschool.runescape.com) community.
