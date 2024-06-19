package org.example;
import java.util.*;

class Library {

    private ArrayList<Book> Available_books;
    private ArrayList<Book> Total_Books;
    private ArrayList<Member> Total_Members;
    private int bkid_initializer = 1;

    public int get_num_of_books() {
        return Available_books.size();
    }

    public int get_num_of_members() {
        return Total_Members.size();
    }

    public ArrayList<Book> get_TBooks() {
        return Total_Books;
    }

    public ArrayList<Book> get_Available_books() {
        return Available_books;
    }

    public ArrayList<Member> get_Total_Members() {
        return Total_Members;
    }

    public Library() {
        this.Available_books = new ArrayList<>();
        this.Total_Members = new ArrayList<>();
        this.Total_Books = new ArrayList<>();
    }

    public void add_a_book(String title2, int total_copies, String author) {
        for (int i = 0; i < total_copies; i++) {
            Book B = new Book(title2, total_copies, author);
            B.set_bookID(this.bkid_initializer);
            Available_books.add(B);
            Total_Books.add(B);
            this.bkid_initializer++;
        }
    }

    public void remove_a_book(int bookid) {
        for (int i = 0; i < Available_books.size(); i++) {
            if (Available_books.get(i).get_bookID() == bookid) {
                Available_books.remove(i);
                break;
            }
        }
        for (int i = 0; i < Total_Books.size(); i++) {
            if (Total_Books.get(i).get_bookID() == bookid) {
                Total_Books.remove(i);
                break;
            }
        }
    }

    public void add_a_member(String name, int age, String phone_no) {
        Member M = new Member(age, name, phone_no);
        Total_Members.add(M);
    }

    public void remove_a_member(String member_id) {
        for (int i = 0; i < Total_Members.size(); i++) {
            String ph = Total_Members.get(i).get_phone_no();

            if (ph.equals(member_id)) {
                if (Total_Members.get(i).get_books().size() != 0) {
                    System.out.println(
                            "First ask the user to return the book and pay the fine before removing the member.");
                    break;
                } else {
                    Total_Members.remove(i);
                    break;
                }
            }
        }
    }

    public void display_books() {
        for (int i = 0; i < Total_Books.size(); i++) {
            System.out.println(Total_Books.get(i).toString());
            System.out.println("-----------------------------");
        }
    }

    public void display_available_books() {
        for (int i = 0; i < Available_books.size(); i++) {
            System.out.println(Available_books.get(i).toString());
            System.out.println("-----------------------------");
        }
    }

    public void display_members() {
        for (int i = 0; i < Total_Members.size(); i++) {
            int fi = Total_Members.get(i).get_fine();
            ArrayList<Book> bk = Total_Members.get(i).get_books();
            for (int k = 0;k<bk.size();k++)
            {
                Book b = bk.get(k);
                b.set_end_time(System.currentTimeMillis());
                int gap = (int) ((b.get_end_time() - b.get_start_time()) / 1000);
                if (gap > 10) {
                    fi+=(gap-10)*3;
                }
            }
            System.out.println("Name: "+Total_Members.get(i).name);
            System.out.println("Age: "+Total_Members.get(i).get_age());
            System.out.println("Fine: "+fi);
            System.out.println("------------------------------");
            ArrayList<Book> bok_arr = Total_Members.get(i).get_books();
            for (int j = 0; j < bok_arr.size(); j++) {
                System.out.println(bok_arr.get(j).toString());
                System.out.println("-------------------");
            }
        }
    }

    public void exit_application() {
        System.out.println("Thanks for visiting!\n");
        System.exit(0);
    }
}

class Book {
    private int bookID;
    private String title;
    private String author;
    private int total_copies;
    private long start_time;
    private long end_time;

    public void set_bookID(int bkid) {
        this.bookID = bkid;
    }

    public void set_start_time(long x) {
        this.start_time = x;
    }

    public void set_end_time(long x) {
        this.end_time = x;
    }

    public long get_end_time() {
        return this.end_time;
    }

    public long get_start_time() {
        return this.start_time;
    }

    public int get_bookID() {
        return this.bookID;
    }

    public String toString() {
        String x = "";
        System.out.println(this.title);
        System.out.println(this.author);
        System.out.println(this.bookID);
        return x;
    }

    public Book(String title, int total_cpoies, String author) {
        this.title = title;
        this.total_copies = total_cpoies;
        this.author = author;
    }
}

class Member {
    public String name;
    private int age;
    private String phone_no;
    private int fine;
    private ArrayList<Book> books;

    public int get_age() {
        return this.age;
    }

    public String get_phone_no() {
        return this.phone_no;
    }

    public int get_member_books() {
        return books.size();
    }

    public ArrayList<Book> get_books() {
        return this.books;
    }

    public int get_fine() {
        return this.fine;
    }

    public Member(int age2, String name2, String phone_no2) {
        this.age = age2;
        this.name = name2;
        this.phone_no = phone_no2;
        books = new ArrayList<>();
    }

    public Member() {
        this.name = "dummy";
    }

    public void display_my_books() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString());
            System.out.println("-----------------------------");
        }
    }

    public int calculate_fine() {
        System.out.println("You had a total fine of Rs. " + this.fine + ". It has been paid successfully!");
        this.fine = 0;
        return fine;
    }

    public void issue_book(int bkid, Library l) {
        if (books.size() == 2) {
            System.out.println("You cannot issue more than 2 books at a time.");
        } else if (books.size() == 0) {
            if (this.fine == 0) {
                Book B;
                ArrayList<Book> b_arr = l.get_Available_books();
                int s = b_arr.size();
                for (int i = 0; i < s; i++) {
                    if (b_arr.get(i).get_bookID() == bkid) {
                        B = b_arr.get(i);
                        B.set_start_time(System.currentTimeMillis());
                        this.books.add(B);
                        l.get_Available_books().remove(i);
                        break;
                    }
                }
                System.out.println("Book with book ID " + bkid + " has been successfully issued");
            } else {
                System.out.println("First clear the fine before issueing the second book");
            }
        } else if (books.size() == 1) {
            Book b = books.get(0);
            b.set_end_time(System.currentTimeMillis());
            int gap = (int) ((b.get_end_time() - b.get_start_time()) / 1000);
            if (gap > 10) {
                this.fine = (gap - 10) * 3;
                System.out.println(" First return the First book with book id : " + b.get_bookID()
                        + " as the due date is expired and also pay the fine.");
            } else {
                Book B;
                ArrayList<Book> b_arr = l.get_Available_books();
                int s = b_arr.size();
                for (int i = 0; i < s; i++) {
                    if (b_arr.get(i).get_bookID() == bkid) {
                        B = b_arr.get(i);
                        B.set_start_time(System.currentTimeMillis());
                        this.books.add(B);
                        l.get_Available_books().remove(i);
                        break;
                    }
                }
            }
        }
    }

    public void return_book(int bkid, Library l) {
        Book b = new Book("1", 2, "dummy");
        ArrayList<Book> b_arr = l.get_Available_books();
        for (int i = 0; i < books.size(); i++) {
            if (this.books.get(i).get_bookID() == bkid) {
                b = this.books.get(i);
                b.set_end_time(System.currentTimeMillis());
                b_arr.add(b);
                books.remove(i);
            }
        }

        int gap = (int) ((b.get_end_time() - b.get_start_time()) / 1000);
        if (gap > 10) {
            this.fine = (gap - 10) * 3;
            System.out.println("Book ID: " + bkid + " successfully returned. " + this.fine
                    + " Rupees has been charged for a delay of " + (gap - 10) + " days. ");
        } else {
            System.out.println("Book ID: " + bkid + " successfully returned.");

            this.fine = 0;
        }
    }
}

public class Main {
    public static boolean is_valid_phone_number(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    public static boolean is_valid_name(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i)) && s.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Library portal initialized\n\n------------------------------");
        Scanner sc = new Scanner(System.in);
        Library l = new Library();

        while (true) {
            try {
                System.out.println(
                        "\n1.Enter as a Librarian\n2.Enter as a member\n3.Exit\n\n------------------------------\n");
                int a = sc.nextInt();
                sc.nextLine();
                if (a == 1) {
                    while (true) {
                        try {
                            System.out.println("\n------------------------------\n\n1. Register a member\n" +
                                    "2. Remove a member\n" +
                                    "3. Add a book\n" +
                                    "4. Remove a book\n" +
                                    "5. View all members along with their books and fines to be paid\n" +
                                    "6. View all books\n" +
                                    "7. Back\n");
                            int x = sc.nextInt();
                            sc.nextLine();
                            System.out.println("\n------------------------------\n\n");
                            if (x == 1) {
                                System.out.print("Name: ");
                                String nam = sc.nextLine();
                                if (!is_valid_name(nam)) {
                                    System.out.println("Name contains characters other than alphabets");
                                    break;
                                }
                                int age = 0;
                                try {
                                    System.out.print("Age: ");
                                    age = sc.nextInt();
                                    sc.nextLine();
                                } catch (java.util.InputMismatchException e) {
                                    System.out.println("Invalid Input. Please enter a valid integer.");
                                    sc.nextLine();
                                    break;
                                }
                                System.out.print("phone number: ");
                                String phone = sc.nextLine();
                                if (!phone.matches("\\d+")) {
                                    System.out.println("The string contains characters other than numbers.");
                                    break;
                                }
                                if (!is_valid_phone_number(phone)) {
                                    System.out.println("The size of the phone number should be 10.");
                                    break;
                                }
                                int f8 = 0;
                                ArrayList<Member> M = l.get_Total_Members();
                                for (int i = 0; i < l.get_num_of_members(); i++) {
                                    String ph = M.get(i).get_phone_no();
                                    if (ph.equals(phone)) {
                                        f8 = 1;
                                    }
                                }
                                if (f8 == 1) {
                                    System.out.println(
                                            "A person with this mobile number already exist. Please enter a valid mobile No.");
                                    break;

                                } else {
                                    l.add_a_member(nam, age, phone);
                                    System.out.println("\n\nMember successfully registered with Member Id: " + phone);
                                }
                            } else if (x == 2) {
                                System.out.println("Phone number: ");
                                String phone = sc.nextLine();
                                if (!phone.matches("\\d+")) {
                                    System.out.println("The string contains characters other than numbers.");
                                    sc.nextLine();
                                    break;
                                }
                                if (!is_valid_phone_number(phone)) {
                                    System.out.println("The size of the phone number should be 10.");
                                    break;
                                }
                                int f4 = 0;
                                ArrayList<Member> M = l.get_Total_Members();
                                for (int i = 0; i < l.get_num_of_members(); i++) {
                                    String ph = M.get(i).get_phone_no();
                                    if (ph.equals(phone)) {
                                        f4 = 1;
                                        break;
                                    }
                                }
                                if (f4 == 1) {
                                    l.remove_a_member(phone);
                                } else {
                                    System.out.println("Member does not exist! ");
                                }
                            } else if (x == 3) {
                                System.out.println("Book Title: ");
                                String nam = sc.nextLine();

                                System.out.println("Author: ");
                                String author = sc.nextLine();
                                if (!is_valid_name(author)) {
                                    System.out.println("Name contains characters other than alphabets");
                                    break;
                                }
                                int cpoies = 0;
                                try {
                                    System.out.println("copies: ");
                                    cpoies = sc.nextInt();
                                    sc.nextLine();
                                } catch (java.util.InputMismatchException e) {
                                    System.out.println("Invalid Input. Please enter a valid integer.");
                                    sc.nextLine();
                                    break;
                                }
                                l.add_a_book(nam, cpoies, author);
                                System.out.println("Book added Successfully");
                            } else if (x == 4) {
                                int bkid = 0;
                                try {
                                    System.out.println("Book id: ");
                                    bkid = sc.nextInt();
                                    sc.nextLine();
                                } catch (java.util.InputMismatchException e) {
                                    System.out.println("Invalid Input. Please enter a valid integer.");
                                    sc.nextLine();
                                    break;
                                }
                                int f5 = 0;
                                ArrayList<Book> b4 = l.get_Available_books();
                                for (int i = 0; i < l.get_num_of_books(); i++) {
                                    if (b4.get(i).get_bookID() == bkid) {
                                        f5 = 1;
                                    }
                                }
                                if (f5 == 0) {
                                    System.out.println("No such book exist with this book ID");
                                } else {
                                    l.remove_a_book(bkid);
                                }
                            } else if (x == 5) {
                                l.display_members();
                            } else if (x == 6) {
                                l.display_books();
                            } else if (x == 7) {
                                break;
                            } else {
                                System.out.println("invalid number");
                            }
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Invalid Input. Please enter a valid integer.");
                            sc.nextLine();
                        }
                    }
                } else if (a == 2) {
                    while (true) {

                        System.out.println("------------------------------\nEnter the name: ");
                        String name = sc.nextLine();
                        if (!is_valid_name(name)) {
                            System.out.println("Name contains characters other than alphabets");
                            break;
                        }
                        System.out.println("Enter the phone number: ");
                        String phone = sc.nextLine();
                        if (!phone.matches("\\d+")) {
                            System.out.println("The string contains characters other than numbers.");
                            break;
                        }
                        if (!is_valid_phone_number(phone)) {
                            System.out.println("The size of the phone number should be 10.");
                        }
                        Member M = new Member(a, name, phone);
                        int flag = 0;
                        ArrayList<Member> mem_array = l.get_Total_Members();
                        for (int i = 0; i < l.get_num_of_members(); i++) {
                            String phone2 = mem_array.get(i).get_phone_no();
                            String name2 = mem_array.get(i).name;
                            if (phone2.equals(phone) && name.equals(name2)) {
                                M = mem_array.get(i);
                                flag = 1;
                            }
                        }
                        int x = 0;
                        if (flag == 0) {
                            System.out.println(
                                    "Member with Name: " + name + " and Phone No: " + phone + " doesn't exist.");
                            break;
                        } else {
                            System.out.println("Welcome " + name + " member id: " + phone + "\n\n");
                        }
                        while (true) {
                            try {
                                System.out.println("1. List Available Books\n" +
                                        "2. List My Books\n" +
                                        "3. Issue book\n" +
                                        "4. Return book\n" +
                                        "5. Pay Fine\n" +
                                        "6. Back");
                                x = sc.nextInt();
                                sc.nextLine();
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Invalid Input. Please enter a valid integer.");
                                sc.nextLine();
                            }
                            if (x == 1) {
                                l.display_available_books();
                            } else if (x == 2) {
                                M.display_my_books();
                            } else if (x == 3) {
                                System.out.println("Enter the Book Id: ");
                                int id = sc.nextInt();
                                int f2 = 0, f12 = 0;
                                ArrayList<Book> bk = l.get_Available_books();
                                for (int i = 0; i < l.get_num_of_books(); i++) {
                                    if (bk.get(i).get_bookID() == id) {
                                        f2 = 1;
                                    }
                                }
                                ArrayList<Book> bk23 = l.get_TBooks();
                                for (int i = 0; i < bk23.size(); i++) {
                                    if (bk23.get(i).get_bookID() == id) {
                                        f12 = 1;
                                    }
                                }
                                if (f2 == 0 && f12 == 0) {
                                    System.out.println("No Book with such Bookid exist");
                                } else if (f12 == 1 && f2 == 0) {
                                    System.out.println("this book is issued by someone else.");
                                } else {
                                    M.issue_book(id, l);
                                }
                            } else if (x == 4) {
                                int id = 0;
                                try {
                                    System.out.println("Enter the book id: ");
                                    id = sc.nextInt();
                                } catch (java.util.InputMismatchException e) {
                                    System.out.println("Invalid Input. Please enter a valid integer.");
                                    sc.nextLine();
                                }
                                int f3 = 0;
                                ArrayList<Book> bk = M.get_books();
                                for (int i = 0; i < M.get_member_books(); i++) {
                                    if (bk.get(i).get_bookID() == id) {
                                        f3 = 1;
                                    }
                                }
                                if (f3 == 0) {
                                    System.out.println("No Book with such Bookid exist with you. ");
                                } else {
                                    M.return_book(id, l);
                                }
                            } else if (x == 5) {
                                M.calculate_fine();
                            } else if (x == 6) {
                                break;
                            } else {
                                System.out.println("invalid input");
                            }
                            System.out.println("-------------------");
                        }
                        if (x == 6) {
                            break;
                        }
                    }
                } else if (a == 3) {
                    System.out.println("Thank You!");
                    System.exit(1);
                } else {
                    System.out.println("Invalid Input");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("\nInvalid Input. Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }
}
