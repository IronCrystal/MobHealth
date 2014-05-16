Mob Health
==========

This is just a simple plugin to let you know the damage you just caused to a mob (including other players), and how much health it has left.  

Now supports Spout! (_Optional_)

Also now supporting SimpleNotice, Heroes (inc. skill usage), Mobs, Monster Apocalypse, MobArena, LikeABoss, Mob Stats and BloodMoon (_All Optional_)

Mob Health now has plenty of options to control how messages are shown,when they are shown (and more importantly when they are *not* shown), and what is displayed - with your own custom messages.



API
===

MobHealth Now has its own API! 
You can trigger damage notifications, and get the health and maxhealth of any mob, regardless of which plugin is responsible for tracking it.

Think of it as Vault for mob health levels ;)  You code for one plugin - I sort out the mess behind the scenes.


	usePermissions: false
If true then the permission node MobHealth.show is needed to enable for a player.

	disableChat: false
Force messages to display only if spout is detected.

	disableSpout: false
Set to true to disable the achievement notification. 

	showRPG: false
show RPG style notification (requires SpoutCraft).

	showSideNotification: false
show Side style notification (requires SpoutCraft).


_Note:_ If disableChat and disableSpout are true with showRPG and showSideNotification set to false then nothing will ever show!!!


    disablePlayers: false
Disable notifications for player hits.

    disableMonsters: false
Disable notifications for 'monster' hits.

    disableAnimals: false
Disable notifications for 'animal' hits.

    disablePets: false
Disable notifications for hits by tamed mobs.

    hideNoDammage: false
Hide notifications that inflict 0 damage.  Custom Egg and Snowball notifications are exempt.

    debugMode: false
Enable extra debugging messages in server logs.

    damageDisplayType: [1|2|3|4]
1. display damage inflicted.  
2. display damage taken.
2. display damage inflicted (-amount resisted)
4. display damage taken (+amount resisted)


    showPlayerHeadHealth: true
Enable Health info above player heads

    showMobHeadHealth: true
Show Health info above players

    useBarForMobs: true
If true then mob health will be shown in a bar like "[||||     ]"
If false then mob health with be displayed like "12/30"

    usePercentForPlayer: true
If true playes heal will be expressed as a %age - not as x/y

    healthBarSize: 10
If using bar format, this is the number of characters in the bar

    cleanDeathMessages: true
If true bars and health info will be removed from death messages

    hideBarForAnimal: false
Hide bars for animal mobs

    hideBarForVillager: true
Hide bars for villagers (useful for shopkeeper plugins)

    hideBarForNPC: true
Hide bar on Citizens NPCs

    forceBarHide: [EnderDragon,Wither]
Hide the bar for the listed mob types

    showbarCustomNameOnly: false
Only use the bar if a mob has a custom name

### Commands

	/MobHealth reload
Reloads current configuration.
	
	/MobHealth toggle [player]
Toggle notifications. [player] is ignored via chat and mandatory via the console.


### Permissions

	mobhealth.* 
Wildcard for all ModHealth permissions.

	mobhealth.show
If usePermissions is true then only players with this node with see damage messages.
	
	mobhealth.command.*
Give player all MobHealth commands (and all future commands).
MobHealth.commands also works for this to allow legacy compatibility.

	mobhealth.command.toggle
Give player MobHealth toggle command.

	mobhealth.command.toggle.others:
Allows user to toggle others with the MobHealth toggle command.

	mobhealth.command.reload
Give player MobHealth reload command.


### Changelog
8.2.0:  Filter rogue &f colour code prefix's.  Add scoreboard event (so it can be cancelled to preserve scoreboards).    

8.1.0:  Switch to UUIDs for persistent data

8.0.1:  Cleanup - remove unused class, update metrics to V7

8.0.0:  Reworked Heroes integration. 1.7.2 compatibility fixes.

7.1.2:  Test Fix for MobArena

7.1.1:  Convert Project to Maven

7.1.0:  Clean up and release.

7.0.3:  Update for 1.6.2 Heroes Build.

7.0.2:  Bugfix for 7.0.1 bugfix.  Bugception. 

7.0.1:  Bugfix for 7.0.0 bugfix. 

7.0.0:  1.6.2 update.  Bugfix for horse errors.

6.1.4:  Bugfix - remove special character from lang.yml

6.1.3:  Command alias' /mh /mobh and /mhealth to cope with other plugins using /mobhealh
        Made "Bar character" editable
        Made bar length configurable.
        Options to not show bars on NPCs and animals.
        Fixed Minor ScoreBoard bugs.

6.1.2:  Maintain Custom Mob names

6.1.1:  Detect scoreboard API and disable new health bars if not found.
        Configurable player health label.
        Fix for Villager Trade crash.

6.1.0:  New healthbars and player health counters - requires 1.5.1 r0.2+ (build #2754+)

6.0.0:  Update for 1.5.1 - mainly Compatibility fixes for new Heroes LaB and corruption code.  
        Auto updater added.

5.3.4:  Fixed lang.yml for Zombie villagers and silverfish.  
        Update for latest LaB, BlodMoon and EpicBoss  
        Added toggle to update checker  
        
5.3.3:  Updated for BloodMoon 0.20.2

5.3.2:  1.4.5 RB 1 Update.  Removed depreciated methods. Fixed Entity Name Detection.

5.3.1:  Fixed player names not showing sometimes.

5.3.0:  Fixed Endermen and WithSkelly names.
        Update to support LikeAboss 1.6.6
        update to support Bloodmoon 0.19
        NEW Support for EpicBoss 2.3.8

5.2.0:  Update to support 1.4 mob types - inc Baby/Villager Zombies and WitherSkellies.

5.1.3:  Allow %S usage for skill name in RPG notification.
        Update Mobs Support for latest beta's

5.1.2:  Begin rework of side notification.

5.1.1:  Start of Side Widget Clean up.

5.1.0:  Dev version now 'stable' version.  
        Added support for BloodMoon.  
        Confirmed compatibility with Mob Stats. 

5.0.0:  Complete code refactor - added API

4.9.1:  Fixed NPE when using /mobhealth toggle for first time  
   
4.9.0:  New plugin metrics  

4.8.8:  Added support for Mobs used with Heroes.

4.8.7:  Heroes Skill improvements.  
        Yet another Mobs fix ;)  
        Beginning of improved MA support.

4.8.6:  UTF-8 fix for Simple notice.  
		Reworked mobs support for latest Mobs Beta
		
4.8.5:  Improved colour handling - thanks again FeildMaster...

4.8.4:  Fixed SimpleNotice Support, for reals this time!

4.8.3:  Fixed SimpleNotice Support - sorry FeildMaster - my bad!

4.8.2:  Added SimpleNotice support (Thanks FeildMaster)
        Fixed Mobs support.
        Various Heroes skills and edge case fixes.

4.8.1:  Stopped attempts to notify offline pet owners.
		Support for ZombieMod for ZARP server.
		http://www.imagicraft.net/wiki/index.php?title=Zombie_Apocalypse_RP_(ZARP)

4.8.0:  Displays damage inflicted by tamed mobs.
        Player toggle state now persistent.

4.7.2:  Mobs and LikeABoss updates.  

4.7.1:  Swap to grownup version numbering system :)   
        Corrected Version number.
        
4.7:  Display item used to inflict damage in spout notifications.

4.6:  Fixed bugs cause by spout not being available.

4.5:  Two new display modes - RPG style and side notification.  
      Heroes support fixes.

4.4:  Added option to change default toggle.

4.3:  Updates Heroes support - now requires heroes build #1308+   
      New Feature:  RPG style notifications.

4.2:  Updated MobArena support - Works with v0.94.4.61+

4.1:  Code Cleanup - remove unused likeaboss event.

4.0:  Updated plugin file - now soft depends supported plugins to load after them.  
      Monster Apocalypse support.

3.9:  LikeaBoss Update

3.8:  Mobs Support.  
      1.2 Entities added.

3.7:  Fix Ender Dragon notifications

3.6:  Heroes Skill Notifications

3.5:  Updated Heroes Code.

3.4:  Lower case permissions nodes for PermissionsEx Compatibility.

3.3:  Chat features moved to separate plugin.
      
3.2:  Initial Heroes support.  
      Added debug mode option to config.  
      New permissions node to allow toggling of other players notifications.

3.1:  Initial MobArena support.

3.0:  Permissions rewrite.

2.5:  New Bukkit event format.  
      Support for Likeaboss Boss entities.  

2.4:  "eleven" message trigger changed to trigger list in lang.yml.  You can now have your own silly phrase trigger whenever you want!  
      Implemented flags to hide damage notifications if target is player, animal or monster.  
      Implemented various options for displaying damage in notifications.  
      Option to hide notifications that inflict '0' real damage.
      
2.3:  /MobHealth toggle  option added.  Toggles display of notifications per player. 

2.2:  Custom messages for Egg and Snowball hits.  
      Just add these lines to lang.yml  
      chatMessageEgg:   
      chatMessageSnowball:  
      spoutEggTitle:  
      spoutEggMessage:  
      spoutSnowballTitle:  
      spoutSnowballMessage:
 
2.1:  Chat colour fixes.

2.0:  Finished config file.  Mob names now configurable in lang.yml.

1.9:  As requested, text for notification/messages is now stored in a config file - lang.yml

1.8:  Changed spout notification icon to bow for projectile hits.

1.7:  Version Checker.

1.6:  Now works for Projectiles!

1.5:  Fixed:  Displays Player name instead of 'unknOwn' when attacking other players.

1.4:  Disabled bonus chat features. Added config option to re-enable them.

1.3:  Added config option to disable spout notification.

1.2:  Added Permissions to optionally control who sees damage notifications.

1.1:  Added Spout notification.

1.0:  First release.


To Do
=====
* Spells damage detection.


Known Bugs/Conflicts
====================

* Plugins that alter mobs health levels usually aren't detected by my plugin, unless listed above as supported.


License & Copyright
====================

Copyright (C) 2012-2013 Darren Douglas (http://sabletopia.co.uk)

Licensed under the GNU General Public License, Version 3

MobHealth is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 
MobHealth is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 
You should have received a copy of the GNU General Public License along with MobHealth.  If not, see <http://www.gnu.org/licenses/>.
