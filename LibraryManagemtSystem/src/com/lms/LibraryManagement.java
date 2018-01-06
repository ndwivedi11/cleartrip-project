package com.lms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagement {

	static List<BookDetails> books = new ArrayList<BookDetails>();
	static List<UserDetails> users = new ArrayList<UserDetails>();
	static Map<Integer, ArrayList<BookIssueDetails>> hm = new HashMap<Integer, ArrayList<BookIssueDetails>>();

	public static void main(String[] args) throws Exception {
		addIssueDetails();
		listOfBooks();
		listOfUsers();
		System.out.println("Library Management System");
		System.out.println("Press 1 to add Book");
		System.out.println("Press 2 to add User");
		System.out.println("Press 3 to issue a book");
		System.out.println("Press 4 to return a book");
		System.out.println("Press 5 to search a book with title and author");
		System.out.println("Press 6 to search as user by user name");
		System.out.println("Press 7 to exit");
		Scanner c = new Scanner(System.in);
		int choice = c.nextInt();
		do {
			switch (choice) {
			case 1:
				addBook();
				break;
			case 2:
				addUser();
				break;
			case 3:
				lendBook();
				break;
			case 4:
				returnBook();
				break;
			case 5:
				searchBook();
				break;
			case 6:
				searchUser();
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Invalid input");
				break;
			}
			c = new Scanner(System.in);
			choice = c.nextInt();
		} while (choice > 0 && choice < 7);
	}

	/**
	 * This method adds a pre defined set of users into a list.
	 */
	private static void listOfUsers() {
		UserDetails ud1 = new UserDetails(1, "user1");
		UserDetails ud2 = new UserDetails(2, "user1");
		UserDetails ud3 = new UserDetails(3, "user1");

		users.add(ud1);
		users.add(ud2);
		users.add(ud3);

	}

	/**
	 * This method add a pre-defined set of books into a list
	 */
	private static void listOfBooks() {

		BookDetails bd1 = new BookDetails(1, "ABC", "Author1");
		BookDetails bd2 = new BookDetails(2, "XYZ", "Author2");
		BookDetails bd3 = new BookDetails(3, "DEF", "Author3");

		books.add(bd1);
		books.add(bd2);
		books.add(bd3);
	}

	/**
	 * This method is used to search a book by a provided tile and author
	 */
	private static void searchBook() {

		System.out.println("Enter Title and Author");
		boolean bookFound = false;
		Scanner c = new Scanner(System.in);
		String title = c.nextLine();
		Scanner c1 = new Scanner(System.in);
		String author = c.nextLine();

		if (title == null && author == null) {
			System.out.println("Please enter either title and author");
		}

		for (BookDetails bd : books) {
			if (bd.getBookName().equals(title) && bd.getAuthor().equals(author)) {
				System.out.println("Book found With Author and Title as" + " " + title + " " + author);
				bookFound = true;
				break;
			}

		}
		if (!bookFound)
			System.out.println("No Book Found with title and author as :" + " " + title + " " + author);

	}

	/**
	 * This menthod is used to search a user by provided user name.
	 */
	private static void searchUser() {

		System.out.println("Enter user name");
		boolean userFound = false;
		Scanner c = new Scanner(System.in);
		String userName = c.nextLine();

		if (userName == null) {
			System.out.println("Please enter user name");
		}

		for (UserDetails ud : users) {
			if (ud.getUserName().equals(userName)) {
				System.out.println("User found with name as " + " " + userName);
				userFound = true;
				break;
			}

		}
		if (!userFound)
			System.out.println("No user found with user name as :" + " " + userName);

	}

	/**
	 * This method captures the logic of returning a book.
	 */
	private static void returnBook() {
		System.out.println("Enter userId & bookId");
		Scanner c = new Scanner(System.in);
		int id = c.nextInt();
		int bookId = c.nextInt();
		List<BookIssueDetails> bd = hm.get(id);
		for (BookIssueDetails b : bd) {
			if (b.getBookNumber() != bookId) {
				System.out.println("Book is successfully returned");
			} else {
				System.out.println("Book is already returned");
			}
		}
	}

	/**
	 * This method adds a pre-defined set of books issues into a list.
	 *
	 */
	private static void addIssueDetails() {
		BookIssueDetails bd1 = new BookIssueDetails(1, "abc", 1, new Date("04/04/2015"));
		BookIssueDetails bd2 = new BookIssueDetails(2, "xyz", 1, new Date());
		BookIssueDetails bd3 = new BookIssueDetails(3, "mn", 1, new Date());
		BookIssueDetails bd4 = new BookIssueDetails(4, "u", 1, new Date());

		ArrayList<BookIssueDetails> list1 = new ArrayList<BookIssueDetails>();
		ArrayList<BookIssueDetails> list2 = new ArrayList<BookIssueDetails>();
		ArrayList<BookIssueDetails> list3 = new ArrayList<BookIssueDetails>();
		ArrayList<BookIssueDetails> list4 = new ArrayList<BookIssueDetails>();

		list1.add(bd1);
		list2.add(bd2);
		list3.add(bd3);
		list4.add(bd4);
		hm.put(100, list1);
		hm.put(101, list2);
		hm.put(103, list3);
		hm.put(104, list4);

	}

	/**
	 * This method has the logic of lending a book to a user. Max 2 books per user
	 * is allowed.
	 */
	private static void lendBook() {

		System.out.println("Enter userId, bookNumber, bookName, Issue Date in (dd-MMM-YYYY)");
		Date date = null;
		Scanner c = new Scanner(System.in);
		int userId = c.nextInt();
		Scanner c1 = new Scanner(System.in);
		int bookNumber = c1.nextInt();
		Scanner c2 = new Scanner(System.in);
		String bookName = c2.nextLine();
		Scanner c3 = new Scanner(System.in);
		String issueDate = c3.nextLine();

		System.out.println("Values Entered:" + userId + " " + bookNumber + " " + bookName + " " + issueDate);

		BookIssueDetails newIssuedBook = new BookIssueDetails();
		newIssuedBook.setName(bookName);
		newIssuedBook.setBookNumer(bookNumber);

		ArrayList<BookIssueDetails> l = new ArrayList<BookIssueDetails>();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		try {

			date = formatter.parse(issueDate);
			newIssuedBook.setIssueDate(date);

		} catch (ParseException e) {
			System.out.println("Please enter date in dd-MMM-yyyy format");
		}

		List<BookIssueDetails> list = hm.get(userId);
		if (list == null || list.isEmpty()) {
			System.out.println(
					"There are no books to be issued. Books are only available with id 100, 101, 102, 103 and 104");
		}else {
			for (BookIssueDetails b : list) {
				int value = b.getNoOfBookIssued();
				newIssuedBook.setNoOfBookIssued(++value);
				l.add(newIssuedBook);
				if (value > 2)
					System.out.println("You already issued max(2) books");
				else
					hm.put(userId, l);
				System.out.println("Book is successfully lent to user");
			}
		}
	}

	/**
	 * This method adds a book
	 * 
	 * @throws Exception
	 */
	private static void addBook() throws Exception {
		System.out.println("Enter Booknumber, name and author");

		int bookNumber = 0;
		String title = null;
		String author = null;

		try {
			Scanner c = new Scanner(System.in);
			bookNumber = c.nextInt();
			Scanner c1 = new Scanner(System.in);
			title = c1.nextLine();
			author = c1.nextLine();

			BookDetails book = new BookDetails(bookNumber, title, author);
			books.add(book);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Book Details Added:" + " " + bookNumber + " " + title + " " + author);

	}

	/**
	 * This method adds a user.
	 * 
	 * @throws Exception
	 */
	private static void addUser() {
		System.out.println("Enter userId, Username");
		int userId = 0;
		String userName = null;
		try {
			Scanner c = new Scanner(System.in);
			userId = c.nextInt();
			Scanner c1 = new Scanner(System.in);
			userName = c1.nextLine();

			UserDetails userDetails = new UserDetails(userId, userName);
			users.add(userDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("User Added:" + " " + userId + " " + userName);
	}

}
