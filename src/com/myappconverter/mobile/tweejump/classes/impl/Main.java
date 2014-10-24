/*
 * Copyright (c) 2014 MyAppConverter
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MyAppConverter License v1.0
 * which accompanies this distribution, and is available at
 * http://www.myappconverter.com/legal/epl-v1.html
 *
 * Contributors:
 *    MyAppConverter Core Team - initial API and implementation
 * @date : Sep, 19 2014 - 15:36:18
 */

package com.myappconverter.mobile.tweejump.classes.impl;

import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.applicationservices.CGRect;
import com.myappconverter.java.applicationservices.CGSize;
import com.myappconverter.java.foundations.NSString;
import com.myappconverter.java.foundations.SEL;
import com.myappconverter.mapping.utils.Math;

public class Main extends com.myappconverter.mobile.tweejump.classes.Main {

	/**
	 * Method : init <!-- begin-user-doc -->
	 * 
	 * @return Main.
	 * @generated
	 */
	public boolean init() {
		if (!com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(super.init())) {
			return false;
		}
		Math.srandom((Math.mach_absolute_time() & -1));
		CCSpriteBatchNode batchNode = CCSpriteBatchNode.batchNodeWithFile(
				new NSString("sprites.png"), 10);
		this.addChild(batchNode, -1, Main.kSpriteManager);
		CCSprite background = CCSprite.spriteWithTexture(
				batchNode.getTexture(), CGGeometry.CGRectMake(0, 0, 320, 480));
		batchNode.addChild(background);
		background.setPosition(CGGeometry.CGPointMake(160, 240));
		this.initClouds();
		this.schedule(this, new SEL(new NSString("step")), 0.1f);
		return true;
	}

	/**
	 * Method : dealloc <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void dealloc() {
		super.dealloc();
	}

	/**
	 * Method : initClouds <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void initClouds() {
		currentCloudTag = Main.kCloudsStartTag;
		while (currentCloudTag < Main.kCloudsStartTag + 12) {
			this.initCloud();
			currentCloudTag++;
		}
		this.resetClouds();
	}

	/**
	 * Method : initCloud <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void initCloud() {
		CGRect rect = CGGeometry.CGRectZero;
		switch ((int) (Math.random() % 3)) {
		case 2:
			rect = CGGeometry.CGRectMake(336, 240, 252, 119);
		case 1:
			rect = CGGeometry.CGRectMake(336, 128, 257, 110);
		case 0:
			rect = CGGeometry.CGRectMake(336, 16, 256, 108);
		}
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite cloud = CCSprite.spriteWithTexture(batchNode.getTexture(),
				rect);
		batchNode.addChild(cloud, 3, currentCloudTag);
		cloud.setOpacity(128);
	}

	/**
	 * Method : resetClouds <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetClouds() {
		currentCloudTag = Main.kCloudsStartTag;
		while (currentCloudTag < Main.kCloudsStartTag + 12) {
			this.resetCloud();
			CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
					.getChildByTag(Main.kSpriteManager);
			CCSprite cloud = (CCSprite) batchNode
					.getChildByTag(currentCloudTag);
			CGPoint pos = cloud.getPosition();
			pos.y -= 480.0f;
			cloud.setPosition(pos);
			currentCloudTag++;
		}
	}

	/**
	 * Method : resetCloud <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetCloud() {
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite cloud = (CCSprite) batchNode.getChildByTag(currentCloudTag);
		float distance = Math.random() % 20 + 5;
		float scale = 5.0f / distance;
		cloud.setScaleX(scale);
		cloud.setScaleY(scale);
		if (Math.random() % 2 == 1) {
			cloud.setScaleX(-cloud.getScaleX());
		}
		CGSize size = cloud.getContentSize();
		float scaled_width = size.width * scale;
		float x = Math.random() % (320 + scaled_width) - scaled_width / 2;
		float y = Math.random() % (480 - scaled_width) + scaled_width / 2 + 480;
		cloud.setPosition(CGGeometry.CGPointMake(x, y));
	}

	/**
	 * Method : step <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void step(float dt) {
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		int t = Main.kCloudsStartTag;
		for (int j = t; j < Main.kCloudsStartTag + 12; j++) {
			CCSprite cloud = (CCSprite) batchNode.getChildByTag(j);
			CGPoint pos = cloud.getPosition();
			CGSize size = cloud.getContentSize();
			pos.x += 0.1f * cloud.getScaleY();
			if (pos.x > 320 + size.width / 2) {
				pos.x = -size.width / 2;
			}
			cloud.setPosition(pos);
		}
	}

}
