import java.io.*;
import java.util.*;

class Book 
{
    int id;
    String title;
    String author;

    Book(int id, String title, String author) 
	{
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String toString() 
	{
        return id + "," + title + "," + author;
    }
}

public class LibraryCLI 
{

    static final String FILE_NAME = "books.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) 
	{

        while (true) 
		{
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try 
			{
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addBook();
                        break;

                    case 2:
                        removeBook();
                        break;

                    case 3:
                        searchBook();
                        break;

                    case 4:
                        viewBooks();
                        break;

                    case 5:
                        System.out.println("Exiting... Thank you!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } 
			catch (NumberFormatException e) 
			{
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // ADD BOOK
    static void addBook() 
	{
        try 
		{
            System.out.print("Enter Book ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(id + "," + title + "," + author + "\n");
            fw.close();

            System.out.println("Book added successfully.");

        } 
		catch (Exception e) 
		{
            System.out.println("Error adding book.");
        }
    }

    // REMOVE BOOK
    static void removeBook() 
	{
        try 
		{
            System.out.print("Enter Book ID to remove: ");
            int id = Integer.parseInt(sc.nextLine());

            File file = new File(FILE_NAME);
            File temp = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) 
			{
                String[] data = line.split(",");
                if (Integer.parseInt(data[0]) != id) 
				{
                    bw.write(line + "\n");
                } 
				else 
				{
                    found = true;
                }
            }

            br.close();
            bw.close();

            file.delete();
            temp.renameTo(file);

            if (found)
                System.out.println("Book removed successfully.");
            else
                System.out.println("Book not found.");

        } 
		catch (Exception e) 
		{
            System.out.println("Error removing book.");
        }
    }

    // SEARCH BOOK
    static void searchBook() 
	{
        try 
		{
            System.out.print("Enter Book ID to search: ");
            int id = Integer.parseInt(sc.nextLine());

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) 
			{
                String[] data = line.split(",");
                if (Integer.parseInt(data[0]) == id) 
				{
                    System.out.println("Book Found");
                    System.out.println("ID: " + data[0]);
                    System.out.println("Title: " + data[1]);
                    System.out.println("Author: " + data[2]);
                    found = true;
                    break;
                }
            }

            br.close();

            if (!found)
                System.out.println("Book not found.");

        } 
		catch (Exception e) 
		{
            System.out.println("Error searching book.");
        }
    }

    // VIEW ALL BOOKS
    static void viewBooks() 
	{
        try 
		{
            File file = new File(FILE_NAME);
            if (!file.exists()) 
			{
                System.out.println("No books available.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            System.out.println("\nID\tTitle\tAuthor");
            System.out.println("--------------------------------");

            while ((line = br.readLine()) != null) 
			{
                String[] data = line.split(",");
                System.out.println(data[0] + "\t" + data[1] + "\t" + data[2]);
            }

            br.close();

        } 
		catch (Exception e) 
		{
            System.out.println("Error reading books.");
        }
    }
}
