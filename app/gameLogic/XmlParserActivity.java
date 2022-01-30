package com.jorgecruces.euler1.gameLogic;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * This class does magic
 */
public class XmlParserActivity extends AppCompatActivity
{
    public ArrayList<Question> getQuestionList()
    {
        ArrayList<Question> questionList = new ArrayList<Question>();

        try
        {

            InputStream in = getAssets().open("questions.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);
            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("question");
            for(int i = 0; i < nList.getLength(); i++)
            {
                Node node = nList.item(i);
                Element explorer = (Element) node;

                String questionLabel,questionTitle, alternative1,alternative2,alternative3,correctAlternative;

                questionTitle = getStringUsingTag("questionTitle",explorer);
                questionLabel = getStringUsingTag("questionLabel",explorer);
                alternative1 = getStringUsingTag("alternative1",explorer);
                alternative2 = getStringUsingTag("alternative2",explorer);
                alternative3 = getStringUsingTag("alternative3",explorer);
                correctAlternative = getStringUsingTag("correctAlternative",explorer);

                if(null == questionTitle) questionTitle = "";

                Question questionTemp = new Question(questionLabel,alternative1,alternative2,alternative3,correctAlternative,questionTitle);
                questionList.add(questionTemp);
            }

        }
        catch (IOException | SAXException | ParserConfigurationException e)
        {

            Toast.makeText(this, "An error ocurred, restart the app", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return questionList;
    }

    public String getStringUsingTag(String tag, Element explorer)
    {
        try
        {

            Node nodeTag = explorer.getElementsByTagName(tag).item(0).getFirstChild();
            if(null == nodeTag) return "";

            return nodeTag.getNodeValue();
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }

}
