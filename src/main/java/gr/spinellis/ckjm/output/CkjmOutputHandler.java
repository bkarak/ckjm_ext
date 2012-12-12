/*
 * $Id: CkjmOutputHandler.java 1.1 2005/05/11 20:40:31 dds Exp $
 *
 * (C) Copyright 2005 Diomidis Spinellis, Julien Rentrop
 *
 * Permission to use, copy, and distribute this software and its
 * documentation for any purpose and without fee is hereby granted,
 * provided that the above copyright notice appear in all copies and that
 * both that copyright notice and this permission notice appear in
 * supporting documentation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 * MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

package gr.spinellis.ckjm.output;

import gr.spinellis.ckjm.ClassMetrics;

import java.io.PrintStream;

/**
 * Interface of output handlers
 * Use this interface to couple your tool to CKJM. Example implenations
 * which could use this tool are ant task writing, IDE integration,
 * GUI based interfaces etc.
 *
 * @author Julien Rentrop
 */
public abstract class CkjmOutputHandler {
    private PrintStream p;

    protected CkjmOutputHandler(PrintStream p) {
        this.p = p;
    }

    protected CkjmOutputHandler() {
        this.p = null;
    }

    protected void print(String s) {
        if (p == null) {
            System.err.println("PrintStream is not defined!");
            return;
        }

        p.print(s);
    }

    protected void println(String s) {
        this.print(s);
        this.print("\n");
    }

    /**
     * Method called when metrics are generated
     * @param name Name of the class
     * @param c Value object that contains the corresponding metrics
     */
    public abstract void handleClass(String name, ClassMetrics c);

    public abstract void header();

    public abstract void footer();
}
