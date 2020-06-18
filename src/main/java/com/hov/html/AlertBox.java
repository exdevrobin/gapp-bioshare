package com.hov.html;

public class AlertBox {
	public String getAlertBox(int boxType, String message, String redirectUrl) {
		/** boxType -> 1.success, 2.warning, 3-> fatal error*/
		
		return "<script>"
		 + "alert('" + message + "');"
		 + "window.location='" + redirectUrl + "';"
		 + "</script>";
	}
}
