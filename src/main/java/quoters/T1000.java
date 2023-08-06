package quoters;

import quoters.profiling.Profiling;

@Profiling
public class T1000 extends TerminatorQuoter implements Quoter {
    @Override
    public void sayQuote() {
        System.out.println("I am cool!");
    }
}
