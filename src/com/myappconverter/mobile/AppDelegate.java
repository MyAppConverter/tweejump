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

package com.myappconverter.mobile;

import android.app.Application;

import com.cocos2dx.wrappers.CCDirector;
import com.myappconverter.java.uikit.UIApplication;
import com.myappconverter.java.uikit.UIWindow;
import com.myappconverter.mobile.tweejump.impl.RootViewController;

public class AppDelegate extends Application {

	/**
	 * The cached value of the '<em>window</em>' property.
	 * 
	 * @see #getWindow()
	 * @generated
	 * @ordered
	 */
	public UIWindow window;

	/**
	 * Returns the value of the '<em><b>window</b></em>' property.
	 * 
	 * @return UIWindow.
	 * @see #setWindow(UIWindow)
	 * @generated
	 */
	public UIWindow getWindow() {
		return this.window;
	}

	/**
	 * Sets the value of the '{@link <em>window</em>}' property.
	 * 
	 * @param UIWindow
	 *            the new value of the '<em>window</em>' property.
	 * @see #getWindow()
	 * @generated
	 */
	public void setWindow(UIWindow window) {
		this.window = window;
	}

	/**
	 * The cached value of the '<em>viewController</em>' property.
	 * 
	 * @see #getViewController()
	 * @generated
	 * @ordered
	 */

	public RootViewController viewController;

	@Override
	public void onTerminate() {
		super.onTerminate();
		CCDirector director = CCDirector.sharedDirector();
		viewController.release();
		window.release();
		director.end();

	}

	/**
	 * Method : removeStartupFlicker <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void removeStartupFlicker() {
	}

	/**
	 * Method : applicationWillResignActive <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applicationWillResignActive(UIApplication application) {
		CCDirector.sharedDirector().pause();
	}

	/**
	 * Method : applicationDidBecomeActive <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applicationDidBecomeActive(UIApplication application) {
		CCDirector.sharedDirector().resume();
	}

	/**
	 * Method : applicationDidReceiveMemoryWarning <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applicationDidReceiveMemoryWarning(UIApplication application) {
		CCDirector.sharedDirector().purgeCachedData();
	}

	/**
	 * Method : applicationDidEnterBackground <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applicationDidEnterBackground(UIApplication application) {
		CCDirector.sharedDirector().stopAnimation();
	}

	/**
	 * Method : applicationWillEnterForeground <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applicationWillEnterForeground(UIApplication application) {
		CCDirector.sharedDirector().startAnimation();
	}

	/**
	 * Method : applicationSignificantTimeChange <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applicationSignificantTimeChange(UIApplication application) {
		CCDirector.sharedDirector().setNextDeltaTimeZero(true);
	}

	/**
	 * Method : dealloc <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void dealloc() {
		CCDirector.sharedDirector().end();
		window.release();
	}

}
