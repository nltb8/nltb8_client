package com.android.vending.billing;

public class AndroidInappBilling {
   public static AndroidInappBilling me;

   public static AndroidInappBilling instance() {
      if (me == null) {
         me = new AndroidInappBilling();
      }

      return me;
   }

   public void consumePurchase(String productId) {
   }
}
