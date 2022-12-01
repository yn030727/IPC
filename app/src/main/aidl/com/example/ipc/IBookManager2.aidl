// IBookManager2.aidl
//尽管在一个包下，我们也要导入Book类，这就是AIDL的特殊之处
package com.example.ipc;
import com.example.ipc.Book1;
// Declare any non-default types here with import statements

interface IBookManager2 {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<Book> getBookList();
    void addBook(in Book book);
}