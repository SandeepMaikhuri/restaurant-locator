package com.sandeepmaikhuri.apps.restaurantlocator.utils;

import android.util.DisplayMetrics;

public class LayoutCustomization
{
	private DisplayMetrics oDisplayMetrics;
	private final int heightFromPSD = 1136;
	private final int widthFromPSD = 640;
	
	public LayoutCustomization(DisplayMetrics oDisplayMetrics)
	{
		this.oDisplayMetrics = oDisplayMetrics;
	}
	
	/**
	 * @param psd fonSize
	 * @return device compatible font size 
	 */
	public float getFontSize(float fontSize)
	{
		return (float) ((fontSize * oDisplayMetrics.xdpi * oDisplayMetrics.widthPixels) / 
				(oDisplayMetrics.xdpi * widthFromPSD * oDisplayMetrics.scaledDensity));
	}
	
	/**
	 * @param psd dimension for a Square/Normal View
	 * @return device compatible dimension for a Square/Normal View
	 */
	public int getSquareViewSize(int dimension)
	{
		return (int) ((dimension * oDisplayMetrics.xdpi * oDisplayMetrics.widthPixels) / 
				(oDisplayMetrics.xdpi * widthFromPSD));
	}
	
	/**
	 * @param psd height for a Rectangular View
	 * @return device compatible height dimension for a Rectangular View
	 */
	public int getRectViewHeightSize(int height)
	{
		return (int) ((height * oDisplayMetrics.ydpi * oDisplayMetrics.heightPixels) / 
				(oDisplayMetrics.ydpi * heightFromPSD));
	}

	/**
	 * @param psd width for a Rectangular View
	 * @return device compatible width dimension for a Rectangular View
	 */
	public int getRectViewWidthSize(int width)
	{
		return (int) ((width * oDisplayMetrics.xdpi * oDisplayMetrics.widthPixels) /
				(oDisplayMetrics.xdpi * widthFromPSD));
	}
	
	/**
	 * @param psd spacingMetrics for a View
	 * @return device compatible spacingMetrics for a View
	 */
	public int getSpacing(int spacingMetrics)
	{
		return (int) ((spacingMetrics * oDisplayMetrics.xdpi * oDisplayMetrics.widthPixels) / (oDisplayMetrics.xdpi * widthFromPSD));
	}
}
