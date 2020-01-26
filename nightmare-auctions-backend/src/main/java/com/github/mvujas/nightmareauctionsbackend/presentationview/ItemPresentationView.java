package com.github.mvujas.nightmareauctionsbackend.presentationview;

public final class ItemPresentationView {

	public static interface Identifier {}
	public static interface Name {}
	public static interface StartingPrice {}
	public static interface PostingTime {}
	public static interface ClosingTime {}
	public static interface Author {}
	public static interface Category {}
	public static interface Bids {}
	public static interface Grade {}
	public static interface NumberOfBids {}
	public static interface Details {}
	public static interface Price {}
	public static interface Over {}
	
	private static interface UserInfo 
		extends UserPresentationView.UsernameOnly, UserPresentationView.AvgGrade {}
	
	private static interface CategoryInfo
		extends CategoryPresentationView.Name {}
	
	private static interface BidInfo 
		extends 
			BidPresentationView.Author, 
			BidPresentationView.Price, 
			BidPresentationView.PostingTime {}
	
	public static interface SummaryView 
		extends 
			UserInfo, 
			CategoryInfo,
			Identifier,
			Name,
			Price,
			NumberOfBids,
			PostingTime,
			Category,
			Author{}
	
	public static interface FullView 
		extends Bids, BidInfo, SummaryView, Over, Details {}

}
