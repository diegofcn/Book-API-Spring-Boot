package com.brandon.Books;


import com.brandon.Books.books.Book;
import com.brandon.Books.books.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping("api/v1/books")
public class BooksApplication {

	private final BookRepository bookRepository;

	public BooksApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
	@GetMapping
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	record NewBookRequest(String title, String author, Integer numberOfPages) {

	}
	@PostMapping
	public void addBook(@RequestBody NewBookRequest request) {
		Book book = new Book();
		book.setTitle(request.title());
		book.setAuthor(request.author());
		book.setNumberOfPages(request.numberOfPages());
		bookRepository.save(book);
	}
	@DeleteMapping("{bookId}")
	public void deleteBook(@PathVariable("bookId") Integer id) {
		bookRepository.deleteById(id);
	}

}
