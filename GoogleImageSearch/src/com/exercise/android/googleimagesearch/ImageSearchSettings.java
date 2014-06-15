package com.exercise.android.googleimagesearch;

import java.io.Serializable;

public class ImageSearchSettings implements Serializable{

	private String imageSize;
	private String colorFilter;
	private String imageType;
	private String siteFilter;
	
	public String getImageSize() {
		return imageSize;
	}
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	public String getColorFilter() {
		return colorFilter;
	}
	public void setColorFilter(String colorFilter) {
		this.colorFilter = colorFilter;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getSiteFilter() {
		return siteFilter;
	}
	public void setSiteFilter(String siteFilter) {
		this.siteFilter = siteFilter;
	}
	
	public String toString(){
		return "Site filter " + this.siteFilter;
	}
	
}
