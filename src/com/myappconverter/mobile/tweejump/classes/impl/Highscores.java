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

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCLabelTTF;
import com.cocos2dx.wrappers.CCMenuItem;
import com.cocos2dx.wrappers.CCScene;
import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.cocos2dx.wrappers.CCTextAlignment;
import com.cocos2dx.wrappers.CCTransitionFade;
import com.cocos2dx.wrappers.CCTransitionScene;
import com.cocos2dx.wrappers.cocos2dxMapping;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.foundations.NSArray;
import com.myappconverter.java.foundations.NSMutableArray;
import com.myappconverter.java.foundations.NSNumber;
import com.myappconverter.java.foundations.NSRange;
import com.myappconverter.java.foundations.NSString;
import com.myappconverter.java.foundations.NSUserDefaults;
import com.myappconverter.java.uikit.UIAlertView;
import com.myappconverter.java.uikit.UITextField;
import com.myappconverter.java.uikit.UITextInputTraits;

public class Highscores extends
		com.myappconverter.mobile.tweejump.classes.Highscores {

	/**
	 * Enum : myenum
	 * 
	 * @generated
	 */
	public enum myenum {

		a(0), b(1), c(2);
		private int value;

		private myenum(int value) {
			this.value = value;
		}
	};

	/**
	 * Method : sceneWithScore <!-- begin-user-doc -->
	 * 
	 * @return CCScene.
	 * @generated
	 */
	public static CCScene sceneWithScore(int lastScore) {
		CCScene game = CCScene.node();
		Highscores layer = new Highscores();
		layer = layer.initWithScore(lastScore);
		game.addChild(layer);
		return game;
	}

	/**
	 * Method : initWithScore <!-- begin-user-doc -->
	 * 
	 * @return Highscores.
	 * @generated
	 */
	public Highscores initWithScore(int lastScore) {
		if (!com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(super.init())) {
			return null;
		}
		currentScore = lastScore;
		this.loadCurrentPlayer();
		this.loadHighscores();
		this.updateHighscores();
		if (currentScorePosition >= 0) {
			this.saveHighscores();
		}
		CCSpriteBatchNode batchNode = (CCSpriteBatchNode) this
				.getChildByTag(Main.kSpriteManager);
		CCSprite title = CCSprite.spriteWithTexture(batchNode.getTexture(),
				CGGeometry.CGRectMake(608, 192, 225, 57));
		batchNode.addChild(title, 5);
		title.setPosition(CGGeometry.CGPointMake(160, 420));
		float start_y = 360.0f;
		float step = 27.0f;
		int count = 0;
		for (Object highscoreInter : highscores) {
			NSArray highscore = (NSArray) highscoreInter;
			NSString player = (NSString) highscore.objectAtIndex(0);
			int score = ((NSNumber) highscore.objectAtIndex(1)).intValue();
			CCLabelTTF label1 = CCLabelTTF.labelWithString(
					NSString.stringWithFormat(new NSString("%d"), (count + 1)),
					CGGeometry.CGSizeMake(30, 40),
					CCTextAlignment.CCTextAlignmentRight,
					new NSString("Arial"), 14);
			this.addChild(label1, 5);
			label1.setColor(cocos2dxMapping.ccBLACK);
			label1.setOpacity(200);
			label1.setPosition(CGGeometry.CGPointMake(15, start_y - count
					* step - 2.0f));
			CCLabelTTF label2 = CCLabelTTF.labelWithString(player,
					CGGeometry.CGSizeMake(240, 40),
					CCTextAlignment.CCTextAlignmentLeft, new NSString("Arial"),
					16);
			this.addChild(label2, 5);
			label2.setColor(cocos2dxMapping.ccBLACK);
			label2.setPosition(CGGeometry.CGPointMake(160, start_y - count
					* step));
			CCLabelTTF label3 = CCLabelTTF.labelWithString(
					NSString.stringWithFormat(new NSString("%d"), score),
					CGGeometry.CGSizeMake(290, 40),
					CCTextAlignment.CCTextAlignmentRight,
					new NSString("Arial"), 16);
			this.addChild(label3, 5);
			label3.setColor(cocos2dxMapping.ccBLACK);
			label3.setOpacity(200);
			label3.setPosition(CGGeometry.CGPointMake(160, start_y - count
					* step));
			count++;
			if (count == 10) {
				break;
			}
		}
		return this;
	}

	/**
	 * Method : dealloc <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void dealloc() {
		highscores.release();
		super.dealloc();
	}

	/**
	 * Method : loadCurrentPlayer <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void loadCurrentPlayer() {
		NSUserDefaults defaults = NSUserDefaults.standardUserDefaults();
		currentPlayer = null;
		currentPlayer = new NSString(
				(String) defaults.objectForKey(new NSString("player")));
		if (!com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(currentPlayer)) {
			currentPlayer = new NSString("anonymous");
		}
	}

	/**
	 * Method : loadHighscores <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void loadHighscores() {
		NSUserDefaults defaults = NSUserDefaults.standardUserDefaults();
		highscores = null;
		highscores = new NSMutableArray();
		List<Object> list = new ArrayList<Object>();
		list.add(defaults.objectForKey(new NSString("highscores")));
		highscores.initWithArray(new NSArray<>(list));
		if (highscores.count() == 0) {
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(1000000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(750000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(500000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(250000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(100000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(50000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(20000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(10000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(5000), null));
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(1000), null));
		}
	}

	/**
	 * Method : updateHighscores <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void updateHighscores() {
		currentScorePosition = -1;
		int count = 0;
		for (Object highscoreInter : highscores) {
			NSArray highscore = (NSArray) highscoreInter;
			int score = 0;
			try {
				score = ((NSNumber) highscore.objectAtIndex(1)).intValue();
			} catch (Exception e) {
				Log.e("Highscores", e.getMessage());
			}
			if (currentScore >= score) {
				currentScorePosition = count;
				break;
			}
			count++;
		}
		if (currentScorePosition >= 0) {
			highscores.insertObjectAtIndex(
					NSArray.arrayWithObjects(currentPlayer,
							NSNumber.numberWithInt(currentScore), null),
					currentScorePosition);
			highscores.removeLastObject();
		}
	}

	/**
	 * Method : saveCurrentPlayer <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void saveCurrentPlayer() {
		NSUserDefaults defaults = NSUserDefaults.standardUserDefaults();
		defaults.setObjectForKey(currentPlayer, new NSString("player"));
	}

	/**
	 * Method : saveHighscores <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void saveHighscores() {
		NSUserDefaults defaults = NSUserDefaults.standardUserDefaults();
		defaults.setObjectForKey(highscores, new NSString("highscores"));
	}

	/**
	 * Method : button1Callback <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void button1Callback(CCMenuItem sender) {
		CCTransitionScene ts = CCTransitionFade.transitionWithDuration(0.5f,
				Game.scene(), cocos2dxMapping.ccWHITE);
		CCDirector.sharedDirector().replaceScene(ts);
	}

	/**
	 * Method : button2Callback <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void button2Callback(CCMenuItem sender) {
		changePlayerAlert = new UIAlertView();
		changePlayerAlert.setTitle(new NSString("Change Player"));
		changePlayerAlert.setMessage(new NSString(""));
		changePlayerAlert.setDelegate(this);
		changePlayerAlert.addButtonWithTitle(new NSString("Save"));
		changePlayerAlert.addButtonWithTitle(new NSString("Cancel"));
		changePlayerTextField = new UITextField();
		changePlayerTextField.initWithFrame(CGGeometry.CGRectMake(20, 45, 245,
				27));
		changePlayerTextField
				.setBorderStyle(UITextField.UITextBorderStyle.UITextBorderStyleRoundedRect);
		changePlayerAlert.addSubview(changePlayerTextField);
		changePlayerTextField.keyboardType = UITextInputTraits.UIKeyboardType.UIKeyboardTypeDefault;
		changePlayerTextField.returnKeyType = UITextInputTraits.UIReturnKeyType.UIReturnKeyDone;
		changePlayerTextField.autocorrectionType = UITextInputTraits.UITextAutocorrectionType.UITextAutocorrectionTypeNo;
		changePlayerTextField.autocapitalizationType = UITextInputTraits.UITextAutocapitalizationType.UITextAutocapitalizationTypeNone;
		changePlayerTextField.setDelegate(this);
		changePlayerTextField.becomeFirstResponder();
		changePlayerAlert.show();
	}

	/**
	 * Method : draw <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void draw() {
		super.draw();
		if (currentScorePosition < 0) {
			return;
		}
		glColor4f(0.0f, 0.0f, 0.0f, 0.2f);
		float w = 320.0f;
		float h = 27.0f;
		float x = (320.0f - w) / 2.0f;
		float y = 359.0f - currentScorePosition * h;
		float[][] vertices = new float[4][2];
		int[] indices = { 0, 1, 3, 2 };
		vertices[0][0] = x;
		vertices[0][1] = y;
		vertices[1][0] = x + w;
		vertices[1][1] = y;
		vertices[2][0] = x + w;
		vertices[2][1] = y + h;
		vertices[3][0] = x;
		vertices[3][1] = y + h;
		FloatBuffer floatBuffer = FloatBuffer.allocate(vertices.length
				* vertices[0].length);
		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices[0].length; j++) {
				floatBuffer.put(vertices[i][j]);
			}
		}

		IntBuffer byteBuffer = IntBuffer.allocate(indices.length);
		for (int i = 0; i < indices.length; i++) {
			byteBuffer.put(indices[i]);
		}

		glDisable(3553);
		glDisableClientState(32888);
		glDisableClientState(32886);
		glVertexPointer(2, 5126, 0, vertices);
		glDrawElements(5, 4, 5121, indices);
		glEnableClientState(32886);
		glEnableClientState(32888);
		glEnable(3553);
		glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	/**
	 * Method : changePlayerDone <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void changePlayerDone() {
		currentPlayer = changePlayerTextField.getText().retain();
		this.saveCurrentPlayer();
		if (currentScorePosition >= 0) {
			highscores.removeObjectAtIndex(currentScorePosition);
			highscores.addObject(NSArray.arrayWithObjects(new NSString(
					"tweejump"), NSNumber.numberWithInt(0), null));
			this.saveHighscores();
			CCDirector.sharedDirector().replaceScene(
					CCTransitionFade.transitionWithDuration(1,
							Highscores.sceneWithScore(currentScore),
							cocos2dxMapping.ccWHITE));
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
			this.changePlayerDone();
		} else {
		}
	}

	/**
	 * Method : textFieldShouldReturn <!-- begin-user-doc -->
	 * 
	 * @return boolean.
	 * @generated
	 */
	public boolean textFieldShouldReturn(UITextField textField) {
		changePlayerAlert.dismissWithClickedButtonIndexAnimated(0, true);
		this.changePlayerDone();
		return true;
	}

	@Override
	public boolean textFieldShouldBeginEditing(UITextField pTextField) {
		return false;
	}

	@Override
	public void textFieldDidBeginEditing(UITextField pTextField) {

	}

	@Override
	public boolean textFieldShouldEndEditing(UITextField pTextField) {
		return false;
	}

	@Override
	public void textFieldDidEndEditing(UITextField pTextField) {

	}

	@Override
	public boolean textFieldShouldChangeCharactersInRangeReplacementString(
			UITextField pTextField, NSRange pRange, NSString pString) {
		return false;
	}

	@Override
	public boolean textFieldShouldClear(UITextField pTextField) {
		return false;
	}

}
