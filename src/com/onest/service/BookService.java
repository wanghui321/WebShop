package com.onest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Book;
import com.onest.bean.BookType;
import com.onest.bean.Page;
import com.onest.dao.BookDao;

@Service
@Transactional
public class BookService {
	@Autowired
	private BookDao bookDao;
	public List<Book> selectAll() {
		List<Book> list = bookDao.selectAll();
		return list;
 	}
	
	public boolean addBook(Book book,String typeName) {
		boolean flag = bookDao.addBook(book,typeName);
		return flag;
	}
	
	public boolean updateBook(Book book,String typeName) {
		boolean flag = bookDao.updateBook(book,typeName);
		return flag;
	}
	
	public boolean deleteBook(Integer bookId) {
		boolean flag = bookDao.deleteBook(bookId);
		return flag;
	}
	
	public Book selectOneBook(Integer bookId) {
		Book book = bookDao.selectOneBook(bookId);
		int checkNumber = book.getCheckNumber();
		book.setCheckNumber(checkNumber+1);
		bookDao.updateCheckNumber(book);
		return book;
	}

	public Long getCount() {
		Long totalCount = bookDao.getCount();
		return totalCount;
	}

	public List<Book> selectByPage(Page page) {
		List<Book> bookList = bookDao.selectByPage(page);
		return bookList;
	}

	public List<Book> selectByType(String typeName) {
		List<Book> bookList = bookDao.selectByType(typeName);
		return bookList;
	}

	public List<Book> selectByName(String bookName) {
		List<Book> bookList = bookDao.selectByName(bookName);
		for (Book book : bookList) {
			int checkNumber = book.getCheckNumber();
			book.setCheckNumber(checkNumber+1);
			bookDao.updateCheckNumber(book);
		}
		return bookList;
	}

	public List<Book> selectByCheckNumber() {
		List<Book> bookList = bookDao.selectByCheckNumber();
		return bookList;
	}

	public List<BookType> getBookType() {
		// TODO Auto-generated method stub
		List<BookType> bookTypeList = bookDao.getBookType();
		return bookTypeList;
	}

	public void addBookType(BookType bookType) {
		// TODO Auto-generated method stub
		bookDao.addBookType(bookType);
	}
}
