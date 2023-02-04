package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {
private List<Book> bookList;
private int id;

public List<Book> getBookList() {
	return bookList;
}
public void setBookList(List<Book> bookList) {
	this.bookList = bookList;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public BookController() {
	this.bookList=new ArrayList<Book>();
	this.id=1;
}

@PostMapping("/create-book")
public ResponseEntity<Book> createBook(@RequestBody Book book){
	book.setId(id++);
	bookList.add(book);
	return new ResponseEntity<>(book,HttpStatus.CREATED);
}
	
@GetMapping("/get-book-by-id/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable String id){
		for(Book book:bookList) {
			if(book.getId()==Integer.parseInt(id)) {
				return new ResponseEntity<>(book,HttpStatus.OK);
			}
	}
		return null;
}
 @GetMapping("/get-all-books")
 public ResponseEntity<List<Book>> getAllBooks(){
	 return new ResponseEntity<>(bookList,HttpStatus.OK);
 }
 
 @GetMapping("/get-book-by-author")
 public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam String author){
	 List<Book> authorbyBook = new ArrayList<>();
	 for(Book book:bookList) {
		 if(book.getAuthor().equals(author)) {
			 authorbyBook.add(book);
		 }
	 }
	 return new ResponseEntity<>(authorbyBook,HttpStatus.OK);
 }
 @GetMapping("/get-book-by-genre")
 public ResponseEntity<List<Book>> getBookByGenre(@RequestParam String genre){
	 List<Book> bookbyGenre = new ArrayList<>();
	 for(Book book:bookList) {
		 if(book.getGenre().equals(genre)) {
			 bookbyGenre.add(book);
		 }
	 }
	 return new ResponseEntity<>(bookbyGenre,HttpStatus.OK);
 }
 
 @DeleteMapping("/delete-book-by-id/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable String id){
	 for(Book book:bookList) {
		 if(book.getId()==Integer.parseInt(id)) {
			 bookList.remove(book);
			 return new ResponseEntity<>("Book with id = "+id+ "deleted",HttpStatus.OK);
		 }
	 }
	 return new ResponseEntity<>("Book Not Found ",HttpStatus.OK);
 }
 
 @DeleteMapping("/delete-book-by-genre")
 public ResponseEntity<String> deletBookByGenre(@RequestParam String genre){
	 //List<Book> bookbyGenre = new ArrayList<>();
	 for(Book book:bookList) {
		 if(book.getGenre().equals(genre)) {
			 bookList.remove(book);
			 return new ResponseEntity<>("book by genre" + genre + " deleted",HttpStatus.OK);
		 }
	 }
	 return new ResponseEntity<>("book not found",HttpStatus.OK);
 }
 @DeleteMapping("/delete-all-books")
 public ResponseEntity<String> deleteAllBooks(){
	 bookList.clear();
	 return new ResponseEntity<>("All Books Deleted ",HttpStatus.OK);
 }
 
 @PutMapping("/update-book-by-id/{id}")
 public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
     for (int i = 0; i < bookList.size(); i++) {
         if (bookList.get(i).getId() == id) {
             bookList.set(i, bookDetails);
             return new ResponseEntity<>("Book with id " + id + " updated successfully", HttpStatus.OK);
         }
     }
     return new ResponseEntity<>("Book with id " + id + " not found", HttpStatus.NOT_FOUND);
 }
}
