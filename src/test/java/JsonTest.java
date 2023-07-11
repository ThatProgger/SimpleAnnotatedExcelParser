import configchecker.ConfigChecker;
import configchecker.impl.DefaultConfigChecker;
import cli.CLI;
import cli.impl.DefaultCli;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import config.Config;
import config.params.DateParam;
import config.params.GroupParam;
import config.params.KeyParam;
import config.params.NameParam;
import configcreator.ConfigCreator;
import configcreator.impl.DefaultConfigCreator;
import filecreator.FileCreator;
import filecreator.impl.DefaultFileCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonTest {
    private final Logger log = LogManager.getLogger();
    private final String jsonFile = "C:\\excel\\config.json";
    private final String json = "{\n" +
            "  \"start\" : 5,\n" +
            "  \"end\" : 0,\n" +
            "  \"pathToRead\" : \"C:\\\\excel\\\\ex.xlsx\",\n" +
            "  \"pathToWrite\" : \"C:\\\\excel\\\\document.xlsx\",\n" +
            "  \"keyParam\" : {\"letter\" : \"L\", \"regexp\" : \"\\\\b(?<key>0[xX][0-9-a-fA-F]+)\\\\b\" },\n" +
            "  \"nameParam\" : {\"letter\" : \"E\"},\n" +
            "  \"dateParam\" : {\"letter\" : \"A\", \"regexp\" : \"^[0-9\\\\.]+\", \"mask\" : \"^2023-04-.+\"},\n" +
            "  \"groupParam\" : {\"letter\" : \"G\", \"ignore\" : [\n" +
            "  \"������������������� ������\", \n" +
            "  \"����� ���\", \n" +
            "  \"����� ���\",\n" +
            "  \"����� �������������.\",\n" +
            "  \"����� ���\",\n" +
            "  \"����\",\n" +
            "  \"��� ����� � ����������\",\n" +
            "  \"������� �����\",\n" +
            "  \"������� ����� � �����\",\n" +
            "  \"������ ������������\",\n" +
            "  \"���\",\n" +
            "  \"��������-������������ ���\",\n" +
            "  \"����� ������������ ��������\",\n" +
            "  \"����� �����������-������������ ���������\",\n" +
            "  \"����� �������������� ���������� � �����\",\n" +
            "  \"����� �������� ��������\",\n" +
            "  \"�������������� �������\",\n" +
            "  \"������-����������� �����\",\n" +
            "  \"���\",\n" +
            "  \"��������\",\n" +
            "  \"�������� ����� �� � ����\",\n" +
            "  \"���������������-������������� �����\",\n" +
            "  \"���������������� ���\",\n" +
            "  \"���\",\n" +
            "  \"���\"]}\n" +
            "}";

    @Test
    public void configTest() {
        Config config = new Gson().fromJson(json, Config.getInstance().getClass());
        Assertions.assertNotNull(config, "������� ������ �� ���������������� ����");
        Assertions.assertEquals(5, config.getStart(), "��������� ������ ������������");
        Assertions.assertEquals(0, config.getEnd(), "������� ������ ������������");
        Assertions.assertEquals("C:\\excel\\ex.xlsx", config.getPathToRead());

        KeyParam keyParam = config.getKeyParam();
        Assertions.assertNotNull(keyParam, String.format("������� ������ �� ������: \"%s\"", keyParam.getClass().getName()));
        Assertions.assertEquals("L", keyParam.getLetter());
        Assertions.assertEquals("\\b(?<key>0[xX][0-9-a-fA-F]+)\\b", keyParam.getRegexp());

        NameParam nameParam = config.getNameParam();
        Assertions.assertNotNull(nameParam, String.format("������� ������ �� ������: \"%s\"", nameParam.getClass().getName()));
        Assertions.assertEquals("E", nameParam.getLetter());

        GroupParam groupParam = config.getGroupParam();
        Assertions.assertNotNull(groupParam, String.format("������� ������ �� ������: \"%s\"", groupParam.getClass().getName()));
        Assertions.assertEquals("G", groupParam.getLetter());
        Assertions.assertNotEquals(0, groupParam.getIgnore().size());

        DateParam dateParam = config.getDateParam();
        Assertions.assertNotNull(dateParam, String.format("������� ������ �� ������: \"%s\"", dateParam.getClass().getName()));
        Assertions.assertEquals("A", dateParam.getLetter());
        Assertions.assertEquals("^[0-9\\.]+", dateParam.getRegexp());
        Assertions.assertEquals("^2023-04-.+", dateParam.getMask());
    }

    @Test
    public void readJsonFromFile() throws FileNotFoundException {
        File file = null;
        JsonReader jsonReader = null;
        FileReader fileReader = null;
        Gson gson = null;

        Config config = null;


        file = new File(jsonFile);
        fileReader = new FileReader(file);
        jsonReader = new JsonReader(fileReader);
        gson = new Gson();
        config = gson.fromJson(jsonReader, Config.getInstance().getClass());


        Assertions.assertNotNull(config, "������� ������ �� ���������������� ����");
        Assertions.assertEquals(5, config.getStart(), "��������� ������ ������������");
        Assertions.assertEquals(0, config.getEnd(), "������� ������ ������������");
        Assertions.assertEquals("C:\\excel\\ex.xlsx", config.getPathToRead());

        KeyParam keyParam = config.getKeyParam();
        Assertions.assertNotNull(keyParam, String.format("������� ������ �� ������: \"%s\"", keyParam.getClass().getName()));
        Assertions.assertEquals("L", keyParam.getLetter());
        Assertions.assertEquals("\\b(?<key>0[xX][0-9-a-fA-F]+)\\b", keyParam.getRegexp());

        NameParam nameParam = config.getNameParam();
        Assertions.assertNotNull(nameParam, String.format("������� ������ �� ������: \"%s\"", nameParam.getClass().getName()));
        Assertions.assertEquals("E", nameParam.getLetter());

        GroupParam groupParam = config.getGroupParam();
        Assertions.assertNotNull(groupParam, String.format("������� ������ �� ������: \"%s\"", groupParam.getClass().getName()));
        Assertions.assertEquals("G", groupParam.getLetter());
        Assertions.assertNotEquals(0, groupParam.getIgnore().size());

        DateParam dateParam = config.getDateParam();
        Assertions.assertNotNull(dateParam, String.format("������� ������ �� ������: \"%s\"", dateParam.getClass().getName()));
        Assertions.assertEquals("A", dateParam.getLetter());
        Assertions.assertEquals("^[0-9\\.]+", dateParam.getRegexp());
        Assertions.assertEquals("^2023-04-.+", dateParam.getMask());
    }


    @Test
    public void IoCConfig (){
        String [] args = new String[2];
        args[0] = "-c";
        args[1] = jsonFile;

        CLI cli = new DefaultCli();
        FileCreator fileCreator = new DefaultFileCreator();
        ConfigCreator configCreator = new DefaultConfigCreator();
        ConfigChecker configChecker = new DefaultConfigChecker();

        Assertions.assertTrue(configChecker.checkConfig(configCreator.createConfig(fileCreator.createFile(cli.parseArgumentsForConfigPath(args)))), "���������������� ���� �� ���������");
    }
}
