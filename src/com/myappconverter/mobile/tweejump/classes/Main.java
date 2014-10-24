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

import com.cocos2dx.wrappers.CCLayer;

public abstract class Main extends CCLayer {
	// Enum constant kSpriteManager
	public static final int kSpriteManager = 0;
	// Enum constant kBird
	public static final int kBird = 1;
	// Enum constant kScoreLabel
	public static final int kScoreLabel = 2;
	// Enum constant kCloudsStartTag
	public static final int kCloudsStartTag = 100;
	// Enum constant kPlatformsStartTag
	public static final int kPlatformsStartTag = 200;
	// Enum constant kBonusStartTag
	public static final int kBonusStartTag = 300;

	// Enum constant kBonus5
	public static final int kBonus5 = 0;
	// Enum constant kBonus10
	public static final int kBonus10 = 1;
	// Enum constant kBonus50
	public static final int kBonus50 = 2;
	// Enum constant kBonus100
	public static final int kBonus100 = 3;
	// Enum constant kNumBonuses
	public static final int kNumBonuses = 4;

	/**
	 * The cached value of the '<em>currentCloudTag</em>' property.
	 * 
	 * @see #getCurrentCloudTag()
	 * @generated
	 * @ordered
	 */

	public int currentCloudTag;

	/**
	 * Method : resetClouds <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void resetClouds();

	/**
	 * Method : resetCloud <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void resetCloud();

	/**
	 * Method : step <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void step(float dt);
}
