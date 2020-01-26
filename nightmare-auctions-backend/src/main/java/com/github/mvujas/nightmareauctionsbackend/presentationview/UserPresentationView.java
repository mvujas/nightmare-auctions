package com.github.mvujas.nightmareauctionsbackend.presentationview;

public class UserPresentationView {

	public static interface TrueIdentifier {}
	public static interface UsernameOnly {}
	public static interface Email {}
	public static interface Roles {}
	public static interface Authorithies {}
	public static interface AvgGrade {}

	public static interface FullProfile 
		extends UsernameOnly, Email, Roles, AvgGrade {}
	
}
