package com.lms;

public class BookDetails {
	private int bookNumber;
	private String title;
	private String author;
	private int count;

	public BookDetails(int bookNumber,String name,String author)
	{
		this.bookNumber=bookNumber;
		this.title=name;
		this.author=author;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getBookName() {
		return title;
	}

	public void setBookName(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
