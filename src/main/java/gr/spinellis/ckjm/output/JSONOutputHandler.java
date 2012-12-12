package gr.spinellis.ckjm.output;


import gr.spinellis.ckjm.ClassMetrics;

import java.io.PrintStream;

public class JSONOutputHandler extends CkjmOutputHandler {

    public JSONOutputHandler(PrintStream p) {
        super(p);
    }

    @Override
    public void handleClass(String name, ClassMetrics c) {

    }
}
