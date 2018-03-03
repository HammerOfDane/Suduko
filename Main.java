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
      
      return;
   
      }
   
   }

class Suduko
{
   Random rand = new Random();
   int x[][][] = genBoard();
   
   public Suduko()
   {
      genNumbers();
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
   
   public void genNumbers()
   {
      //creates the empty board
       x = genBoard();
      //sets the list of possible numbers
      int list[] = {1,2,3,4,5,6,7,8,9};
      //counter for number of slots filled
      int nums= 0;

      //format for array loop is [i][j][k]
      for(int i = 0; i < 9; i += 1)
      {
         for(int j = 0; j < 3; j += 1)
         {
            for(int k = 0; k < 3; k+=1)
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
                     restart = testRow(x, p, i);
                     restart = restart & testBox(x, p, i, j);
                     restart = restart & testCol(x, p, j, k);
                     if(restart == false)
                     {
                        restarti+= 1;
                     }                   
                  }
                  //if no number can go in the current spot it restarts
                  if(restarti == 9)
                  {
                     genNumbers();
                  }
                  //picks random number and tests if it will work
                  int val = rand.nextInt(9) + 1;
                  row = testRow(x, val, i);
                  box = testBox(x, val, i, j);
                  column = testCol(x, val, j, k);
                  //if the number works, it is assigned to the current slot
                  if(column && row && box)
                  {
                  nums += 1;
                  x[i][j][k] = val;
                  //below line showed progress of current iteration of loop
                  //System.out.println("The puzzle has " + nums + "/81 slots filled");
                  }
                  //if all slots are filled, loop ends
                  if(nums == 81)
                  {
                     break;
                  } 
               }while(!column || !row || !box);
            }
         }
      }
      //code placed durign tests to ensure function reached return
      printBoard(x);
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
   
}