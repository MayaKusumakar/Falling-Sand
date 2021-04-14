import java.awt.*;
import java.util.*;

public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
  
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int FISH = 4;
  public static final int ACID = 5;
  public static final int LAVA = 6;
  public static final int SHRINK = 7;
  public static final int OBSIDEAN = 8;
  public static final int GAS = 9;
  public static final int FUNGI = 10;
  public static final int FUNGIREMOVER = 11;
  public static final int TRANSPORT = 12;
  public static final int CLEAR = 13;
  public static final int HEIGHT = 80;
  public static final int WIDTH = 120;
  
  
  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    names = new String[14];
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[FISH] = "FISH";
    names[ACID] = "ACID";
    names[LAVA] = "LAVA";
    names[SHRINK] = "SHRINK";
    names[OBSIDEAN] = "OBSIDEAN";
    names[GAS] = "GAS";
    names[FUNGI] = "FUNGI";
    names[FUNGIREMOVER] = "FUNGI GHOSTS";
    names[TRANSPORT] = "TRANSPORT";
    names[CLEAR] = "Goodbye World";
    
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    grid = new int[numRows][numCols];
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
   grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
  for(int r = 0; r <grid.length; r++){
   for(int c = 0; c < grid[0].length; c++){
      if (grid[r][c] == EMPTY)
         display.setColor(r,c,new Color(0,0,0));
      if (grid[r][c] == METAL)
         display.setColor(r,c,new Color(130,130,130));
      if (grid[r][c] == SAND)
         display.setColor(r,c,new Color(255,211,0));
      if (grid[r][c] == WATER)
         display.setColor(r,c,new Color(50,60,250));
      if (grid[r][c] == ACID)
         display.setColor(r,c,new Color(0,160,0));
      if (grid[r][c] == SHRINK)
         display.setColor(r,c,new Color(255,255,255));
      if (grid[r][c] == LAVA)
         display.setColor(r,c,new Color(255,0,0));
      if (grid[r][c] == OBSIDEAN)
         display.setColor(r,c,new Color(211,211,211));
      if (grid[r][c] == GAS)
         display.setColor(r,c,new Color(100,100,200));
      if (grid[r][c] == FUNGI)
         display.setColor(r,c,new Color(20,200,20));
      if (grid[r][c] == FUNGIREMOVER)
         //display.setColor(r,c,new Color(56,87,129)); 
         display.setColor(r,c,new Color(0,0,0));  
      if (grid[r][c] == FISH)
         display.setColor(r, c, new Color(255, 105, 180));
      if (grid[r][c] == TRANSPORT)
         display.setColor(r,c,new Color(60,212,53));
     }
     }
  }
  
  

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step()
  {
     int locx = (int)(Math.random() * grid.length);
     int locy = (int)(Math.random() * grid[0].length);
          
     waterMove(locx, locy);
     sandMove(locx, locy);
     acidMove(locx, locy);
     lavaMove(locx, locy);
     shrinkMove(locx, locy);
     gasMove(locx, locy);
     fungiMove(locx, locy);
     fungiRemoverMove(locx, locy);
     fishMove(locx, locy);
     transport(locx, locy);
     clear(locx, locy);
     
          
                  
  
  }
  
  public void clear(int locx, int locy){
  if(grid[locx][locy] == CLEAR){
  
   for(int r = 0; r < grid.length; r++){
      for(int c = 0; c < grid[0].length; c++){
         grid[r][c] = EMPTY;
      }
   }
   }
  }
  public void transport(int locx, int locy){
   int transportint = (int)(Math.random()*4)+1;
   if(grid[locx][locy] == TRANSPORT){
      if(transportint == 1){
         if(!(locy>=grid[0].length-1)){
            if(grid[locx][locy+1] == EMPTY){
                  grid[locx][locy] = EMPTY;
                  grid[locx][locy+1] = TRANSPORT;
             } else if(grid[locx][locy+1] == WATER)
                  relocate(locx, locy, WATER);
               else if(grid[locx][locy+1] == SAND)
                  relocate(locx, locy, SAND);
               else if(grid[locx][locy+1] == LAVA)
                  relocate(locx, locy, LAVA);
               else if(grid[locx][locy+1] == OBSIDEAN)
                  relocate(locx, locy, OBSIDEAN);
               else if(grid[locx+1][locy] == METAL)
                  relocate(locx, locy, METAL);
               else if(grid[locx+1][locy] == GAS)
                  relocate(locx, locy, GAS);
             }
         }
      if(transportint == 2){
         if(!(locy< 1)){
              if(grid[locx][locy-1] == EMPTY){
                     grid[locx][locy] = EMPTY;
                     grid[locx][locy-1] = TRANSPORT;
              } else if(grid[locx][locy-1] == WATER)
                  relocate(locx, locy, WATER);
               else if(grid[locx][locy-1] == SAND)
                  relocate(locx, locy, SAND);
               else if(grid[locx][locy-1] == LAVA)
                  relocate(locx, locy, LAVA);
               else if(grid[locx][locy-1] == OBSIDEAN)
                  relocate(locx, locy, OBSIDEAN);
               else if(grid[locx+1][locy] == METAL)
                  relocate(locx, locy, METAL);
               else if(grid[locx+1][locy] == GAS)
                  relocate(locx, locy, GAS);

         }

      }
      
      if(transportint == 3){
         if(!(locx >= grid.length-1)){
            if(grid[locx+1][locy] == EMPTY){
                 grid[locx][locy] = EMPTY;
                  grid[locx+1][locy] = TRANSPORT;
               }else if(grid[locx+1][locy] == WATER)
                  relocate(locx, locy, WATER);
               else if(grid[locx+1][locy] == SAND)
                  relocate(locx, locy, SAND);
               else if(grid[locx+1][locy] == LAVA)
                  relocate(locx, locy, LAVA);
               else if(grid[locx+1][locy] == OBSIDEAN)
                  relocate(locx, locy, OBSIDEAN);
               else if(grid[locx+1][locy] == METAL)
                  relocate(locx, locy,METAL);
               else if(grid[locx+1][locy] == GAS)
                  relocate(locx, locy, GAS);

            }
      }
      
      if(transportint == 4){
         if(!(locx<1)){
            if(grid[locx-1][locy] == EMPTY){
               grid[locx][locy] = EMPTY;
               grid[locx-1][locy] = TRANSPORT;
              }else if(grid[locx-1][locy] == WATER)
                  relocate(locx, locy, WATER);
               else if(grid[locx-1][locy] == SAND)
                  relocate(locx, locy, SAND);
               else if(grid[locx-1][locy] == LAVA)
                  relocate(locx, locy,LAVA);
               else if(grid[locx-1][locy] == OBSIDEAN)
                  relocate(locx, locy,OBSIDEAN);
               else if(grid[locx+1][locy] == METAL)
                  relocate(locx, locy,METAL);


         
      if(locx >= grid.length-1){
         grid[locx][locy] = EMPTY;
      }  
      }
}
}
}

  
  
  public void relocate(int x1, int y1, int type){
   int x = (int)(Math.random()*WIDTH);
   int y = (int)(Math.random()*HEIGHT);
   grid[x1][y1] = EMPTY;
   grid[x][y] = type;
   }
  //maybe add if fish is not in water, it goes away?
  public void fishMove(int locx, int locy){
  int fishint = (int)(Math.random()*100)+1;
   if(grid[locx][locy] == FISH){
      if(fishint == 1){
         if(!(locy>=grid[0].length-1)){
            if(grid[locx][locy+1] == WATER){
                  grid[locx][locy] = WATER;
                  grid[locx][locy+1] = FISH;
            }    
         }
      }
      if(fishint == 2){
         if(!(locy< 1)){
             if(grid[locx][locy-1] == WATER){
                 grid[locx][locy] = WATER;
                 grid[locx][locy-1] = FISH;
             } 
         }
      }
      
      if(fishint == 3){
         if(!(locx >= grid.length-1)){
            if(grid[locx+1][locy] == WATER){
                 grid[locx][locy] = WATER;
                  grid[locx+1][locy] = FISH;
            }
         }
      }
      
      if(fishint == 4){
         if(!(locx<1)){
            if(grid[locx-1][locy] == WATER){
               grid[locx][locy] = WATER;
               grid[locx-1][locy] = FISH;
            }
         }
      }
      
      if(locx >= 1 && locx < grid.length-1 && locy>= 1 && locy < grid[0].length-1){
         if(grid[locx+1][locy] != WATER && grid[locx-1][locy] != WATER && grid[locx][locy+1] != WATER && grid[locx][locy-1] != WATER){
            if(grid[locx][locy] == FISH){
               grid[locx][locy] = EMPTY;
            }
         }
      }
    }
  }
  
  public void fungiRemoverMove(int locx, int locy){
  int removerint = (int)(Math.random()*4)+1;
   if(grid[locx][locy] == FUNGIREMOVER){
      if(removerint == 1){
         if(!(locy>=grid[0].length-1)){
            if(grid[locx][locy+1] == FUNGI){
                  grid[locx][locy] = EMPTY;
                  grid[locx][locy+1] = FUNGIREMOVER;
            }    
         }
      }
      if(removerint == 2){
         if(!(locy< 1)){
             if(grid[locx][locy-1] == FUNGI){
                 grid[locx][locy] = EMPTY;
                 grid[locx][locy-1] = FUNGIREMOVER;
             } 
         }
      }
      
      if(removerint == 3){
         if(!(locx >= grid.length-1)){
            if(grid[locx+1][locy] == FUNGI){
                 grid[locx][locy] = EMPTY;
                  grid[locx+1][locy] = FUNGIREMOVER;
            }
         }
      }
      
      if(removerint == 4){
         if(!(locx<1)){
            if(grid[locx-1][locy] == FUNGI){
               grid[locx][locy] = EMPTY;
               grid[locx-1][locy] = FUNGIREMOVER;
              }
         }
      } 
    }
}

   
  
  public void gasMove(int locx, int locy){
   
  int gasint = (int)(Math.random() * 7)+1;
      
           if (grid[locx][locy] == GAS){
              if(gasint == 1 || gasint == 2){
                 if(!(locx <1)){
                    if(grid[locx-1][locy] == EMPTY){
                       grid[locx][locy] = EMPTY;
                       grid[locx-1][locy] = GAS;
                    }
                 }
              }
              if(gasint == 3 || gasint == 4){
                  if(!(locy>=grid[0].length-1)){
                     if(grid[locx][locy+1] == EMPTY){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy+1] = GAS;
                     }
                  }
              }
              if(gasint == 5 || gasint == 6){
                  if(!(locy< 1)){
                     if(grid[locx][locy-1] == EMPTY){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy-1] = GAS;
                     } 
                  }
              } if (gasint == 7){
                  if(!(locx>=grid.length-1)){
                     if(grid[locx+1][locy] == EMPTY){
                        grid[locx][locy] = EMPTY;
                        grid[locx+1][locy] = GAS;
                     }
                  }
               }                        
           }      
       }


  public void shrinkMove(int locx, int locy){
  int shrinkint = (int)(Math.random()*4)+1;
   if(grid[locx][locy] == SHRINK){
      if(shrinkint == 1){
         if(!(locy>=grid[0].length-1)){
            if(grid[locx][locy+1] == EMPTY){
                  grid[locx][locy] = EMPTY;
                  grid[locx][locy+1] = SHRINK;
             } else if(grid[locx][locy+1] == WATER)
                  shrinkType(WATER);
               else if(grid[locx][locy+1] == SAND)
                  shrinkType(SAND);
               else if(grid[locx][locy+1] == LAVA)
                  shrinkType(LAVA);
               else if(grid[locx][locy+1] == OBSIDEAN)
                  shrinkType(OBSIDEAN);
               else if(grid[locx][locy+1] == METAL)
                  shrinkType(METAL);
               else if(grid[locx][locy+1] == GAS)
                  shrinkType(GAS);
             }
         }
      if(shrinkint == 2){
         if(!(locy< 1)){
              if(grid[locx][locy-1] == EMPTY){
                     grid[locx][locy] = EMPTY;
                     grid[locx][locy-1] = SHRINK;
              } else if(grid[locx][locy-1] == WATER)
                  shrinkType(WATER);
               else if(grid[locx][locy-1] == SAND)
                  shrinkType(SAND);
               else if(grid[locx][locy-1] == LAVA)
                  shrinkType(LAVA);
               else if(grid[locx][locy-1] == OBSIDEAN)
                  shrinkType(OBSIDEAN);
               else if(grid[locx][locy-1] == METAL)
                  shrinkType(METAL);
               else if(grid[locx][locy-1] == GAS)
                  shrinkType(GAS);

         }

      }
      
      if(shrinkint == 3){
         if(!(locx >= grid.length-1)){
            if(grid[locx+1][locy] == EMPTY){
                 grid[locx][locy] = EMPTY;
                  grid[locx+1][locy] = SHRINK;
               }else if(grid[locx+1][locy] == WATER)
                  shrinkType(WATER);
               else if(grid[locx+1][locy] == SAND)
                  shrinkType(SAND);
               else if(grid[locx+1][locy] == LAVA)
                  shrinkType(LAVA);
               else if(grid[locx+1][locy] == OBSIDEAN)
                  shrinkType(OBSIDEAN);
               else if(grid[locx+1][locy] == METAL)
                  shrinkType(METAL);
               else if(grid[locx+1][locy] == GAS)
                  shrinkType(GAS);

            }
      }
      
      if(shrinkint == 4){
         if(!(locx<1)){
            if(grid[locx-1][locy] == EMPTY){
               grid[locx][locy] = EMPTY;
               grid[locx-1][locy] = SHRINK;
              }else if(grid[locx-1][locy] == WATER)
                  shrinkType(WATER);
               else if(grid[locx-1][locy] == SAND)
                  shrinkType(SAND);
               else if(grid[locx-1][locy] == LAVA)
                  shrinkType(LAVA);
               else if(grid[locx-1][locy] == OBSIDEAN)
                  shrinkType(OBSIDEAN);
               else if(grid[locx-1][locy] == METAL)
                  shrinkType(METAL);
               else if(grid[locx-1][locy] == GAS)
                  shrinkType(GAS);


         
      if(locx >= grid.length-1){
         grid[locx][locy] = EMPTY;
      }  
      }
}
}
}
  public void shrinkType(int type){
  for(int r = 0; r < grid.length; r++){
   for(int c = 0; c < grid[0].length; c++){
      if(grid[r][c] == type){
         if(countType(type) > 100){
         
         int chance = (int)(Math.random()*2);
         if(chance == 0){
            grid[r][c] = EMPTY;
         }
         }
      }
   }
  }
  }
  
  public int countType(int type){
  int count = 0;
  for(int r = 0; r < grid.length; r++){
   for(int c = 0; c < grid[0].length; c++){
      if(grid[r][c] == type){
         count++;
      }
   }
   }
   return count;

  
  }
  //teleport to random place on map
  public void fungiMove(int locx, int locy){
  int fungiint = (int)(Math.random()*300)+1;
   if(grid[locx][locy] == FUNGI){
      if(fungiint == 1){
         if(!(locy>=grid[0].length-1)){
            if(grid[locx][locy+1] != FUNGIREMOVER)
                grid[locx][locy+1] = FUNGI;
         } 
      }
      
      else if(fungiint == 2){
         if(!(locy< 1)){  
            if(grid[locx][locy-1] != FUNGIREMOVER)
                grid[locx][locy-1] = FUNGI;
               
         }

      }
      
      else if(fungiint == 3){
         if(!(locx >= grid.length-1)){
            if(grid[locx+1][locy] != FUNGIREMOVER)
             grid[locx+1][locy] = FUNGI;         
         }
      }
      
      else if(fungiint == 4){
         if(!(locx<1)){
            if(grid[locx-1][locy] != FUNGIREMOVER)
             grid[locx-1][locy] = FUNGI;
              
         }
      }     
   }
}



  public void lavaMove(int locx, int locy){
   int lavaint = (int)(Math.random() * 3)+1;
      
           if (grid[locx][locy] == LAVA){
              if(lavaint == 1){
                 if(!(locx >= grid.length-1)){
                    if(grid[locx+1][locy] == EMPTY){
                       grid[locx][locy] = EMPTY;
                       grid[locx+1][locy] = LAVA;
                    }else if(grid[locx+1][locy] == WATER){
                        grid[locx][locy] = EMPTY;
                        grid[locx+1][locy] = OBSIDEAN;
                  }

                 }
              }
              if(lavaint == 2){
                  if(!(locy>=grid[0].length-1)){
                     if(grid[locx][locy+1] == EMPTY){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy+1] = LAVA;
                     }else if(grid[locx][locy+1] == WATER){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy+1] = OBSIDEAN;
                  }

                  }
              }
              if(lavaint == 3){
                  if(!(locy< 1)){
                     if(grid[locx][locy-1] == EMPTY){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy-1] = LAVA;
                     } else if(grid[locx][locy-1] == WATER){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy-1] = OBSIDEAN;
                  }
              }
                  
           }

         }
          
       }

  //go left or right
   public void acidMove(int locx, int locy){
   int acidint = (int)(Math.random()*20)+1;
   if(grid[locx][locy] == ACID){
      if(acidint == 1){
         if(!(locy>=grid[0].length-1)){
                  grid[locx][locy] = EMPTY;
                  grid[locx][locy+1] = ACID;
             }
         }
      if(acidint == 2){
         if(!(locy< 1)){
              if(grid[locx][locy-1] == EMPTY){
                     grid[locx][locy] = EMPTY;
                     grid[locx][locy-1] = ACID;
              } 
         }

      }
      
      if(acidint == 3){
         if(!(locx >= grid.length-1)){
                 grid[locx][locy] = EMPTY;
                  grid[locx+1][locy] = ACID;
                           
            }
      }

         
      if(locx >= grid.length-1){
         grid[locx][locy] = EMPTY;
      }  
      }
}
   
  
            
         
         
          
       
  
   
  //whatever it touches it shrinks that and creates less of it
  public void sandMove(int locx, int locy){
  if (grid[locx][locy] == SAND){
         if(!(locx >= grid.length-1)){
            if(grid[locx+1][locy] == EMPTY){
               grid[locx][locy] = EMPTY;
               grid[locx+1][locy] = SAND;
            } else if(grid[locx+1][locy] == WATER){
            grid[locx][locy] = WATER;
               grid[locx+1][locy] = SAND;
               
            }
               
         }
     }
     }
          

     

  public void waterMove(int locx, int locy){
  int waterint = (int)(Math.random() * 3)+1;
      
           if (grid[locx][locy] == WATER){
              if(waterint == 1){
                 if(!(locx >= grid.length-1)){
                    if(grid[locx+1][locy] == EMPTY){
                       grid[locx][locy] = EMPTY;
                       grid[locx+1][locy] = WATER;
                    }
                 }
              }
              if(waterint == 2){
                  if(!(locy>=grid[0].length-1)){
                     if(grid[locx][locy+1] == EMPTY){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy+1] = WATER;
                     }
                  }
              }
              if(waterint == 3){
                  if(!(locy< 1)){
                     if(grid[locx][locy-1] == EMPTY){
                        grid[locx][locy] = EMPTY;
                        grid[locx][locy-1] = WATER;
                     } 
                  }
              }
                  
           }

         
          
       }

  
  //do not modify
  public void run()
  {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}
