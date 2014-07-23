Brute
=====

For those times when you see a puzzle and feel the irrational need to solve it by using a search and backtracking algorithm.

The Idea
---

The abstract class ```Backtracker``` models trial and backtracking over a graph of possible solutions to a problem.  We start off at one solution (the 'top' of the graph), and the complete valid solution is at the 'bottom'.  We can get from the top to the bottom via a series of moves through 'valid' solutions (the definition of valid of course depends on the problem being solved).  We can move down, sideways or up. When we reach a valid solution that is also 'finished', we terminate.

What can it solve?
=====

Take a look at the tests for full examples.

Sudoku
---

```
Integer[][] numbers = {
    			{0,9,0,0,0,8,0,0,6},
    			{7,0,2,0,1,0,4,0,0},
    			{0,1,0,0,0,2,0,3,0},
    			{0,0,0,0,0,0,5,0,8},
    			{0,4,0,0,8,0,0,2,0},
    			{6,0,1,0,0,0,0,0,0},
    			{0,3,0,4,0,0,0,6,0},
    			{0,0,9,0,7,0,1,0,3},
    			{5,0,0,2,0,0,0,4,0}};
    	Sudoku s = new Sudoku(numbers);
    	s.attempt();
    	s.print(System.out);
```

Gives:

```

3 9 4 7 5 8 2 1 6 
7 5 2 6 1 3 4 8 9 
8 1 6 9 4 2 7 3 5 
2 7 3 1 6 4 5 9 8 
9 4 5 3 8 7 6 2 1 
6 8 1 5 2 9 3 7 4 
1 3 7 4 9 5 8 6 2 
4 2 9 8 7 6 1 5 3 
5 6 8 2 3 1 9 4 7

```


Griddler (a.k.a. nonogram or Hanjie)
---

```

public void testGriddler() {
        Integer[][] rowHints = {{17},
                                {25},
                                {27},
                                {29},
                                {8,9},
                                {5,5},
                                {5,5},
                                {4,3,4},
                                {4,4,4},
                                {4,4,4},
                                {4,4,4},
                                {4,3,4},
                                {4,3,3,4,4},
                                {4,3,4,4,4},
                                {4,3,4,4,4},
                                {4,3,2,2,4},
                                {4,3,3,2,4},
                                {4,3,4,2,4},
                                {4,3,3,2,4},
                                {4,3,2,2,4},
                                {4,5,4,2,4},
                                {4,5,4,2,4},
                                {4,5,3,2,4},
                                {4,4},
                                {4,4,4},
                                {4,5,4},
                                {4,5,4},
                                {5,10},
                                {5,9},
                                {8,8},
                                {29},
                                {29},
                                {28},
                                {17,6},
                                {5}};
        
        Integer[][] colHints = {{28},
                                {30},
                                {32},
                                {32},
                                {6,6},
                                {4,4},
                                {5,5},
                                {5,5},
                                {4,3,3,4},
                                {4,16,4},
                                {4,16,4},
                                {4,16,4},
                                {4,3,4},
                                {4,4},
                                {4,5,3,4},
                                {4,7,3,4},
                                {4,3,7,4},
                                {4,3,5,4},
                                {4,4},
                                {4,3,4,4},
                                {5,11,10},
                                {5,11,10},
                                {5,3,10},
                                {4,8},
                                {6,7},
                                {34},
                                {34},
                                {33},
                                {26,5},
                                {4}
                                };
        Griddler g = new Griddler(rowHints, colHints);
        g.attempt();
        g.print(System.out);


```

Gives:

```

      #################       
  #########################   
 ###########################  
############################# 
########            ######### 
#####                   ##### 
#####                   ##### 
####     ###             #### 
####    ####             #### 
####    ####             #### 
####    ####             #### 
####     ###             #### 
####     ###   ### ####  #### 
####     ###  #### ####  #### 
####     ###  #### ####  #### 
####     ###  ##    ##   #### 
####     ###  ###   ##   #### 
####     ###  ####  ##   #### 
####     ###   ###  ##   #### 
####     ###    ##  ##   #### 
####    ##### ####  ##   #### 
####    ##### ####  ##   #### 
####    ##### ###   ##   #### 
####                     #### 
####               ####  #### 
####               ##### #### 
####               ##### #### 
#####              ########## 
#####               ######### 
########            ########  
############################# 
 #############################
  ############################
      ################# ######
                         #####


```

Creation of magic squares 
---

(3x3 and 4x4 within a reasonable time)

```

Magic m = new Magic(3);
    	m.attempt();
    	m.print(System.out);

```

Gives:

```

2 9 4 
7 5 3 
6 1 8 

```

Licensed under Apache licence v2.

http://www.apache.org/licenses/LICENSE-2.0.html

