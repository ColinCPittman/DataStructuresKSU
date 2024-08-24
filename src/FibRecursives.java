public class FibRecursives {
    /** Return the Fibonacci number for the specified index */
    public static long fib(long index) {
        return fib(index, 1, 0);
    }

    /** Auxiliary tail-recursive method for fib */
    private static int fib(long index, int next, int result) {
        if (index == 0)
            return result;
        else
            return fib(index - 1, next + result, next);
    }

    public static void main(String[] args) {
        fib(5);
    }
}

