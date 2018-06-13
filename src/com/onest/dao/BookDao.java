package com.onest.dao;

import java.util.List;

import com.onest.bean.Book;
import com.onest.bean.BookType;
import com.onest.bean.Page;

public interface BookDao {
	public List<Book> selectAll();
	public boolean addBook(Book book,String typeName);
	public boolean updateBook(Book book,String typeName);
	public boolean deleteBook(Integer bookId);
	public Book selectOneBook(Integer bookId);
	public Long getCount();
	public List<Book> selectByPage(Page page);
	public List<Book> selectByType(String typeName);
	public List<Book> selectByName(String bookName);
	public void updateCheckNumber(Book book);
	public List<Book> selectByCheckNumber();
	public List<BookType> getBookType();
	public void addBookType(BookType bookType);
}
