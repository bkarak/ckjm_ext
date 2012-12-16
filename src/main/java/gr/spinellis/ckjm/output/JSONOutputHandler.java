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
        String filename = name.replace(".", "/") + ".java";

        // The big object
        JSONObject jsonobj = new JSONObject();

        jsonobj.put("id", name);
        jsonobj.put("filename", filename);
        jsonobj.put("category", "class");

        JSONArray measArray = new JSONArray();

        // Measurements
        measArray.add(createMeasInt("WeightedMethodsPerClass", c.getWmc()));
        measArray.add(createMeasInt("DepthOfInheritanceTree", c.getDit()));
        measArray.add(createMeasInt("NumberOfChildren", c.getNoc()));
        measArray.add(createMeasInt("CouplingBetweenObjects", c.getCbo()));
        measArray.add(createMeasInt("ResponseForClass", c.getRfc()));
        measArray.add(createMeasInt("LackOfCohesionInMethods", c.getLcom()));
        measArray.add(createMeasInt("AfferentCouplings", c.getCa()));
        measArray.add(createMeasInt("EfferentCouplings", c.getCe()));
        measArray.add(createMeasInt("NumberOfPublicMethods", c.getNpm()));
        measArray.add(createMeasDouble("LackOfCohesionInMethods3", c.getLcom3()));
        measArray.add(createMeasInt("LinesOfCode", c.getLoc()));
        measArray.add(createMeasDouble("DataAccessMetric", c.getDam()));
        measArray.add(createMeasInt("MeasureOfAggregation", c.getMoa()));
        measArray.add(createMeasDouble("MeasureOfFunctionalAbstraction", c.getMfa()));
        measArray.add(createMeasDouble("CohesionAmongMethodsOfClass", c.getCam()));
        measArray.add(createMeasInt("InheritanceCoupling", c.getIc()));
        measArray.add(createMeasInt("CouplingBetweenMethods", c.getCbm()));
        measArray.add(createMeasDouble("AverageMethodComplexity", c.getAmc()));

        jsonobj.put("measurement", measArray);

        for (String mname : c.getMethodNames()) {
            JSONObject mobj = new JSONObject();

            mobj.put("id", mname);
            mobj.put("filename", filename);
            mobj.put("category", "method");

            JSONArray marray = new JSONArray();
            marray.add(createMeasInt("McCabe", c.getCC(mname)));
            mobj.put("measuremnt", marray);

            jsonDocument.add(mobj);
        }

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
