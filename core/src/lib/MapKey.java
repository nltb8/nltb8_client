package lib;

import com.badlogic.gdx.Input;

public class MapKey {
   private static mHashtable h = new mHashtable();

   public static void load() {
      h.put("A", new Integer(97));
      h.put("B", new Integer(98));
      h.put("C", new Integer(99));
      h.put("D", new Integer(100));
      h.put("E", new Integer(101));
      h.put("F", new Integer(102));
      h.put("G", new Integer(103));
      h.put("H", new Integer(104));
      h.put("I", new Integer(105));
      h.put("J", new Integer(106));
      h.put("K", new Integer(107));
      h.put("L", new Integer(108));
      h.put("M", new Integer(109));
      h.put("N", new Integer(110));
      h.put("O", new Integer(111));
      h.put("P", new Integer(112));
      h.put("Q", new Integer(113));
      h.put("R", new Integer(114));
      h.put("S", new Integer(115));
      h.put("T", new Integer(116));
      h.put("U", new Integer(117));
      h.put("V", new Integer(118));
      h.put("W", new Integer(119));
      h.put("X", new Integer(120));
      h.put("Y", new Integer(121));
      h.put("Z", new Integer(122));
      h.put("0", new Integer(48));
      h.put("1", new Integer(49));
      h.put("2", new Integer(50));
      h.put("3", new Integer(51));
      h.put("4", new Integer(52));
      h.put("5", new Integer(53));
      h.put("6", new Integer(54));
      h.put("7", new Integer(55));
      h.put("8", new Integer(56));
      h.put("9", new Integer(57));
      h.put("SPACE", new Integer(32));
      h.put("F1", -21);
      h.put("F2", -22);
      h.put("EQUALS", -25);
      h.put("MINUS", 45);
      h.put("F3", -23);
      h.put("UP", 1);
      h.put("DOWN", 6);
      h.put("LEFT", 2);
      h.put("RIGHT", 5);
      h.put("BACKSPACE", -8);
      h.put("PERIOD", new Integer(46));
      h.put("AT", new Integer(64));
      h.put("TAB", -26);
      h.put("DELETE", -8);
      h.put("ENTER", 8);
      h.put(".", 46);
      h.put("/", 47);
      h.put("TAB", -22);
      h.put("-", 95);
      //numpad
      h.put("NUMPAD 0", new Integer(48));
      h.put("NUMPAD 1", new Integer(49));
      h.put("NUMPAD 2", new Integer(50));
      h.put("NUMPAD 3", new Integer(51));
      h.put("NUMPAD 4", new Integer(52));
      h.put("NUMPAD 5", new Integer(53));
      h.put("NUMPAD 6", new Integer(54));
      h.put("NUMPAD 7", new Integer(55));
      h.put("NUMPAD 8", new Integer(56));
      h.put("NUMPAD 9", new Integer(57));
   }

   public static int map(int kyeCode) {
      try {
         String k = Input.Keys.toString(kyeCode).toUpperCase();
         int v = (Integer)h.get(k);
         return v;
      } catch (Exception var3) {
         return 0;
      }
   }
}
