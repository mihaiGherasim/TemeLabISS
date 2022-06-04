package model;

public class Book {
    int isbn;
    String titlu;
    String autor;
    int nrExemplare;

    public Book(String titlu, String autor, int nrExemplare) {
        this.isbn=0;
        this.titlu = titlu;
        this.autor = autor;
        this.nrExemplare = nrExemplare;
    }

    public Book(){

    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNrExemplare() {
        return nrExemplare;
    }

    public void setNrExemplare(int nrExemplare) {
        this.nrExemplare = nrExemplare;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", nrExemplare='" + nrExemplare + '\''+
                '}';
    }
}
