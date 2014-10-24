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
 * @date : Sep, 19 2014 - 15:36:19
 */

package com.myappconverter.mobile;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCEGLView;
import com.cocos2dx.wrappers.CCTexture2D;
import com.cocos2dx.wrappers.CCTexture2DPixelFormat;
import com.cocos2dx.wrappers.ccDeviceOrientation;
import com.cocos2dx.wrappers.ccDirectorType;
import com.myappconverter.java.uikit.UIApplication;
import com.myappconverter.java.uikit.UIScreen;
import com.myappconverter.java.uikit.UIWindow;
import com.myappconverter.mapping.utils.GenericMainContext;
import com.myappconverter.mobile.tweejump.classes.impl.Game;
import com.myappconverter.mobile.tweejump.impl.RootViewController;

public class Cocos2dxMainActivity extends Cocos2dxActivity {
	public Cocos2dxGLSurfaceView mGLView;

	public static Cocos2dxGLSurfaceView mGLViewSt;
	/**
	 * The cached value of the '<em>window</em>' property.
	 * 
	 * @see #getWindow()
	 * @generated
	 * @ordered
	 */
	public UIWindow window;

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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GenericMainContext.MAIN_CONTEXT_ACTIVITY = this;
		String packageName = getApplication().getPackageName();
		super.setPackageName(packageName);
		mGLView = new Cocos2dxGLSurfaceView(this);
		mGLViewSt = mGLView;
		setContentView(mGLView);
		mGLView.setCocos2dxStarter(this);
	}

	@Override
	public void cocos2dxInit(int w, int h) {

		window = new UIWindow();
		window.initWithFrame(UIScreen.mainScreen().bounds());
		if (!com.myappconverter.java.foundations.ExpressNullable
				.assertCondition(CCDirector
						.setDirectorType(ccDirectorType.kCCDirectorTypeDisplayLink))) {
			CCDirector.setDirectorType(ccDirectorType.kCCDirectorTypeDefault);
		}
		CCDirector director = CCDirector.sharedDirector();
		viewController = new RootViewController();
		viewController.initWithNibNameBundle(null, null);
		viewController.wantsFullScreenLayout = true;
		CCEGLView glView = CCEGLView.sharedOpenGLView();
		glView.setFrameWidthAndHeight(w, h);
		glView.create(w, h);
		director.setOpenGLView(glView);
		director.setDeviceOrientation(ccDeviceOrientation.kCCDeviceOrientationPortrait);
		director.setAnimationInterval(1.0f / 60);
		viewController.setView(glView);
		window.addSubview(viewController.view());
		window.makeKeyAndVisible();
		CCTexture2D
				.setDefaultAlphaPixelFormat(CCTexture2DPixelFormat.kCCTexture2DPixelFormat_RGBA8888);
		this.removeStartupFlicker();
		CCDirector.sharedDirector().runWithScene(Game.scene());

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

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	public void finish() {
		CCDirector.sharedDirector().end();
		mGLView.postDelayed(new Runnable() {
			public void run() {
				Cocos2dxMainActivity.super.finish();
			}
		}, 100);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	static {
		System.loadLibrary("cocosdenshion");
		System.loadLibrary("game");
	}
}
