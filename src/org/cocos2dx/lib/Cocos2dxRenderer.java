/****************************************************************************
Copyright (c) 2010-2011 cocos2d-x.org

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 ****************************************************************************/
package org.cocos2dx.lib;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCTouch;

public class Cocos2dxRenderer implements GLSurfaceView.Renderer {
	private final static long NANOSECONDSPERSECOND = 1000000000L;
	private final static long NANOSECONDSPERMINISECOND = 1000000;
	private static long animationInterval = (long) (1.0 / 60 * NANOSECONDSPERSECOND);
	private static CCTouch s_pTouches[] = new CCTouch[5];
	private long last;
	private int screenWidth;
	private int screenHeight;
	protected Cocos2dxGLSurfaceView cocos2dxGLSurfaceView;

	public Cocos2dxRenderer(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView_) {
		super();
		cocos2dxGLSurfaceView = cocos2dxGLSurfaceView_;
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		cocos2dxGLSurfaceView.cocos2dxInit(screenWidth, screenHeight);
		last = System.nanoTime();
		CCDirector.gl = gl;
	}

	public void setScreenWidthAndHeight(int w, int h) {
		this.screenWidth = w;
		this.screenHeight = h;
	}

	public void onSurfaceChanged(GL10 gl, int w, int h) {
	}

	public void onDrawFrame(GL10 gl) {

		long now = System.nanoTime();
		long interval = now - last;

		// should render a frame when onDrawFrame() is called
		// or there is a "ghost"
		CCDirector.sharedDirector().mainLoop();

		// fps controlling
		if (interval < animationInterval) {
			try {
				// because we render it before, so we should sleep twice time
				// interval
				Thread.sleep((animationInterval - interval) * 2
						/ NANOSECONDSPERMINISECOND);
			} catch (Exception e) {
			}
		}

		last = now;
	}

	public void handleActionDown(int id, float x, float y) {
		nativeTouchesBegin(id, x, y);

		// CGRect rcRect = CCEGLView::sharedOpenGLView().getViewPort();
		// float fScreenScaleFactor =
		// CCEGLView::sharedOpenGLView().getScreenScaleFactor();
		// CCSet set;
		//
		// CCTouch *pTouch = s_pTouches[id];
		// if (! pTouch)
		// {
		// LOGD("Beginning touches with id: %d, x=%f, y=%f", id, x, y);
		//
		// pTouch = new CCTouch();
		// pTouch->SetTouchInfo(0, (x - rcRect.origin.x) / fScreenScaleFactor,
		// (y - rcRect.origin.y) /
		// fScreenScaleFactor, id);
		// s_pTouches[id] = pTouch;
		// set.addObject(pTouch);
		//
		// cocos2d::CCDirector::sharedDirector()->getOpenGLView()->getDelegate()->touchesBegan(&set,
		// NULL);
		// }
		// else
		// {
		// LOGD("Beginnig touches with id: %d error", id);
		// }

	}

	public void handleActionUp(int id, float x, float y) {
		nativeTouchesEnd(id, x, y);
	}

	public void handleActionCancel(int[] id, float[] x, float[] y) {
		nativeTouchesCancel(id, x, y);
	}

	public void handleActionMove(int[] id, float[] x, float[] y) {
		nativeTouchesMove(id, x, y);
	}

	public void handleKeyDown(int keyCode) {
		nativeKeyDown(keyCode);
	}

	public void handleOnPause() {
		nativeOnPause();
	}

	public void handleOnResume() {
		nativeOnResume();
	}

	public static void setAnimationInterval(double interval) {
		animationInterval = (long) (interval * NANOSECONDSPERSECOND);
	}

	private static native void nativeTouchesBegin(int id, float x, float y);

	private static native void nativeTouchesEnd(int id, float x, float y);

	private static native void nativeTouchesMove(int[] id, float[] x, float[] y);

	private static native void nativeTouchesCancel(int[] id, float[] x,
			float[] y);

	private static native boolean nativeKeyDown(int keyCode);

	private static native void nativeRender();

	private static native void nativeOnPause();

	private static native void nativeOnResume();

	// ///////////////////////////////////////////////////////////////////////////////
	// handle input method edit message
	// ///////////////////////////////////////////////////////////////////////////////

	public void handleInsertText(final String text) {
		nativeInsertText(text);
	}

	public void handleDeleteBackward() {
		nativeDeleteBackward();
	}

	public String getContentText() {
		return nativeGetContentText();
	}

	private static native void nativeInsertText(String text);

	private static native void nativeDeleteBackward();

	private static native String nativeGetContentText();
}
