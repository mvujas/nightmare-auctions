package com.github.mvujas.nightmareauctionsbackend.presentationview;

public class GradePresentationView {

	public static interface CompleteGrade {}
	public static interface GivenGrade {}
	public static interface ReceivedGrade {}
	
	public static interface Identifier {}
	public static interface ItemInfo {}
	
	public static interface UserInfo 
		extends UserPresentationView.UsernameOnly {}
	
	
	
	public static interface IncompletedGradesView 
		extends Identifier, ItemInfo, GivenGrade, UserInfo {};
		
	public static interface UserReceivedGradesView
		extends Identifier, ItemInfo, ReceivedGrade, CompleteGrade, UserInfo {}

}
