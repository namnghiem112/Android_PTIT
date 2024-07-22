package com.example.book_crud;

public class Book {
    private int img;
    private String masach, tensach;

    public Book() {
    }

    public Book(int img, String masach, String tensach) {
        this.img = img;
        this.masach = masach;
        this.tensach = tensach;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    @Override
    public String toString() {
        return "Book{" +
                "img=" + img +
                ", masach='" + masach + '\'' +
                ", tensach='" + tensach + '\'' +
                '}';
    }
}
