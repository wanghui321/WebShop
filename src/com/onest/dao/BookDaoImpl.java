
package com.onest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Book;
import com.onest.bean.BookType;
import com.onest.bean.Page;

@Repository
@Transactional
public class BookDaoImpl implements BookDao{
	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public List<Book> selectAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book");
		List<Book> list = query.list();
		return list;
	}

	@Override
	public boolean addBook(Book book,String typeName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(book);
		Query query = session.createQuery("update Book set typeId = (select typeId from BookType where typeName=?) where BookId=?");
		query.setString(0, typeName);
		query.setInteger(1, book.getBookId());
		int ref = query.executeUpdate();
		if (ref > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateBook(Book book, String typeName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"update Book set bookName=?,price=?,author=?,publisher=?,typeId="
				+ "(select typeId from BookType where typeName=?)where bookId=?");
		query.setString(0,book.getBookName());
		query.setInteger(1, book.getPrice());
		query.setString(2, book.getAuthor());
		query.setString(3, book.getPublisher());
		query.setString(4, typeName);
		query.setInteger(5, book.getBookId());
		int ref = query.executeUpdate();
		if(ref > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBook(Integer bookId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Book where bookId=?");
		query.setInteger(0, bookId);
		int ref = query.executeUpdate();
		if (ref > 0)
			return true;
		return false;
	}

	@Override
	public Book selectOneBook(Integer bookId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book where bookId=?");
		query.setInteger(0, bookId);
		Book book = (Book)query.uniqueResult();
		return book;
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Book");
		Long totalCount = (Long)query.uniqueResult();
		return totalCount;
	}

	@Override
	public List<Book> selectByPage(Page page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book");
		query.setFirstResult((page.getDpage()-1) * page.getPagecount());
		query.setMaxResults(page.getPagecount());
		List<Book> bookList = query.list();
		return bookList;
	}

	@Override
	public List<Book> selectByType(String typeName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book where "
				+ "bookType=(from BookType where typeName=?)");
		query.setString(0, typeName);
		List<Book> bookList = query.list();
		System.out.println(bookList.size());
		return bookList;
	}

	@Override
	public List<Book> selectByName(String bookName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book where bookName like'%" + bookName + "%'");
		List<Book> bookList = query.list();
		return bookList;
	}

	@Override
	public void updateCheckNumber(Book book) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Book set checkNumber=? where bookId=?");
		query.setInteger(0, book.getCheckNumber());
		query.setInteger(1, book.getBookId());
		query.executeUpdate();
	}

	@Override
	public List<Book> selectByCheckNumber() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book order by checkNumber desc");
		List<Book> bookList = query.list();
		return bookList;
	}

	@Override
	public List<BookType> getBookType() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BookType");
		List<BookType> bookTypeList = query.list();
		return bookTypeList;
	}

	@Override
	public void addBookType(BookType bookType) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(bookType);
	}

}
