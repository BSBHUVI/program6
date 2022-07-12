package com.example.program6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class parsing extends AppCompatActivity {
    public TextView xmldata,jsondata;
    public int mode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);
        xmldata=findViewById(R.id.xmldata);
        jsondata=findViewById(R.id.jsondata);
        mode=getIntent().getIntExtra("mode",0);
        if(mode==1){
            parsexml();
        }else{
            parsejson();
        }
    }

    private void parsejson() {
        try {
            InputStream inputStream=getAssets().open("input.json");
            byte[] data=new byte[inputStream.available()];
            inputStream.read(data);
            String readData=new String(data);
            JSONObject jsonObject=new JSONObject(readData);
            JSONObject jsonObject1=jsonObject.getJSONObject("employee");
            jsondata.setText("Cityname :"+jsonObject1.getString("Cityname")+"\n");
            jsondata.append("Latitude :"+jsonObject1.getString("Latitude")+"\n");
            jsondata.append("Longitude :"+jsonObject1.getString("Longitude")+"\n");
            jsondata.append("Temperature :"+jsonObject1.getString("Temperature")+"\n");
            jsondata.append("Humidity :"+jsonObject1.getString("Humidity")+"\n");

        }catch (Exception e){

        }
    }

    private void parsexml() {
        try {
            InputStream is=getAssets().open("input.xml");
            DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
            Document doc=dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList nList=doc.getElementsByTagName("employee");
            for(int i=0;i< nList.getLength();i++){
                Node node= nList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element2=(Element) node;
                    xmldata.setText("Cityname :"+ getValue("Cityname",element2)+"\n");
                    xmldata.append("Latitude :"+ getValue("Latitude",element2)+"\n");
                    xmldata.append("Longitude :"+ getValue("Longitude",element2)+"\n");
                    xmldata.append("Temperature :"+ getValue("Temperature",element2)+"\n");
                    xmldata.append("Humidity :"+ getValue("Humidity",element2)+"\n");
                }
            }
        }catch (Exception e){

        }


    }

    private String getValue(String tag, Element element) {
        NodeList nodeList=element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node= nodeList.item(0);
        return  node.getNodeValue();
    }

}