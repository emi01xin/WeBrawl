// FIGHT GAME
// Sheng Fang, Emily Wang, Grace Tsai

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Attack{
  String name, special;
  int cost, dmg;
  
  public Attack(String n, int c, int d, String s){
    name = n;
    cost = c;
    dmg = d;
    special = s;
  }
}