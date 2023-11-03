package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


@ExtendWith(MockitoExtension.class)
class Test16FinalMethods {

    @Test
    void shouldTestAFinalMethod() {
        String str1 = "in the latest versions of Mockito, we can test final methods (for this we need to use mockito-inline dependency)";
        String str2 = "in the latest versions of Mockito, we can test final methods (for this we need to use mockito-inline dependency)";

        assertEquals(str1, str2);
        assertSame(str1, str2);
    }


}