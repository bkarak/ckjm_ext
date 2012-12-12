/*
 * $Id: PrintPlainResults.java 1.1 2005/05/11 20:40:31 dds Exp $
 *
 * (C) Copyright 2005 Diomidis Spinellis, Julien Rentrop
 *
 * Permission to use, copy, and distribute this software and its documentation
 * for any purpose and without fee is hereby granted, provided that the above
 * copyright notice appear in all copies and that both that copyright notice and
 * this permission notice appear in supporting documentation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 * MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

package gr.spinellis.ckjm.output;

import gr.spinellis.ckjm.ClassMetrics;

import java.io.PrintStream;

/**
 * Simple plain text output formatter
 * @author Julien Rentrop
 */
public class PlainOutputHandler extends CkjmOutputHandler {
    public PlainOutputHandler(PrintStream p) {
        super(p);
    }

    public void handleClass(String name, ClassMetrics c) {
        this.println(name + " " + c.toString());
    }
}
