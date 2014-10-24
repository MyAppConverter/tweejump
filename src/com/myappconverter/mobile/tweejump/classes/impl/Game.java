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

import android.util.Log;
import android.view.MotionEvent;

import com.cocos2dx.andoird.wrappers.CCTargetedTouchHandler;
import com.cocos2dx.andoird.wrappers.CCTouchDispatcher;
import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCLabelBMFont;
import com.cocos2dx.wrappers.CCScaleTo;
import com.cocos2dx.wrappers.CCScene;
import com.cocos2dx.wrappers.CCSequence;
import com.cocos2dx.wrappers.CCSize;
import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.cocos2dx.wrappers.CCTransitionFade;
import com.cocos2dx.wrappers.ccColor3B;
import com.cocos2dx.wrappers.ccVertex2F;
import com.cocos2dx.wrappers.protocols.CCTouchDelegateProtocol;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.applicationservices.CGRect;
import com.myappconverter.java.applicationservices.CGSize;
import com.myappconverter.java.foundations.NSString;
import com.myappconverter.java.foundations.SEL;
import com.myappconverter.java.uikit.UIAcceleration;
import com.myappconverter.java.uikit.UIAccelerometer;
import com.myappconverter.java.uikit.UIAlertView;
import com.myappconverter.java.uikit.UIApplication;
import com.myappconverter.java.uikit.protocols.UIAccelerometerDelegate;
import com.myappconverter.mapping.utils.Math;

public class Game extends com.myappconverter.mobile.tweejump.classes.Game
		implements CCTouchDelegateProtocol, UIAccelerometerDelegate {

	private static CCTouchDispatcher mDispatcher;

	/**
	 * Method : scene <!-- begin-user-doc -->
	 * 
	 * @return CCScene.
	 * @generated
	 */
	public static CCScene scene() {
		CCScene game = CCScene.node();
		Game layer = new Game();
		layer.init();
		mDispatcher = CCTouchDispatcher.sharedDispatcher();
		CCTargetedTouchHandler ccTargetedTouchHandler = new CCTargetedTouchHandler(
				layer, 0, true);
		mDispatcher.getTargetedHandlers().add(0, ccTargetedTouchHandler);
		game.addChild(layer);
		return game;
	}

	/**
	 * Method : init <!-- begin-user-doc -->
	 * 
	 * @return Game.
	 * @generated
	 */
	public boolean init() {
		if (!com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(super.init())) {
			return false;
		}
		gameSuspended = true;
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		this.initPlatforms();
		CCSprite bird = CCSprite.spriteWithTexture(batchNode.getTexture(),
				CGGeometry.CGRectMake(608, 16, 44, 32));
		batchNode.addChild(bird, 4, Main.kBird);
		CCSprite bonus;
		for (int i = 0; i < Main.kNumBonuses; i++) {
			bonus = CCSprite.spriteWithTexture(batchNode.getTexture(),
					CGGeometry.CGRectMake(608 + i * 32, 256, 25, 25));
			batchNode.addChild(bonus, 4, Main.kBonusStartTag + i);
			bonus.setIsVisible(false);
		}
		CCLabelBMFont scoreLabel = CCLabelBMFont.labelWithString(new NSString(
				"0"), new NSString("bitmapFont.fnt"));
		this.addChild(scoreLabel, 5, Main.kScoreLabel);
		scoreLabel.setPosition(CGGeometry.CGPointMake(160, 430));
		this.schedule(this, new SEL(new NSString("step")), 0.1f);
		this.setIsTouchEnabled(false);
		this.setIsAccelerometerEnabled(true);
		UIAccelerometer acc = UIAccelerometer.sharedAccelerometer();
		acc.setDelegate(Game.this);
		acc.setUpdateInterval((1.0f / 60));
		this.startGame();
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
	 * Method : initPlatforms <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void initPlatforms() {
		currentPlatformTag = Main.kPlatformsStartTag;
		while (currentPlatformTag < Main.kPlatformsStartTag + 10) {
			this.initPlatform();
			currentPlatformTag++;
		}
		this.resetPlatforms();
	}

	/**
	 * Method : initPlatform <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void initPlatform() {
		CGRect rect = CGGeometry.CGRectZero;
		switch ((int) (Math.random() % 2)) {
		case 1:
			rect = CGGeometry.CGRectMake(608, 128, 90, 32);
		case 0:
			rect = CGGeometry.CGRectMake(608, 64, 102, 36);
		}
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite platform = CCSprite.spriteWithTexture(batchNode.getTexture(),
				rect);
		batchNode.addChild(platform, 3, currentPlatformTag);
	}

	/**
	 * Method : startGame <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void startGame() {
		score = 0;
		this.resetClouds();
		this.resetPlatforms();
		this.resetBird();
		this.resetBonus();
		UIApplication.sharedApplication().setIdleTimerDisabled(true);
		gameSuspended = false;
	}

	/**
	 * Method : resetPlatforms <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetPlatforms() {
		currentPlatformY = -1;
		currentPlatformTag = Main.kPlatformsStartTag;
		currentMaxPlatformStep = 60.0f;
		currentBonusPlatformIndex = 0;
		currentBonusType = 0;
		platformCount = 0;
		while (currentPlatformTag < Main.kPlatformsStartTag + 10) {
			this.resetPlatform();
			currentPlatformTag++;
		}
	}

	/**
	 * Method : resetPlatform <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetPlatform() {
		if (currentPlatformY < 0) {
			currentPlatformY = 30.0f;
		} else {
			currentPlatformY += Math.random() % (currentMaxPlatformStep - 50)
					+ 50;
			if (currentMaxPlatformStep < 300) {
				currentMaxPlatformStep += 0.5f;
			}
		}
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite platform = (CCSprite) batchNode
				.getChildByTag(currentPlatformTag);
		if (Math.random() % 2 == 1) {
			platform.setScaleX(-1.0f);
		}
		float x;
		CGSize size = platform.getContentSize();
		if (currentPlatformY == 30.0f) {
			x = 160.0f;
		} else {
			x = Math.random() % (320 - size.width) + size.width / 2;
		}
		platform.setPosition(CGGeometry.CGPointMake(x, currentPlatformY));
		platformCount++;
		if (platformCount == currentBonusPlatformIndex) {
			CCSprite bonus = (CCSprite) batchNode
					.getChildByTag(Main.kBonusStartTag + currentBonusType);
			bonus.setPosition(CGGeometry.CGPointMake(x, currentPlatformY + 30));
			bonus.setIsVisible(true);
		}
	}

	/**
	 * Method : resetBird <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetBird() {
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite bird = (CCSprite) batchNode.getChildByTag(Main.kBird);
		bird_pos = new CGPoint();
		bird_pos.x = 160;
		bird_pos.y = 160;
		bird.setPosition(bird_pos);
		bird_vel = new ccVertex2F();
		bird_vel.setX(0);
		bird_vel.setY(0);
		bird_acc = new ccVertex2F();
		bird_acc.setX(0);
		bird_acc.setY(-550.0f);
		birdLookingRight = true;
		bird.setScaleX(1.0f);
	}

	/**
	 * Method : resetBonus <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetBonus() {

		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite bonus = (CCSprite) batchNode.getChildByTag(Main.kBonusStartTag
				+ currentBonusType);
		bonus.setIsVisible(false);
		currentBonusPlatformIndex += Math.random() % (50 - 30) + 30;
		if (score < 10000) {
			currentBonusType = 0;
		} else {
			if (score < 50000) {
				currentBonusType = (int) (Math.random() % 2);
			} else {
				if (score < 100000) {
					currentBonusType = (int) (Math.random() % 3);
				} else {
					currentBonusType = (int) (Math.random() % 2 + 2);
				}
			}
		}
	}

	/**
	 * Method : step <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void step(float dt) {
		super.step(dt);
		if (com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(gameSuspended)) {
			return;
		}
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite bird = (CCSprite) batchNode.getChildByTag(Main.kBird);
		bird_pos.x += bird_vel.getX() * dt;
		if (bird_vel.getX() < -30.0f
				&& com.myappconverter.java.foundations.ExpressNullable
						.assertCondition(birdLookingRight)) {
			birdLookingRight = false;
			bird.setScaleX(-1.0f);
		} else {
			if (bird_vel.getX() > 30.0f
					&& !com.myappconverter.java.foundations.ExpressNullable
							.assertCondition(birdLookingRight)) {
				birdLookingRight = true;
				bird.setScaleX(1.0f);
			}
		}
		CGSize bird_size = bird.getContentSize();
		float max_x = 320 - bird_size.width / 2;
		float min_x = 0 + bird_size.width / 2;
		if (bird_pos.x > max_x) {
			bird_pos.x = max_x;
		}
		if (bird_pos.x < min_x) {
			bird_pos.x = min_x;
		}
		bird_vel.setY(bird_vel.getY() + bird_acc.getY() * dt);
		bird_pos.setY(bird_pos.getY() + bird_vel.getY() * dt);
		CCSprite bonus = (CCSprite) batchNode.getChildByTag(Main.kBonusStartTag
				+ currentBonusType);
		if (com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(bonus.getIsVisible())) {
			CGPoint bonus_pos = bonus.getPosition();
			float range = 20.0f;
			if (bird_pos.x > bonus_pos.x - range
					&& bird_pos.x < bonus_pos.x + range
					&& bird_pos.y > bonus_pos.y - range
					&& bird_pos.y < bonus_pos.y + range) {
				switch (currentBonusType) {
				case Main.kBonus100:
					score += 100000;
				case Main.kBonus50:
					score += 50000;
				case Main.kBonus10:
					score += 10000;
				case Main.kBonus5:
					score += 5000;
				}
				NSString scoreStr = NSString.stringWithFormat(
						new NSString("%d"), score);
				CCLabelBMFont scoreLabel = (CCLabelBMFont) this
						.getChildByTag(Main.kScoreLabel);
				scoreLabel.setString(scoreStr);
				CCScaleTo a1 = CCScaleTo.actionWithDuration(0.2f, 1.5f, 0.8f);
				CCScaleTo a2 = CCScaleTo.actionWithDuration(0.2f, 1.0f, 1.0f);
				scoreLabel.runAction(CCSequence.actions(a1, a2, a1, a2, a1, a2,
						null));
				this.resetBonus();
			}
		}
		int t;
		if (bird_vel.getY() < 0) {
			t = Main.kPlatformsStartTag;
			for (int j = t; j < t + 10; j++) {
				CCSprite platform = (CCSprite) batchNode.getChildByTag(j);
				CGSize platform_size = platform.getContentSize();
				CGPoint platform_pos = platform.getPosition();
				max_x = platform_pos.x - platform_size.width / 2 - 10;
				min_x = platform_pos.x + platform_size.width / 2 + 10;
				float min_y = platform_pos.y
						+ (platform_size.height + bird_size.height) / 2 - 10;
				if (bird_pos.x > max_x && bird_pos.x < min_x
						&& bird_pos.y > platform_pos.y && bird_pos.y < min_y) {
					this.jump();
				}
			}
			if (bird_pos.y < -bird_size.height / 2) {
				this.showHighscores();
			}
		} else {
			if (bird_pos.y > 240) {
				float delta = bird_pos.y - 240;
				bird_pos.y = 240;
				currentPlatformY -= delta;
				t = Main.kCloudsStartTag;
				for (int j = t; j < Main.kCloudsStartTag + 12; j++) {
					CCSprite cloud = (CCSprite) batchNode.getChildByTag(j);
					CGPoint pos = cloud.getPosition();
					pos.y -= delta * cloud.getScaleY() * 0.8f;
					if (pos.y < -cloud.getContentSize().height / 2) {
						currentCloudTag = j;
						this.resetCloud();
					} else {
						cloud.setPosition(pos);
					}
				}
				t = Main.kPlatformsStartTag;
				for (int j = t; j < Main.kPlatformsStartTag + 10; j++) {
					CCSprite platform = (CCSprite) batchNode.getChildByTag(j);
					CGPoint pos = platform.getPosition();
					pos = CGGeometry.CGPointMake(pos.x, pos.y - delta);
					if (pos.y < -platform.getContentSize().height / 2) {
						currentPlatformTag = j;
						this.resetPlatform();
					} else {
						platform.setPosition(pos);
					}
				}
				if (com.myappconverter.java.foundations.ExpressNullable
						.assertCondition(bonus.getIsVisible())) {
					CGPoint pos = bonus.getPosition();
					pos.y -= delta;
					if (pos.y < -bonus.getContentSize().height / 2) {
						this.resetBonus();
					} else {
						bonus.setPosition(pos);
					}
				}
				score += delta;
				NSString scoreStr = NSString.stringWithFormat(
						new NSString("%d"), score);
				CCLabelBMFont scoreLabel = (CCLabelBMFont) this
						.getChildByTag(Main.kScoreLabel);
				scoreLabel.setString(scoreStr);
			}
		}
		bird.setPosition(bird_pos);
	}

	/**
	 * Method : jump <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void jump() {
		bird_vel.setY(350.0f + Math.fabsf(bird_vel.getX()));
	}

	/**
	 * Method : showHighscores <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void showHighscores() {
		gameSuspended = true;
		UIApplication.sharedApplication().setIdleTimerDisabled(false);
		execService.shutdownNow();
		CCDirector.sharedDirector().replaceScene(
				CCTransitionFade.transitionWithDuration(1, Highscores
						.sceneWithScore(score), new ccColor3B(255, 255, 255)));
	}

	/**
	 * Method : accelerometer <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void accelerometer(UIAccelerometer accelerometer,
			UIAcceleration acceleration) {
		if (!com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(acceleration)) {
			return;
		}
		float accel_filter = 0.1f;
		try {
			bird_vel.setX(bird_vel.getX()
					* accel_filter
					+ (float) UIAcceleration
							.convertGravityFromIOStoAND(acceleration.getX())
					* (1.0f - accel_filter) * 500.0f);
		} catch (Exception e) {
			Log.e("Game", e.getMessage());
		}
	}

	/**
	 * Method : alertView <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void alertView(UIAlertView alertView, int buttonIndex) {
		if (buttonIndex == 0) {
			this.startGame();
		} else {
			this.startGame();
		}
	}

	@Override
	public void resetClouds() {
		super.resetClouds();

	}

	@Override
	public void resetCloud() {
		super.resetCloud();
	}

	public boolean ccTouchesBegan(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		CGPoint location = new CGPoint(x, y);
		location = CCDirector.sharedDirector().convertToGL(location);
		return true;
	}

	public boolean ccTouchesMoved(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		CGPoint location = new CGPoint(x, y);

		location = CCDirector.sharedDirector().convertToGL(location);

		return true;
	}

	public boolean ccTouchesEnded(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		CGPoint location = new CGPoint(x, y);
		location = CCDirector.sharedDirector().convertToGL(location);

		return true;
	}

	public boolean ccTouchesCancelled(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		CGPoint location = new CGPoint(x, y);
		location = CCDirector.sharedDirector().convertToGL(location);

		return true;
	}

	@Override
	public void accelerometerDidAccelerate(UIAccelerometer accelerometer,
			UIAcceleration acceleration) {
		accelerometer(accelerometer, acceleration);

	}

}
