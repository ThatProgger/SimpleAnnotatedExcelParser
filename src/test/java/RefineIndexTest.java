import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RefineIndexTest {

    @Test
    public void refineIndex (){
        int start = 1;
        int end = 2;
        Map<String, Integer> map = new HashMap<>();

        map.put("start", start);
        map.put("end", end);

        Assertions.assertEquals(1, map.get("start").intValue());
        Assertions.assertEquals(2, map.get("end").intValue());
    }
}
