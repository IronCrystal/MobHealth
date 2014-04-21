/*
 * This file is part of MobHealth.
 * Copyright (C) 2012-2013 Darren Douglas - darren.douglas@gmail.com
 *
 * MobHealth is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MobHealth is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MobHealth.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.java.me.sablednah.MobHealth;

import java.util.Arrays;

import main.java.me.sablednah.MobHealth.API.MobHealthAPI;
import me.sablednah.zombiemod.PutredineImmortui;
import me.sablednah.zombiemod.ZombieMod;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Skeleton.SkeletonType;

import com.herocraftonline.heroes.api.events.SkillDamageEvent;

public class SkillMessageScheduler implements Runnable {

	private Player				player;
	private SkillDamageEvent	skillDamageEvent;
	private LivingEntity		targetMob;
	public MobHealth			plugin;
	private int					HealthBefore;
	private int					DamageBefore;

	public SkillMessageScheduler(Player shooter, SkillDamageEvent skillDamageEvent, LivingEntity targetMob, int HealthBefore, int DamageBefore, MobHealth plugin) {
		this.plugin = plugin;
		this.skillDamageEvent = skillDamageEvent;
		this.player = shooter;
		this.targetMob = targetMob;
		this.HealthBefore = HealthBefore;
		this.DamageBefore = DamageBefore;
	}

	public void run() {

		int thisDamange = 0, mobsHealth = 0, mobsMaxHealth = 0, damageTaken = 0, damageResisted = 0;
		Boolean isPlayer = false, isMonster = false, isAnimal = false;
		String damageOutput;

		MobHealthAPI API = new MobHealthAPI(plugin);

		/*
		 * thisDamange = damageEvent.getDamage(); if (thisDamange > 200) { thisDamange = DamageBefore; }
		 */
		mobsMaxHealth = API.getMobMaxHealth(targetMob);
		mobsHealth = API.getMobHealth(targetMob);
		
		thisDamange = DamageBefore;
		damageTaken = HealthBefore - mobsHealth;
		if (damageTaken > 9950) { // heroes hacky fix
			damageTaken = thisDamange;
		}
		damageResisted = thisDamange - damageTaken;

		
		if (mobsHealth < -50) { // hack to deal with mods using overkill
			mobsHealth = 0;
		}

		String skillName = skillDamageEvent.getSkill().getName();

		if (MobHealth.debugMode) {
			System.out.print("--");
			System.out.print("[MobHealth] " + skillDamageEvent.getDamage() + " skillDamageEvent.getDamage();.");
			System.out.print("[MobHealth] " + DamageBefore + " DamageBefore.");
			System.out.print("[MobHealth] " + thisDamange + " thisDamange.");
			System.out.print("[MobHealth] " + mobsHealth + " mobsHealth.");
			System.out.print("[MobHealth] " + HealthBefore + " HealthBefore.");
			System.out.print("[MobHealth] " + damageTaken + " damageTaken.");
			System.out.print("[MobHealth] " + damageResisted + " damageResisted.");
			System.out.print("[MobHealth] " + targetMob.getLastDamage() + " targetMob.getLastDamage().");
		}

		String mobtype = new String(targetMob.getClass().getName());

		if (mobtype.indexOf("org.bukkit.craftbukkit.entity.Craft") == -1) {
			if (targetMob instanceof Player) {
				isPlayer = true;
				mobtype = ((Player) targetMob).getDisplayName();
			} else {
				System.out.print("[MobHealth] " + mobtype + " unknown.");
				mobtype = "unKn0wn";
			}
		} else {
			if  (targetMob instanceof Zombie) {
				mobtype = "Zombie";
				if (((Zombie)targetMob).isVillager()) { mobtype = mobtype + "Vilager";}
				if (((Zombie)targetMob).isBaby()) { mobtype = mobtype + "Baby";}
			} else if (targetMob instanceof Skeleton) {
				mobtype = "Skeleton";
				if (((Skeleton)targetMob).getSkeletonType() == SkeletonType.WITHER) {mobtype = mobtype + "Wither";}
			} else {
				mobtype = mobtype.replaceAll("org.bukkit.craftbukkit.entity.Craft", "");
			}
			
			if (Arrays.asList(MobHealth.animalList).contains(mobtype))
				isAnimal = true;
			if (Arrays.asList(MobHealth.monsterList).contains(mobtype))
				isMonster = true;
			if (MobHealth.entityLookup.get(mobtype) != null) {
				mobtype = MobHealth.entityLookup.get(mobtype);
			}
			// is entity tracked by ZombieMod.
			if (MobHealth.hasZM) {
				ZombieMod ZM = (ZombieMod) plugin.getServer().getPluginManager().getPlugin("ZombieMod");
				PutredineImmortui zomb = ZM.getZombie((Entity) targetMob);
				if (zomb != null) {
					mobtype = zomb.commonName;
				}
				zomb = null;
				ZM = null;
			}
		}

		switch (MobHealth.damageDisplayType) {
			case 4: // # 4: display damage taken (+amount resisted)
				damageOutput = Integer.toString(damageTaken);
				if (damageResisted > 0)
					damageOutput += "(+" + damageResisted + ")";
				break;
			case 3: // # 3: display damage inflicted (-amount resisted)
				damageOutput = Integer.toString(thisDamange);
				if (damageResisted > 0)
					damageOutput += "(-" + damageResisted + ")";
				break;
			case 2: // # 2: display damage taken.
				damageOutput = Integer.toString(damageTaken);
				break;
			default: // # 1: display damage inflicted.
				damageOutput = Integer.toString(thisDamange);
		}

		Boolean checkForZeroDamageHide = true;

		if ((MobHealth.hideNoDammage && (damageTaken > 0)) || !MobHealth.hideNoDammage) {
			checkForZeroDamageHide = false;
		}

		if (MobHealth.debugMode) {
			if (isPlayer) {
				System.out.print("Is Player");
			} else {
				System.out.print("Is not Player");
			}
			if (isAnimal) {
				System.out.print("Is Animal");
			} else {
				System.out.print("Is not Animal");
			}
			if (isMonster) {
				System.out.print("Is Monster");
			} else {
				System.out.print("Is not Monster");
			}
		}

		if (((MobHealth.disablePlayers && !isPlayer) || !MobHealth.disablePlayers) && ((MobHealth.disableMonsters && !isMonster) || !MobHealth.disableMonsters) && ((MobHealth.disableAnimals && !isAnimal) || !MobHealth.disableAnimals)
				&& (!checkForZeroDamageHide)) {
			API.showNotification(player, damageOutput, mobtype, mobsHealth, mobsMaxHealth, skillName);
		}
	}
}