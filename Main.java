/*
Dane Malanoski
Auto-Generating Suduko
Start Date: 2/27/2018
End Date: 
*/


import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class Main
   {
   public static void main(String[] args)
      {
      //creates instance of class
      Suduko board = board = new Suduko();
      
      board.printBoard(board.x);
      System.out.println("---------------------------------------");
      board.printBoard(board.y);
      
      board.checkCells();
      board.printBoardVals(board.possvals);
      
      return;
   
      }
   
   }

class Suduko
{  
   //allows use of random number
   Random rand = new Random();
   //solved puzzle
   int x[][][] = genBoard();
   //will be the unsolved puzzle
   int y[][][] = genBoard();
   //temp array holds the value of the unsolved puzzle while removing numbers
   int w[][][] = genBoard();
   //sets the list of possible numbers
   int list[] = {1,2,3,4,5,6,7,8,9};
   //sets a list that stores possible values for each position
   int possvals[][][] = new int[9][9][9];
   //stores number of solutions if you remove a number
   int solutions = 0;
   
   
   //instantiates the class by generating the table
   public Suduko()
   {
      genNumbers();
      
      copyArray(x,y);
      
      desolver();
      
      
   }
   //compares arrays
   public boolean compareArray(int[][][] u, int[][][] q)
   {
      for(int i = 0; i < 9; i += 1)
      {
            for(int j = 0; j < 3; j += 1)
            {
               for(int k = 0; k < 3; k+=1)
               {
                  if(u[i][j][k] == 0)
                  {
                  continue;
                  }
                  if(u[i][j][k] != q[i][j][k])
                  {
                  return false;
                  }
                  else
                  {
                  continue;
                  }
               }
            }
      }
      return true;
   }
   
   //sets all values for y to x values
   public void copyArray(int[][][] u, int[][][] q)
   {
      for(int i = 0; i < 9; i += 1)
      {
            for(int j = 0; j < 3; j += 1)
            {
               for(int k = 0; k < 3; k+=1)
               {
                  q[i][j][k] = u[i][j][k];
               }
            }
      }
   }
   
   //counts and prints the number of cells remaining
   public int checkCells()
   {
      int cells = 0;
      
      for(int i = 0; i < 9; i += 1)
      {
            for(int j = 0; j < 3; j += 1)
            {
               for(int k = 0; k < 3; k+=1)
               {
                  if(y[i][j][k] != 0)
                  {
                     cells += 1;
                  }
               }
            }
      }
      System.out.println("Cells remaining: " + cells);
      return cells;
   }
   
   //checks to see if you can remove the value at current coordinates
   public int checkValueLevI(int a, int b, int c)
   {
      boolean numberPass;
      int numsi = 0;
      y[a][b][c] = 0;
      
      
      //tests each possible number in the spot          
      for(int l = 0; l < 9; l +=1)
      {
         int p = list[l];
         numberPass = testRow(y, p, a);
         numberPass = numberPass & testBox(y, p, a, b);
         numberPass = numberPass & testCol(y, p, b, c);
         if(numberPass == true)
         {
            numsi+= 1;
            if((b == 0) & (c == 0))
            {
               possvals[a][0][l] = p;
            }
            else if((b == 0))
            {
               possvals[a][c][l] = p;
            }
            else if((b == 1))
            {
               possvals[a][3+c][l] = p;
            }
            else if((b == 2))
            {
               possvals[a][6+c][l] = p;
            }
         }
                          
      }
      if((numsi == 1)/* || (numsi == 2)*/)
      {
         return numsi;
      }
      else if(numsi > 1)
      {
         y[a][b][c] = x[a][b][c];
         return numsi;
      }
      else if(numsi == 0)
      {
         y[a][b][c] = x[a][b][c];
         return numsi;
      }
      return numsi;
   }
   
   //Runs a higher level check on puzzle square
   public void checkValueLevII(int a, int b, int c)
   {
      
      int ind2 = 0;
      
      if((b == 0) & (c == 0))
      {
         ind2 = 0;
      }
      else if((b == 0))
      {
         ind2 = c;
      }
      else if((b == 1))
      {
         ind2 = 3 + c;
      }
      else if((b == 2))
      {
         ind2 = 6 + c;
      }
      
      if(possvals[a][ind2].length == 2)
      {
         y[a][b][c] = 0;
      }
      
      
      solutions = 0;
      do
      {
         for(int i = 0; i < possvals[a][ind2].length; i += 1)
         {
            y[a][b][c] = possvals[a][ind2][i];
            loopednumbers(y);
            if(compareArray(y,x) == true)
            {
               copyArray(w,y);
               continue;
            }
            else if(compareArray(y,x) == false)
            {
               copyArray(w,y);
            }
         }
      }while(solutions <= 1);
      if(solutions == 1)
      {
         y[a][b][c] = 0;
         copyArray(w,y);
      }
      else
      {
         y[a][b][c] = w[a][b][c];
      }
      
      return;    
   }
   
   //code to remove as many values as possible from puzzle
   public void desolver()
   {
      //boolean solvable = true;
      copyArray(y,w);
      for(int i = 0; i < 500; i += 1)
      {
         int row = rand.nextInt(9);
         int bigCol = rand.nextInt(3);
         int lilCol = rand.nextInt(3);
         
         while(y[row][bigCol][lilCol] == 0)
         {
            row = rand.nextInt(9);
            bigCol = rand.nextInt(3);
            lilCol = rand.nextInt(3);
         }
         
         checkValueLevI(row, bigCol, lilCol);
         //checkValueLevII(row, bigCol, lilCol);
         
      
      }
      loopednumbers(w);
      //copyArray(y,w);
      printBoard(w);
      //loopednumbers(y);
      System.out.println("------------------------------------------");
      //System.out.println(solutions);
      return;
   }
   
   //generates an empty 1 x 3 row
   public int[] genRowS()
   {
      int x[] = new int[3];   
      
      return x;
   }
   
   //generates an empty 1x9 row
   public int[][] genRowL()
   {
      int a[] = genRowS();
      int b[] = genRowS();
      int c[] = genRowS();
      
      int u[][] = {a, b, c};
      
      return u;
   }
   
   //generates an empty 9x9 board
   public int[][][] genBoard()
   {
      int[][] u[] = new int[9][][]; 
      
      for(int i = 0; i < 9; i += 1)
      {
         u[i] = genRowL();
      }
       
      return u;   
   }
   
   //shifted the recursive loop from in gennumbers to its own so i could utilize elsewhere
   //is technically a solver but recieved errors when trying to change name
   public void loopednumbers(int t[][][])
   {
      //counter for number of slots filled
      int nums= 0;
      int b[][][] = genBoard();
      copyArray(t,b);

      //format for array loop is [i][j][k]
      for(int i = 0; i < 9; i += 1)
      {
         for(int j = 0; j < 3; j += 1)
         {
            for(int k = 0; k < 3; k+=1)
            {
               //System.out.println(nums);
               if(t[i][j][k] == 0)
               {
               //sets the tests to false for each new spot
               boolean row = false;
               boolean box = false;
               boolean column = false;
               boolean restart = false;
               int restarti = 0;
               // repeatededly trys numbers in spot until it finds one that fits
               do
               {  
                  
                  //before testing numbers it goes through and sees if it is even possible to fill           
                  for(int l = 0; l < 9; l +=1)
                  {
                     int p = list[l];
                     restart = testRow(t, p, i);
                     restart = restart & testBox(t, p, i, j);
                     restart = restart & testCol(t, p, j, k);
                     if(restart == false)
                     {
                        restarti+= 1;
                     }                   
                  }
                  //if no number can go in the current spot it restarts
                  if(restarti == 9)
                  {
                     //t = genBoard();
                     copyArray(b,t);
                     loopednumbers(t);
                     return;
                  }
                  //picks random number and tests if it will work
                  int val = rand.nextInt(9) + 1;
                  row = testRow(t, val, i);
                  box = testBox(t, val, i, j);
                  column = testCol(t, val, j, k);
                  //if the number works, it is assigned to the current slot
                  if(column && row && box)
                  {
                  nums += 1;
                  t[i][j][k] = val;
                  //below line showed progress of current iteration of loop
                  //System.out.println("The puzzle has " + nums + "/81 slots filled");
                  }
                  //if all slots are filled, loop ends
                  if(nums == 81)
                  {
                     solutions += 1;
                     break;
                  } 
                  
               }while(!column || !row || !box);
               }
               
            }
         }
      }
   }
   
   public void genNumbers()
   {
      //creates the empty board
       x = genBoard();

      loopednumbers(x);
      
      //code placed durign tests to ensure function reached return
      //printBoard(x);
      return;
   }
   
   //tests the column the current value is being placed in
   public boolean testCol(int a[][][], int val, int col, int subcol)
   {
      boolean pass = true;
      for(int i = 0; i < 9; i += 1)
      {
         if(a[i][col][subcol] == val)
         {
            pass = false;
         }
      }
      return pass;
   }
   //tests the row the current value is being placed in
   public boolean testRow(int a[][][], int val, int row)
   {
      boolean pass = true;
           
         for(int i = 0; i < 3; i += 1)
         {
            for(int j = 0; j < 3 ; j += 1)
            {
               if(a[row][i][j] == val)
               {
               pass = false;
               }
            }
         }
      return pass;
   }
   
   // tests each 3x3 box the current val is being placed in
   public boolean testBox(int a[][][], int val, int row, int col)
   {
      boolean pass = true;
      
      if((0 <= row) && (row <= 2))
      {
         if(col == 0)
         {
            for(int i = 0; i < 3; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
         else if(col == 1)
         {
            for(int i = 0; i < 3; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
         else if(col == 2)
         {
            for(int i = 0; i < 3; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
      }
      else if((3 <= row) && (row <= 5))
      {
         if(col == 0)
         {
            for(int i = 3; i < 6; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
         else if(col == 1)
         {
            for(int i = 3; i < 6; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
         else if(col == 2)
         {
            for(int i = 3; i < 6; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
      }
      else if((6 <= row) && (row <= 8))
      {
      if(col == 0)
         {
            for(int i = 6; i < 9; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
         else if(col == 1)
         {
            for(int i = 6; i < 9; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
         else if(col == 2)
         {
            for(int i = 6; i < 9; i += 1)
            {
               for(int j = 0; j < 3; j +=1)
               {
                  if(a[i][col][j] == val)
                  {
                     pass = false;
                  }
               }
            }
         }
      }
      return pass;
   }
   //prints board
   public void printBoard(int a[][][])
   {
      for(int i = 0; i < 9; i += 1)
      {
         for(int j = 0; j < 3; j += 1)
         {
            for(int k = 0; k < 3; k+=1)
            {
            System.out.print(a[i][j][k] + " ");
            }
         }
         System.out.println();
      }
   }
   //prints board
   public void printBoardVals(int a[][][])
   {
      for(int i = 0; i < 9; i += 1)
      {
         for(int j = 0; j < 9; j += 1)
         {
            System.out.print("[ ");
            for(int k = 0; k < 9; k+=1)
            {
            if(a[i][j][k] != 0)
            System.out.print(a[i][j][k] + " ");
            }
            System.out.print("]");
         }
         System.out.println();
      }
   }
   
}