package gr.spinellis.ckjm.output;

import gr.spinellis.ckjm.ClassMetrics;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;


public class JSONOutputHandler extends CkjmOutputHandler {
    private JSONArray jsonDocument;

    public JSONOutputHandler(PrintStream p) {
        super(p);
        jsonDocument = new JSONArray();
    }

    public void header() {

    }

    public void footer() {
        try {
            StringWriter out = new StringWriter();
            jsonDocument.writeJSONString(out);
            String jsonText = out.toString();
            System.out.print(jsonText);
        } catch (IOException e) {
            System.err.println("ERROR - " + e.toString());
        }
    }

    @Override
    public void handleClass(String name, ClassMetrics c) {
        // The big object
        JSONObject jsonobj = new JSONObject();

        jsonobj.put("id", name);
        jsonobj.put("filename", name.replace(".", "/") + ".class");
        jsonobj.put("category", "class");

        JSONArray measArray = new JSONArray();

        // Measurements



        jsonobj.put("measurement", measArray);

        jsonDocument.add(jsonobj);
    }

    private JSONObject createMeasInt(String measName, int value) {
        JSONObject o = new JSONObject();

        o.put("name", measName);
        o.put("value", new Integer(value));
        o.put("result-type", "integer");

        return o;
    }

    private JSONObject createMeasDouble(String measName, double value) {
        JSONObject o = new JSONObject();

        o.put("Name", measName);
        o.put("value", new Double(value));
        o.put("result-type", "float");

        return o;
    }
}

/*
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
 */
