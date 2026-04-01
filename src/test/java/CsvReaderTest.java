import org.example.CsvReader;
import org.example.Department;
import org.example.Person;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

    @Test
    void testParseCsvHappyPath() {
        String csvData = "id;name;gender;BirtDate;Division;Salary\n" +
                "28281;Aahan;Male;15.05.1990;I;48000\n" +
                "28282;Aala;Female;20.10.1995;I;26000\n" +
                "28283;Aaleahya;Female;10.01.1992;F;10000";

        InputStream in = new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8));

        List<Person> persons = CsvReader.parseCsv(in);

        assertEquals(3, persons.size(), "Должно быть прочитано 3 человека");

        Person firstPerson = persons.get(0);
        assertEquals("Aahan", firstPerson.getName());
        assertEquals(48000.0, firstPerson.getSalary());
    }

    @Test
    void testDepartmentSingletonLogic() {
        String csvData = "id;name;gender;BirtDate;Division;Salary\n" +
                "1;John;Male;01.01.2000;IT;100\n" +
                "2;Jane;Female;02.02.2000;IT;200\n" +
                "3;Bob;Male;03.03.2000;HR;150";

        InputStream in = new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8));
        List<Person> persons = CsvReader.parseCsv(in);

        Department depJohn = persons.get(0).getDepartment();
        Department depJane = persons.get(1).getDepartment();
        Department depBob = persons.get(2).getDepartment();

        assertSame(depJohn, depJane, "У людей из IT должен быть один и тот же объект подразделения");

        assertNotSame(depJohn, depBob, "У HR должен быть другой объект подразделения");
    }

    @Test
    void testEmptyOrBrokenLines() {
        String csvData = "id;name;gender;BirtDate;Division;Salary\n" +
                "1;John;Male\n" +
                "2;Jane;Female;02.02.2000;IT;200";

        InputStream in = new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8));
        List<Person> persons = CsvReader.parseCsv(in);

        assertEquals(1, persons.size(), "Сломанные строки должны игнорироваться");
    }
}