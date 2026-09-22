/* Original code by Rafael Echeverri
COP 3330
This program uses menu driven input to allow for purchase of tickets
for a concert venue. The program is build with methods with several
functionalities and allows for a display of concert ground, as well as
number of tickets bought and total revenue generated, accounting for 
the cost of different regions in the concert area.
*/

import java.util.*;



class ticketing_v1 {

        // constants
        final public static int TOTAL_ROWS = 26;
        final public static int TOTAL_COLS = 60;
        final public static int REGION_ROWS = 13;
        final public static int REGION_COLS = 20;
        final public static int[] COST = {200, 300, 200, 100, 150, 100};









    
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        // we make empty array once program begins
        char[][] seatingArray = makeEmptyChart();

        // variables tracking total cost and occupancy
        int occupancy = 0;
        int cost = 0;
        int total_occupancy = 0;

        for(int i = 0 ; i < 1;) // loop control to allow iterative inputs
            {
                System.out.println("Please select a choce on the menu below:"); // menu user prompt
                System.out.println("1. Buy Concert tickets");
                System.out.println("2. See Stadium Ticket Map");
                System.out.println("3. View Total Revenue, Tickets Sold");
                System.out.println("4. Exit Ticketing System V1");
                int q = getMenuChoice(stdin);
                if(q == 1) // case 1, user buys tickets
                {
                    System.out.println("Which region(1-6) do you want your tickets in?");
                    int region = getRegion(stdin);
                    if(region == 0)
                    {
                        System.out.println("\nPlease enter a valid region.\n"); // Base case; no region chosen
                            continue; //next iteration
                    }
                    System.out.println("How many tickets do you want?");
                    int seatCount = stdin.nextInt();
                    System.out.println("");

                    occupancy = buySeats(seatingArray, region, seatCount); // purchases seat with user input and saves occupancy
                    if(occupancy == -1) // sentinel value for too much occupancy
                    {
                        System.out.println("\nSorry the transaction couldn't be made.");
                        System.out.println("Not enough available seats\n");
                        continue;
                    }

                    if(occupancy == -2) // sentinel value for negative number of tickets entered
                    {
                        System.out.println("Sorry you can't but a negative number of seats\n");
                        continue;
                    }
                    // total cost and occupancy count
                    cost += (occupancy * COST[region-1]);
                    total_occupancy += occupancy;
                }
                else if(q == 2) // case 2, prints current chart
                {
                    System.out.println("\nHere is the current seating chart:\n");
                    printChart(seatingArray);
                }
                else if(q == 3) // case 3, displays tickets, revenue
                {
                    System.out.println("\nCurrent tickets sold = " + total_occupancy);
                    System.out.println("Current revenue = $" + (cost));
                    System.out.println("");
                }
                else if(q == 4) // case 4, exiting menu
                {
                    // displays information, similar to a combination of cases 2 and 3
                    System.out.println("\nThank you for using Ticketing System V1");
                    System.out.println("Total tickets sold = " + total_occupancy);
                    System.out.println("Total revenue = $" + (cost));
                    System.out.println("Here is the final seating chart:\n");
                    printChart(seatingArray);
                    i++; // gets out of loop
                }
                else
                    System.out.println("\nSorry that choice is not valid. Please try again.\n"); // error checking
            }
        
    }



    
    public static char[][] makeEmptyChart()
{
    // initializes all values in the array to 'O'
    char[][] seating = new char[TOTAL_ROWS][TOTAL_COLS];
    for(int i = 0; i < TOTAL_ROWS; i ++)
            {
                for(int j = 0; j < TOTAL_COLS; j++)
                    {
                        seating[i][j] = 'O';
                    }
}
    return seating;
}










    
public static int getMenuChoice(Scanner stdin)
{
    // simply gets the choice the user entered in menu and return its number
    int n = stdin.nextInt();
    if(n == 1)
    {
        return 1;
    }
    if(n == 2)
    {
        return 2;
    }   
    if(n == 3)
    {
        return 3;
    }
    if(n == 4)
    {
        return 4;
    }
    else
    {
        return 0; // in case of wrong value entered
    }
}







    public static void printChart(char[][] seats)
    {
        int num = 1; // for printing column numbers
        int count = 0;
        System.out.print("  ");
        for(int i = 1; i <= TOTAL_COLS; i++)
            {
                // prints the whole first row of numbers
                System.out.print(num);
                count++;
                num++;
                if(num == 10)
                    num = 0;
                if(count == 20)
                {
                    System.out.print(" ");
                    count = 0;
                } 
            }
        System.out.println("\n");
        count = 0;
        char letter = 'A'; // for printing row letters
        for(int i = 0; i < TOTAL_ROWS; i ++)
            {
                // prints letter at the start of each row
                System.out.print(letter);
                letter++;
                System.out.print(" ");

                for(int j = 0; j < TOTAL_COLS; j++)
                    {
                        // goes through 2D array and prints each element
                        System.out.print(seats[i][j]);
                        count++;
                        if(count == 20)
                        {
                            // space after 20 columns
                            System.out.print(" ");
                            count = 0;
                        } 
                    }
                System.out.println(""); // newline after every row ends
                if(i == 12)
                    System.out.println(""); // extra newline after 13 rows
            }
    }







    


    public static int getRegion(Scanner stdin)
    {
        // simply takes user input for region and returns said number
        int i = stdin.nextInt();
            if(i == 1)
                return 1;
            if(i == 2)
                return 2;
            if(i == 3)
                return 3;
            if(i == 4)
                return 4;
            if(i == 5)
                return 5;
            if(i == 6)
                return 6;
            return 0; // base case for wrong input
            }  



    public static int buySeats(char[][] seats, int region, int numTickets)
    {
        /* rowsum, colsum accounts for the region we are 
        located in */
        int rowsum = 0;
        int colsum = 0;
        int price = 0;
        int seatCount = 0;
        int occupied = 0;


    if(numTickets < 0) // case if number of tickets is negative
        return -2; // sentinel
        
    if(region == 1)
    {
        // calculates offset based on region
        rowsum = 0;
        colsum = 0;
    }
    if(region == 2)
    {
        rowsum = 0;
        colsum = 20;
    }
    if(region == 3)
    {
        rowsum = 0;
        colsum = 40;
    }
    if(region == 4)
    {
        rowsum = 13;
        colsum = 0;
    }
    if(region == 5)
    {
        rowsum = 13;
        colsum = 20;
    }
    if(region == 6)
    {
        rowsum = 13;
        colsum = 40;
    }


    

    
        
    for(int i = 0; i < REGION_ROWS; i++)
        {
            //Calculates potential occupancy before allocating seats
            for(int j = 0; j < REGION_COLS; j++)
                {
                    if(seats[i+ rowsum][j + colsum] == 'X')
                    {
                        occupied++;
                        seatCount++;
                    }
                }
        }


    if(numTickets > ((REGION_ROWS*REGION_COLS)-occupied)) // case if not enough tickets left
        return -1; // sentinel
        
    char letter = 'A';
    letter += rowsum; // applies offset
    seatCount = 0; // resets seat count
    int num = 0;
    num += colsum; // applies offset
    int n = 0;
    System.out.println("Here are the tickets you are recieving:");   
        
    for(int i = 0; i < REGION_ROWS; i++)
        {
            for(int j = 0; j < REGION_COLS; j++)
                {
                    //if seat is empty...
                    if(seats[i+ rowsum][j + colsum] == 'O')
                    {
                        //prints letter and number of seat bought
                        System.out.print(letter);
                        System.out.print(num + n);
                        n++;
                        System.out.print(" ");
                        if(n > 19)
                        {
                            System.out.println("");
                            n = 0;
                        }
                        // changes open spot to reserved
                        seats[i + rowsum][j + colsum] = 'X';
                        seatCount++;
                    }
                    if(seatCount >= numTickets)
                        break;
                }
            letter++;
            if(seatCount >= numTickets)
                break;
        }
    System.out.println("\n");
    return numTickets; // returns number of tickets
    }






    public static void listTickets(char[][] seats)
    {
        // lists all tickets purchased
        char letter = 'A'; // letter indicating row
        int count = 0;
        int countTwo = 0; // number indicating column
        for(int i = 0; i < TOTAL_ROWS; i++)
            {
                for(int j = 0; j < TOTAL_COLS; j++)
                    {
                        if(seats[i][j] == 'X')
                        {
                            // goes thru array, printing corresponding letter and number
                            System.out.print(letter);
                            System.out.print(countTwo);
                            System.out.print(" ");
                            count++;
                            if(count == 20)
                            {
                                // 20 elements per line
                                count = 0;
                                System.out.println("");
                            }

                            
                        }
                        countTwo++;
                        if(countTwo >= 60)
                            countTwo = 0; // only 0-59 for rows
                    }
                letter++; // goes to next letter
            }
    }
}