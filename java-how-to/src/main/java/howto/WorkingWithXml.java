package howto;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorkingWithXml {

    public static void main(String[] args) {

        Tutorials tutorials = new Tutorials();
        tutorials.setTutorial(new ArrayList<>());
        Tutorial t = new Tutorial();
        t.setTutId("01");
        t.setType("java");
        t.setTitle("Guava");
        t.setDescription("Introduction to Guava");
        t.setDate("08/02/2024");
        t.setAuthor("GuavaAuthor");
        tutorials.getTutorial().add(t);
        File file = new File("C:\\WORK\\test.xml");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tutorials.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tutorials, file);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
}


@Getter
class Tutorial {

    private String tutId;

    private String type;

    private String title;

    private String description;

    private String date;

    private String author;

    @XmlAttribute
    public void setTutId(String tutId) {
        this.tutId = tutId;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }
}

@Getter
@XmlRootElement
class Tutorials {

    private List<Tutorial> tutorial;

    @XmlElement
    public void setTutorial(List<Tutorial> tutorial) {
        this.tutorial = tutorial;
    }
}

/*

<tutorials>
    <tutorial tutId="01" type="java">
        <title>Guava</title>
        <description>Introduction to Guava</description>
        <date>04/04/2016</date>
        <author>GuavaAuthor</author>
    </tutorial>
</tutorials>

 */