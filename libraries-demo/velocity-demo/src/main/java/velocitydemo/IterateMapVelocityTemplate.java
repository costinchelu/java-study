package velocitydemo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IterateMapVelocityTemplate {

    public static void main(String[] args) {

        String relativePathFromClasspath = "./libraries-demo/velocity-demo/src/main/resources/templates/SampleRenderMap.vm";

        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext vc = new VelocityContext();
        Template t = ve.getTemplate(relativePathFromClasspath);

        Map<String, List<SampleObject>> bar = new HashMap<>();
        List<SampleObject> objectList1 = new ArrayList<>();
        List<SampleObject> objectList2 = new ArrayList<>();
        SampleObject s1 = new SampleObject("desc1", "act1", "act1");
        SampleObject s2 = new SampleObject("desc2", "act2", "act2");
        SampleObject s3 = new SampleObject("desc3", "act3", "act3");
        SampleObject s4 = new SampleObject("desc4", "act4", "act4");
        objectList1.add(s1);
        objectList1.add(s2);
        objectList2.add(s3);
        objectList2.add(s4);
        bar.put("ref1", objectList1);
        bar.put("ref2", objectList2);
        vc.put("bar", bar);
        vc.put("fileName", "some filename");

        StringWriter sw = new StringWriter();
        t.merge(vc, sw);
        System.out.println(sw);
    }
}
