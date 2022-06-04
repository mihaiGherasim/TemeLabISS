package model;

public class Loan {
    private Integer id;
    private int isbn;
    private int noBooks;
    private String name;
    private String email;
    private int dayLoan;
    private int monthLoan;
    private int yearLoan;

    public Loan(int isbn, int noBooks, String name, String email, int dayLoan, int monthLoan, int yearLoan) {
        this.id = 0;
        this.isbn = isbn;
        this.noBooks = noBooks;
        this.name = name;
        this.email = email;
        this.dayLoan = dayLoan;
        this.monthLoan = monthLoan;
        this.yearLoan = yearLoan;
    }

    public Loan(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getNoBooks() {
        return noBooks;
    }

    public void setNoBooks(int noBooks) {
        this.noBooks = noBooks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDayLoan() {
        return dayLoan;
    }

    public void setDayLoan(int dayLoan) {
        this.dayLoan = dayLoan;
    }

    public int getMonthLoan() {
        return monthLoan;
    }

    public void setMonthLoan(int monthLoan) {
        this.monthLoan = monthLoan;
    }

    public int getYearLoan() {
        return yearLoan;
    }

    public void setYearLoan(int yearLoan) {
        this.yearLoan = yearLoan;
    }

    //    public Loan(int isbn, int noBooks, String name, String email,
//                int dayLoan, int monthLoan, int yearLoan) {
//        this.isbn = isbn;
//        this.noBooks = noBooks;
//        this.name = name;
//        this.email = email;
//        this.dayLoan = dayLoan;
//        this.monthLoan = monthLoan;
//        this.yearLoan = yearLoan;
//    }
//
//    public Loan(){
//
//    }
//
//    public int getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(int isbn) {
//        this.isbn = isbn;
//    }
//
//    public int getNoBooks() {
//        return noBooks;
//    }
//
//    public void setNoBooks(int noBooks) {
//        this.noBooks = noBooks;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getDayLoan() {
//        return dayLoan;
//    }
//
//    public void setDayLoan(int dayLoan) {
//        this.dayLoan = dayLoan;
//    }
//
//    public int getMonthLoan() {
//        return monthLoan;
//    }
//
//    public void setMonthLoan(int monthLoan) {
//        this.monthLoan = monthLoan;
//    }
//
//    public int getYearLoan() {
//        return yearLoan;
//    }
//
//    public void setYearLoan(int yearLoan) {
//        this.yearLoan = yearLoan;
//    }
}
