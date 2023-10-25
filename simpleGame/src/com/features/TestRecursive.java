package com.features;

public class TestRecursive {
    static {
        System.out.println("Partie statique");
    }
        public static void main(String args[]) {
            TestRecursive test1 = new TestRecursive();
            System.out.println(test1.methodX(100));

            int a, b;

            a = 5 >> 2;
            b = a >>> 2;
            System.out.println(b);


            int i=1, j=1;
            try {
                i++;
                j--;
                if(i == j)
                    i++;
            }
            catch(ArithmeticException e) {
                System.out.println(0);
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(1);
            }
            catch(Exception e) {
                System.out.println(2);
            }
            finally {
                System.out.println(3);
            }
            System.out.println(4);

        }
        public int methodX(int nbr) {
            if (nbr == 1) return 1;
            else{
                //System.out.println(nbr);
                return (methodX(nbr - 1) + nbr);
            }
        }
}
