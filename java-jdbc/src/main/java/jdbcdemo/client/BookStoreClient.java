package jdbcdemo.client;

import jdbcdemo.domain.Book;
import jdbcdemo.domain.Chapter;
import jdbcdemo.domain.Publisher;
import jdbcdemo.service.BookStoreService;

import java.util.ArrayList;
import java.util.List;

public class BookStoreClient {

	public static void main(String[] args) {

		BookStoreService bookStoreService = new BookStoreService();

		//persisting object graph
		Publisher publisher = new Publisher("MANN", "Manning Publications Co.");
		Book book = new Book("9781617290459", "Java Persistence with Hibernate, Second Edition", publisher);
		List<Chapter> chapters = new ArrayList<Chapter>();
		Chapter chapter1 = new Chapter("Introducing JPA and Hibernate", 1);
		chapters.add(chapter1);
		Chapter chapter2 = new Chapter("Domain Models and Metadata", 2);
		chapters.add(chapter2);
		book.setChapters(chapters);

		bookStoreService.persistObjectGraph(book);

		//retrieving object graph
		Book book2 = bookStoreService.retrieveObjectGraph("9781617290459");
		System.out.println(book2);
	}
}





