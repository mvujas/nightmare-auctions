package com.github.mvujas.nightmareauctionsbackend.presentationview;

public final class ItemPresentationView {

	public static class SummaryView 
		implements UserPresentationView.UsernameOnly, UserPresentationView.AvgGrade {}

	public static class FullView extends SummaryView {}

}
