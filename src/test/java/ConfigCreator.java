import cli.CLI;
import cli.impl.DefaultCli;
import filecreator.FileCreator;
import filecreator.impl.DefaultFileCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ����� ��� ������������ ��������� ������� ���������� ���������� ������.
 */
public class ConfigCreator {


    @Test
    public void argumentParse() {
        String[] args = new String[2];
        args[0] = "-c";
        args[1] = "C:\\excel\\document.xlsx";
        CLI cli = new DefaultCli();
        Assertions.assertEquals("C:\\excel\\document.xlsx", cli.parseArgumentsForConfigPath(args));
    }

    @Test
    public void pathIsEmpty (){
        String[] args = new String[2];
        args[0] = "-c";
        args[1] = "";
        CLI cli = new DefaultCli();
        FileCreator fileCreator = new DefaultFileCreator();

      RuntimeException runtimeException =  Assertions.assertThrows(RuntimeException.class, () -> {
            fileCreator.createFile(cli.parseArgumentsForConfigPath(args));
        });

      Assertions.assertEquals("���� � ����� ������ �����������", runtimeException.getMessage());
    }


    @Test
    public void pathIsIncorrect(){
        String[] args = new String[2];
        args[0] = "-c";
        args[1] = "C:\\excel\\";
        CLI cli = new DefaultCli();
        FileCreator fileCreator = new DefaultFileCreator();

        RuntimeException runtimeException =  Assertions.assertThrows(RuntimeException.class, () -> {
            fileCreator.createFile(cli.parseArgumentsForConfigPath(args));
        });

        Assertions.assertEquals("�� ���������� ���� ���� �� ���������", runtimeException.getMessage());
    }
}
