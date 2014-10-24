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

package com.myappconverter.mobile.tweejump.classes;

import com.cocos2dx.wrappers.ccVertex2F;
import com.myappconverter.java.applicationservices.CGPoint;

public abstract class Game extends
		com.myappconverter.mobile.tweejump.classes.impl.Main {

	/**
	 * The cached value of the '<em>bird_pos</em>' property.
	 * 
	 * @see #getBird_pos()
	 * @generated
	 * @ordered
	 */

	public CGPoint bird_pos;

	/**
	 * The cached value of the '<em>bird_vel</em>' property.
	 * 
	 * @see #getBird_vel()
	 * @generated
	 * @ordered
	 */

	public ccVertex2F bird_vel;

	/**
	 * The cached value of the '<em>bird_acc</em>' property.
	 * 
	 * @see #getBird_acc()
	 * @generated
	 * @ordered
	 */

	public ccVertex2F bird_acc;

	/**
	 * The cached value of the '<em>currentPlatformY</em>' property.
	 * 
	 * @see #getCurrentPlatformY()
	 * @generated
	 * @ordered
	 */

	public float currentPlatformY;

	/**
	 * The cached value of the '<em>currentPlatformTag</em>' property.
	 * 
	 * @see #getCurrentPlatformTag()
	 * @generated
	 * @ordered
	 */

	public int currentPlatformTag;

	/**
	 * The cached value of the '<em>currentMaxPlatformStep</em>' property.
	 * 
	 * @see #getCurrentMaxPlatformStep()
	 * @generated
	 * @ordered
	 */

	public float currentMaxPlatformStep;

	/**
	 * The cached value of the '<em>currentBonusPlatformIndex</em>' property.
	 * 
	 * @see #getCurrentBonusPlatformIndex()
	 * @generated
	 * @ordered
	 */

	public int currentBonusPlatformIndex;

	/**
	 * The cached value of the '<em>currentBonusType</em>' property.
	 * 
	 * @see #getCurrentBonusType()
	 * @generated
	 * @ordered
	 */

	public int currentBonusType;

	/**
	 * The cached value of the '<em>platformCount</em>' property.
	 * 
	 * @see #getPlatformCount()
	 * @generated
	 * @ordered
	 */

	public int platformCount;

	/**
	 * The cached value of the '<em>gameSuspended</em>' property.
	 * 
	 * @see #getGameSuspended()
	 * @generated
	 * @ordered
	 */

	public boolean gameSuspended;

	/**
	 * The cached value of the '<em>birdLookingRight</em>' property.
	 * 
	 * @see #getBirdLookingRight()
	 * @generated
	 * @ordered
	 */

	public boolean birdLookingRight;

	/**
	 * The cached value of the '<em>score</em>' property.
	 * 
	 * @see #getScore()
	 * @generated
	 * @ordered
	 */

	public int score;

	/**
	 * Method : scene <!-- begin-user-doc -->
	 * 
	 * @return CCScene.
	 * @generated
	 */
}
