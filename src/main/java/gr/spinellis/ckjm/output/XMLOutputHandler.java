/*
 * $Id: PrintXmlResults.java 1.4 2005/11/05 08:33:18 dds Exp $
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
import java.util.Iterator;
import java.util.List;

/**
 * XML output formatter
 *
 * @author Julien Rentrop, Vassilios Karakoidas (vassilios.karakoidas@gmail.com)
 */
public class XMLOutputHandler extends CkjmOutputHandler {
    private final static String endl = System.getProperty("line.separator");

    public XMLOutputHandler(PrintStream p) {
        super(p);
    }

    public void header() {
        this.println("<?xml version=\"1.0\"?>");
        this.println("<ckjm>");
    }

    public void handleClass(String name, ClassMetrics c) 
    {
        this.println("\t<class>");
        this.println("\t\t<name>" + name + "</name>");
        this.println("\t\t<wmc>" + c.getWmc() + "</wmc>");
        this.println("\t\t<dit>" + c.getDit() + "</dit>");
        this.println("\t\t<noc>" + c.getNoc() + "</noc>");
        this.println("\t\t<cbo>" + c.getCbo() + "</cbo>");
        this.println("\t\t<rfc>" + c.getRfc() + "</rfc>");
        this.println("\t\t<lcom>" + c.getLcom() + "</lcom>");
        this.println("\t\t<ca>" + c.getCa() + "</ca>");
        this.println("\t\t<ce>" + c.getCe() + "</ce>");
        this.println("\t\t<npm>" + c.getNpm() + "</npm>");
        this.println("\t\t<lcom3>" + c.getLcom3() + "</lcom3>");
        this.println("\t\t<loc>" + c.getLoc() + "</loc>");
        this.println("\t\t<dam>" + c.getDam() + "</dam>");
        this.println("\t\t<moa>" + c.getMoa() + "</moa>");
        this.println("\t\t<mfa>" + c.getMfa() + "</mfa>");
        this.println("\t\t<cam>" + c.getCam() + "</cam>");
        this.println("\t\t<ic>" + c.getIc() + "</ic>");
        this.println("\t\t<cbm>" + c.getCbm() + "</cbm>");
        this.println("\t\t<amc>" + c.getAmc() + "</amc>");
        this.println(printXmlCC(c));
        this.println("\t</class>");
    }

    public void footer () {
        this.println("</ckjm>");
    }

    private String printXmlCC( ClassMetrics cm )
    {
        StringBuilder xmlCC = new StringBuilder();
        List<String> methodNames = cm.getMethodNames();
        Iterator<String> itr = methodNames.iterator();
        String name;
        
        xmlCC.append("\t\t<cc>").append(endl);
        while(itr.hasNext())
        {
            name = itr.next();
            name=name.replaceAll("<|>", "_"); 
            xmlCC.append("\t\t\t<method name=\"").append(name).append("\">");
            xmlCC.append(cm.getCC(name));
            xmlCC.append("</method>").append(endl);
        }
        xmlCC.append("\t\t</cc>");
        
        return xmlCC.toString();
    }
}
