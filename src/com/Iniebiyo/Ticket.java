package com.Iniebiyo;
        import java.util.Date;
public class Ticket {

    private int priority;
    private String reporter; //Stores person or department who reported issue
    private String description;
    private Date dateReported;
    private String ticketResolution;
    private Date dateResolved;

    //STATIC Counter - accessible to all Ticket objects.
    //If any Ticket object modifies this counter, all Ticket objects will have the modified value
    //Make it private - only Ticket objects should have access
    private static int staticTicketIDCounter = 1;
    //The ID for each ticket - instance variable. Each Ticket will have its own ticketID variable
    protected int ticketID;

    public String getDescription() {
        return description;
    }

    public String getReporter() {
        return reporter;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public String getTicketResolution() {
        return ticketResolution;
    }

    public void setTicketResolution(String ticketResolution) {
        this.ticketResolution = ticketResolution;
    }

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }

    public Ticket(String desc, int p, String rep, Date date,
                  String ticketResolution, Date dateResolved) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;

        this.dateReported = date;
        this.ticketResolution = ticketResolution;
        this.dateResolved = dateResolved;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;



    }

    public int getTicketID() {
        return ticketID;
    }

    protected int getPriority() {
        return priority;
    }

    //public String toString(){
    //return("ID: " + this.ticketID + " Issue: " + this.description + " Priority: " + this.priority + " Reported by: "
    //        + this.reporter + " Reported on: " + this.dateReported );
    //Called automatically if a Ticket object is an argument to System.out.println
    public String toString(){
        String resolvedDateString = (dateResolved == null) ? "Unresolved" : this.dateResolved.toString();
        String resolutionString = (this.ticketResolution == null) ? "Unresolved" : this.ticketResolution;
        return ("ID: " + this.ticketID + " Issue: " + this.description + " Priority: "+ this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported + " Resolution: "+ resolutionString + " Resolved on: " + resolvedDateString);

    }

}
