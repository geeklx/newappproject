package com.haier.cellarette.baselibrary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


import com.haier.cellarette.baselibrary.R;

import java.util.LinkedList;

/**
 * 上下半圆的SeekBar
 */
public class CircularSeekBar extends View {
	private boolean lock = false;
	/** The context */
	private Context mContext;

	Handler mHander = new Handler();

	/** The listener to listen for changes */
	private OnSeekChangeListener mListener;

	/** The color of the progress ring */
	private Paint circleColor;

	/** The progress circle ring background */
	private Paint circleRing;

	/** The angle of progress */
	private int angle = 0;

	/** The start angle (12 O'clock */
	private int startAngle = 270;

	/** The width of the progress ring */
	private int barsWidth = 2;

	/** The width of the progress ring */
	private int barWidth = 15;

	/** The width of the view */
	private int width;

	/** The height of the view */
	private int height;

	/** The maximum progress amount */
	private int maxProgress = 15;

	/** The current progress */
	private int progress;

	/** The progress percent */
	private int progressPercent;

	/** The radius of the outer circle */
	private float outerRadius;

	/** The circle's center X coordinate */
	private float cx;

	/** The circle's center Y coordinate */
	private float cy;

	/** The left bound for the circle RectF */
	private float left;

	/** The right bound for the circle RectF */
	private float right;

	/** The top bound for the circle RectF */
	private float top;

	/** The bottom bound for the circle RectF */
	private float bottom;

	/**
	 * The adjustment factor. This adds an adjustment of the specified size to
	 * both sides of the progress bar, allowing touch events to be processed
	 * more user friendlily (yes, I know that's not a word)
	 */
	private float adjustmentFactor = 3;

	/** The progress mark when the view isn't being progress modified */
	private Bitmap progressMark;

	/** The progress mark when the view is being progress modified. */
	private Bitmap progressMarkPressed;

	/**
	 * 防止画图不全
	 */
	private int padding;

	/**
	 * 是否是可编辑的
	 */
	private boolean editable = false;

	/**
	 * 停止操作的时间后，改为不可编辑状态
	 */
	// private final long WAIT_SHOW_TIME = 1000 * 3;

	/**
	 * 刷新室内温度需要等待的时间
	 */
	private final long WAIT_REFRESH_TEMPERATURE_HOME_TIME = 0;

	/**
	 * 状态改为编辑后，是否有操作
	 */
	private boolean haveOption = false;

	/**
	 * true表示：如果不在编辑模式下时，触摸SeekBar进入编辑模式
	 */
	private boolean touchToEdit = false;

	private LinkedList<Runnable> runningThread = new LinkedList<Runnable>();

	/** The flag to see if view is pressed */
	private boolean IS_PRESSED = false;

	/**
	 * The flag to see if the setProgress() method was called from our own
	 * View's setAngle() method, or externally by a user.
	 */
	private boolean CALLED_FROM_ANGLE = false;

	private boolean isTouchBottom;

	/**
	 * 拖动前的值
	 */
	private int preValue;

	private Paint color;

	Typeface typeFace;
	/**
	 * 移动后返回值
	 */
	private String valueOf;

	/** The rectangle containing our circles and arcs. */
	private RectF rect = new RectF();

	{

		circleColor = new Paint();
		circleRing = new Paint();

		color = new Paint();

		circleColor.setColor(getResources().getColor(R.color.red)); // Set
																							// default
		// progress
		// color to holo
		// blue.
		circleRing.setColor(getResources().getColor(R.color.gray_dark));// Set
		// default
		// background
		// color
		// to
		// Gray
		color.setColor(Color.WHITE); // Set default

		circleColor.setAntiAlias(true);
		circleRing.setAntiAlias(true);

		circleColor.setStrokeWidth(barWidth);
		circleRing.setStrokeWidth(barsWidth);

		circleRing.setStyle(Style.STROKE);
		circleColor.setStyle(Style.STROKE);
	}

	/**
	 * Instantiates a new circular seek bar.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 * @param defStyle
	 *            the def style
	 */
	public CircularSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initDrawable();
	}

	/**
	 * Instantiates a new circular seek bar.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 */
	public CircularSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initDrawable();
	}

	/**
	 * Instantiates a new circular seek bar.
	 * 
	 * @param context
	 *            the context
	 */
	public CircularSeekBar(Context context) {
		super(context);
		mContext = context;
		initDrawable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		width = getMeasuredWidth(); // Get View Width
		height = getMeasuredHeight();// Get View Height

		int size = (width > height) ? height : width; // Choose the smaller
		// between width and
		// height to make a
		// square

		cx = width / 2; // Center X for circle
		cy = height / 2; // Center Y for circle
		outerRadius = size / 2 - padding; // Radius of the outer circle

		left = cx - outerRadius; // Calculate left bound of our rect
		right = cx + outerRadius;// Calculate right bound of our rect
		top = cy - outerRadius;// Calculate top bound of our rect
		bottom = cy + outerRadius;// Calculate bottom bound of our rect

		rect.set(left, top, right, bottom); // assign size to rect
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(cx, cy, outerRadius, circleRing);
		if (angle >= 270 && angle <= 360) {
			startAngle = 270;
			canvas.drawArc(rect, startAngle, angle - 270, false, circleColor);
		} else {
			startAngle = 0;
			canvas.drawArc(rect, 270, 90, false, circleColor);
			canvas.drawArc(rect, startAngle, angle, false, circleColor);
		}

		if (editable) {
			drawMarkerAtProgress(canvas);
		}

		super.onDraw(canvas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (editable) {
			getParent().requestDisallowInterceptTouchEvent(true);
			float x = event.getX();
			float y = event.getY();
			boolean up = false;
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lock = false;
				preValue = progress;
				moved(x, y, up);
				isTouchBottom = (y + dip2px(mContext, 20)) > cy;
				break;
			case MotionEvent.ACTION_MOVE:
				moved(x, y, up);
				break;
			case MotionEvent.ACTION_UP:
				up = true;
				moved(x, y, up);
				// startUnEditThread();
				if (preValue != progress) {
					if (mListener != null) {
						mListener.onSetValues(progress);
					}
				}
				break;
			}
			return true;
		} else {
			if (touchToEdit && event.getAction() == MotionEvent.ACTION_UP) {
				edit();
			}
			return super.onTouchEvent(event);
		}
	}

	/**
	 * Inits the drawable.
	 */
	public void initDrawable() {
		progressMark = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tuoyuan);
		progressMarkPressed = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tuoyuan);
		padding = (int) ((1.0 * Math.max(progressMark.getWidth(), progressMark.getHeight())) / 2);
		typeFace = Typeface.createFromAsset(mContext.getAssets(), "DINCond-Regular.ttf");
	}

	/**
	 * Draw marker at the current progress point onto the given canvas.
	 * 
	 * @param canvas
	 *            the canvas
	 */
	public void drawMarkerAtProgress(Canvas canvas) {
		int tw = (progressMark.getWidth() / 2);
		int th = (progressMark.getHeight() / 2);
		float dip2px = dip2px(mContext, 30);
		color.setTextSize(dip2px);
		color.setTypeface(typeFace);
		double ta = angle * Math.PI / 180;
		if (IS_PRESSED) {
			canvas.drawBitmap(progressMarkPressed, (float) (cx + outerRadius * Math.cos(ta)) - tw,
					(float) (cy + outerRadius * Math.sin(ta)) - th, null);
			 valueOf = String.valueOf(progress + minValue);
			if (valueOf.length() < 2) {
				canvas.drawText("0" + valueOf, (float) (cx + tw - dip2px(mContext, 15) + outerRadius * Math.cos(ta))
						- tw, (float) (cy + th + dip2px(mContext, 10) + outerRadius * Math.sin(ta)) - th, color);
			} else {
				canvas.drawText(valueOf, (float) (cx + tw - dip2px(mContext, 15) + outerRadius * Math.cos(ta)) - tw,
						(float) (cy + th + dip2px(mContext, 10) + outerRadius * Math.sin(ta)) - th, color);
			}

		} else {
			canvas.drawBitmap(progressMark, (float) (cx + outerRadius * Math.cos(ta)) - tw, (float) (cy + outerRadius
					* Math.sin(ta))
					- th, null);
			String valueOf = String.valueOf(progress + minValue);
			if (valueOf.length() < 2) {
				canvas.drawText("0" + valueOf, (float) (cx + tw - dip2px(mContext, 15) + outerRadius * Math.cos(ta))
						- tw, (float) (cy + th + dip2px(mContext, 10) + outerRadius * Math.sin(ta)) - th, color);
			} else {
				canvas.drawText(valueOf, (float) (cx + tw - dip2px(mContext, 15) + outerRadius * Math.cos(ta)) - tw,
						(float) (cy + th + dip2px(mContext, 10) + outerRadius * Math.sin(ta)) - th, color);
			}
		}
	}

	/**
	 * Get the angle.
	 * 
	 * @return the angle
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * 编辑，设置为可拖�?
	 */
	public void edit() {
		// HomeActivity.currentTouchMode =
		// BaseActivity.TOUCH_MODE_EDIT_TEMPERATURE;
		editable = true;
		// startUnEditThread();
		invalidate();
	}

	/**
	 * 设置为不可编辑，不可拖动
	 */
	public void unEdit() {
		// HomeActivity.currentTouchMode = BaseActivity.TOUCH_MODE_NO_TOUCH;
		editable = false;
		invalidate();
		startRefreshTemperatureOfRoomThread();
	}

	/**
	 * @return ture表示正在执行编辑操作，可以拖动进�?
	 */
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	// /**
	// * �?��隐藏线程
	// */
	// private void startUnEditThread() {
	// haveOption = false;
	// synchronized (runningThread) {
	// if (runningThread.size() > 0) {
	// Runnable tempthread = runningThread.remove();
	// mHander.removeCallbacks(tempthread);
	// }
	// }
	// Thread thread = new Thread() {
	// public void run() {
	// synchronized (runningThread) {
	// if (!haveOption) {
	// unEdit();
	// }
	// runningThread.remove(this);
	// }
	// }
	// };
	//
	// runningThread.add(thread);
	// mHander.postDelayed(thread, WAIT_SHOW_TIME);
	// }

	/**
	 * �?��刷新室内温度线程线程
	 */
	private void startRefreshTemperatureOfRoomThread() {
		haveOption = false;
		synchronized (runningThread) {
			if (runningThread.size() > 0) {
				Runnable tempthread = runningThread.remove();
				mHander.removeCallbacks(tempthread);
			}
		}
		Thread thread = new Thread() {
			public void run() {
				synchronized (runningThread) {
					if (mListener != null) {
						mListener.onRefreshTemperatureOfRoom();
					}
					runningThread.remove(this);
				}
			}
		};

		runningThread.add(thread);
		mHander.postDelayed(thread, WAIT_REFRESH_TEMPERATURE_HOME_TIME);
	}

	/**
	 * 获取传过来的最大值
	 */
	int maxValue;
	int minValue;
	/**
	 * 设置摄氏度值，和温度的最低温度和最高温度
	 * 
	 * @param value
	 * @param max
	 * @param min
	 */
	public void setTemperatureValue(int value, int max, int min) {
		minValue = min;
		maxValue = max;
		maxProgress = max - min;
		int a = 270 / (max - min) * (value - min);
		if (a <= 90) {
			setAngle(270 + a);
		} else {
			setAngle(a - 90);
		}

	}

	/**
	 * Set the angle.
	 * 
	 * @param angle
	 *            the new angle
	 */
	public void setAngle(int angle) {
		this.angle = Math.min(angle, 180);
		if (angle >= 270 && angle <= 360) {
			this.angle = angle;
		}
		if (angle >= 225 && angle < 270) {
			this.angle = 270;
		}

		float donePercent = 0;
		if (this.angle >= 270 && this.angle <= 360) {
			donePercent = (((float) this.angle - 270) / 270) * 100;
		} else {
			donePercent = (((float) this.angle + 90) / 270) * 100;
		}
		// float donePercent = (((float) this.angle) / 270) * 100;
		float progress = (donePercent / 100) * getMaxProgress();
		setProgressPercent(Math.round(donePercent));
		CALLED_FROM_ANGLE = true;
		setProgress(Math.round(progress));
	}

	/**
	 * Sets the seek bar change listener.
	 * 
	 * @param listener
	 *            the new seek bar change listener
	 */
	public void setSeekBarChangeListener(OnSeekChangeListener listener) {
		mListener = listener;
	}

	/**
	 * Gets the seek bar change listener.
	 * 
	 * @return the seek bar change listener
	 */
	public OnSeekChangeListener getSeekBarChangeListener() {
		return mListener;
	}

	/**
	 * Gets the bar width.
	 * 
	 * @return the bar width
	 */
	public int getBarWidth() {
		return barWidth;
	}

	/**
	 * Sets the bar width.
	 * 
	 * @param barWidth
	 *            the new bar width
	 */
	public void setBarWidth(int barWidth) {
		this.barWidth = barWidth;
	}

	/**
	 * Gets the max progress.
	 * 
	 * @return the max progress
	 */
	public int getMaxProgress() {
		return maxProgress;
	}

	/**
	 * Sets the max progress.
	 * 
	 * @param maxProgress
	 *            the new max progress
	 */
	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	/**
	 * Gets the progress.
	 * 
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * Sets the progress.
	 * 
	 * @param progress
	 *            the new progress
	 */
	public void setProgress(int progress) {
		haveOption = true;
		if (this.progress != progress) {
			this.progress = progress;
			if (!CALLED_FROM_ANGLE) {
				double newPercent = (this.progress * 1.0 / this.maxProgress) * 100;
				double newAngle = (newPercent / 100) * 270;
				if (this.angle < 0) {
					this.setAngle(-(int) newAngle);
				} else {
					this.setAngle((int) newAngle);
				}
				this.setProgressPercent((int) newPercent);
			}
			if (mListener != null) {
				mListener.onProgressChange(this, Math.abs(this.getProgress()));
			}
			CALLED_FROM_ANGLE = false;
		}
		invalidate();
	}

	/**
	 * Gets the progress percent.
	 * 
	 * @return the progress percent
	 */
	public int getProgressPercent() {
		return progressPercent;
	}

	/**
	 * Sets the progress percent.
	 * 
	 * @param progressPercent
	 *            the new progress percent
	 */
	public void setProgressPercent(int progressPercent) {
		this.progressPercent = progressPercent;
	}

	/**
	 * Sets the ring background color.
	 * 
	 * @param color
	 *            the new ring background color
	 */
	public void setRingBackgroundColor(int color) {
		circleRing.setColor(color);
	}

	/**
	 * Sets the progress color.
	 * 
	 * @param color
	 *            the new progress color
	 */
	public void setProgressColor(int color) {
		circleColor.setColor(color);
	}

	public boolean isTouchToEdit() {
		return touchToEdit;
	}

	public void setTouchToEdit(boolean touchToEdit) {
		this.touchToEdit = touchToEdit;
	}

	private float oldDegrees;
	private float newDegrees;

	/**
	 * Moved.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param up
	 *            the up
	 */

	private void moved(float x, float y, boolean up) {
		// float distance = (float) Math.sqrt(Math.pow((x - cx), 2) +
		// Math.pow((y - cy), 2));

		// if (isTouchBottom && (Math.abs(getProgress()) > getMaxProgress() *
		// 1.0 / 2) && (y < cy)) {
		// return;
		// }
		//
		// if ((!isTouchBottom) && (Math.abs(getProgress()) > getMaxProgress() *
		// 1.0 / 2) && (y > cy)) {
		// return;
		// }
		// isTouchBottom = y > cy;
		if(lock){
			return;
		}
		
		isTouchBottom = true;
		if (/*
			 * distance < outerRadius + adjustmentFactor && distance >
			 * outerRadius - adjustmentFactor &&
			 *//* y > cy && */!up) {
			IS_PRESSED = true;

			// markPointX = x;
			// markPointY = y;

			float degrees = 0;

			oldDegrees = newDegrees;

			if (isTouchBottom) {
				degrees = (float) ((float) ((Math.toDegrees(Math.atan2(x - cx, cy - y)) + 270.0)) % 360.0);
			}/*
			 * else { // degrees = (float) ((float)
			 * ((Math.toDegrees(-Math.atan2(x - cx, cy - y)) + 90.0)) % 360.0);
			 * }
			 */
			// // and to make it count 0-360
			// if (degrees < 0) {
			// degrees += 2 * Math.PI;
			// }

			// if(degrees>=180&&degrees<270){
			// return;
			// }
			// if(degrees>=270&&degrees<=360){
			// degrees-=270;
			// }else{
			// degrees+=90;
			// }


			newDegrees = degrees;

			if (newDegrees > 180 && newDegrees < 270){
				lock = true;
				return;
			}
			if (maxValue == 20) {
				if (progress > 15) {
					if (newDegrees > oldDegrees) // 方向顺时针
						return;

					if (newDegrees < 160 || newDegrees > 180)
						return;
				}
			}
			if (maxValue == 18) {
				if (progress > 13) {
					if (newDegrees > oldDegrees) // 方向顺时针
						return;

					if (newDegrees < 160 || newDegrees > 180)
						return;
				}
			}

			// 在初始位置不可点击
			// if(progress == 0){
			// if(newDegrees < oldDegrees) // 方向逆时针
			// return;
			//
			// if( newDegrees < 270 || newDegrees > 290)
			// return;
			// }

			if (isTouchBottom) {
				setAngle(Math.round(degrees));
			} else {
				if (x > cx) {
					setProgress(0);
				} else {
					setProgress(maxProgress);
				}
			}

			/*
			 * else { setAngle(Math.round(-degrees)); }
			 */
			invalidate();

		} else {
			IS_PRESSED = false;
			CALLED_FROM_ANGLE = false;
			invalidate();
		}

	}

	/**
	 * Gets the adjustment factor.
	 * 
	 * @return the adjustment factor
	 */
	public float getAdjustmentFactor() {
		return adjustmentFactor;
	}

	/**
	 * Sets the adjustment factor.
	 * 
	 * @param adjustmentFactor
	 *            the new adjustment factor
	 */
	public void setAdjustmentFactor(float adjustmentFactor) {
		this.adjustmentFactor = adjustmentFactor;
	}

	/**
	 * The listener interface for receiving onSeekChange events. The class that
	 * is interested in processing a onSeekChange event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>setSeekBarChangeListener(OnSeekChangeListener)<code> method. When
	 * the onSeekChange event occurs, that object's appropriate
	 * method is invoked.
	 * 
//	 * @see OnSeekChangeEvent
	 */
	public interface OnSeekChangeListener {

		/**
		 * On progress change.
		 * 
		 * @param view
		 *            the view
		 * @param newProgress
		 *            the new progress
		 */
        void onProgressChange(CircularSeekBar view, int newProgress);

		/**
		 * 当拖动进度抬起事件触发时回调
		 * 
		 * @param progress
		 */
        void onSetValues(int progress);

		/**
		 * 当需要刷新显示室内温度时回调
		 */
        void onRefreshTemperatureOfRoom();
	}

	/**
	 * 根据手机的屏幕属性从 dip 的单位 转成为 px(像素)
	 * 
	 * @param context
	 * @param value
	 * @return
	 */
	public static float dip2px(Context context, float value) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return value * metrics.density;
	}
}
