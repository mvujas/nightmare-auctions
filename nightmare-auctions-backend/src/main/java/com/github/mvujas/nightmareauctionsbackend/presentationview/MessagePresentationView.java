package com.github.mvujas.nightmareauctionsbackend.presentationview;

public class MessagePresentationView {

	public static interface Identifier {}
	public static interface Text {}
	public static interface Sender {}
	public static interface Receiver {}
	public static interface SendingTime {}
	
	private static interface UserIdentifyingInfo
		extends UserPresentationView.UsernameOnly {}
	
	public static interface MessagesBetweenUsersView 
		extends Sender, Receiver, Text, SendingTime, UserIdentifyingInfo {}
	
}
