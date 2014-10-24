
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

import com.myappconverter.java.foundations.NSMutableArray;
import com.myappconverter.java.foundations.NSString;
import com.myappconverter.java.uikit.UIAlertView;
import com.myappconverter.java.uikit.UITextField;
import com.myappconverter.java.uikit.protocols.UITextFieldDelegate;
import com.myappconverter.mobile.tweejump.classes.impl.Main;

public abstract class Highscores extends Main implements UITextFieldDelegate {

	/**
	 * The cached value of the '<em>currentPlayer</em>' property.
	 * 
	 * @see #getCurrentPlayer()
	 * @generated
	 * @ordered
	 */

	public NSString currentPlayer;

	/**
	 * The cached value of the '<em>currentScore</em>' property.
	 * 
	 * @see #getCurrentScore()
	 * @generated
	 * @ordered
	 */

	public int currentScore;

	/**
	 * The cached value of the '<em>currentScorePosition</em>' property.
	 * 
	 * @see #getCurrentScorePosition()
	 * @generated
	 * @ordered
	 */

	public int currentScorePosition;

	/**
	 * The cached value of the '<em>highscores</em>' property.
	 * 
	 * @see #getHighscores()
	 * @generated
	 * @ordered
	 */

	public NSMutableArray highscores;

	/**
	 * The cached value of the '<em>changePlayerAlert</em>' property.
	 * 
	 * @see #getChangePlayerAlert()
	 * @generated
	 * @ordered
	 */

	public UIAlertView changePlayerAlert;

	/**
	 * The cached value of the '<em>changePlayerTextField</em>' property.
	 * 
	 * @see #getChangePlayerTextField()
	 * @generated
	 * @ordered
	 */

	public UITextField changePlayerTextField;

}
