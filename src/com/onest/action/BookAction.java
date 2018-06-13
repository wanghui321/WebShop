package com.onest.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onest.bean.Book;
import com.onest.bean.BookType;
import com.onest.bean.Page;
import com.onest.page.Paging;
import com.onest.service.BookService;

@Controller
public class BookAction {
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/selectAllBook")
	public String selectAllBook(Model model) {
		List<Book> list = bookService.selectAll();
		model.addAttribute("bookList",list);
		return "index";
	}
	
	@RequestMapping("/adminSelectAllBook")
	public String adminSelectAllBook(Model model,Integer pageS) {
		Long totalCount = bookService.getCount();
		Paging paging = new Paging();
		Page page = paging.checkByPage(totalCount, pageS);
		List<Book> bookList = bookService.selectByPage(page);
		model.addAttribute("page",page);
		model.addAttribute("bookList",bookList);
		return "frame_right";
	}
	
	@RequestMapping("/addBook")
	public String addBook(HttpServletRequest request,Model model,Book book,String typeName,@RequestParam MultipartFile file) {
		String rootPath = request.getServletContext().getRealPath("/");
		try {
			FileCopyUtils.copy(file.getBytes(),new File(rootPath + "/Images",file.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String realPath = "Images/" + file.getOriginalFilename();
		book.setImgUrl(realPath);
		boolean flag = bookService.addBook(book,typeName);
		if (flag) {
			return "redirect:/adminSelectAllBook.do";
		} else {
			model.addAttribute("errorMsg","添加失败");
			return "addBook";
		}
	}
	
	@RequestMapping("/updateSelectAllBook")
	public String updateSelectAllBook(Model model,Integer pageS) {
		Long totalCount = bookService.getCount();
		Paging paging = new Paging();
		Page page = paging.checkByPage(totalCount, pageS);
		List<Book> bookList = bookService.selectByPage(page);
		model.addAttribute("page",page);
		model.addAttribute("bookList",bookList);
		return "updateBook";
	}
	
	@RequestMapping("/updateBook")
	public String updateBook (Model model,Book book,String typeName) {
		boolean flag = bookService.updateBook(book,typeName);
		if (flag)
			return "redirect:/updateSelectAllBook.do";
		else {
			model.addAttribute("errorMsg","修改失败");
			return "alterBook";
		}
	}
	
	@RequestMapping("/deleteBook")
	public String deleteBook (Model model,Integer bookId) {
		boolean flag = bookService.deleteBook(bookId);
		if (flag)
			return "redirect:/updateSelectAllBook.do";
		else {
			model.addAttribute("errorMsg","删除失败");
			return "alterBook";
		}
	}
	
	@RequestMapping("/selectOneBook")
	public String selectOneBook(Model model,Integer bookId) {
		Book book = bookService.selectOneBook(bookId);
		model.addAttribute("book",book);
		return "selectOneBook";
	}
	
	@RequestMapping("/selectBookByType")
	public String selectByType(Model model,String typeName) {
		List<Book> bookList = bookService.selectByType(typeName);
		model.addAttribute("bookTypeList",bookList);
		return "index";
	}
	
	@RequestMapping("/selectByName")
	public String selectByName(Model model,String bookName) {
		List<Book> bookList = bookService.selectByName(bookName);
		if (bookList.size()>0)
			model.addAttribute("bookList",bookList);
		else
			model.addAttribute("errorMsg","没有找到该书相关信息");
		return "selectBookByName";
	}
	
	@RequestMapping("/selectByCheckNumber")
	public String selectByCheckNumber(Model model) {
		List<Book> bookList = bookService.selectByCheckNumber();
		model.addAttribute("bookList",bookList);
		return "index";
	}
	
	@RequestMapping("/getBookType")
	public String getBookType(Model model,Integer bookId) {
		List<BookType> bookTypeList = bookService.getBookType();
		Book book = bookService.selectOneBook(bookId);
		model.addAttribute("bookTypeList",bookTypeList);
		model.addAttribute("book",book);
		return "alterBook";
	}
	
	@RequestMapping("/bookType")
	public String bookType(Model model) {
		List<BookType> bookTypeList = bookService.getBookType();
		model.addAttribute("bookTypeList",bookTypeList);
		return "addBook";
	}
	
	@RequestMapping("/bookType1")
	public String bookType1(Model model) {
		List<BookType> bookTypeList = bookService.getBookType();
		model.addAttribute("bookTypeList",bookTypeList);
		return "bookType";
	}
	
	@RequestMapping("/addBookType")
	public String addBookType(BookType bookType) {
		bookService.addBookType(bookType);
		return "redirect:/bookType1.do";
	}
}
