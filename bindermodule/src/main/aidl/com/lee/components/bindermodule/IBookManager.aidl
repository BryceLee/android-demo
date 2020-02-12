package com.lee.components.bindermodule;
import com.lee.components.bindermodule.Book;
interface IBookManager{
List<Book> getBookList();
void addBook(in Book book);
}
