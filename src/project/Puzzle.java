package project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class Puzzle 
{
    private int solution[][]=new int[9][9];
    private int puzzle[][]=new int[9][9];
    public Puzzle()
    {
        generateSudoku();
    }
    
    private void generateSudoku()
     {
        Random rand=new Random();
        for(int m=0;m<9;m=m+3)
            for(int n=0;n<9;n+=3)
              {
                do{
                for (int i=m;i<m+3;i++)
                for(int j=n;j<n+3;j++)
                            getSolution()[i][j]=1+rand.nextInt(3);
                }while(checkSolution(m,n));
              }
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(((i==0||i==1||i==2)&&(j==3||j==4||j==5))||((i==6||i==7||i==8)&&(j==0||j==1||j==2))||((i==3||i==4||i==5)&&(j==6||j==7||j==8)))
                {
                switch(getSolution()[i][j])
                 {
                    case 0:
                        break;
                    case 1:
                        getSolution()[i][j]=4;
                        break;
                    case 2:
                        getSolution()[i][j]=5;
                        break;
                    case 3:
                        getSolution()[i][j]=6; 
                        break;
                 }
                }
                else if(((i==0||i==1||i==2)&&(j==6||j==7||j==8))||((i==6||i==7||i==8)&&(j==3||j==4||j==5))||((i==3||i==4||i==5)&&(j==0||j==1||j==2)))
                {
                switch(getSolution()[i][j])
                 {
                    case 0:
                        break;
                    case 1:
                        getSolution()[i][j]=7;
                        break;
                    case 2:
                        getSolution()[i][j]=8;
                        break;
                    case 3:
                        getSolution()[i][j]=9;
                        break;
                 }
                }
              }
        }
        rowChange(1,3);
        rowChange(2,6);
        rowChange(5,7);
                
        for(int i=0;i<2;i++)
        {
            int temp1=0,temp2=0;
            do{
                temp1=3*rand.nextInt(3);temp2=3*rand.nextInt(3);
            }while(temp1==temp2);
            if(i==0)
                colPermutation(temp1,temp2);
            else
                rowPermutation(temp1,temp2);
        }
        rotateMatrix();
        transposeMatrix();
        finalShuffle();
        for(int i=0;i<9;i++)
            System.arraycopy(solution[i], 0, puzzle[i], 0, 9);
        ArrayList x=new ArrayList();
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                x.add(new grid(i,j));
        Collections.shuffle(x);
        for(int i=0;i<x.size();i++)
        {
            grid g1=(grid)x.get(i);
            generatePuzzle(g1.row,g1.col);
        }
     }
    
    private class grid{
        public int row,col;
        private grid(int a,int b)
        {
            row=a;col=b;
        }
    }
    
    private boolean checkSolution(int m,int n)
    {
     
            for(int i=m;i<m+3;i++)
            {
               for(int j=n;j<n+3;j++)
                {
                    for(int l=n;l<n+3;l++)
                        {
                            if(getSolution()[i][j]==getSolution()[i][l]&&l!=j)
                                return true;
                        }
                    for(int k=m;k<m+3;k++)
                        {
                            if(getSolution()[i][j]==getSolution()[k][j]&&k!=i)
                                return true;
                        }                    
                } 
            }
            return false;
    }
    
    private void rowPermutation(int r1,int r2)
     {
      int temp;
      for(int j=0;j<+2;j++)
      {
          for(int i=0;i<9;i++)
        {
            temp=getSolution()[r1][i];
                getSolution()[r1][i]=getSolution()[r2][i];
                getSolution()[r2][i]=temp;
        }
         r1++;r2++; 
      }
     }
    
    private void colPermutation(int c1,int c2)
     {
      int temp;
      for(int j=0;j<=2;j++)
      {
        for(int i=0;i<9;i++)
          {
            temp=getSolution()[i][c1];
                getSolution()[i][c1]=getSolution()[i][c2];
                getSolution()[i][c2]=temp;
          }
        c1++;c2++;
      }
     } 
    private void rowChange(int r1,int r2)
    {
      int temp;
      for(int j=0;j<9;j++)
       {
        temp=getSolution()[r1][j];
            getSolution()[r1][j]=getSolution()[r2][j];
            getSolution()[r2][j]=temp;
       }
    }
    
    private void colChange(int c1,int c2)
    {
        
     int temp;
     for(int j=0;j<9;j++)
      {
       temp=getSolution()[j][c1];
            getSolution()[j][c1]=getSolution()[j][c2];
            getSolution()[j][c2]=temp;
      }
    }    
    private void rotateMatrix()
    {
        Random rand=new Random();
        int temp1=1+rand.nextInt(4);
        int count=1;
        int[][] temp = new int[9][9];
        do
        {
          for(int i = 0; i < 9;i++) 
          {
             for(int j = 0; j < 9; j++) 
             {
             temp[i][j] = getSolution()[9 - j - 1][i];
             }
          }
          count++;
        }while(count<=temp1);
        setSolution(temp);
    }
    
    private void transposeMatrix()
     {
      int[][] tMatrix = new int[9][9];
      for(int x = 0; x < 9; x++)
        {
          for(int y = 0; y < 9; y++)
           {
             tMatrix[x][y] = getSolution()[y][x];
           }
        }
        setSolution(tMatrix);
    
     }
    
    private void finalShuffle()
     {
        String seed[][]=new String[9][9];
        for(int i=0;i<getSolution().length;i++)
      {
        for(int j=0;j<getSolution()[0].length;j++)
        {
            switch (getSolution()[i][j]) 
            {
                case 1:
                    seed[i][j]="a";
                    break;
                case 2:
                    seed[i][j]="b";
                    break;
                case 3:
                    seed[i][j]="c";
                    break;
                case 4:
                    seed[i][j]="d";
                    break;
                case 5:
                    seed[i][j]="e";
                    break;
                case 6:
                    seed[i][j]="f";
                    break;
                case 7:
                    seed[i][j]="g";
                    break;
                case 8:
                    seed[i][j]="h";
                    break;
                case 9:
                    seed[i][j]="i";
                    break;
                default:
                    break;
            }
        }
       }
        Integer [] temp =new Integer[9] ;
        for(int i=0;i<temp.length;i++)
            temp[i]=i+1;
        Collections.shuffle(Arrays.asList(temp));
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                    switch (seed[i][j]) {
                    case "a":
                        getSolution()[i][j]=temp[0];
                        break;
                    case "b":
                        getSolution()[i][j]=temp[1];
                        break;
                    case "c":
                        getSolution()[i][j]=temp[2];
                        break;
                    case "d":
                        getSolution()[i][j]=temp[3];
                        break;
                    case "e":
                        getSolution()[i][j]=temp[4];
                        break;
                    case "f":
                        getSolution()[i][j]=temp[5];
                        break;
                    case "g":
                        getSolution()[i][j]=temp[6];
                        break;
                    case "h":
                        getSolution()[i][j]=temp[7];
                        break;
                    case "i":
                        getSolution()[i][j]=temp[8];
                        break;
                }
            }
        }
     }

    private void generatePuzzle(int k1, int k2)
    {
    int row_from;
   int row_to;
   int col_from;
   int col_to;
   int i,j,b,c;
   int rem1,rem2;
   int flag;
   int temp=getPuzzle()[k1][k2];
   int count=9;
   for(i=1;i<=9;i++)
   { flag=1;
      for(j=0;j<9;j++)
      {
         if(j!=k2)
        {
           if(i!=   getPuzzle()[k1][j])
           {
              continue;
           }
           else
           {
              flag=0;
              break;
           }
        } 
     }
     if(flag==1)
     {
        for(c=0;c<9;c++)
        {
           if(c!=k1)
           {
              if(i!=    getPuzzle()[c][k2])
              {
                 continue;
              }
              else
              {
              flag=0;
              break; 
              }
           }
       }
    }
    if(flag==1)
    {
       rem1=k1%3; rem2=k2%3;
       row_from=k1-rem1;
       row_to=k1+(2-rem1);
       col_from=k2-rem2;
       col_to=k2+(2-rem2);
       for(c=row_from;c<=row_to;c++)
       {
          for(b=col_from;b<=col_to;b++)
          {
             if(c!=k1 && b!=k2)
             {
                if(i!=      getPuzzle()[c][b])
                continue;
                else{
                   flag=0;
                   break;
                }
            }
         }
      }
   }
   if(flag==0)
   count--;
  }

  if(count==1)
  { 
            getPuzzle()[k1][k2]=0;
     count--;
  }
}
    
    public int[][] getSolution() {
        return solution;
    }

    public void setSolution(int[][] solution) {
        this.solution=solution;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }
    
    public void display()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                System.out.print(getPuzzle()[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                System.out.print(getSolution()[i][j]);
            }
            System.out.println();
        }
    }
}
