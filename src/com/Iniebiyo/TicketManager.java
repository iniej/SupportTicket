package com.Iniebiyo;

import java.util.*;
import java.util.Scanner;
import java.io.*;


public class TicketManager {
    //static LinkedList<Ticket> resolvedTicketQue = new LinkedList<>();

    public static void main(String[] args) throws IOException{

        LinkedList<Ticket> resolvedTickets = new LinkedList<Ticket>();
        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();

        Scanner scan = new Scanner(System.in);
        System.out.println("Here is a list of open tickets");
        System.out.println();
        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("Open_Tickets.txt"));
//            String line = bufferedReader.readLine();
//
//            while (line != null) {
//                System.out.println(line);
//                line = bufferedReader.readLine();
//            }
//            System.out.println();
//            bufferedReader.close();

            BufferedWriter openTicket = new BufferedWriter(new FileWriter("Open_Tickets.txt"));
            BufferedWriter resolvedTicket = new BufferedWriter(new FileWriter("Resolved_Ticket.txt"));


            //Scanner scan = new Scanner(System.in);

            while (true) {
                System.out.println("1. Enter Ticket\n2. Delete Ticket by ID\n3. Delete Ticket by Issue\n4." +
                        " Search by Issue\n5. Display All Tickets\n6. display resolved Ticket\n7. Quit");
                int task = Integer.parseInt(scan.nextLine());

                if (task == 1) {
                    //Call addTickets, which will let us enter any number of new tickets
                    addTickets(ticketQueue);
                    for (Ticket ot : ticketQueue) {
                        openTicket.write(ot + "\n");
                    }
                    openTicket.close();

                } else if (task == 2) {
                    //delete a ticket by ID.
                    deleteTicket(ticketQueue, resolvedTickets);
                } else if (task == 3) {
                    //Delete tickets by issue.
                    deleteTicketByIssue(ticketQueue, resolvedTickets);


                } else if (task == 4) {
                    //Search ticket by issue.
                    searchByIssue(ticketQueue);
                } else if (task == 5) {
                    //Print all tickets.
                    printAllTickets(ticketQueue);
                } else if (task == 6) {
                    //Display all resolved tickets.
                    printResolvedTickets(resolvedTickets);
                } else if (task == 7) {
                    //Quit. Future prototype may want to save all tickets to a file
                    System.out.println("Quitting program");
                    for (Ticket rt : resolvedTickets) {
                        resolvedTicket.write(rt + "\n");
                    }
                    resolvedTicket.close();
                    break;
                } else {
                    //this will happen for option 3 or any other selection that is a valid int
                    //TODO Program crashes if you enter anything else - please fix
                    printAllTickets(ticketQueue);
                }
            }
            scan.close();
        } catch (IOException ioe) {
            System.out.println("File not found");
            BufferedWriter bufferedWriter =   new BufferedWriter(new FileWriter("Open_Tickets.txt"));
        }
    }



    protected static void   deleteTicketByIssue(LinkedList <Ticket>ticketQueue,
                                                LinkedList<Ticket>resolvedTicket){

        searchByIssue(ticketQueue);
        deleteTicket(ticketQueue, resolvedTicket);
    }


    protected static void searchByIssue(LinkedList<Ticket>ticketQueue){
        Scanner search = new Scanner(System.in);
        System.out.println("Enter the search string");
        String searchString = search.nextLine();
        for(Ticket t: ticketQueue){
            if(t.getDescription().contains(searchString) || t.getDescription().toLowerCase().contains(searchString)){
                System.out.println(t);
            }
        }
    }
    //Search and delete ticket by ID.
    protected static void deleteTicket(LinkedList<Ticket> ticketQueue,
                                       LinkedList<Ticket> resolvedTicket) {
        //What to do here? Need to delete ticket, but how do we identify the ticket to delete?
        //TODO implement this method

        printAllTickets(ticketQueue);
        int deleteID;
        Scanner deletescanner = new Scanner(System.in);
        if (ticketQueue.size() == 0) {//no tickets'

            System.out.println("No ticket to delete\n");
            return;
        }
        deleteID = Input.getPositiveIntInput("Enter ID of ticket to delete");
        //System.out.println("Delete tickets method called");

        boolean found = false;
        for (Ticket ticket : ticketQueue) {
            if (ticket.getTicketID() == deleteID){
                found = true;

                String res = Input.getStringInput("Enter ID of the ticket to delete");

                Date resolvedDate = new Date();
                int priority = ticket.getPriority();
                String description = ticket.getDescription();
                String rep = ticket.getReporter();
                Ticket tr = new Ticket(description, priority, rep, new Date(), res, resolvedDate);
                resolvedTicket.add(tr);

                ticketQueue.remove(ticket);
                System.out.println(String.format("Ticket %d deleted ", deleteID));

                break;
            }


        }
        while(!found) {
            System.out.println("Ticket ID not found, no ticket deleted");
            deleteID = Input.getPositiveIntInput("Please enter the ticket number again");


            for(Ticket T : ticketQueue){
                if(T.getTicketID()== deleteID){
                    found = true;
                    String res = Input.getStringInput("Please enter a resolution for the deleted ticket : ID ");

                    ticketQueue.remove(T);
                    System.out.println(T);

                    System.out.println(String.format("Ticket %d deleted",deleteID));

                    break;
                }
            }

        }
        printAllTickets(ticketQueue);
    }

    //protected static void deleteTicket(LinkedList<Ticket> ticketQueue) {
        //TODO implement this method
       // System.out.println("Delete tickets method called");
    //}

    protected static void addTickets(LinkedList<Ticket> ticketQueue) {
        Scanner sc = new Scanner(System.in);
        String description, reporter, resolution;
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        int priority;

        while (true){
            System.out.println("Enter problem");
            description = sc.nextLine();
            System.out.println("Who reported this issue?");
            reporter = sc.nextLine();
            System.out.println("Enter priority of " + description);
            priority = Integer.parseInt(sc.nextLine());
            resolution = null;
            Date dateresolved = new Date();

            Ticket t = new Ticket(description, priority, reporter, dateReported, resolution, dateresolved);
            ticketQueue.add(t);

            printAllTickets(ticketQueue); //To test, let's print out all of the currently stored tickets

            System.out.println("More tickets to add?");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All open tickets ----------");
        for (Ticket t : tickets ) {
            System.out.println(t); //println will call toString on its argument
        }
        System.out.println(" ------- End of ticket list ----------");
    }
    protected static void printResolvedTickets(LinkedList<Ticket> resolvedTicket){
        System.out.println("--------------All resolved tickets-------------");
        for (Ticket tr : resolvedTicket){
            System.out.println(tr);
        }
        System.out.println("-----------End of tickets-------------------");
    }
}
